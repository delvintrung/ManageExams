package app.GUI.Component.AdminUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import app.BLL.User_BLL;
import app.DTO.User_DTO;
import app.GUI.AdminManageScreen;
import app.GUI.Component.Dialog.UserCRUDDialog;

import java.awt.GridLayout;

public class UserPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	
	private AdminManageScreen main;
	private User_DTO currentUser;
	private ArrayList<User_DTO> users;
	private User_BLL userBLL;

	public UserPanel(AdminManageScreen main, User_DTO admin) {
		this.main = main;
		this.currentUser = admin;
		users = new ArrayList<User_DTO>();
		userBLL = new User_BLL();
		
		initComponents();
		
		users = userBLL.getUsers();
		
		loadTableData(users);
	}
	
	private void loadTableData(ArrayList<User_DTO> users) {
		tableModel.setRowCount(0); 
        
        for (User_DTO user : users) {
            tableModel.addRow(new Object[]{
                user.getUserID(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserFullName(),
                user.getIsAdmin() == 1 ? "Admin" : "User"
            });
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        for (int i = 0; i < table.getColumnCount(); i++) {
        	table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
	}
	
	private void initComponents() {
		setSize(738,563);
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 10, 350, 41);
		add(panel);
		panel.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});
		txtSearch.setBackground(new Color(255, 255, 255));
		txtSearch.setBounds(74, 10, 261, 23);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm:");
		lblNewLabel.setBounds(10, 10, 54, 23);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(387, 10, 310, 41);
		add(panel_1);
		
		btnCreate = new JButton("Thêm mới");
		btnCreate.setBackground(new Color(255, 255, 255));
		btnCreate.addActionListener(this);
		panel_1.setLayout(new GridLayout(0, 3, 6, 0));
		panel_1.add(btnCreate);
		
		btnUpdate = new JButton("Sửa");
		btnUpdate.setBackground(new Color(255, 255, 255));
		btnUpdate.addActionListener(this);
		panel_1.add(btnUpdate);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setBackground(new Color(255, 255, 255));
		btnDelete.addActionListener(this);
		panel_1.add(btnDelete);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 61, 718, 398);
		add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 718, 398);
		panel_2.add(scrollPane);
		
		tableModel = new DefaultTableModel(new Object[]{"ID", "Tên đăng nhập", "Email", "Họ tên", "Role"}, 0) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		table = new JTable(tableModel);
		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
	}
	
	private int getSelectedRow() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(main, "Bạn chưa chọn user nào", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return index;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int index = getSelectedRow();
		
        if (e.getSource() == btnCreate) {
            UserCRUDDialog userDialog = new UserCRUDDialog(main, true, "Thêm user", this, "create", null);
            userDialog.setVisible(true);
            
            users = userBLL.getUsers();
            loadTableData(users);
        } else if (e.getSource() == btnDelete) {
            if (index != -1) {
            	if (users.get(index).getIsAdmin() == 1) {
            		JOptionPane.showMessageDialog(this, "Không thể xóa admin");
            		return;
            	}
            	
                if (JOptionPane.showConfirmDialog(main, "Bạn có chắc muốn xóa user này không?", "", JOptionPane.YES_NO_OPTION) == 0) {
                    userBLL.delete(users.get(index).getUserID());
                }
                
                users = userBLL.getUsers();
                loadTableData(users);
            }
        } else {         
            if (index != -1) {
            	if (users.get(index).getUserName().equals("admin")) {
            		JOptionPane.showMessageDialog(this, "Không thể sửa thông tin admin gốc của hệ thống");
            		return;
            	}
            	
            	UserCRUDDialog userDialog = new UserCRUDDialog(main, true, "Sửa user", this, "update", users.get(index));
            	userDialog.setVisible(true);
            	
            	users = userBLL.getUsers();
                loadTableData(users);
            }
        }        
    }
	
	public void search() {
		String search = txtSearch.getText();
		ArrayList<User_DTO> searchList = userBLL.search(search);
		loadTableData(searchList);
	}
}