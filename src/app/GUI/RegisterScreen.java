package app.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Main;
import app.BLL.User_BLL;
import app.DTO.User_DTO;
import app.Helper.Validator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class RegisterScreen extends javax.swing.JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailTxt;
	private JTextField fullnameTxt;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private JButton btnNewButton;
	
	private User_BLL userBLL = new User_BLL();
		
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,600);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.highlight"));
		panel.setBounds(245, -10, 394, 573);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Đăng ký");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(117, 39, 181, 47);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(47, 116, 45, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Họ Tên");
		lblNewLabel_2_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(47, 153, 84, 13);
		panel.add(lblNewLabel_2_1);
		
		emailTxt = new JTextField();
		emailTxt.setBounds(177, 115, 194, 19);
		panel.add(emailTxt);
		emailTxt.setColumns(10);
		
		JLabel lblNewLabel_2_2 = new JLabel("Tên đăng nhập");
		lblNewLabel_2_2.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(47, 216, 127, 21);
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Mật khẩu");
		lblNewLabel_2_2_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_2_2_1.setBounds(47, 274, 96, 13);
		panel.add(lblNewLabel_2_2_1);
		
		fullnameTxt = new JTextField();
		fullnameTxt.setColumns(10);
		fullnameTxt.setBounds(177, 153, 194, 19);
		panel.add(fullnameTxt);
		
		usernameTxt = new JTextField();
		usernameTxt.setColumns(10);
		usernameTxt.setBounds(177, 215, 194, 19);
		panel.add(usernameTxt);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(177, 273, 194, 19);
		panel.add(passwordTxt);
		
		btnNewButton = new JButton("Đăng ký");
		btnNewButton.setBounds(154, 364, 85, 21);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(139, -43, 630, 628);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\namba\\eclipse-workspace\\ManageExams\\src\\public\\courses-offline-banner-resize.png"));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(383, -53, 630, 628);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\namba\\eclipse-workspace\\ManageExams\\src\\public\\courses-offline-banner-resize.png"));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\namba\\eclipse-workspace\\ManageExams\\src\\public\\icai-exemption-criteria-for-may-2024-new-course.png"));
		lblNewLabel_4.setBounds(431, -10, 555, 573);
		contentPane.add(lblNewLabel_4);
		
	}

	public RegisterScreen() {
		initComponents();
		initComponentsCustom();
		
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CheckRegister();
			}

			});
	}

	private void initComponentsCustom() {
		// TODO Auto-generated method stub
		this.setLocationRelativeTo(null);
	}
	
	private void CheckRegister() {
		// TODO Auto-generated method stub
		String email = emailTxt.getText();
		String fullname = fullnameTxt.getText();
		String username = usernameTxt.getText();
		String password = passwordTxt.getText();
		System.out.println(Validator.isEmpty(email) + "|" + !Validator.isEmail(email));
		
        if(Validator.isEmpty(email) || !Validator.isEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không được bỏ trống hoặc sai định dạng");
            return;
        }
        if(fullname.equals("")) {
            JOptionPane.showMessageDialog(this, "Tên hiển thị không được bỏ trống");
            return;
        }
        if(password.equals("")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được rỗng");
            return;
        }
        if(username.equals("")) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được bỏ trống");
            return;
        }
        
        User_DTO newUser = new User_DTO(username, email, password, fullname, 0);
        
        if (userBLL.create(newUser)) {
        	JOptionPane.showMessageDialog(this, "Đăng ký thành công");
        	new LoginScreen().setVisible(true);
        } else {
        	JOptionPane.showMessageDialog(this, "Đăng ký thất bại. Vui lòng kiểm tra lại thông tin!");
        }
        
        
	}
}
