import annotationSteps.AnnotationSteps;
import attachment.Attachments;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class LambdaAndAnnotationStepTest {

    String REPOSITORY = new String("allure");
    String SearchText = new String("Allure adapter for Cucumber is not generating a proper story label in the results when");
    AnnotationSteps steps = new AnnotationSteps();

    @Test
    @DisplayName("Проверка наличия конкретного раздела в Issue")
    public void selenideAndListenerTest() {
        //SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу GitHub", () -> {
            Selenide.open("http://github.com");
        });
        step("Ищем репозиторий allure-java по запросу " + REPOSITORY, () -> {
            $("[placeholder='Search GitHub']").setValue(REPOSITORY).pressEnter();
            $(By.linkText("allure-framework/allure-java")).click();
        });
        step("Ищем '" + SearchText + "' по запросу в разделе Issue", () -> {
            $("#issues-tab").click();
            $("#js-repo-pjax-container")
                    .shouldHave(text(SearchText))
                    .shouldBe(visible);
        });
    }

    @Test
    public void annotatedTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        steps.openPage();
        steps.searchForRepository(REPOSITORY);
        steps.searchText(SearchText);
        Attachments.screenshotAs("Screen");
        Attachments.pageSource();
    }

}

