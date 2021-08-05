package blog.dskim.designPatterns.factoryMethod.factory;

import blog.dskim.designPatterns.factoryMethod.buttons.Button;

// Base factory class. Note that "factory" is merely a role
// for the class. It should have some core business logic
// which needs different products to be created.
public abstract class Dialog {
    public void renderWindow() {
        // ... other code ...

        Button okButton = creatButton();
        okButton.render();
    }

    // Subclasses will override this method in order to create
    // specific button objects.
    public abstract Button creatButton();

}
