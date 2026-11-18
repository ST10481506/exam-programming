package com.mycompany.mattresssalesapp;

import java.util.Scanner;

public class SalesreportApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] products = {
            "Cozy Nights Monarch mattress",
            "Restonic Barbados mattress",
            "Sealy Phuket mattress"
        };

        int[][] sales = new int[3][3];

        for (int i = 0; i < products.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Enter number of sales for month " + (j + 1) + " for " + products[i] + ": ");
                sales[i][j] = scanner.nextInt();
            }
            System.out.println();
        }

        Product productReport = new Product(products, sales);

        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println("**************************************************");
        System.out.println("TOTAL PRODUCT SALES: " + (int) productReport.getTotalSold());
        System.out.println("AVERAGE PRODUCT SALES: " + (int) productReport.getAverageSold());
        System.out.println("MOST POPULAR PRODUCT: " + productReport.getMostPopularProduct());

        scanner.close();
    }
}