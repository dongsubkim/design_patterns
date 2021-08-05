package blog.dskim.designPatterns.factoryMethod.factory;

import blog.dskim.designPatterns.factoryMethod.buttons.Button;
import blog.dskim.designPatterns.factoryMethod.buttons.HtmlButton;

// HTML Dialog will produce HTML buttons.
public class HtmlDialog extends Dialog{

    @Override
    public Button creatButton() {
        return new HtmlButton();
    }
}
