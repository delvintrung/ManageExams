package app.GUI.Sidebar;

import javax.swing.JPanel;

import app.DTO.User_DTO;
import app.GUI.AdminManageScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SideMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	AdminManageScreen main;
	User_DTO currentUser; 
	SideMenuItem menuItems[];
	Color selectedItemBgColor = new Color(190, 215, 220);
	Color itemBgColor = new Color(255, 255, 255);
    Color itemFontColor = new Color(0, 0, 0);
    Color selectedItemFontColor = new Color(0, 0, 0);
    JPanel centerPanel = new JPanel();
	String[][] menu = {
			{"Topic", "topic", "trending-topic.png"},
	        {"Câu hỏi", "questions", "question-mark.png"},
	        {"Đáp án", "answer", "question-and-answer.png"},
	        {"Thống kê", "dashboard", "business.png"},
	        {"Tạo đề thi", "exams", "exam.png"},
	        {"Người dùng", "users", "group.png"},
	    };

	/**
	 * Create the panel.
	 */
	public SideMenu(AdminManageScreen main, User_DTO currentUser) {
		centerPanel.setLayout(new GridLayout(menu.length, 1));
		initComponents();
        this.currentUser = currentUser;
        this.main = main;
        menuItems = new SideMenuItem[menu.length];
        for(int i=0; i<menu.length; i++) {
            menuItems[i] = new SideMenuItem(main, menu[i][0], menu[i][1], menu[i][2]);
            menuItems[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    selectingMenuItem(evt);
                }
            });
            centerPanel.add(menuItems[i]);
//            if(i == 0) {
//                centerPanel.add(menuItems[i]);
//                continue;
//            }
//            if(i==4)
//                continue;
            
        }
        menuItems[0].isSelected = true;
        menuItems[0].setBackground(selectedItemBgColor);
        menuItems[0].nameLabel.setForeground(selectedItemFontColor);
        centerPanel.setBackground(new Color(240, 240, 240));
        centerPanel.setBounds(10, 5, 180, 585);
        add(centerPanel, BorderLayout.CENTER);
	}

	protected void selectingMenuItem(MouseEvent evt) {
		// TODO Auto-generated method stub
		for (int i = 0; i < menu.length; i++) {
            if (evt.getSource() == menuItems[i]) {
                menuItems[i].isSelected = true;
                menuItems[i].setBackground(selectedItemBgColor);
                menuItems[i].nameLabel.setForeground(selectedItemFontColor);
            } else {
                menuItems[i].isSelected = false;
                menuItems[i].setBackground(itemBgColor);
                menuItems[i].nameLabel.setForeground(itemFontColor);
            }
        }
		
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250, 600));
		setBackground(new Color(255, 255, 255));
	}
	

}
