import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открыть главную страницу")
    public void openMainPAge() {
        open("https://github.com");
    }

    @Step("Найти репозиторий {repo}")
    public void searchRepository(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();
    }

    @Step("Нажать на ссылку репозитория {repo}")
    public void clickRepository(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открыть вкладку Issues")
    public void openIssue() {
        $("#issues-tab").click();
    }

//    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
//    public byte[] takeScreenshot() {
//        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
//    }


    @Step("Проверить наличие Issues №80 {issue}")
    public void shouldDisplayElementNumber(int issue) {
        $(withText("#" + issue)).should(Condition.exist);
    }
}
