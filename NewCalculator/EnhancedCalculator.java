import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EnhancedCalculator extends JFrame implements ActionListener {
    JButton b[] = new JButton[10];
    JButton bAdd, bSubtract, bMultiply, bDivide, bEquals, bClear;
    JTextField res;
    String currentInput = "";
    double num1 = 0;
    char operator = '\0';

    public EnhancedCalculator() {
        super("Modern Style Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel displayPanel = new JPanel(new BorderLayout());
        res = new JTextField();
        res.setEditable(false);
        res.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        displayPanel.add(res, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 10, 10));

        for (int i = 0; i <= 9; i++) {
            b[i] = createNumberButton(i);
            buttonPanel.add(b[i]);
        }
///****************************************************************/
        bAdd = createOperatorButton("+");
        bSubtract = createOperatorButton("-");
        bMultiply = createOperatorButton("*");
        bDivide = createOperatorButton("/");
        bEquals = createButton("=", new Color(0, 150, 0)); // Dark green color
        bClear = createButton("C", new Color(150, 0, 0)); // Dark red color

        buttonPanel.add(bAdd);
        buttonPanel.add(bSubtract);
        buttonPanel.add(bMultiply);
        buttonPanel.add(bDivide);
        buttonPanel.add(bEquals);
        buttonPanel.add(bClear);

        add(displayPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public JButton createButton(String label, Color bgColor) {
        JButton button = new JButton(label);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        return button;
    }

    public JButton createNumberButton(int num) {
        return createButton(String.valueOf(num), Color.DARK_GRAY);
    }

    public JButton createOperatorButton(String label) {
        return createButton(label, Color.BLUE);
    }

    public void actionPerformed(ActionEvent ae) {
        JButton source = (JButton) ae.getSource();
        String buttonText = source.getText();

        if (Character.isDigit(buttonText.charAt(0))) {
            currentInput += buttonText;
            res.setText(currentInput);
        } else if (buttonText.equals("=")) {
            if (!currentInput.isEmpty()) {
                double num2 = Double.parseDouble(currentInput);
                double result = performOperation(num1, num2, operator);
                res.setText(String.valueOf(result));
                currentInput = "";
                num1 = result;
            }
        } else if (buttonText.equals("C")) {
            currentInput = "";
            num1 = 0;
            operator = '\0';
            res.setText("");
        } else {
            if (!currentInput.isEmpty()) {
                num1 = Double.parseDouble(currentInput);
                operator = buttonText.charAt(0);
                currentInput = "";
            }
        }
    }

    public double performOperation(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return Double.NaN; // Division by zero
                }
            default:
                return num2; // Default case: return the second operand
        }
    }
// created by Shahzaib Ibrar
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EnhancedCalculator());
    }
}
