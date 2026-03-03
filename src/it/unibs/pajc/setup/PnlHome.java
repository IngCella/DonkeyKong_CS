package it.unibs.pajc.setup;

import javax.swing.*;
import java.awt.*;

public class PnlHome extends JPanel {

    private Image background;

    public PnlHome(CardLayout cardLayout, JPanel mainPanel) {

        background = new ImageIcon(getClass().getResource("/it/unibs/pajc/assets/home.jpg")).getImage();
        setLayout(null);

        JButton btnHost = createGameButton("HOST GAME");
        btnHost.setBounds(220, 220, 260, 60);
        add(btnHost);

        JButton btnJoin = createGameButton("JOIN GAME");
        btnJoin.setBounds(220, 300, 260, 60);
        add(btnJoin);
        
        btnHost.addActionListener(e -> cardLayout.show(mainPanel, "SERVER"));
        btnJoin.addActionListener(e -> cardLayout.show(mainPanel, "CLIENT"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Disabilita interpolazione per effetto pixel
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        g2.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    private JButton createGameButton(String text) {

        JButton button = new JButton(text);

        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);

        button.setBackground(new Color(170, 0, 0));
        button.setForeground(Color.WHITE);

        button.setFont(new Font("Monospaced", Font.BOLD, 22));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

        // HOVER EFFECT
        button.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(220, 0, 0));
                button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4));
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(170, 0, 0));
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return button;
    }
}