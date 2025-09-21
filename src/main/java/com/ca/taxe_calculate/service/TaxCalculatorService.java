package com.ca.taxe_calculate.service;

import com.ca.taxe_calculate.model.Product;

/**
 * Classe de service responsable du calcul des taxes et du prix TTC pour les
 * produits.
 * 
 */
public class TaxCalculatorService {

	/**
	 * Calcule le taux de TVA applicable en fonction de la catégorie du produit.
	 *
	 * @param product Le produit à évaluer
	 * @return Le taux de TVA en pourcentage (ex : 10.0 pour 10%)
	 */
	public static double calculateTVA(Product product) {
		switch (product.getCategory()) {
		case BOOK:
			return 10.0;
		case FOOD:
		case MEDICAL:
			return 0.0;
		default:
			return 20.0;
		}
	}

	/**
	 * Retourne le taux de taxe d'importation.
	 *
	 * @param product Le produit à évaluer
	 * @return 5.0 si le produit est importé, sinon 0.0
	 */
	public static double calculateImportTax(Product product) {
		return product.isImported() ? 5.0 : 0.0;
	}

	/**
	 * Arrondit une valeur au 5 centimes supérieurs.
	 *
	 * @param value La valeur à arrondir
	 * @return La valeur arrondie à la hausse au multiple de 0.05 le plus proche
	 */
	public static double roundToNearestFiveCents(double value) {
		return Math.ceil(value * 20.0) / 20.0;
	}

	/**
	 * Calcule le montant total des taxes (TVA + taxe d'import) pour un produit
	 * donné. Chaque composant de la taxe est arrondi séparément.
	 *
	 * @param product Le produit concerné
	 * @return Le montant total des taxes arrondi
	 */
	public static double calculateTaxAmount(Product product) {
		double tva = calculateTVA(product);
		double importTax = calculateImportTax(product);
		double tvaAmount = roundToNearestFiveCents(product.getPrice() * tva / 100);
		double importAmount = roundToNearestFiveCents(product.getPrice() * importTax / 100);
		return tvaAmount + importAmount;
	}

	/**
	 * Calcule le prix total TTC (prix HT + taxes) pour un produit.
	 *
	 * @param product Le produit concerné
	 * @return Le prix TTC du produit
	 */
	public static double calculateTotalPrice(Product product) {
		double totalTax = calculateTaxAmount(product);
		return product.getPrice() + totalTax;
	}
}
