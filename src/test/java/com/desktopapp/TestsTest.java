package com.desktopapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import javafx.util.converter.LocalDateStringConverter;

public class TestsTest {

    @Test
    void cpfIsValid() {
        assertEquals(
            Tests.cpfIsValid("120.718.059-92"), true
        );

        assertEquals(
            Tests.cpfIsValid("12071805992"), true
        );

        assertEquals(
            Tests.cpfIsValid("120.718.05992"), true
        );

        assertEquals(
            Tests.cpfIsValid("120.718.059-9"), false
        );

        assertEquals(
            Tests.cpfIsValid("120.71892"), false
        );

        assertEquals(
            Tests.cpfIsValid("asdfasdfdsfgdafsg"), false
        );

        assertEquals(
            Tests.cpfIsValid("aaa.aaa.aaa-aa"), false
        );
    }

    @Test
    void phoneIsValid() {
        assertEquals(
            Tests.phoneIsValid("(41) 98524-9657"), true
        );

        assertEquals(
            Tests.phoneIsValid("(41)98524-9657"), true
        );

        assertEquals(
            Tests.phoneIsValid("(41)985249657"), true
        );

        assertEquals(
            Tests.phoneIsValid("4198524-9657"), true
        );

        assertEquals(
            Tests.phoneIsValid("41985249657"), true
        );

        assertEquals(
            Tests.phoneIsValid("(aa) aaaaa-aaaa"), false
        );

        assertEquals(
            Tests.phoneIsValid("sdfgasdfgasdfg"), false
        );

        assertEquals(
            Tests.phoneIsValid("41 985249657"), true
        );
    }

    @Test
    void emailIsValid() {
        assertEquals(
            Tests.emailIsValid("loregobara@gmail.com"), true
        );

        assertEquals(
            Tests.emailIsValid("loregobara@outlook.com"), true
        );

        assertEquals(
            Tests.emailIsValid("loregobara@gmail.dot"), true
        );

        assertEquals(
            Tests.emailIsValid("loregobara@gmail,com"), false
        );

        assertEquals(
            Tests.emailIsValid("loregobaragmail.com"), false
        );

        assertEquals(
            Tests.emailIsValid("loregobara@gmail"), false
        );
    }

    @Test
    void valueIsValid() {
        assertEquals(
            Tests.valueIsValid("25"), true
        );

        assertEquals(
            Tests.valueIsValid("25,0"), true
        );

        assertEquals(
            Tests.valueIsValid("25.0"), true
        );

        assertEquals(
            Tests.valueIsValid("25,70"), true
        );

        assertEquals(
            Tests.valueIsValid("25.89"), true
        );

        assertEquals(
            Tests.valueIsValid("25.888"), false
        );

        assertEquals(
            Tests.valueIsValid("25,."), false
        );

        assertEquals(
            Tests.valueIsValid("25,755"), false
        );

        assertEquals(
            Tests.valueIsValid("aa,a"), false
        );

        assertEquals(
            Tests.valueIsValid("87.a"), false
        );
    }

    @Test
    void codeIsValid() {
        assertEquals(
            Tests.codeIsValid("12345678"), true
        );

        assertEquals(
            Tests.codeIsValid("12345678910"), true
        );

        assertEquals(
            Tests.codeIsValid("asdfghjk"), false
        );

        assertEquals(
            Tests.codeIsValid("1234567"), false
        );
    }

    @Test
    void isEmptyNull() {

        assertEquals(
            Tests.isEmptyNull(""), true
        );

        assertEquals(
            Tests.isEmptyNull("null"), false
        );

        assertEquals(
            Tests.isEmptyNull("a"), false
        );
    }

    @Test
    void isUnderAge() {
        assertEquals(
            Tests.isUnderAge(LocalDate.of(2006, 6, 4)), false
        );

        assertEquals(
            Tests.isUnderAge(LocalDate.of(2006, 6, 5)), true
        );
    }
}
