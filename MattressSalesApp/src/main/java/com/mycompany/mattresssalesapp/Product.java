package com.mycompany.mattresssalesapp;

public class Product implements IProducts {
    private String[] productNames;
    private int[][] salesData;

    public Product(String[] productNames, int[][] salesData) {
        this.productNames = productNames;
        this.salesData = salesData;
    }

    @Override
    public double getTotalSold() {
        double total = 0;
        for (int[] productSales : salesData) {
            for (int sale : productSales) {
                total += sale;
            }
        }
        return total;
    }

    @Override
    public double getAverageSold() {
        double total = getTotalSold();
        int totalMonths = salesData.length * salesData[0].length;
        return total / totalMonths;
    }

    @Override
    public String getMostPopularProduct() {
        int maxSales = 0;
        String popularProduct = "";
        for (int i = 0; i < salesData.length; i++) {
            int productTotal = 0;
            for (int j = 0; j < salesData[i].length; j++) {
                productTotal += salesData[i][j];
            }
            if (productTotal > maxSales) {
                maxSales = productTotal;
                popularProduct = productNames[i];
            }
        }
        return popularProduct + " (" + maxSales + ")";
    }
}