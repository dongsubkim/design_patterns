원본자료 - [Refactoring Guru](https://refactoring.guru/design-patterns/factory-method)
# Factory Method
- A.k.a: Virtual Constructor

## 설명
- 팩토리 메소드는 생성 디자인 패턴으로 상위 클래스의 객체를 생성하는 인터페이스를 제공하지만, 하위 클래스들이 생성될 객체의 타입을 결정할 수 있게 한다.

- 팩토리 메소드 패턴은 직접적으로 `new`를 사용해 객체를 생성하지 말고 특별한 *factory* 메소드를 이용해 생성하는 것.
    - 객체는 여전히 `new` 키워드로 생성이 되지만, `new`가 factory 메소드 안에서 사용된다.
    - factory 메소드로 생성된 객체들은 products로 주로 불림.

- 이제 factory method를 subclass에서 override해서 해당 메소드로 생성하는 products의 타입을 결정할 수 있다.
    - 단점:
        - subclass들은 여러 타입의 products들이 공통된 base class/interface를 가지고 있을 때만 생성할 수 있다.
        - factory method의 base class는 리턴 타입이 factory method를 가진 interface로 선언되어야 한다.


## 예제
![Screen Shot 2021-08-05 at 7.53.40 PM.png](https://res.cloudinary.com/ddeivviyp/image/upload/v1628162219/MyBlog/hykxvrbyoi5kixhdcvag.png)
![Screen Shot 2021-08-05 at 7.57.25 PM.png](https://res.cloudinary.com/ddeivviyp/image/upload/v1628162220/MyBlog/yhtckkujehctpl2zpmfp.png)
- `Truck` 과 `Ship` 모두 `Transport` 인터페이스를 구현하고 `deliver` 메소드를 각자 다른 방식으로 Override한다.
- `RoadLogistics`의 팩토리 메소드는 truck 객체들을 리턴하고, `SeaLogistics`는 ship 객체들을 리턴한다.
- 팩토리 메소드를 사용하는 코드(주로 *client* 라고 불림)는 실제로 사용하는 객체가 truck인든 ship이든 차이점을 느끼지 못한다.
    - client는 모든 products를 `Transport`라는 추상체로 대하기 때문
- client에게 모든 `transport` 객체는 `deliver` 메소드를 가지고 있다는 것은 알지만, 그 안이 어떻게 구현되었는지는 중요하지 않다


## 구조
![Screen Shot 2021-08-05 at 8.05.39 PM.png](https://res.cloudinary.com/ddeivviyp/image/upload/v1628162220/MyBlog/xxxgeihvwr5mharktulh.png)
1. `Product`는 인테페이스로 `Product`의 서브 클래스(`Concrete ProductA`, `Concrete ProductB`)와 `creator`클래스가 생성하는 모든 객체들의 추상 타입.
2. `Concrete Products`들은 `product`인터페이스의 다른 구현 버전
3. `Creator` 클래스는 새로운 `product` 객체를 생성하는 factory method(`createProduct()`)를 선언.
    - 해당 팩토리 메소드의 리턴 타입은 `product` 인터페이스와 같아야 한다.
    - 팩토리 메소드를 abstract으로 선언하고, subclass들이 강제로 구현하게 할 수도 있고, default product type을 만들어서 `creator`안에 base factory method가 리턴하도록 해도 됨.
    - 주의: 이름과는 다르게 `creator` 클래스의 주 목적은 `product`를 생성하는 것이 아니다. 일반적으로, `creator`는 이미 `product`에 연관된 중요한 로직을 가지고 있고, factory method가 그 로직을 `concrete preoducts` 와 decoupling 해주는 역활
4. `Concrete Creators` 는 base factory method를 Override해서 다양한 타입의 `product`를 리턴한다.
    - 주의: 팩토리 메소드는 새로운 객체를 생성할 필요는 없다.

## 활용도
- 사용하게될 객체들의 정확한 타입이나 의존성들을 사전에 알 수 없을 때 팩토리 메소드 사용
    - 팩토리 메소드는 product를 생성하는 코드와 product를 사용하는 코드를 분리시켜 줍니다. 그러므로, product를 생성하는 코드를 나머지 코드로부터 확장하기 쉽게 만들어 줍니다.
    - 예를 들어, 앱에 새로운 상품 타입을 추가할 때, 새로운 creator 서브클래스만 만들고, factory method를 override하면 됩니다.
- library나 framework의 사용자들에게 안의 내용물에 대한 확장성을 제공하고 싶을 때 팩토리 메소드 사용
    - library나 framework의 기본 기능들을 확장하는 가장 쉬운 방법은 상속이지만, 상속한 서브클래스를 프레임워크가 원래 클래스 대신 쓰게 하는 것은 어려움
    - 해결책은 프레임워크 내에서 구성요소들을 생성하는 코드를 하나의 팩토리 메소드로 줄이고, 구성요소를 확장하고 싶을 때 이 팩토리 메소드를 override한다.
- 새로운 객체들을 매번 생성하는 대신 기존의 객체들을 재사용해서 시스템의 리소스를 절약하고 싶을 때 팩토리 메소드 사용
    - 거대하고 리소스를 많이 잡아먹는 객체들(데이터 베이스 커넥션, 파일 시스템, 네트워크 리소스등)을 다룰 때 자주 발생
    - constructor는 매번 새로운 객체를 생성해 리턴해야 하니, 팩토리 메소드를 만들어서 새로운 객체를 생성하거나, 기존의 객체를 재사용이 가능할 때 리턴해주도록 사용

## 구현 방법
1. 모든 products들이 같은 인터페이스를 구현. 해당 인터페이스는 모든 product에서 사용되는 메소드들을 선언
2. creator 클래스에 빈 팩토리 메소드 추가. 해당 메소드는 1번에서 product들이 공통적으로 구현한 interface 타입을 리턴.
3. 팩토리 메소드에 product를 생성하는 코드를 넣고, creator의 코드에서 product를 생성하는 모든 부분을 찾아서 팩토리 메소드를 사용하도록 바꾼다.
    - 팩토리 메소드에 리턴 타입을 관리하기 위해 임시 parameter를 추가하는 경우도 있다.
    - 이 시점에 코드가 어지러워도 괜찮다.
4. 팩토리 메소드 내에 있는 각각의 product 타입마다 creator 서브클래스들을 생성한다. 서브클래스내에서 팩토리 메소드들을 override하고 베이스 메소드로부터 알맞은 코드 부분을 가져온다.
5. 만약 product 타입이 너무 많아서 각 타입마다 서브클래스를 만드는 것이 적절하지 않다면, 서브클래스의 기본 변수를 재사용할수 있다.
6. 베이스 팩토리 메소드가 모두 사라져 비었다면, abstract method로 만든다. 만약 코드가 남아있다면, default behavior로 남겨둔다.

## 장/단점
- 장점
    - creator와 concrete products 사이의 강한 결합도를 피할 수 있다.
    - SRP: product 생성 코드를 한 프로그램으로 옮겨, 유지보수가 쉬워짐
    - OCP: 새로운 타입의 product를 기존의 코드를 고장내지 않고 추가 가능.
- 단점
    - 새로운 서브클래스들을 많이 만들어 코드가 더 복잡해질 수 있다. 기존에 creator 클래스들의 계층에 팩토리 메소드 패턴을 사용할 때가 가장 이상적인 시나리오

## 다른 패턴과의 관계
- 많은 디자인들이 Factory Method(덜 복잡하고 서브클래스들을 통해 커스텀하기 쉬운)를 사용하는걸 시작으로 Abstract Factory, Prototype, 또는 Builder 패턴(더 유연하지만, 더 복잡)으로 진화한다.
- Abstract Factory 클래스들은 Factory Methods들의 집합에 기반하지만, Prototype을 사용할 수도 있다.
- Iterator와 Factory Method를 같이 사용해 collection subclasses들이 collection들과 호환이 되는 다양한 타입의 iterators를 리턴하게 사용가능.
- Prototype 은 상속에 기반하지 않기 때문에 상속에 대한 단점은 없지만, Prototype은 복제한 객체의 복잡한 initialization이 요구된다. 팩토리 메소드는 상속에 기반하기 때문에, initialization step이 필요없다.
- Factory Method는 특별한 Template Method이고, 동시에 거대한 Template Method로 진입하는 관문이 될 수 있다.
