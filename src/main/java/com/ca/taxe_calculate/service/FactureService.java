package com.ca.taxe_calculate.service;

import java.util.List;

import com.ca.taxe_calculate.model.Product;

public class FactureService {
	
	public static void printInvoice(List<Product> products) {
        double totalTaxes = 0.0;
        double totalPrice = 0.0;

        for (Product product : products) {
            double totalPriceForProduct = 0.0;
            double taxAmount = TaxCalculatorService.calculateTaxAmount(product);
            totalPriceForProduct = (TaxCalculatorService.calculateTotalPrice(product)) * product.getQuantity();
            totalTaxes += taxAmount * product.getQuantity();
            totalPrice += totalPriceForProduct;
            System.out.printf("%d %s à %.2f€ : %.2f€%n",
                    product.getQuantity(), product.getName(), product.getPrice(), totalPriceForProduct);
        }

        System.out.printf("%nMontant des taxes : %.2f€%n", totalTaxes);
        System.out.printf("Total : %.2f€%n", totalPrice);
    }
}
