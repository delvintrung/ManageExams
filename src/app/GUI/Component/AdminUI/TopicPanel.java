package app.GUI.Component.AdminUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import app.BLL.Topic_BLL;
import app.DTO.Topic_DTO;
import app.DTO.User_DTO;
import app.GUI.AdminManageScreen;
import app.GUI.Component.Dialog.TopicCRUDDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        for (int i = 0; i < table.getColumnCount(); i++) {
        	table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
	}
	
	public void initComponents() {
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
		
		tableModel = new DefaultTableModel(new Object[]{"ID", "Tiêu đề", "Topic cha (ID)"}, 0) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		table = new JTable(tableModel);
		table.setBackground(new Color(255, 255, 255));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
	
	public void search() {
		String search = txtSearch.getText();
		ArrayList<Topic_DTO> searchList = topicBLL.search(search);
		loadTableData(searchList);
	}
}
