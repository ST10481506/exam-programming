package com.mycompany.mattresssalesapp;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProductApp extends JFrame {
    private JTextField txtCustomerName;
    private JComboBox<String> cmbProducts;
    private JTextField txtPrice;

    public ProductApp() {
        setTitle("Product App");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 20));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(new JLabel("Customer Name:"));
        txtCustomerName = new JTextField();
        add(txtCustomerName);

        add(new JLabel("Select Product:"));
        String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        cmbProducts = new JComboBox<>(products);
        add(cmbProducts);

        add(new JLabel("Enter Price:"));
        txtPrice = new JTextField();
        add(txtPrice);

        add(new JLabel(""));
        JButton btnProcess = new JButton("PROCESS");
        add(btnProcess);

        createMenuBar();
        btnProcess.addActionListener(e -> processSale());
        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        processItem.addActionListener(e -> processSale());
        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(e -> clearForm());
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);
    }

    private void processSale() {
        try {
            String customerName = txtCustomerName.getText();
            String productName = (String) cmbProducts.getSelectedItem();

            if (customerName.isEmpty() || txtPrice.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double price = Double.parseDouble(txtPrice.getText());
            ProductCalculations calc = new ProductCalculations(price);
            double vat = calc.GetVat();
            double commission = calc.GetCommission();
            double total = calc.GetTotal();

            String message = String.format(
                "Customer Name: %s\nProduct: %s\nVAT: R %.1f\nCOMMISSION: R %.1f\nTOTAL: R %.1f",
                customerName, productName, vat, commission, total
            );
            JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);

            saveToFile(customerName, productName, vat, commission, total);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric price.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveToFile(String name, String product, double vat, double commission, double total) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt", true))) {
            writer.write("DATA LOG\n");
            writer.write("*****************************\n");
            writer.write("Customer Name: " + name + "\n");
            writer.write("Product: " + product + "\n");
            writer.write(String.format("VAT: R %.1f\n", vat));
            writer.write(String.format("COMMISSION: R %.1f\n", commission));
            writer.write(String.format("TOTAL: R %.1f\n", total));
            writer.write("*****************************\n\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving to file.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtCustomerName.setText("");
        txtPrice.setText("");
        cmbProducts.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp());
    }
}