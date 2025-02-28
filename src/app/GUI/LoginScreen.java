package app.GUI;

import javax.swing.*;

import app.Main;
import app.DAL.Login_DAL;
import app.DTO.User_DTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;

public class LoginScreen extends JFrame {
	private static final long serialVersionUID = 7465587089766977890L;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JButton btnRegister;
	
	private Login_DAL loginDAL = new Login_DAL();
	private Main main;
	private RegisterScreen registerScreen;
	
	public LoginScreen() {
		initComponents();
		initComponentsCustom();
		btnLogin.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  try {
					checkLogin();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
			} );
	}
	
	private void checkLogin() throws SQLException {
		// TODO Auto-generated method stub
		String username = textField.getText();
		String password = passwordField.getText();
        User_DTO account = loginDAL.selectByUserName(username);
        
        if(username.equals("")) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được rỗng");
            return;
        }
        if(password.equals("")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được rỗng");
            return;
        }
        if(!BCrypt.checkpw(password, account.getUserPassword())) {
            JOptionPane.showMessageDialog(this, "Sai mật khẩu");
            return;
        }
        
        if(account != null && account.getIsAdmin() == 1) {
        	AdminManageScreen adminManageScreen = new AdminManageScreen(account);
        	adminManageScreen.setVisible(true);
        	dispose();
        	return;
        } else {
        	HomeScreen homeScreen = new HomeScreen(account);
        	homeScreen.setVisible(true);
        	dispose();
        	System.out.println("Dang nhap thanh cong");
        }
	} 


	private void initComponentsCustom() {
		// TODO Auto-generated method stub
		this.setLocationRelativeTo(null);
	}


	private void initComponents() {
		// TODO Auto-generated method stub
		setTitle("Login");
		setSize(1000,600);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 471, 573);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel.setBounds(117, 85, 221, 58);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 13));
		lblNewLabel_1.setBounds(32, 202, 152, 17);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu");
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(32, 259, 152, 17);
		panel.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setBounds(178, 188, 238, 34);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(178, 253, 238, 34);
		panel.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Nếu chưa có tài khoản ?");
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(67, 422, 203, 13);
		panel.add(lblNewLabel_2);
		
		btnLogin = new JButton("Đăng nhập");
		btnLogin.setFont(new Font("Verdana", Font.BOLD, 14));
		btnLogin.setBounds(138, 338, 181, 34);
		panel.add(btnLogin);
		
		btnRegister = new JButton("Đăng ký");
		btnRegister.setFont(new Font("Verdana", Font.BOLD, 14));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerScreen = new RegisterScreen();
				registerScreen.setVisible(true);
			}
		});
		btnRegister.setBounds(239, 414, 177, 27);
		panel.add(btnRegister);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(468, 0, 519, 573);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(-14, -127, 738, 834);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(new ImageIcon(getClass().getResource("/public/1000_F_407119847_nncJDJSF4tF4nD9s10RJvw5IV27ADlmr.jpg")));
	}
}