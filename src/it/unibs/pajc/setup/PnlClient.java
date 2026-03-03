package it.unibs.pajc.setup;

import javax.swing.*;
import java.awt.*;

public class PnlClient extends JPanel {

	private Image background;
    
    private JTextField txtClientUsername;
    private JTextField txtClientIP;
    private JTextField txtClientPort;
    
    private JButton btnGo;

	public PnlClient(CardLayout cardLayout, JPanel mainPanel) {

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
        txtClientUsername = new JTextField();
        txtClientUsername.setBounds(250, y_position, 200, 30);
        add(txtClientUsername);

        y_position += gapBlock;
        JLabel lblIP = new JLabel("Server IP");
        lblIP.setBounds(250, y_position, 200, 25);
        lblIP.setFont(new Font("Monospaced", Font.BOLD, 16));
        add(lblIP);

        y_position += gapLblTxt;
        txtClientIP = new JTextField("");
        txtClientIP.setBounds(250, y_position, 200, 30);
        add(txtClientIP);

        y_position += gapBlock;
        JLabel lblPort = new JLabel("Server Port");
        lblPort.setBounds(250, y_position, 200, 25);
        lblPort.setFont(new Font("Monospaced", Font.BOLD, 16));
        add(lblPort);

        y_position += gapLblTxt;
        txtClientPort = new JTextField();
        txtClientPort.setBounds(250, y_position, 200, 30);
        add(txtClientPort);

        JButton btnBack = new JButton("◄ BACK");
        btnBack.setBounds(30, 460, 130, 45);
        styleGameButton(btnBack);
        add(btnBack);

        btnGo = new JButton("GO!");
        btnGo.setBounds(540, 460, 130, 45);
        styleGameButton(btnGo);
        add(btnGo);

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

    public JTextField getTxtClientUsername() {
		return txtClientUsername;
	}

	public JTextField getTxtClientIP() {
		return txtClientIP;
	}

	public JTextField getTxtClientPort() {
		return txtClientPort;
	}
	
	public JButton getBtnGo() {
		return btnGo;
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