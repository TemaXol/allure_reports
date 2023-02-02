import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final Integer ISSUE = 80;


    @Test
    public void stepsSelenide() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открыть главную страницу",  () -> {
            open("https://github.com");
        });

        step("Найти репозиторий" + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Нажать на ссылку репозитория" + REPOSITORY, () ->  {
            $(linkText(REPOSITORY)).click();
        });
        step("Открыть вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверить наличие Issues №80" + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedSteps() {

        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPAge();
        steps.searchRepository(REPOSITORY);
        steps.clickRepository(REPOSITORY);
        steps.openIssue();
        steps.shouldDisplayElementNumber(ISSUE);
    }
}
