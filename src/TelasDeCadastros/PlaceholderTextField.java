package TelasDeCadastros;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlaceholderTextField extends JTextField {
	private static final long serialVersionUID = 1L; // Adicionando serialVersionUID
	private String placeholder;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;

        // Add a focus listener to handle the placeholder
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText("");
                    repaint();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText("");
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the placeholder text
        if (getText().isEmpty() && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
            g2.dispose();
        }
    }

}
