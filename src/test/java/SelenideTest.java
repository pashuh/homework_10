import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;


public class SelenideTest {

    @Test
    @DisplayName("Проверка наличия конкретного раздела в Issue")
    public void selenideAndListenerTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        Selenide.open("http://github.com");
        $("[placeholder='Search GitHub']").setValue("Allure").pressEnter();
        $(By.linkText("allure-framework/allure-java")).click();
        $("#issues-tab").click();
        $("#js-repo-pjax-container")
                .shouldHave(Condition.text("Allure adapter for Cucumber is not generating a proper story label in the results when"))
                .shouldBe(Condition.visible);
        }

    @Test
    @DisplayName("Негативный тест")
        public void failedTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        Selenide.open("http://github.com");
        $("[TESTTEST='Search GitHub']").setValue("Allure").pressEnter();
        $(By.linkText("allure-framework/allure-java")).click();
        $("#issues-tab").click();
        $("#js-repo-pjax-container")
                .shouldHave(Condition.text("Allure adapter for Cucumber is not generating a proper story label in the results when"))
                .shouldBe(Condition.visible);
    }
}
