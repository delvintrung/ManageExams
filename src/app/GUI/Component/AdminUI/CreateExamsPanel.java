package app.GUI.Component.AdminUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import app.BLL.Question_BLL;
import app.BLL.Test_BLL;
import app.BLL.Topic_BLL;
import app.DAL.Question_DAL;
import app.DAL.Test_DAL;
import app.DTO.Exam_DTO;
import app.DTO.Question_DTO;
import app.DTO.Test_DTO;
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
import java.net.http.WebSocket.Listener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import javax.swing.DropMode;
import javax.swing.table.DefaultTableModel;

public class CreateExamsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	AdminManageScreen main;
	User_DTO currentUser;
	private JTable table;
	List<Topic_DTO> topics;
	Topic_BLL tpBll = new Topic_BLL();
	Question_DAL qDAL = new Question_DAL();
	Test_BLL tBLL = new Test_BLL();
	Question_BLL qBll = new Question_BLL();
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
	private Date selectedDate = new Date();
	private DefaultTableModel tableModel = new DefaultTableModel();
	public List<Test_DTO> allTest = tBLL.getAllTest();

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
		panelSelectTopic.setPreferredSize(new Dimension(600, 500));
		
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
		panelSelectTopic.revalidate();
		panelSelectTopic.repaint();
		
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
		String[] column = {
				"TestID", "TestCode", "TestTitle", "TestTime", "Số câu dễ", "Số câu tb", "Số câu khó", "Giới hạn làm", 	"Ngày làm"
			};
		
		
		tableModel = new DefaultTableModel(column,0);
 		table = new JTable(tableModel);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(0, 43, 676, 130);
		panel.add(sp);
		
		JButton btnGenarateTest = new JButton("Trộn đề");
		btnGenarateTest.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnGenarateTest.setBackground(Color.WHITE);
		btnGenarateTest.setBounds(269, 195, 123, 35);
		panel.add(btnGenarateTest);
		
		btnGenarateTest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				int column = 1;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, column).toString();
				int testID = tBLL.getTestIDByTestCode(value);
    			
    			List<Integer> questionsID = qBll.getQuesOfTestByTestId(testID);
    			
    			try {
					tBLL.GenarateExams(value, questionsID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(27, 10, 676, 96);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("TestCode");
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(10, 15, 81, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("TestTitle");
		lblNewLabel_2_1.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblNewLabel_2_1.setBounds(10, 38, 81, 13);
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
		txtTestCode.setBounds(101, 7, 178, 19);
		panel_1.add(txtTestCode);
		txtTestCode.setColumns(10);
		
		txtTestTitle = new JTextField();
		txtTestTitle.setColumns(10);
		txtTestTitle.setBackground(Color.WHITE);
		txtTestTitle.setBounds(101, 36, 178, 19);
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
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Ngày thi");
		lblNewLabel_2_1_1.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblNewLabel_2_1_1.setBounds(10, 73, 81, 13);
		panel_1.add(lblNewLabel_2_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(101, 61, 229, 35);
		panel_1.add(panel_3);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(0, 7, 116, 19);
        dateChooser.setDate(new Date());
        
        JButton btnGetDate = new JButton("Chọn");
        btnGetDate.setBackground(Color.WHITE);
        btnGetDate.setBounds(126, 7, 93, 21);
        
        btnGetDate.addActionListener(e -> {
            selectedDate = dateChooser.getDate();
        });
        panel_3.setLayout(null);

        panel_3.add(dateChooser);
        panel_3.add(btnGetDate);
		
		
		initComponents();
		initComponents();
		loadDataToTable(allTest);
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setSize(738,563);
		
	}
	
	private void loadDataToTable(List<Test_DTO> allTest) {
		tableModel.setRowCount(0); 
        
        for (Test_DTO t : allTest) {
        	tableModel.addRow(new Object[]{
                t.getTestID(), t.getTestCode(), t.getTestTitle(), t.getTestTime(), t.getNum_easy(), t.getNum_medium(), t.getNum_diff(),	t.getTestLimit(), t.getTestDate(), t.getTestStatus()
            });
        }
    }
	
	
	private void createTest() throws SQLException {
        List<Integer> selectedTopics = new ArrayList<>();
        for (JCheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                selectedTopics.add(Integer.parseInt(checkBox.getActionCommand()));
            }
        }

        if (selectedTopics.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một chủ đề!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        

        
        
        String testCode = txtTestCode.getText();
        String testTitle = txtTestTitle.getText();
        int time = Integer.parseInt(txtTime.getText());
        int limit = Integer.parseInt(txtLimit.getText());
        int numEasyQty = Integer.parseInt(txtEasyQty.getText());
        int numMediumQty = Integer.parseInt(txtMediumQty.getText());
        int numDifficultQty = Integer.parseInt(txtDifficultQty.getText());
        
//        List<Integer> questions = qDAL.getRandomQuestions(10, numEasyQty, numMediumQty, numDifficultQty);
        
        if(Validator.isEmpty(testCode))
        {
        	JOptionPane.showMessageDialog(this, "Chưa điền TestCode", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }else 
        
        if(Validator.isEmpty(testTitle))
        {
        	JOptionPane.showMessageDialog(this, "Chưa điền TestTitle", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }else
        
        if(Validator.isEmpty(String.valueOf(time)))
        {
        	JOptionPane.showMessageDialog(this, "Chưa điền TestCode", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else if(!Validator.isInteger(String.valueOf(time))) {
        	JOptionPane.showMessageDialog(this, "Thời gian phải là số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }else
        
        if(Validator.isEmpty(String.valueOf(limit)))
        {
        	JOptionPane.showMessageDialog(this, "Không được bỏ trống số lần làm", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else if(!Validator.isInteger(String.valueOf(limit))) {
        	JOptionPane.showMessageDialog(this, "Số lần làm phải là số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
        	
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            String formattedDate = sdf.format(selectedDate);
        		if(tBLL.saveTest(testCode,testTitle,time,selectedTopics,limit, numEasyQty, numMediumQty, numDifficultQty, formattedDate)) {
        			
        			System.out.println("Tao de thi thanh cong");
        			loadDataToTable(tBLL.getAllTest());
        		}else {
        			System.out.println("Tao de that bai");
        		};
        }
        
        
        
    }
}
