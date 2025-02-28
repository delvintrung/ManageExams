package app.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import app.BLL.Exam_BLL;
import app.DAL.Exam_DAL;
import app.DTO.User_DTO;
import app.Helper.ExamData;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField fullnameTxt;
	private JTextField emailTxt;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private JButton btnGoToExam;
	
	private Exam_BLL exam_BLL;
	
	public HomeScreen(User_DTO currentUser) throws SQLException {
		this.setTitle("ManageExams - Xin chào, " + currentUser.getUserFullName());
        this.setSize(new Dimension(1000, 600));
        getContentPane().setLayout(new BorderLayout(0, 0));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        exam_BLL = new Exam_BLL();

        JLabel lblNewLabel = new JLabel("Những bài thi hiện có");
        lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(276, 0, 710, 91);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel.setBorder(new EmptyBorder(40, 10, 20, 10));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(276, 448, 710, 76);
		panel.setBorder(new EmptyBorder(20, 0, 50, 0));
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnGoToExam = new JButton("Làm bài thi");
		btnGoToExam.addActionListener(e -> {
            int selectedRow = table.getSelectedRow(); 
            
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trong bảng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String testCode = table.getValueAt(selectedRow, 0).toString();
            String exCode = table.getValueAt(selectedRow, 2).toString();
            Exam_DAL dal = new Exam_DAL();
            try {
				ExamData exam = dal.getExamByID(testCode);
				String[] questions = exam.questions;
				String[][] options = exam.options;
				String[] correctAnswers = exam.correctAnswers;
		        String[] userAnswers = new String[questions.length];
		        new ExamScreen(currentUser,questions,options, correctAnswers,userAnswers,exCode).setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		btnGoToExam.setBackground(Color.WHITE);
		btnGoToExam.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel.add(btnGoToExam);
		
		table = new JTable();
		String[][] data = exam_BLL.getAllExamForTable();
	 
	    // Column Names
		String[] columnNames = { "Mã bài thi", "Mã đề", "exCode", "Số câu hỏi", "Mức độ" };
	 
	    // Initializing the JTable
	    table = new JTable(data, columnNames);
	    table.setBounds(30, 40, 200, 300);
	 
	    // adding it to JScrollPane
	    JScrollPane sp = new JScrollPane(table);
	    sp.setBounds(276, 106, 710, 61);
		contentPane.add(sp);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 274, 563);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin thí sinh");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 10, 254, 38);
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Lưu thay đổi");
		btnNewButton_2.setBounds(146, 205, 118, 21);
		panel_1.add(btnNewButton_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 305, 254, 248);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Lịch sử thi gần nhất");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(37, 10, 183, 13);
		panel_2.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Xem lịch sử");
		btnNewButton_1.setBounds(66, 211, 115, 27);
		panel_2.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnGoToExam, btnNewButton_1}));
		
		JLabel lblNewLabel_3 = new JLabel("Họ tên");
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(20, 58, 75, 21);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Email");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(20, 94, 75, 21);
		panel_1.add(lblNewLabel_3_1);
		
		fullnameTxt = new JTextField();
		fullnameTxt.setBounds(92, 58, 172, 26);
		fullnameTxt.setText(currentUser.getUserFullName());
		panel_1.add(fullnameTxt);
		fullnameTxt.setColumns(10);
		
		emailTxt = new JTextField();
		emailTxt.setColumns(10);
		emailTxt.setText(currentUser.getUserEmail());
		emailTxt.setBounds(92, 89, 172, 26);
		panel_1.add(emailTxt);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Tên đăng nhập");
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_3_1_1.setBounds(20, 134, 75, 21);
		panel_1.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Mật khẩu");
		lblNewLabel_3_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_3_1_1_1.setBounds(20, 173, 75, 21);
		panel_1.add(lblNewLabel_3_1_1_1);
		
		usernameTxt = new JTextField();
		usernameTxt.setColumns(10);
		usernameTxt.setText(currentUser.getUserName());
		usernameTxt.setBounds(92, 125, 172, 26);
		usernameTxt.setEditable(false);
		usernameTxt.setEnabled(false);
		panel_1.add(usernameTxt);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setText(currentUser.getUserPassword());
		passwordTxt.setBounds(92, 165, 172, 30);
		panel_1.add(passwordTxt);
	}
}
