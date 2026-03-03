package it.unibs.pajc.setup;

import javax.swing.*;
import java.awt.*;

public class PnlConfig extends JPanel {

    private Image background;
    private boolean isHost;
    
    private JTextField txtUsername;
    private JTextField txtIP;
    private JTextField txtPort;
    
    private JButton btnStart;

	public PnlConfig(CardLayout cardLayout, JPanel mainPanel) {

        background = new ImageIcon(getClass().getResource("/it/unibs/pajc/assets/home.jpg") ).getImage();

        setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(250, 150, 200, 25);
        lblUsername.setFont(new Font("Monospaced", Font.BOLD, 16));
        lblUsername.setForeground(Color.BLACK);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(250, 175, 200, 30);
        add(txtUsername);

        JLabel lblIP = new JLabel("Your IP");
        lblIP.setBounds(250, 220, 200, 25);
        lblIP.setFont(new Font("Monospaced", Font.BOLD, 16));
        add(lblIP);

        txtIP = new JTextField("192.168.0.10");
        txtIP.setBounds(250, 245, 200, 30);
        add(txtIP);

        JLabel lblPort = new JLabel("Port");
        lblPort.setBounds(250, 290, 200, 25);
        lblPort.setFont(new Font("Monospaced", Font.BOLD, 16));
        add(lblPort);

        txtPort = new JTextField();
        txtPort.setBounds(250, 315, 200, 30);
        add(txtPort);

        JButton btnBack = new JButton("< BACK");
        btnBack.setBounds(30, 460, 130, 45);
        styleGameButton(btnBack);
        add(btnBack);

        btnStart = new JButton("START!");
        btnStart.setBounds(540, 460, 130, 45);
        styleGameButton(btnStart);
        add(btnStart);

        btnBack.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Disabilita interpolazione per effetto pixel
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        g2.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    public JTextField getTxtUsername() {
		return txtUsername;
	}

	public JTextField getTxtIP() {
		return txtIP;
	}

	public JTextField getTxtPort() {
		return txtPort;
	}
	
	public JButton getBtnStart() {
		return btnStart;
	}

	private void styleGameButton(JButton button) {

        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);

        button.setBackground(new Color(170, 0, 0));
        button.setForeground(Color.WHITE);

        button.setFont(new Font("Monospaced", Font.BOLD, 20));
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

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(140, 0, 0)); // effetto pressione
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(220, 0, 0));
            }
        });
    }
}