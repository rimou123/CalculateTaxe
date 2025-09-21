package com.ca.taxe_calculate;

import org.junit.jupiter.api.Test;

import com.ca.taxe_calculate.model.Product;
import com.ca.taxe_calculate.service.TaxCalculatorService;
import com.ca.taxe_calculate.enumeration.Category;
import static org.junit.jupiter.api.Assertions.*;

public class TaxCalculatorServiceTest {
	
	@Test
	void testCalculateTVA() {
		assertEquals(10.0, TaxCalculatorService.calculateTVA(
                new Product("Livre", 10.0, 1, false, Category.BOOK)));

        assertEquals(0.0, TaxCalculatorService.calculateTVA(
                new Product("Chocolat", 5.0, 1, false, Category.FOOD)));

        assertEquals(0.0, TaxCalculatorService.calculateTVA(
                new Product("Médicament", 8.0, 1, false, Category.MEDICAL)));

        assertEquals(20.0, TaxCalculatorService.calculateTVA(
                new Product("Parfum", 50.0, 1, false, Category.OTHER)));
	}
	
	@Test
	void testCalculateImportTax() {
		assertEquals(5.0, TaxCalculatorService.calculateImportTax(
                new Product("Parfum importé", 50.0, 1, true, Category.OTHER)));

        assertEquals(0.0, TaxCalculatorService.calculateImportTax(
                new Product("Parfum local", 50.0, 1, false, Category.OTHER)));
	}
	
	@Test
    void testRoundToNearestFiveCents() {
        assertEquals(0.95, TaxCalculatorService.roundToNearestFiveCents(0.95));
        assertEquals(1.00, TaxCalculatorService.roundToNearestFiveCents(0.99));
        assertEquals(1.00, TaxCalculatorService.roundToNearestFiveCents(1.00));
        assertEquals(1.05, TaxCalculatorService.roundToNearestFiveCents(1.01));
        assertEquals(1.05, TaxCalculatorService.roundToNearestFiveCents(1.02));
    }
	
	@Test
    void testCalculateTaxAmount_book() {
        Product product = new Product("Livre", 12.49, 1, false, Category.BOOK);
        double taxes = TaxCalculatorService.calculateTaxAmount(product);
        assertEquals(1.25, taxes); // 12.49 * 10% = 1.249 → arrondi à 1.25
    }
	
	@Test
    void testCalculateTotalPrice() {
        Product product = new Product("Parfum importé", 47.50, 1, true, Category.OTHER);
        double total = TaxCalculatorService.calculateTotalPrice(product);
        assertEquals(59.40, total, 0.001); // 47.50 + 11.90
    }


}
