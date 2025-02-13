package models.screens.android;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;

public class DeviceLocationScreen {
    public static final SelenideElement permissionAllowed = $(id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"));

    @Step("Кликнуть на кнопке While using the App")
    public DeviceLocationScreen clickPermissionAllowed() {
        permissionAllowed.click();
        return this;
    }

}
