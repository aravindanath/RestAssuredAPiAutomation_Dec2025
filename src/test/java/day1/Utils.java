package day1;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Utils
{

    public static void getValue()
    {
        System.out.println("Hello from Utils class");
    }

    public static String name(){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        return firstName;
    }

    public static String lastname() {
        return new Faker().name().lastName();

    }

        public static String email() {
            return "test_" + System.currentTimeMillis() + "@mail.com";
        }

        // Phone Number
        public static String phone() {
            return new Faker().phoneNumber().subscriberNumber(10);
        }

        // Street Address Line 1
        public static String street1() {
            return new Faker().address().streetAddress();
        }

        // Street Address Line 2
        public static String street2() {
            return new Faker().address().secondaryAddress();
        }

        // City
        public static String city() {
            return new Faker().address().city();
        }

        // State
        public static String state() {
            return new Faker().address().state();
        }

        // Postal Code (India)
        public static String postalCode() {
            return new Faker().address().zipCode();
        }

        // Country (Fixed or Dynamic)
        public static String country() {
            return "India";
        }

        // Birthdate (API format: yyyy-MM-dd)
        public static String birthDate() {
            return "1970-01-01";
        }

    // Random Birthdate in yyyy-MM-dd format between 1950-01-01 and 2005-12-31
    public static String randomBirthDate() {
        LocalDate start = LocalDate.of(1950, 1, 1);
        LocalDate end = LocalDate.of(2005, 12, 31);
        long days = ChronoUnit.DAYS.between(start, end);
        long randomDays = (long) (Math.random() * days);
        LocalDate randomDate = start.plusDays(randomDays);
        return randomDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    }

// pojo what is pojo
