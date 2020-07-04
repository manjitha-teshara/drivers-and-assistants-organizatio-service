package com.driversandassistantsorganizationapp.demo.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.driversandassistantsorganizationapp.demo.controllers.Demo;
import org.mockito.MockitoAnnotations;

public class DemoTest {
//    @Mock
Demo demo = Mockito.mock(Demo.class);
    @BeforeEach
    public void setUp() {
//       MockitoAnnotations.initMocks(this);
        demo = Mockito.mock(Demo.class);
    }

    @Test
    public void testPrintResult_when_input_null() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            demo.printResult(null);
        });
    }

    @Test
    public void testPrintResult_when_input_all_same() {
        String input = "aaaa";
        demo.printResult(input);
//        Mockito.verify(demo, Mockito.times(1)).findNoOfRepeatChar("aaaa");
    }

    @Test
    public void testPrintResult_when_input_expected() {
        String input = "abcd";
        demo.printResult(input);
//        Mockito.verify(demo, Mockito.times(1)).findNoOfRepeatChar("aaaa");
    }

    @Test
    public void testFindNoOfRepeatChar() {
    }
}