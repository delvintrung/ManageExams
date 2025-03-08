package app.GUI.Component.AdminUI;

import java.awt.Color;


import javax.swing.JPanel;

import app.BLL.Test_BLL;
import app.BLL.Topic_BLL;
import app.DAL.Question_DAL;
import app.DAL.Test_DAL;
import app.DTO.Question_DTO;
import app.DTO.Topic_DTO;
import app.DTO.User_DTO;
import app.GUI.AdminManageScreen;
import app.Helper.Validator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.DropMode;

public class CreateExamsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	AdminManageScreen main;
	User_DTO currentUser;
	private JTable table;
	List<Topic_DTO> topics;
	Topic_BLL tpBll = new Topic_BLL();
	Question_DAL qDAL = new Question_DAL();
	Test_BLL tBLL = new Test_BLL();
	int space =0;
	private List<JCheckBox> checkBoxList = new ArrayList<>();
	private JTextField txtTestCode;
	private JTextField txtTestTitle;
	private JTextField txtTime;
	private JTextField txtLimit;
	Validator validator = new Validator();
	private JTextField txtEasyQty;
	private JTextField txtMediumQty;
	private JTextField txtDifficultQty;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public CreateExamsPanel(AdminManageScreen main, User_DTO admin) throws SQLException {
		setBackground(Color.WHITE);
		this.main= main;
		this.currentUser = admin;
		setLayout(null);
		topics = tpBll.getParentTopic();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 117, 676, 135);
		add(scrollPane);
		
		JPanel panelSelectTopic = new JPanel();
		scrollPane.setViewportView(panelSelectTopic);
		panelSelectTopic.setBackground(Color.WHITE);
		panelSelectTopic.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chọn Topic Cần Tạo");
		lblNewLabel.setBounds(40, 10, 141, 22);
		panelSelectTopic.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(437, 0, 237, 133);
		panelSelectTopic.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Số câu dễ");
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(10, 10, 93, 13);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Số câu trung bình");
		lblNewLabel_3_1.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblNewLabel_3_1.setBounds(10, 53, 138, 13);
		panel_2.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Số câu khó");
		lblNewLabel_3_2.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblNewLabel_3_2.setBounds(10, 96, 93, 13);
		panel_2.add(lblNewLabel_3_2);
		
		txtEasyQty = new JTextField();
		txtEasyQty.setFont(new Font("Verdana", Font.PLAIN, 10));
		txtEasyQty.setBounds(141, 7, 44, 19);
		panel_2.add(txtEasyQty);
		txtEasyQty.setColumns(2);
		
		txtMediumQty = new JTextField();
		txtMediumQty.setFont(new Font("Verdana", Font.PLAIN, 10));
		txtMediumQty.setColumns(2);
		txtMediumQty.setBounds(141, 50, 44, 19);
		panel_2.add(txtMediumQty);
		
		txtDifficultQty = new JTextField();
		txtDifficultQty.setFont(new Font("Verdana", Font.PLAIN, 10));
		txtDifficultQty.setColumns(2);
		txtDifficultQty.setBounds(141, 93, 44, 19);
		panel_2.add(txtDifficultQty);
		
		for (Topic_DTO topic : topics) {
			
			JCheckBox chckbxNewCheckBox = new JCheckBox(topic.getTpTitle());
			chckbxNewCheckBox.setBounds(60, 38 + space, 178, 21);
			chckbxNewCheckBox.setActionCommand(String.valueOf(topic.getTpID()));
			panelSelectTopic.add(chckbxNewCheckBox);
			checkBoxList.add(chckbxNewCheckBox);
			chckbxNewCheckBox.setBackground(Color.WHITE);
			chckbxNewCheckBox.setFont(new Font("Verdana", Font.PLAIN, 12));
			space += 37;
		}
		
		JButton btnCreateTest = new JButton("Tạo bài thi");
		btnCreateTest.setBackground(Color.WHITE);
		btnCreateTest.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCreateTest.setBounds(285, 262, 177, 35);
		add(btnCreateTest);
		
		btnCreateTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					createTest();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(27, 313, 676, 240);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Các bài thi đang có");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 10, 154, 23);
		panel.add(lblNewLabel_1);
		
		table = new JTable();
		table.setBounds(20, 43, 614, 130);
		panel.add(table);
		
		JButton btnGenarateTest = new JButton("Trộn đề");
		btnGenarateTest.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnGenarateTest.setBackground(Color.WHITE);
		btnGenarateTest.setBounds(269, 195, 123, 35);
		panel.add(btnGenarateTest);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(27, 10, 676, 96);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("TestCode");
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(10, 10, 81, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("TestTitle");
		lblNewLabel_2_1.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblNewLabel_2_1.setBounds(10, 61, 81, 13);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Thời gian(phút)");
		lblNewLabel_2_2.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblNewLabel_2_2.setBounds(365, 10, 110, 13);
		panel_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Lượt làm bài");
		lblNewLabel_2_3.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblNewLabel_2_3.setBounds(365, 61, 90, 13);
		panel_1.add(lblNewLabel_2_3);
		
		txtTestCode = new JTextField();
		txtTestCode.setBackground(Color.WHITE);
		txtTestCode.setBounds(101, 7, 178, 29);
		panel_1.add(txtTestCode);
		txtTestCode.setColumns(10);
		
		txtTestTitle = new JTextField();
		txtTestTitle.setColumns(10);
		txtTestTitle.setBackground(Color.WHITE);
		txtTestTitle.setBounds(101, 45, 178, 29);
		panel_1.add(txtTestTitle);
		
		txtTime = new JTextField();
		txtTime.setColumns(10);
		txtTime.setBackground(Color.WHITE);
		txtTime.setBounds(473, 7, 90, 29);
		panel_1.add(txtTime);
		
		txtLimit = new JTextField();
		txtLimit.setColumns(10);
		txtLimit.setBackground(Color.WHITE);
		txtLimit.setBounds(472, 45, 91, 29);
		panel_1.add(txtLimit);
		
		
		initComponents();
		initComponents();
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setSize(738,563);
		
	}
	
	
	private void createTest() throws SQLException {
        List<Integer> selectedTopics = new ArrayList<>();
        for (JCheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
            	System.out.println(Integer.parseInt(checkBox.getActionCommand()));
                selectedTopics.add(Integer.parseInt(checkBox.getActionCommand()));
            }
        }

        if (selectedTopics.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một chủ đề!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String topicIds = selectedTopics.toString().replace("[", "(").replace("]", ")");

        
        
        String testCode = txtTestCode.getText();
        String testTitle = txtTestTitle.getText();
        int time = Integer.parseInt(txtTime.getText());
        int limit = Integer.parseInt(txtLimit.getText());
        int numEasyQty = Integer.parseInt(txtEasyQty.getText());
        int numMediumQty = Integer.parseInt(txtMediumQty.getText());
        int numDifficultQty = Integer.parseInt(txtDifficultQty.getText());
        
//        List<Question_DTO> questions = qDAL.getRandomQuestions(topicIds, 10, numEasyQty, numMediumQty, numDifficultQty);
//        
//        if(validator.isEmpty(testCode))
//        {
//        	JOptionPane.showMessageDialog(this, "Chưa điền TestCode", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }else 
//        
//        if(validator.isEmpty(testTitle))
//        {
//        	JOptionPane.showMessageDialog(this, "Chưa điền TestTitle", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }else
//        
//        if(validator.isEmpty(String.valueOf(time)))
//        {
//        	JOptionPane.showMessageDialog(this, "Chưa điền TestCode", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        } else if(!validator.isInteger(String.valueOf(time))) {
//        	JOptionPane.showMessageDialog(this, "Thời gian phải là số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }else
//        
//        if(validator.isEmpty(String.valueOf(limit)))
//        {
//        	JOptionPane.showMessageDialog(this, "Không được bỏ trống số lần làm", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        } else if(!validator.isInteger(String.valueOf(limit))) {
//        	JOptionPane.showMessageDialog(this, "Số lần làm phải là số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        } else {
//        	
//        		if(tBLL.saveTest(questions, testCode,testTitle,time,limit)) {
//        			System.out.println("Tao de thi thanh cong");
//        		}else {
//        			System.out.println("Tao de that bai");
//        		};
//        }
//        
//        
//        if (questions.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Không đủ câu hỏi để tạo bài test!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(this, "Tạo bài test thành công!\n", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//        }
    }
}
