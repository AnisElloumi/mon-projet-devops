package com.devops;

public class Calculator {

    public int additionner(int a, int b) {
        return a + b;
    }

    public int soustraire(int a, int b) {
        return a - b;
    }

    public int multiplier(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println("=== Calculatrice DevOps ===");
        System.out.println("5 + 3 = " + calc.additionner(5, 3));
        System.out.println("5 - 3 = " + calc.soustraire(5, 3));
        System.out.println("5 x 3 = " + calc.multiplier(5, 3));
    }
}
