package it.unibs.pajc.setup;

import javax.swing.*;
import java.awt.*;

public class PnlServer extends JPanel {

    private Image background;
    
    private JTextField txtServerUsername;
    private JTextField txtServerIP;
    private JTextField txtServerPort;
    
    private JButton btnStart;

	public PnlServer(CardLayout cardLayout, JPanel mainPanel) {

        background = new ImageIcon(getClass().getResource("/it/unibs/pajc/assets/home.jpg") ).getImage();
        setLayout(null);
        
        int y_position = 195;
        int gapLblTxt = 25;
        int gapBlock = 45;

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(250, y_position, 200, 25);
        lblUsername.setFont(new Font("Monospaced", Font.BOLD, 16));
        lblUsername.setForeground(Color.BLACK);
        add(lblUsername);

        y_position += gapLblTxt;
        txtServerUsername = new JTextField();
        txtServerUsername.setBounds(250, y_position, 200, 30);
        add(txtServerUsername);

        y_position += gapBlock;
        JLabel lblIP = new JLabel("Your IP");
        lblIP.setBounds(250, y_position, 200, 25);
        lblIP.setFont(new Font("Monospaced", Font.BOLD, 16));
        add(lblIP);

        y_position += gapLblTxt;
        txtServerIP = new JTextField("127.0.0.1");
        txtServerIP.setBounds(250, y_position, 200, 30);
        txtServerIP.setEditable(false);
        txtServerIP.setFocusable(false);
        add(txtServerIP);

        y_position += gapBlock;
        JLabel lblPort = new JLabel("Port");
        lblPort.setBounds(250, y_position, 200, 25);
        lblPort.setFont(new Font("Monospaced", Font.BOLD, 16));
        add(lblPort);

        y_position += gapLblTxt;
        txtServerPort = new JTextField();
        txtServerPort.setBounds(250, y_position, 200, 30);
        add(txtServerPort);

        JButton btnBack = new JButton("◄ BACK");
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

    public JTextField getTxtServerUsername() {
		return txtServerUsername;
	}

	public JTextField getTxtServerIP() {
		return txtServerIP;
	}

	public JTextField getTxtServerPort() {
		return txtServerPort;
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