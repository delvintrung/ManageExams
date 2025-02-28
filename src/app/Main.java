package app;

import javax.swing.*;
import app.GUI.LoginScreen;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;

    public Main() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {    
            new LoginScreen().setVisible(true);
        });
    }
}