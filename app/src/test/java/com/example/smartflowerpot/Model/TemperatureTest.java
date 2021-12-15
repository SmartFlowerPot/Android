package com.example.smartflowerpot.Model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TemperatureTest extends TestCase {

    Temperature temperature;

    @Test
    public void test0CDegrees(){
        temperature = new Temperature();
        temperature.setTemperature(0);
        assertEquals(32.0, temperature.convertToFahrenheit());
    }

    @Test
    public void test1CDegrees(){
        temperature = new Temperature();
        temperature.setTemperature(1);
        assertEquals(33.8, temperature.convertToFahrenheit());
    }

    @Test
    public void test2CDegrees(){
        temperature = new Temperature();
        temperature.setTemperature(2);
        assertEquals(35.6, temperature.convertToFahrenheit());
    }

    @Test
    public void test5CDegrees(){
        temperature = new Temperature();
        temperature.setTemperature(5);
        assertEquals(41.0, temperature.convertToFahrenheit());
    }

    @Test
    public void test10CDegrees(){
        temperature = new Temperature();
        temperature.setTemperature(10);
        assertEquals(50.0, temperature.convertToFahrenheit());
    }

    @Test
    public void testMinu1CDegrees(){
        temperature = new Temperature();
        temperature.setTemperature(-1);
        assertEquals(30.2, temperature.convertToFahrenheit());
    }

    @Test
    public void testMinu2CDegrees(){
        temperature = new Temperature();
        temperature.setTemperature(-2);
        assertEquals(28.4, temperature.convertToFahrenheit());
    }

    @Test
    public void testMinu5CDegrees(){
        temperature = new Temperature();
        temperature.setTemperature(-5);
        assertEquals(23.0, temperature.convertToFahrenheit());
    }

    @Test
    public void testMinu10CDegrees(){
        temperature = new Temperature();
        temperature.setTemperature(-10);
        assertEquals(14.0, temperature.convertToFahrenheit());
    }
}