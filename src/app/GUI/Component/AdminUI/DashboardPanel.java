package app.GUI.Component.AdminUI;

import javax.swing.JPanel;

import app.DTO.User_DTO;
import app.GUI.AdminManageScreen;

import java.awt.Color;

public class DashboardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	AdminManageScreen main;
	User_DTO currentUser;

	/**
	 * Create the panel.
	 */
	public DashboardPanel(AdminManageScreen main, User_DTO admin) {
		setBackground(Color.ORANGE);
		this.main= main;
		this.currentUser = admin;
		initComponents();
		initComponents();
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setSize(738,563);
		
	}

}
