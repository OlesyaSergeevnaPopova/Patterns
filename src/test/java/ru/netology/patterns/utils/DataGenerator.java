package ru.netology.patterns.utils;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import ru.netology.patterns.entities.RegistrationInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DataGenerator  {

    @UtilityClass
    public static class Registration {

        public static RegistrationInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(faker.address().city(), faker.name().fullName(),
                    faker.phoneNumber().phoneNumber());
        }
    }

    public String generateDate(int days){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}