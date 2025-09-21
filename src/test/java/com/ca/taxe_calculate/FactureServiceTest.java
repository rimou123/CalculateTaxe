package com.ca.taxe_calculate;

import com.ca.taxe_calculate.model.Product;
import com.ca.taxe_calculate.enumeration.Category;
import com.ca.taxe_calculate.service.TaxCalculatorService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe de tests unitaires pour la génération de factures et le calcul correct
 * des taxes sur différents paniers d'achat.
 */
public class FactureServiceTest {
	/**
	 * Test du panier 1 : - 2 livres à 12.49€ - 1 CD musical à 14.99€ - 3 barres de
	 * chocolat à 0.85€
	 * 
	 * Livres : TVA 10% CD : TVA 20% Chocolat : TVA 0% Aucun produit importé.
	 */
	@Test
	void testPanier1() {
		List<Product> panier1 = new ArrayList<>();
		panier1.add(new Product("livre", 12.49, 2, false, Category.BOOK));
		panier1.add(new Product("CD musical", 14.99, 1, false, Category.OTHER));
		panier1.add(new Product("barre de chocolat", 0.85, 3, false, Category.FOOD));

		double totalTaxes = panier1.stream()
				.mapToDouble(p -> TaxCalculatorService.calculateTaxAmount(p) * p.getQuantity()).sum();

		double total = panier1.stream().mapToDouble(p -> TaxCalculatorService.calculateTotalPrice(p) * p.getQuantity())
				.sum();

		System.out.println("Panier 1\n ");
		for (Product p : panier1) {
			double prixTTCParUnite = TaxCalculatorService.calculateTotalPrice(p);
			double prixTotal = prixTTCParUnite * p.getQuantity();
			System.out
					.println(p.getQuantity() + " " + p.getName() + " à " + p.getPrice() + "  : " + prixTotal + " TTC");
		}
		System.out.println();
		System.out.printf("Montant des taxes : %.2f€%n", totalTaxes);
		System.out.printf("Total : %.2f€%n", total);

		assertEquals(5.50, totalTaxes, 0.01);
		assertEquals(48.02, total, 0.01);
	}

	/**
	 * Test du panier 2 : - 2 boîtes de chocolats importées à 10€ - 3 flacons de
	 * parfum importés à 47.50€
	 * 
	 * Tous les produits sont importés, donc soumis à 5% en plus. Chocolats (FOOD) :
	 * TVA 0% + import 5% Parfum (OTHER) : TVA 20% + import 5%
	 */
	@Test
	void testPanier2() {
		List<Product> panier2 = new ArrayList<>();
		panier2.add(new Product("boîte de chocolats importée", 10.00, 2, true, Category.FOOD));
		panier2.add(new Product("flacon de parfum importé", 47.50, 3, true, Category.OTHER));

		double totalTaxes = panier2.stream()
				.mapToDouble(p -> TaxCalculatorService.calculateTaxAmount(p) * p.getQuantity()).sum();

		double total = panier2.stream().mapToDouble(p -> TaxCalculatorService.calculateTotalPrice(p) * p.getQuantity())
				.sum();
		System.out.println();
		System.out.println("Panier 2 \n");
		for (Product p : panier2) {
			double prixTTCParUnite = TaxCalculatorService.calculateTotalPrice(p);
			double prixTotal = prixTTCParUnite * p.getQuantity();
			System.out
					.println(p.getQuantity() + " " + p.getName() + " à " + p.getPrice() + "  : " + prixTotal + " TTC");
		}
		System.out.println();
		System.out.printf("Montant des taxes : %.2f€%n", totalTaxes);
		System.out.printf("Total : %.2f€%n", total);

		assertEquals(36.70, totalTaxes, 0.01);
		assertEquals(199.20, total, 0.01);
	}

	/**
	 * Test du panier 3 : - 2 flacons de parfum importés à 27.99€ - 1 flacon de
	 * parfum à 18.99€ - 3 boîtes de pilules à 9.75€ - 2 boîtes de chocolats
	 * importés à 11.25€
	 * 
	 * Parfum : 20% (et 5% si importé) Médicament : 0% Chocolat : 0% + 5% si importé
	 */
	@Test
	void testPanier3() {
		List<Product> panier3 = new ArrayList<>();
		panier3.add(new Product("flacon de parfum importé", 27.99, 2, true, Category.OTHER));
		panier3.add(new Product("flacon de parfum", 18.99, 1, false, Category.OTHER));
		panier3.add(new Product("boîte de pilules contre la migraine", 9.75, 3, false, Category.MEDICAL));
		panier3.add(new Product("boîte de chocolats importée", 11.25, 2, true, Category.FOOD));

		double totalTaxes = panier3.stream()
				.mapToDouble(p -> TaxCalculatorService.calculateTaxAmount(p) * p.getQuantity()).sum();

		double total = panier3.stream().mapToDouble(p -> TaxCalculatorService.calculateTotalPrice(p) * p.getQuantity())
				.sum();
		System.out.println();
		System.out.println("Panier 3 \n");
		for (Product p : panier3) {
			double prixTTCParUnite = TaxCalculatorService.calculateTotalPrice(p);
			double prixTotal = prixTTCParUnite * p.getQuantity();
			System.out
					.println(p.getQuantity() + " " + p.getName() + " à " + p.getPrice() + "  : " + prixTotal + " TTC");
		}
		System.out.println();
		System.out.printf("Montant des taxes : %.2f€%n", totalTaxes);
		System.out.printf("Total : %.2f€%n", total);

		assertEquals(19.00, totalTaxes, 0.01);
		assertEquals(145.72, total, 0.01);
	}
}
