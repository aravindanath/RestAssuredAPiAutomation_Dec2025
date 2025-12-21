package day1;

import com.github.javafaker.Faker;

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
    }

// pojo what is pojo


