package app.GUI.Sidebar;

import javax.swing.JPanel;

import app.Main;
import app.GUI.AdminManageScreen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class SideMenuItem extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	public boolean isSelected;
	AdminManageScreen main;
	String target;
	JLabel nameLabel = new JLabel();
	JLabel iconLabel = new JLabel("");
	
	/**
	 * Create the panel.
	 */
	public SideMenuItem(AdminManageScreen main, String name, String target, String imageLink) {
		initComponents();
		nameLabel.setText(name);
		this.main = main;
        this.target = target;
        iconLabel.setIcon(new ImageIcon(getClass().getResource("/public/icon/" + imageLink)));
        addMouseListener(this);
			
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setPreferredSize(new Dimension(250, 20));
		setBackground(Color.WHITE);
		setLayout(null);
		
		nameLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		nameLabel.setBounds(83, 10, 141, 30);
		add(nameLabel);
		
	
		iconLabel.setBounds(12, 0, 49, 50);
		add(iconLabel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		main.switchCard(this.target);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!isSelected) {
            setBackground(new Color(222, 222, 222));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!isSelected) {
            setBackground(new Color(255, 255, 255));
        }	 
	}
}
