package app.GUI.Component.AdminUI;

import app.BLL.Dashboard_BLL;
import app.DTO.Dashboard_DTO;
import javax.swing.JPanel;

import app.DTO.User_DTO;
import app.GUI.AdminManageScreen;
import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DashboardPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
private AdminManageScreen main;
	private User_DTO currentUser;
	private ArrayList<Dashboard_DTO> Dashboard = new ArrayList<Dashboard_DTO>();
	private Dashboard_BLL dashboardBLL;
	private JTextField txtSearch;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
        JDateChooser dateChooserfrom = new JDateChooser();
        JDateChooser dateChooserto = new JDateChooser();
	public DashboardPanel(AdminManageScreen main, User_DTO admin) {
		setBackground(Color.ORANGE);
		this.main= main;
		this.currentUser = admin;
		initComponents();
//		initComponents();
                dashboardBLL = new Dashboard_BLL();
		Dashboard = dashboardBLL.getDashboard();
		loadTableData(Dashboard);
                
	}

public void initComponents() {
		setSize(738,563);
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 10, 273, 41);
		add(panel);
		panel.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setBackground(new Color(255, 255, 255));
		txtSearch.setBounds(10, 10, 253, 21);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(387, 10, 273, 50);
		add(panel_1);
		
		JLabel lblNewLabel_3 = new JLabel("từ ngày");
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(10, 10, 93, 13);
		panel_1.add(lblNewLabel_3);
                
                
		dateChooserfrom.setBounds(0, 7, 200, 19);
//                dateChooserfrom.setDate(new Date());
                panel_1.add(dateChooserfrom);
		
                JLabel lblNewLabel_4 = new JLabel("đến ngày");
		lblNewLabel_4.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(10, 10, 93, 13);
		panel_1.add(lblNewLabel_4);
               
		dateChooserto.setBounds(0, 7, 200, 19);
//                dateChooserto.setDate(new Date());
                panel_1.add(dateChooserto);
                
                btnCreate = new JButton("Lọc");
		btnCreate.setBackground(new Color(255, 255, 255));
                btnCreate.setBounds(250, 30, 50, 19);
		btnCreate.addActionListener(this);
		panel_1.add(btnCreate);
                
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 61, 718, 398);
		add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 718, 398);
		panel_2.add(scrollPane);
		
		tableModel = new DefaultTableModel(new Object[]{"Mã bài thi", "SL dự thi", "SL đạt","SL không đạt"}, 0);
		table = new JTable(tableModel);
		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
                

	}
	private void loadTableData(ArrayList<Dashboard_DTO> dbs) {
		tableModel.setRowCount(0); 
        
        for (Dashboard_DTO db : dbs) {
            tableModel.addRow(new Object[]{
                db.gettestcode(), db.getSLduthi(), db.getSLdat(),db.getSLkodat()
            });
        }
	}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCreate) {
            if(dateChooserfrom.getDate()==null||dateChooserto.getDate()==null){
                JOptionPane.showMessageDialog(null, "Bạn chưa nhập ngày tháng để lọc!");
            }
            else if(dateChooserfrom.getDate().compareTo(dateChooserto.getDate())>0){
                JOptionPane.showMessageDialog(null, "Ngày tháng bạn nhập chưa hợp lệ!");
            }
            else{
                dashboardBLL = new Dashboard_BLL();
		Dashboard = dashboardBLL.getDashboardfilter(txtSearch.getText(),dateChooserfrom.getDate(),dateChooserto.getDate());
		loadTableData(Dashboard);
            }
        }   
    }

}
