package com.chatbot.identifiers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends Frame implements ActionListener {
    private TextField num1, num2, result;
    private Button addButton, subButton, mulButton, divButton, clearButton;
    private Label label1, label2, label3;

    public Calculator() {
        // Set up the frame
        setTitle("Basic Calculator");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));
        setVisible(true);

        // Initialize components
        label1 = new Label("Number 1:");
        label2 = new Label("Number 2:");
        label3 = new Label("Result:");
        num1 = new TextField();
        num2 = new TextField();
        result = new TextField();
        result.setEditable(false); // Result field should be non-editable

        addButton = new Button("Add");
        subButton = new Button("Subtract");
        mulButton = new Button("Multiply");
        divButton = new Button("Divide");
        clearButton = new Button("Clear");

        // Add components to the frame
        add(label1);
        add(num1);
        add(label2);
        add(num2);
        add(label3);
        add(result);
        add(addButton);
        add(subButton);
        add(mulButton);
        add(divButton);
        add(clearButton);

        // Add action listeners
        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Close the window
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            double number1 = Double.parseDouble(num1.getText());
            double number2 = Double.parseDouble(num2.getText());
            double calcResult = 0;

            switch (command) {
                case "Add":
                    calcResult = number1 + number2;
                    break;
                case "Subtract":
                    calcResult = number1 - number2;
                    break;
                case "Multiply":
                    calcResult = number1 * number2;
                    break;
                case "Divide":
                    if (number2 != 0) {
                        calcResult = number1 / number2;
                    } else {
                        result.setText("Error: Division by zero");
                        return;
                    }
                    break;
                case "Clear":
                    num1.setText("");
                    num2.setText("");
                    result.setText("");
                    return;
            }
            result.setText(String.valueOf(calcResult));
        } catch (NumberFormatException ex) {
            result.setText("Error: Invalid number format");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
