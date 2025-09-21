package com.ca.taxe_calculate;

import java.util.ArrayList;
import java.util.List;

import com.ca.taxe_calculate.enumeration.Category;
import com.ca.taxe_calculate.model.Product;
import com.ca.taxe_calculate.service.FactureService;

public class App {
    public static void main(String[] args) {

        // === Panier 1 ===
        List<Product> panier1 = new ArrayList<>();
        panier1.add(new Product("livre", 12.49, 2, false, Category.BOOK));
        panier1.add(new Product("CD musical", 14.99, 1, false, Category.OTHER));
        panier1.add(new Product("barre de chocolat", 0.85, 3, false, Category.FOOD));

        System.out.println("Panier 1");
        FactureService.printInvoice(panier1);

        // === Panier 2 ===
        List<Product> panier2 = new ArrayList<>();
        panier2.add(new Product("boîte de chocolats importée", 10.00, 2, true, Category.FOOD));
        panier2.add(new Product("flacon de parfum importé", 47.50, 3, true, Category.OTHER));
        System.out.println();
        System.out.println("\nPanier 2");
        FactureService.printInvoice(panier2);

        // === Panier 3 ===
        List<Product> panier3 = new ArrayList<>();
        panier3.add(new Product("flacon de parfum importé", 27.99, 2, true, Category.OTHER));
        panier3.add(new Product("flacon de parfum", 18.99, 1, false, Category.OTHER));
        panier3.add(new Product("boîte de pilules contre la migraine", 9.75, 3, false, Category.MEDICAL));
        panier3.add(new Product("boîte de chocolats importés", 11.25, 2, true, Category.FOOD));
        System.out.println();
        System.out.println("\nPanier 3");
        FactureService.printInvoice(panier3);
    }
}
