package com.example.myapplication.classTest;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PosNumberValidatorTest {

    PosNumberValidator SUT;

    @Before
    public  void setup(){
        SUT = new PosNumberValidator();
    }


    @Test
    public void test1() {
        boolean result = SUT.isPos(-1);
        assertThat(result,is(false));
    }

    @Test void test2(){
        boolean result = SUT.isPos(0);
        assertThat(result, is(false));
    }

    @Test void test3(){
        boolean result = SUT.isPos(1);
        assertThat(result,is(true));
    }


}