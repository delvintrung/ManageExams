package app.GUI;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.DTO.User_DTO;
import app.GUI.Component.AdminUI.CreateExamsPanel;
import app.GUI.Component.AdminUI.DashboardPanel;
import app.GUI.Component.AdminUI.QuestionsPanel;
import app.GUI.Component.AdminUI.TopicPanel;
import app.GUI.Sidebar.SideMenu;
import java.awt.Color;
import java.sql.SQLException;

public class AdminManageScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel Content = new JPanel();
	public CardLayout cardLayout;
	public SideMenu sideMenu;
	QuestionsPanel questionsPanel;
	DashboardPanel dashboardPanel;
	CreateExamsPanel createExamsPanel;
	TopicPanel topicPanel;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AdminManageScreen(User_DTO currentAdmin) throws SQLException {
		getContentPane().setBackground(Color.WHITE);
		initCompponents(currentAdmin);
	}

	private void initCompponents(User_DTO currentAdmin) throws SQLException {
		// TODO Auto-generated method stub
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		setTitle("Admin");
		setSize(1000,600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		questionsPanel = new QuestionsPanel(this, currentAdmin);
		dashboardPanel = new DashboardPanel(this, currentAdmin);
		createExamsPanel = new CreateExamsPanel(this, currentAdmin);
		topicPanel = new TopicPanel(this, currentAdmin);
		
		cardLayout = new CardLayout();
	    Content.setLayout(cardLayout);
	    Content.setBounds(248, 0, 738, 563);
	    
	    Content.add(topicPanel, "topic");
	    Content.add(questionsPanel, "questions");
	    Content.add(dashboardPanel, "dashboard");
	    Content.add(createExamsPanel,"exams");
	    getContentPane().add(Content);
	    
	    
	    
	    JPanel panel = new SideMenu(this, currentAdmin); 
	    panel.setBounds(0, 0, 250, 563);
	    getContentPane().add(panel);
	}
	
	public void switchCard(String panelName) {
        cardLayout.show(Content, panelName);
    }
}
