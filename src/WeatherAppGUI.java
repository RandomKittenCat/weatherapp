package com.weatherapp; // Assuming a package structure if Main.java uses one, otherwise remove.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherAppGUI extends JFrame {

    private JTextField locationInputField;
    private JButton getWeatherButton; // This button will be removed in the next step
    private JTextArea weatherDisplayArea;

    public WeatherAppGUI() {
        // --- Dark Theme Colors ---
        Color darkBlue = new Color(25, 25, 112); // Midnight Blue
        Color whiteText = Color.WHITE;

        // Set up the main frame
        setTitle("Weather App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        getContentPane().setBackground(darkBlue);

        // Set up the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(darkBlue);
        panel.setBorder(BorderFactory.createLineBorder(darkBlue, 10)); // Add a border

        // Input Panel (North)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBackground(darkBlue);

        JLabel locationLabel = new JLabel("City or Zip:");
        locationLabel.setForeground(whiteText);
        inputPanel.add(locationLabel);

        locationInputField = new JTextField(20);
        locationInputField.setBackground(new Color(40, 40, 130));
        locationInputField.setForeground(whiteText);
        locationInputField.setCaretColor(whiteText); // Set cursor color
        locationInputField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 60, 150)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding
        ));
        inputPanel.add(locationInputField);

        panel.add(inputPanel, BorderLayout.NORTH);

        // Weather Display Area (Center)
        weatherDisplayArea = new JTextArea();
        weatherDisplayArea.setEditable(false);
        weatherDisplayArea.setLineWrap(true);
        weatherDisplayArea.setWrapStyleWord(true);
        weatherDisplayArea.setBackground(new Color(40, 40, 130));
        weatherDisplayArea.setForeground(whiteText);
        weatherDisplayArea.setMargin(new Insets(10, 10, 10, 10)); // Padding

        JScrollPane scrollPane = new JScrollPane(weatherDisplayArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove scroll pane border
        scrollPane.getViewport().setBackground(darkBlue);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add action listener to the text field for Enter key press
        locationInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchWeather();
            }
        });

        add(panel);
    }

    private void fetchWeather() {
        String location = locationInputField.getText();
        if (!location.isEmpty()) {
            weatherDisplayArea.setText("Fetching weather for: " + location + "...\n");
            // In a real application, you would call your API here
            // For now, simulate a response
            simulateWeatherFetch(location);
        } else {
            weatherDisplayArea.setText("Please enter a city or zip code.");
        }
    }

    private void simulateWeatherFetch(String location) {
        // This is a placeholder for your API call
        // In reality, this would be a network request
        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                Thread.sleep(1000); // Simulate network delay
                if (location.equalsIgnoreCase("New York") || location.equals("10001")) {
                    return "Weather in " + location + ": Sunny, 25°C. Humidity: 60%.";
                } else if (location.equalsIgnoreCase("London") || location.equals("SW1A 0AA")) {
                    return "Weather in " + location + ": Cloudy, 15°C. Wind: 10 km/h.";
                } else {
                    return "Could not fetch weather for " + location + ". Please try again.";
                }
            }

            @Override
            protected void done() {
                try {
                    weatherDisplayArea.append(get());
                } catch (Exception e) {
                    weatherDisplayArea.append("Error fetching weather: " + e.getMessage());
                }
            }
        }.execute();
    }

    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WeatherAppGUI().setVisible(true);
            }
        });
    }
}
