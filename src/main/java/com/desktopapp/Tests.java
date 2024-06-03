package com.desktopapp;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tests {
    public static boolean cpfIsValid(String cpf) {
        Matcher regexCPF = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}").matcher(cpf);

        return regexCPF.matches();
    }

    public static boolean phoneIsValid(String phone) {
        Matcher regexPhone = Pattern.compile("^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[0-9])[0-9]{3}\\-?[0-9]{4}$").matcher(phone);

        return regexPhone.matches();
    }

    public static boolean emailIsValid(String email) {
        Matcher regexEmail = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$").matcher(email);

        return regexEmail.matches();
    }

    public static boolean valueIsValid(String value) {
        Matcher regexValue = Pattern.compile("^\\d+(?:[\\.,]\\d{1,2})?$").matcher(value);

        return regexValue.matches();
    }

    public static boolean codeIsValid(String code) {
        Matcher regexCode = Pattern.compile("^\\d{8,}$").matcher(code);

        return regexCode.matches();
    }

    public static boolean isEmptyNull(String value) {
        return value.isEmpty() || value == null;
    }

    public static boolean isUnderAge(LocalDate date) {
        int age = (Period.between(date, LocalDate.now())).getYears();

        return age < 18;
    }
}
