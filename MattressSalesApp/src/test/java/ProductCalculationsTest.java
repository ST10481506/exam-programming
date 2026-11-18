package com.mycompany.mattresssalesapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductCalculationsTest {

    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        double price = 3500.0;
        ProductCalculations calc = new ProductCalculations(price);
        double expectedVat = 525.0;
        double actualVat = calc.GetVat();
        assertEquals(expectedVat, actualVat, 0.001, "The calculated VAT should be 15% of the price.");
    }

    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        double price = 3500.0;
        ProductCalculations calc = new ProductCalculations(price);
        double expectedTotal = 4200.0;
        double actualTotal = calc.GetTotal();
        assertEquals(expectedTotal, actualTotal, 0.001, "The calculated Total should be the price plus VAT and commission.");
    }
}