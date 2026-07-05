import javax.swing.*;
import java.awt.*;

public class TemperatureConverter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            JFrame frame = new JFrame("Temperature Converter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(480, 320);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            mainPanel.setBackground(new Color(240, 244, 248));

            JLabel titleLabel = new JLabel("Temperature Converter");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
            titleLabel.setForeground(new Color(16, 42, 67));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
            inputPanel.setOpaque(false);

            JTextField inputField = new JTextField(8);
            inputField.setFont(new Font("SansSerif", Font.PLAIN, 16));
            inputField.setHorizontalAlignment(JTextField.CENTER);
            inputField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(204, 214, 224), 2),
                    BorderFactory.createEmptyBorder(6, 6, 6, 6)));

            String[] scales = { "Celsius", "Fahrenheit", "Kelvin" };
            JComboBox<String> fromBox = new JComboBox<>(scales);
            JComboBox<String> toBox = new JComboBox<>(scales);
            fromBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
            toBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
            fromBox.setBackground(Color.WHITE);
            toBox.setBackground(Color.WHITE);
            fromBox.setFocusable(false);
            toBox.setFocusable(false);

            JLabel toLabel = new JLabel("to");
            toLabel.setForeground(new Color(98, 125, 152));
            toLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

            inputPanel.add(inputField);
            inputPanel.add(fromBox);
            inputPanel.add(toLabel);
            inputPanel.add(toBox);

            JButton convertButton = new JButton("CONVERT");
            convertButton.setFont(new Font("SansSerif", Font.BOLD, 14));
            convertButton.setBackground(new Color(38, 128, 235));
            convertButton.setForeground(Color.WHITE);
            convertButton.setFocusPainted(false);
            convertButton.setOpaque(true);
            convertButton.setBorderPainted(false);
            convertButton.setPreferredSize(new Dimension(150, 40));
            convertButton.setMaximumSize(new Dimension(150, 40));
            convertButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            convertButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            JLabel resultLabel = new JLabel("Result: --");
            resultLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
            resultLabel.setForeground(new Color(16, 42, 67));
            resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            convertButton.addActionListener(e -> {
                try {
                    double val = Double.parseDouble(inputField.getText());
                    String from = (String) fromBox.getSelectedItem();
                    String to = (String) toBox.getSelectedItem();
                    double res = val;

                    if (from != null && to != null && !from.equals(to)) {
                        if (from.equals("Celsius")) {
                            res = to.equals("Fahrenheit") ? (val * 9 / 5) + 32 : val + 273.15;
                        } else if (from.equals("Fahrenheit")) {
                            res = to.equals("Celsius") ? (val - 32) * 5 / 9 : (val - 32) * 5 / 9 + 273.15;
                        } else if (from.equals("Kelvin")) {
                            res = to.equals("Celsius") ? val - 273.15 : (val - 273.15) * 9 / 5 + 32;
                        }
                    }
                    resultLabel.setText(String.format("Result: %.2f %s", res, to));
                    resultLabel.setForeground(new Color(19, 115, 51));
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid number");
                    resultLabel.setForeground(new Color(217, 48, 37));
                }
            });

            mainPanel.add(titleLabel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));
            mainPanel.add(inputPanel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            mainPanel.add(convertButton);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
            mainPanel.add(resultLabel);

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}