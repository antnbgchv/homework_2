import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests {

    @BeforeAll
    static void config() {
        //configParams
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillRegistrationFormTest() {

        open("/automation-practice-form");

        //addsAndFooter
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        //values
        $("#firstName").setValue("Homer");
        $("#lastName").setValue("Simpson");
        $("#userEmail").setValue("mr.plow@gmail.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890");

        //dateOfBirth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("May");
        $(".react-datepicker__year-select").selectOptionContainingText("1956");
        $(".react-datepicker__day.react-datepicker__day--012").click();

        //subjects
        $("#subjectsContainer input").setValue("Physics").pressEnter();

        //hobbies
        $("#hobbiesWrapper").$(byText("Sports")).click();

        //uploadImg
        $("#uploadPicture").uploadFromClasspath("coolPic.jpg");

        //currentAddress
        $("#currentAddress").setValue("742 Evergreen Terrace");

        //stateAndCity
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();

        //formSubmit
        $("#submit").click();

        //tests
        $(".modal-content").should(appear);
        $(".table-responsive").$(byText("Student Name"))
                .parent().shouldHave(text("Homer Simpson"));
        $(".table-responsive").$(byText("Student Email"))
                .parent().shouldHave(text("mr.plow@gmail.com"));
        $(".table-responsive").$(byText("Gender"))
                .parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile"))
                .parent().shouldHave(text("1234567890"));
        $(".table-responsive").$(byText("Date of Birth"))
                .parent().shouldHave(text("12 May,1956"));
        $(".table-responsive").$(byText("Subjects"))
                .parent().shouldHave(text("Physics"));
        $(".table-responsive").$(byText("Hobbies"))
                .parent().shouldHave(text("Sports"));
        $(".table-responsive").$(byText("Picture"))
                .parent().shouldHave(text("coolPic.jpg"));
        $(".table-responsive").$(byText("Address"))
                .parent().shouldHave(text("742 Evergreen Terrace"));
        $(".table-responsive").$(byText("State and City"))
                .parent().shouldHave(text("Haryana Karnal"));

    }
}
