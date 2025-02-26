package app.GUI.Component.AdminUI;

import java.awt.Color;

import javax.swing.JPanel;

import app.BLL.Question_BLL;
import app.BLL.Topic_BLL;
import app.DTO.Question_DTO;
import app.DTO.Topic_DTO;
import app.DTO.User_DTO;
import app.GUI.AdminManageScreen;
import app.GUI.Component.Dialog.AddRemoveQuestionDialog;
import app.GUI.Component.Dialog.TopicCRUDDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;

public class TopicPanel extends JPanel implements ActionListener {
	private AdminManageScreen main;
	private User_DTO currentUser;
	private ArrayList<Topic_DTO> topics = new ArrayList<Topic_DTO>();
	private Topic_BLL topicBLL;

	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	
	public TopicPanel(AdminManageScreen main, User_DTO admin) {
		this.main= main;
		this.currentUser = admin;
		initComponents();
		
		topicBLL = new Topic_BLL();
		
		topics = topicBLL.getTopics();
		
		loadTableData(topics);
	}
	
	private void loadTableData(ArrayList<Topic_DTO> topics) {
		tableModel.setRowCount(0); 
        
        for (Topic_DTO topic : topics) {
            tableModel.addRow(new Object[]{
                topic.getTpID(), topic.getTpTitle(), topic.getTpParent()
            });
        }
	}
	
	/**
	 * Create the panel.
	 */
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
		panel_1.setBounds(387, 10, 273, 41);
		add(panel_1);
		
		btnCreate = new JButton("Thêm mới");
		btnCreate.setBackground(new Color(255, 255, 255));
		btnCreate.addActionListener(this);
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
		
		tableModel = new DefaultTableModel(new Object[]{"ID", "Tiêu đề", "Topic cha"}, 0);
		table = new JTable(tableModel);
		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
	}
	
	private int getSelectedRow() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(main, "Bạn chưa chọn topic nào", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return index;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCreate) {
            TopicCRUDDialog topicDialog = new TopicCRUDDialog(main, true, "Thêm topic", this, "create", null);
            topicDialog.setVisible(true);
            
            topicBLL = new Topic_BLL();
            topics = topicBLL.getTopics();
            loadTableData(topics);
        } else if(e.getSource() == btnDelete) {
            int index = getSelectedRow();
            
            if (index != -1) {
                if (JOptionPane.showConfirmDialog(main, "Bạn có chắc muốn xóa topic này không?", "", JOptionPane.YES_NO_OPTION) == 0) {
                    topicBLL.delete(topics.get(index).getTpID());
                }
                topics = topicBLL.getTopics();
                loadTableData(topics);
            }
            
        } else {
            int index = getSelectedRow();
            if(index != -1) {
            	TopicCRUDDialog topicDialog = new TopicCRUDDialog(main, true, "Sửa topic", this, "update", topics.get(index));
            	topicDialog.setVisible(true);
            	topics = topicBLL.getTopics();
            	loadTableData(topics);
            }
        }        
    }
}
