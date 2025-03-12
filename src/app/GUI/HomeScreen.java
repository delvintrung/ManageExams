package app.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import app.BLL.Exam_BLL;
import app.BLL.Result_BLL;
import app.BLL.User_BLL;
import app.DAL.Exam_DAL;
import app.DTO.Result_DTO;
import app.DTO.User_DTO;
import app.GUI.Component.Dialog.UserHistoryResultDialog;
import app.Helper.ExamData;
import app.Helper.Validator;

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
	private DefaultTableModel tableModel;
	private JTable tblHistory;
	
	private Exam_BLL exam_BLL;
	private User_BLL userBLL;
	private Result_BLL resultBLL;
	private User_DTO currentUser;
	private ArrayList<Result_DTO> historyResults;
	
	public HomeScreen(User_DTO user) throws SQLException {
		this.currentUser = user;
		exam_BLL = new Exam_BLL();
		userBLL = new User_BLL();
		resultBLL = new Result_BLL();
		
		this.setTitle("ManageExams - Xin chào, " + currentUser.getUserFullName());
        this.setSize(new Dimension(1000, 600));
        getContentPane().setLayout(new BorderLayout(0, 0));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
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
		
		JButton btnUpdateUser = new JButton("Lưu thay đổi");
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUser(currentUser);
			}
		});
		btnUpdateUser.setBounds(146, 205, 118, 21);
		panel_1.add(btnUpdateUser);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 305, 254, 248);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Lịch sử thi gần nhất");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(37, 10, 183, 13);
		panel_2.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Xem tất cả");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserHistoryResultDialog historyDialog = 
						new UserHistoryResultDialog(HomeScreen.this, true, user, historyResults);
				
				historyDialog.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(66, 211, 135, 27);
		panel_2.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnGoToExam, btnNewButton_1}));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 234, 168);
		panel_2.add(scrollPane);
		
		tableModel = new DefaultTableModel(new Object[]{"Mã bài thi", "Ngày thi", "Điểm"}, 0) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		tblHistory = new JTable(tableModel);
		tblHistory.setBackground(new Color(255, 255, 255));
		tblHistory.setShowGrid(false);
		tblHistory.setShowVerticalLines(false);
		tblHistory.setShowHorizontalLines(false);
		scrollPane.setViewportView(tblHistory);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		JLabel lblNewLabel_3 = new JLabel("Họ tên");
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(20, 58, 75, 21);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Email");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1.setFont(new Font("Verdana", Font.PLAIN, 12));
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
		lblNewLabel_3_1_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_3_1_1.setBounds(20, 125, 75, 26);
		panel_1.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Mật khẩu");
		lblNewLabel_3_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_3_1_1_1.setBounds(20, 167, 75, 28);
		panel_1.add(lblNewLabel_3_1_1_1);
		
		usernameTxt = new JTextField();
		usernameTxt.setEnabled(false);
		usernameTxt.setColumns(10);
		usernameTxt.setText(currentUser.getUserName());
		usernameTxt.setBounds(92, 125, 172, 26);
		usernameTxt.setEditable(false);
		panel_1.add(usernameTxt);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setText(currentUser.getUserPassword());
		passwordTxt.setBounds(92, 165, 172, 30);
		panel_1.add(passwordTxt);
		
		JButton btnLogout = new JButton("Đăng xuất");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUser = null;
				new LoginScreen().setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(20, 205, 118, 21);
		panel_1.add(btnLogout);
		
		historyResults = new ArrayList<Result_DTO>();
		historyResults = resultBLL.getUserResults(currentUser.getUserID());
		
		loadHistoryResultTable(historyResults);
	}
	
	public void updateUser(User_DTO user) {
		String fullname = fullnameTxt.getText();
		String email = emailTxt.getText();
		String password = passwordTxt.getText();
		
		if (fullname.equals("") || email.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
			return;
		}
		if (!Validator.isEmail(email)) {
			JOptionPane.showMessageDialog(this, "Email không hợp lệ!");
			return;
		}
		
		User_DTO updatedUser = new User_DTO(user.getUserName(), email, password, fullname, 0);
		
		if (!userBLL.update(this.currentUser, updatedUser)) {
			JOptionPane.showMessageDialog(this, "Có lỗi xảy ra! Vui lòng thử lại!");
			return;
		}
		
		JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!");
		this.currentUser = updatedUser;
	}
	
	private void loadHistoryResultTable(ArrayList<Result_DTO> results) {
		tableModel.setRowCount(0);
		
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
		
	    int limit = Math.min(results.size(), 5);

	    for (int i = 0; i < limit; i++) {
	        Result_DTO result = results.get(i);
	        try {
	            Date date = inputFormat.parse(result.getRs_date());
	            String formattedDate = outputFormat.format(date);
	            tableModel.addRow(new Object[]{
	                result.getExCode(),
	                formattedDate,
	                result.getRs_mark()
	            });
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        for (int i = 0; i < tblHistory.getColumnCount(); i++) {
        	tblHistory.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
	}
}
