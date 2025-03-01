package app.GUI.Component.Dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.BLL.User_BLL;
import app.DTO.User_DTO;
import app.GUI.Component.AdminUI.UserPanel;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;

public class UserCRUDDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField txtEmail;
	private JComboBox<String> cbxRole;
	private JButton btnSave;
	
	private User_DTO currentUser;
	private User_BLL userBLL;
	private JTextField txtFullName;
	private JPasswordField txtPassword;
	private JLabel lblTnngNhp;
	private JTextField txtUsername;

	public UserCRUDDialog(Frame parent, boolean modal, String title, UserPanel userPanel, String mode,  User_DTO currentUser) {
		super(parent, modal);
		
		this.currentUser = currentUser;
		userBLL = new User_BLL();
		
		setSize(700,600);
		getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);
        
        JLabel lblTitle = new JLabel("Email:");
        lblTitle.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblTitle.setBounds(87, 182, 74, 39);
        getContentPane().add(lblTitle);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(200, 183, 404, 39);
        getContentPane().add(txtEmail);
        txtEmail.setColumns(10);
        
        btnSave = new JButton("Xác nhận");
        btnSave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if ("create".equals(mode)) {
        			create();        			
        		} else {
        			update();
        		}
        	}
        });
        btnSave.setBackground(Color.CYAN);
        btnSave.setBounds(507, 409, 97, 33);
        getContentPane().add(btnSave);
        
        JLabel lblRole = new JLabel("Role:");
        lblRole.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblRole.setBounds(87, 340, 74, 36);
        getContentPane().add(lblRole);
        
        cbxRole = new JComboBox<String>();
        cbxRole.setModel(new DefaultComboBoxModel<String>(new String[] {"User", "Admin"}));
        cbxRole.setFont(new Font("Verdana", Font.PLAIN, 12));
        cbxRole.setBounds(200, 339, 404, 39);
        getContentPane().add(cbxRole);
        
        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnCancel.setBackground(Color.CYAN);
        btnCancel.setBounds(401, 409, 85, 33);
        getContentPane().add(btnCancel);
        
        JLabel lblHTn = new JLabel("Họ tên:");
        lblHTn.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblHTn.setBounds(87, 110, 74, 39);
        getContentPane().add(lblHTn);
        
        txtFullName = new JTextField();
        txtFullName.setColumns(10);
        txtFullName.setBounds(200, 111, 404, 39);
        getContentPane().add(txtFullName);
        
        JLabel lblMtKhu = new JLabel("Mật khẩu:");
        lblMtKhu.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblMtKhu.setBounds(87, 259, 74, 39);
        getContentPane().add(lblMtKhu);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 260, 404, 39);
        getContentPane().add(txtPassword);
        
        lblTnngNhp = new JLabel("Tên đăng nhập:");
        lblTnngNhp.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblTnngNhp.setBounds(87, 41, 108, 39);
        getContentPane().add(lblTnngNhp);
        
        txtUsername = new JTextField();
        txtUsername.setColumns(10);
        txtUsername.setBounds(200, 41, 404, 39);
        getContentPane().add(txtUsername);
        this.setTitle(title);
        
        this.setLocationRelativeTo(null);
        
        if ("update".equals(mode) && currentUser != null) {
        	txtUsername.setText(currentUser.getUserName());
        	txtUsername.setEnabled(false);
            txtEmail.setText(currentUser.getUserEmail());
            txtFullName.setText(currentUser.getUserFullName());
            txtPassword.setText(currentUser.getUserPassword());
            cbxRole.setSelectedIndex(currentUser.getIsAdmin());
        }
	}

	public void create() {
	    try {
	    	String username = txtUsername.getText().trim();
	        String email = txtEmail.getText().trim();
	        String fullname = txtFullName.getText().trim();
	        String password = txtPassword.getText();
	        int isAdmin = cbxRole.getSelectedIndex();
	        
	        if (username.isEmpty()) {
	            showMessage("Tên đăng nhập không được để trống.");
	            return;
	        } 
	        if (fullname.isEmpty()) {
	            showMessage("Họ tên không được để trống.");
	            return;
	        }
	        if (email.isEmpty()) {
	            showMessage("Email không được để trống.");
	            return;
	        }
	        if (password.isEmpty()) {
	            showMessage("Mật khẩu không được để trống.");
	            return;
	        }

	        User_DTO newUser = new User_DTO();
	        newUser.setUserName(username);
	        newUser.setUserEmail(email);
	        newUser.setUserFullName(fullname);
	        newUser.setUserPassword(password);
	        newUser.setIsAdmin(isAdmin);

	        boolean isCreated = userBLL.create(newUser);
	        if (isCreated) {
	            showMessage("Thêm user thành công!");
//	            topicPanel.reloadTopics();
	            dispose();
	        } else {
	            showMessage("Thêm user thất bại.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        showMessage("Có lỗi xảy ra. Vui lòng thử lại!");
	    }
	}

	public void update() {
	    try {
	        if (currentUser == null) {
	            showMessage("Không tìm thấy user cần cập nhật.");
	            return;
	        }

	        String email = txtEmail.getText().trim();
	        String fullname = txtFullName.getText().trim();
	        String password = txtPassword.getText();
	        int isAdmin = cbxRole.getSelectedIndex();
	        
	        if (fullname.isEmpty()) {
	            showMessage("Họ tên không được để trống.");
	            return;
	        }
	        if (email.isEmpty()) {
	            showMessage("Email không được để trống.");
	            return;
	        }
	        if (password.isEmpty()) {
	            showMessage("Mật khẩu không được để trống.");
	            return;
	        }
	        
	        User_DTO updatedUser = new User_DTO();
	        updatedUser.setUserName(password);
	        updatedUser.setUserEmail(email);
	        updatedUser.setUserFullName(fullname);
	        updatedUser.setUserPassword(password);
	        updatedUser.setIsAdmin(isAdmin);

	        boolean isUpdated = userBLL.update(currentUser, updatedUser);
	        if (isUpdated) {
	            showMessage("Cập nhật user thành công!");
//	            topicPanel.reloadTopics();
	            dispose();
	        } else {
	            showMessage("Cập nhật user thất bại.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        showMessage("Có lỗi xảy ra. Vui lòng thử lại!");
	    }
	}

	private void showMessage(String message) {
	    JOptionPane.showMessageDialog(this, message);
	}
}
