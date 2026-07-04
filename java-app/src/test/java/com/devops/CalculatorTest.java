package com.devops;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calc = new Calculator();

    @Test
    void testAdditionner() {
        assertEquals(8, calc.additionner(5, 3));
        assertEquals(0, calc.additionner(0, 0));
        assertEquals(-2, calc.additionner(-5, 3));
    }

    @Test
    void testSoustraire() {
        assertEquals(2, calc.soustraire(5, 3));
    }

    @Test
    void testMultiplier() {
        assertEquals(15, calc.multiplier(5, 3));
    }
}
