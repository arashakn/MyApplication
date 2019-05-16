package com.example.myapplication.classTest;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StringReverserTest {
    StringReverser UDT;

    @Before
    public void setup(){
        UDT = new StringReverser();
    }

    @Test
    public void reverse_emptyString_emptyStringReturned() {
        String result = UDT.reverse("");
        assertThat(result,is(""));
    }

    @Test
    public void reverse_singleCharString_sameStringReturned() {
        String result = UDT.reverse("a");
        assertThat(result,is("a"));
    }

    @Test
    public void reverse_longString_reverseStringReturned() {
        String result = UDT.reverse("abbcd");
        assertThat(result,is("dcbba"));
    }


}