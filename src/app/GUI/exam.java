package app.GUI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class exam extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public exam() {
		setBackground(Color.WHITE);
		setSize(700,600);
		setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Câu 10");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNewLabel_1.setBounds(66, 20, 538, 18);
		add(lblNewLabel_1);
		
		JPanel optionPanel = new JPanel();
		optionPanel.setForeground(Color.GRAY);
		optionPanel.setBackground(Color.WHITE);
		optionPanel.setBounds(53, 338, 585, 182);
		add(optionPanel);
		optionPanel.setLayout(new GridLayout(3,2));
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_1.setBackground(Color.WHITE);
		optionPanel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_2.setBackground(Color.WHITE);
		optionPanel.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("New radio button");
		optionPanel.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		optionPanel.add(rdbtnNewRadioButton);
		
		JPanel imagePanel = new JPanel();
		imagePanel.setBackground(Color.WHITE);
		imagePanel.setBounds(138, 114, 402, 204);
		add(imagePanel);
		
		JLabel image = new JLabel("image");
		imagePanel.add(image);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(210, 529, 272, 43);
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton prev = new JButton("Câu trước");
		prev.setBounds(10, 10, 115, 21);
		panel_1.add(prev);
		
		JButton next = new JButton("Câu sau");
		next.setBounds(147, 10, 115, 21);
		panel_1.add(next);
		
		JTextArea txtrDayLaCau = new JTextArea();
		txtrDayLaCau.setFont(new Font("Maiandra GD", Font.PLAIN, 14));
		txtrDayLaCau.setWrapStyleWord(true);
		txtrDayLaCau.setEditable(false);
		txtrDayLaCau.setLineWrap(true);
		txtrDayLaCau.setText("Day la cau hoi kha la dddai, neu nu nhu ma dai qua thi tu ding xuong hangf, neu khong xuong thi chinh lai so cot");
		txtrDayLaCau.setColumns(10);
		txtrDayLaCau.setRows(4);
		txtrDayLaCau.setBounds(53, 47, 585, 57);
		add(txtrDayLaCau);

	}
}
