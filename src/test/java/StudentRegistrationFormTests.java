import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTests {

    @BeforeAll
    static void config() {
        //конфигурационные параметры (соображал как открывать браузер фуллскрин, пока не нашел понятное решение)
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillRegistrationFormTest() {

        open("/automation-practice-form");

        //задаем значения строкам
        $("#firstName").setValue("Homer");
        $("#lastName").setValue("Simpson");
        $("#userEmail").setValue("mr.plow@gmail.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890");

        //выбор даты рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("May");
        $(".react-datepicker__year-select").selectOptionContainingText("1956");
        $(".react-datepicker__day.react-datepicker__day--012").click();

        /* выбор subject'а (не получалось просто с #subjectsContainer, нашел информацию,
        что нужно добавить input, мысли зачем это есть, но понимания нет, объясните пожалуйста!) */
        $("#subjectsContainer input").setValue("Physics").pressEnter();

        //выбор хобби
        $("#hobbiesWrapper").$(byText("Sports")).click();

        //загрузка изображения
        $("#uploadPicture").uploadFile(new File("src/test/resources/coolPic.jpg"));

        //ввод текущего адреса
        $("#currentAddress").setValue("742 Evergreen Terrace");

        //выбор штата и города (уверен эти строки можно оптимизировать)
        $(byText("Select State")).click();
        $(byText("Haryana")).click();
        $(byText("Select City")).click();
        $(byText("Karnal")).click();

        //отправка формы
        $("#submit").click();

        /* тесты (справедливости ради считаю эти тесты некорректными, но разобраться как
        искать элемент по тексту, а потом переходить на parent или sibling и т.д. не удалось */
        $(".table").shouldHave(
                text("Homer Simpson"),
                text("mr.plow@gmail.com"),
                text("Male"),
                text("1234567890"),
                text("12 May,1956"),
                text("Physics"),
                text("Sports"),
                text("coolPic.jpg"),
                text("742 Evergreen Terrace"),
                text("Haryana Karnal")
        );

    }
}
