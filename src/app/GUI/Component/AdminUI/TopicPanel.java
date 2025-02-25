package app.GUI.Component.AdminUI;

import java.awt.Color;

import javax.swing.JPanel;

import app.DTO.User_DTO;
import app.GUI.AdminManageScreen;
import javax.swing.JLabel;

public class TopicPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	AdminManageScreen main;
	User_DTO currentUser;

	/**
	 * Create the panel.
	 */
	public TopicPanel(AdminManageScreen main, User_DTO admin) {
		setBackground(Color.WHITE);
		this.main= main;
		this.currentUser = admin;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(44, 31, 45, 13);
		add(lblNewLabel);
		initComponents();
		initComponents();
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setSize(738,563);
		
	}
}
