package com.chatbot.identifiers;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationManager extends Frame implements ActionListener {
    private TextField nameField, emailField, phoneField;
    private CheckboxGroup genderGroup;
    private Checkbox maleCheckbox, femaleCheckbox, otherCheckbox;
    private Choice countryChoice;
    private TextArea displayArea;
    private List<String> userData;
    
    public RegistrationManager() {
        // Set up the frame
        setTitle("Registration Manager");
        setSize(600, 400);
        setLayout(new GridLayout(9, 2));
        setVisible(true);
        
        // Initialize components
        Label nameLabel = new Label("Name:");
        Label emailLabel = new Label("Email:");
        Label phoneLabel = new Label("Phone:");
        Label genderLabel = new Label("Gender:");
        Label countryLabel = new Label("Country:");
        
        nameField = new TextField();
        emailField = new TextField();
        phoneField = new TextField();
        
        genderGroup = new CheckboxGroup();
        maleCheckbox = new Checkbox("Male", genderGroup, false);
        femaleCheckbox = new Checkbox("Female", genderGroup, false);
        otherCheckbox = new Checkbox("Other", genderGroup, false);
        
        countryChoice = new Choice();
        countryChoice.add("USA");
        countryChoice.add("Canada");
        countryChoice.add("UK");
        countryChoice.add("Australia");
        countryChoice.add("Other");
        
        displayArea = new TextArea();
        displayArea.setEditable(false);
        
        Button submitButton = new Button("Submit");
        Button displayButton = new Button("Display");
        Button saveButton = new Button("Save");
        
        submitButton.addActionListener(this);
        displayButton.addActionListener(this);
        saveButton.addActionListener(this);
        
        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(phoneLabel);
        add(phoneField);
        add(genderLabel);
        add(maleCheckbox);
        add(new Label(""));
        add(femaleCheckbox);
        add(new Label(""));
        add(otherCheckbox);
        add(countryLabel);
        add(countryChoice);
        add(submitButton);
        add(displayButton);
        add(saveButton);
        add(displayArea);
        
        userData = new ArrayList<>();
        
        // Close the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "Submit":
                submitData();
                break;
            case "Display":
                displayData();
                break;
            case "Save":
                saveData();
                break;
        }
    }
    
    private void submitData() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String gender = genderGroup.getSelectedCheckbox() != null ? genderGroup.getSelectedCheckbox().getLabel() : "Not specified";
        String country = countryChoice.getSelectedItem();
        
        String data = String.format("Name: %s, Email: %s, Phone: %s, Gender: %s, Country: %s", name, email, phone, gender, country);
        userData.add(data);
        
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        genderGroup.setSelectedCheckbox(null);
        countryChoice.select(0);
        
        displayArea.setText("Data submitted successfully!");
    }
    
    private void displayData() {
        StringBuilder displayText = new StringBuilder();
        for (String data : userData) {
            displayText.append(data).append("\n");
        }
        displayArea.setText(displayText.toString());
    }
    
    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("registration_data.txt"))) {
            for (String data : userData) {
                writer.write(data);
                writer.newLine();
            }
            displayArea.setText("Data saved successfully!");
        } catch (IOException ex) {
            displayArea.setText("Error saving data: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new RegistrationManager();
    }
}

