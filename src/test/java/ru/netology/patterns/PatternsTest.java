package ru.netology.patterns;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import ru.netology.patterns.entities.RegistrationInfo;
import ru.netology.patterns.utils.DataGenerator;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;



public class PatternsTest {

    @Test
    void shouldFillForm() {
        Configuration.holdBrowserOpen = true;
        RegistrationInfo form = DataGenerator.Registration.generateInfo("ru");
        String eventDay = DataGenerator.generateDate(5);


        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue(form.city);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").setValue(eventDay);
        $("[name=\"name\"]").setValue(form.name);
        $("[name=\"phone\"]").setValue(form.phone);
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + eventDay), Duration.ofSeconds(15));

    }


    @Test
    void shouldReschedule() {
        Configuration.holdBrowserOpen = true;
        RegistrationInfo form = DataGenerator.Registration.generateInfo("ru");
        String eventDay = DataGenerator.generateDate(3);


        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue(form.city);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").setValue(eventDay);
        $("[name=\"name\"]").setValue(form.name);
        $("[name=\"phone\"]").setValue(form.phone);
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + eventDay), Duration.ofSeconds(15));

        String eventNewDay = DataGenerator.generateDate(7);

        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue(form.city);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").setValue(eventNewDay);
        $("[name=\"name\"]").setValue(form.name);
        $("[name=\"phone\"]").setValue(form.phone);
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $(byXpath("//*[@id=\"root\"]/div/div[2]/div[3]/button/span/span[2]")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + eventNewDay), Duration.ofSeconds(15));
    }
}
