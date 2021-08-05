package blog.dskim.designPatterns.factoryMethod.factory;

import blog.dskim.designPatterns.factoryMethod.buttons.Button;
import blog.dskim.designPatterns.factoryMethod.buttons.WindowsButton;

// Windows Dialog will produce Windows buttons.
public class WindowsDialog extends Dialog {
    @Override
    public Button creatButton() {
        return new WindowsButton();
    }
}
