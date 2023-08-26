import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator {
    private Frame frame;
    private TextField textField;
    private Button[] numberButtons;
    private Button[] operatorButtons;
    private Button clearButton;
    private Button equalsButton;
    private Button closeButton;

    private String input = "";

    public SimpleCalculator() {
        frame = new Frame("Simple Calculator");
        textField = new TextField();
        numberButtons = new Button[10];
        operatorButtons = new Button[4];
        clearButton = new Button("C");
        equalsButton = new Button("=");
        closeButton = new Button("Close");

        frame.setLayout(new BorderLayout());
        frame.setSize(600, 800);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        textField.setFont(new Font("Sans Regular", Font.BOLD, 12));
        frame.add(textField, BorderLayout.NORTH);

        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].setFont(new Font("Sans Regular", Font.BOLD, 12)); 
            numberButtons[i].addActionListener(new NumberButtonListener());
            buttonPanel.add(numberButtons[i]);
        }

        operatorButtons[0] = new Button("+");
        operatorButtons[1] = new Button("-");
        operatorButtons[2] = new Button("*");
        operatorButtons[3] = new Button("/");

        for (Button operatorButton : operatorButtons) {
            operatorButton.setFont(new Font("Sans Regular", Font.BOLD, 18)); 
            operatorButton.addActionListener(new OperatorButtonListener());
            buttonPanel.add(operatorButton);
        }

        clearButton.setFont(new Font("Sans Regular", Font.BOLD, 12)); 
        clearButton.addActionListener(new ClearButtonListener());
        buttonPanel.add(clearButton);

        equalsButton.setFont(new Font("Sans Regular", Font.BOLD, 12)); 
        equalsButton.addActionListener(new EqualsButtonListener());
        buttonPanel.add(equalsButton);

        closeButton.setFont(new Font("Sans Regular", Font.BOLD, 12));
        closeButton.addActionListener(new CloseButtonListener());
        buttonPanel.add(closeButton);

        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    private class NumberButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String digit = ((Button) e.getSource()).getLabel();
            input += digit;
            textField.setText(input);
        }
    }

    private class OperatorButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String operator = ((Button) e.getSource()).getLabel();
            input += " " + operator + " ";
            textField.setText(input);
        }
    }

    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            input = "";
            textField.setText("");
        }
    }

    private class EqualsButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] tokens = input.split(" ");
            double result = Double.parseDouble(tokens[0]);
            for (int i = 1; i < tokens.length; i += 2) {
                String operator = tokens[i];
                double operand = Double.parseDouble(tokens[i + 1]);
                switch (operator) {
                    case "+":
                        result += operand;
                        break;
                    case "-":
                        result -= operand;
                        break;
                    case "*":
                        result *= operand;
                        break;
                    case "/":
                        result /= operand;
                        break;
                }
            }
            textField.setText(Double.toString(result));
            input = "";
        }
    }

    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
