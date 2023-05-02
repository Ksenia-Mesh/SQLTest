package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    SelenideElement login = $("[data-test-id='login'] input");
    SelenideElement pass = $("[data-test-id='password'] input");
    SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");
    SelenideElement button = $("[data-test-id='action-login']");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        clearInput();
        login.setValue(info.getLogin());
        pass.setValue(info.getPassword());
        button.click();
        return new VerificationPage();
    }

    public void invalidLogin(DataHelper.AuthInfo info) {
        clearInput();
        login.setValue(info.getLogin());
        pass.setValue(info.getPassword());
        button.click();
        errorNotification.shouldBe(visible);
    }
    private void clearInput() {
        login.sendKeys(Keys.CONTROL + "A");
        login.sendKeys(Keys.DELETE);
        pass.sendKeys(Keys.CONTROL + "A");
        pass.sendKeys(Keys.DELETE);
    }
}
