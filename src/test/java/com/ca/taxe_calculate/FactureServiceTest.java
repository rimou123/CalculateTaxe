package com.ca.taxe_calculate;

import com.ca.taxe_calculate.model.Product;
import com.ca.taxe_calculate.enumeration.Category;
import com.ca.taxe_calculate.service.TaxCalculatorService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FactureServiceTest {

    @Test
    void testPanier1() {
        List<Product> panier1 = new ArrayList<>();
        panier1.add(new Product("livre", 12.49, 2, false, Category.BOOK));
        panier1.add(new Product("CD musical", 14.99, 1, false, Category.OTHER));
        panier1.add(new Product("barre de chocolat", 0.85, 3, false, Category.FOOD));

        double totalTaxes = panier1.stream()
                .mapToDouble(p -> TaxCalculatorService.calculateTaxAmount(p) * p.getQuantity())
                .sum();

        double total = panier1.stream()
                .mapToDouble(p -> TaxCalculatorService.calculateTotalPrice(p) * p.getQuantity())
                .sum();

        System.out.println("Panier 1\n ");
        for( Product p : panier1) {
        	double prixTTCParUnite = TaxCalculatorService.calculateTotalPrice(p);
            double prixTotal = prixTTCParUnite * p.getQuantity();
         System.out.println(p.getQuantity() + " " + p.getName() + " à " + p.getPrice() + "  : " + prixTotal+ " TTC");
        }
        System.out.println();
        System.out.printf("Montant des taxes : %.2f€%n", totalTaxes);
        System.out.printf("Total : %.2f€%n", total);

        assertEquals(5.50, totalTaxes, 0.01);
        assertEquals(48.02, total, 0.01);
    }

    @Test
    void testPanier2() {
        List<Product> panier2 = new ArrayList<>();
        panier2.add(new Product("boîte de chocolats importée", 10.00, 2, true, Category.FOOD));
        panier2.add(new Product("flacon de parfum importé", 47.50, 3, true, Category.OTHER));

        double totalTaxes = panier2.stream()
                .mapToDouble(p -> TaxCalculatorService.calculateTaxAmount(p) * p.getQuantity())
                .sum();

        double total = panier2.stream()
                .mapToDouble(p -> TaxCalculatorService.calculateTotalPrice(p) * p.getQuantity())
                .sum();
        System.out.println();
        System.out.println("Panier 2 \n");
        for( Product p : panier2) {
        	double prixTTCParUnite = TaxCalculatorService.calculateTotalPrice(p);
            double prixTotal = prixTTCParUnite * p.getQuantity();
         System.out.println(p.getQuantity() + " " + p.getName() + " à " + p.getPrice() + "  : " + prixTotal+ " TTC");
        }
        System.out.println();
        System.out.printf("Montant des taxes : %.2f€%n", totalTaxes);
        System.out.printf("Total : %.2f€%n", total);

        assertEquals(36.70, totalTaxes, 0.01);
        assertEquals(199.20, total, 0.01);
    }

    @Test
    void testPanier3() {
        List<Product> panier3 = new ArrayList<>();
        panier3.add(new Product("flacon de parfum importé", 27.99, 2, true, Category.OTHER));
        panier3.add(new Product("flacon de parfum", 18.99, 1, false, Category.OTHER));
        panier3.add(new Product("boîte de pilules contre la migraine", 9.75, 3, false, Category.MEDICAL));
        panier3.add(new Product("boîte de chocolats importée", 11.25, 2, true, Category.FOOD));

        double totalTaxes = panier3.stream()
                .mapToDouble(p -> TaxCalculatorService.calculateTaxAmount(p) * p.getQuantity())
                .sum();

        double total = panier3.stream()
                .mapToDouble(p -> TaxCalculatorService.calculateTotalPrice(p) * p.getQuantity())
                .sum();
        System.out.println();
        System.out.println("Panier 3 \n");
        for( Product p : panier3) {
        	double prixTTCParUnite = TaxCalculatorService.calculateTotalPrice(p);
            double prixTotal = prixTTCParUnite * p.getQuantity();
         System.out.println(p.getQuantity() + " " + p.getName() + " à " + p.getPrice() + "  : " + prixTotal+ " TTC");
        }
        System.out.println();
        System.out.printf("Montant des taxes : %.2f€%n", totalTaxes);
        System.out.printf("Total : %.2f€%n", total);

        assertEquals(19.00, totalTaxes, 0.01);
        assertEquals(145.72, total, 0.01);
    }
}
