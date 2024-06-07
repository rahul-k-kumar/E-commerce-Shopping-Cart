import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShoppingCartApp extends JFrame {
    private Cart cart;
    private JTextArea cartTextArea;
    private JLabel totalLabel;

    public ShoppingCartApp() {
        cart = new Cart();

        setTitle("Shopping Cart Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Product List Panel
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(0, 1));

        Product[] products = {
                new Product(1, "Product 1", 10.00),
                new Product(2, "Product 2", 20.00),
                new Product(3, "Product 3", 30.00)
        };

        for (Product product : products) {
            JButton productButton = new JButton(product.toString());
            productButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cart.addProduct(product);
                    updateCartDisplay();
                }
            });
            productPanel.add(productButton);
        }

        add(new JScrollPane(productPanel), BorderLayout.WEST);

        // Cart Panel
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BorderLayout());

        cartTextArea = new JTextArea();
        cartTextArea.setEditable(false);
        cartPanel.add(new JScrollPane(cartTextArea), BorderLayout.CENTER);

        totalLabel = new JLabel("Total: $0.00");
        cartPanel.add(totalLabel, BorderLayout.SOUTH);

        add(cartPanel, BorderLayout.CENTER);

        // Checkout Button
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart.checkout();
                updateCartDisplay();
                JOptionPane.showMessageDialog(ShoppingCartApp.this, "Thank you for your purchase!");
            }
        });
        add(checkoutButton, BorderLayout.SOUTH);
    }

    private void updateCartDisplay() {
        List<Product> items = cart.getItems();
        StringBuilder cartContent = new StringBuilder();
        for (Product item : items) {
            cartContent.append(item.toString()).append("\n");
        }
        cartTextArea.setText(cartContent.toString());
        totalLabel.setText("Total: $" + cart.getTotal());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShoppingCartApp().setVisible(true);
            }
        });
    }
}
