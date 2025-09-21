package com.ca.taxe_calculate.service;

import java.util.List;

import com.ca.taxe_calculate.model.Product;

/*
 * 
 * Service responsable de l'affichage de la facture détaillé d'un panier de produit
 * 
 * */
public class FactureService {
	/**
	 * Affiche la facture complète pour une liste de produits. Pour chaque produit,
	 * elle affiche : la quantité le nom le prix unitaire HT le prix total TTC Puis,
	 * elle affiche : - le montant total des taxes - le montant total de la commande
	 * (TTC)
	 *
	 * @param products Liste des produits dans le panier
	 */
	public static void printInvoice(List<Product> products) {
		double totalTaxes = 0.0;
		double totalPrice = 0.0;

		for (Product product : products) {
			double totalPriceForProduct = 0.0;
			double taxAmount = TaxCalculatorService.calculateTaxAmount(product);
			totalPriceForProduct = (TaxCalculatorService.calculateTotalPrice(product)) * product.getQuantity();
			totalTaxes += taxAmount * product.getQuantity();
			totalPrice += totalPriceForProduct;
			System.out.printf("%d %s à %.2f€ : %.2f€%n", product.getQuantity(), product.getName(), product.getPrice(),
					totalPriceForProduct);
		}

		System.out.printf("%nMontant des taxes : %.2f€%n", totalTaxes);
		System.out.printf("Total : %.2f€%n", totalPrice);
	}
}
