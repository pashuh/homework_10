package annotationSteps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AnnotationSteps {

    @Step("Открываем главную страницу GitHub")
    public void openPage() {
        open("http://github.com");
    }

    @Step("Поиск репозитория")
    public void searchForRepository(String repository) {
        $("[placeholder='Search GitHub']").setValue(repository).pressEnter();
        $(By.linkText("allure-framework/allure-java")).click();
    }

    @Step("Поиск определенного раздела в репозитории в вкладке Issue")
    public void searchText(String text) {
        $("#issues-tab").click();
        $("#js-repo-pjax-container")
                .shouldHave(Condition.text(text))
                .shouldBe(Condition.visible);
    }
}
