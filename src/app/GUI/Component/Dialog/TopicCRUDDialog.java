package app.GUI.Component.Dialog;

import javax.swing.JDialog;
import app.BLL.Topic_BLL;
import app.DTO.Topic_DTO;
import app.GUI.Component.AdminUI.TopicPanel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TopicCRUDDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private TopicPanel topicPanel;
	private String mode;
	private Topic_DTO updatedTopic;
	private Topic_BLL topicBLL = new Topic_BLL();
	private JTextField txtTitle;
	private JComboBox cbxParentTopic;
	private JButton btnCreate;

	public TopicCRUDDialog(Frame parent, boolean modal, String title, TopicPanel topicPanel, String mode, Topic_DTO updatedTopic) {
        super(parent, modal);
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);
        
        JLabel lblTitle = new JLabel("Tên:");
        lblTitle.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblTitle.setBounds(87, 143, 74, 27);
        getContentPane().add(lblTitle);
        
        txtTitle = new JTextField();
        txtTitle.setBounds(200, 138, 404, 39);
        getContentPane().add(txtTitle);
        txtTitle.setColumns(10);
        
        btnCreate = new JButton("Xác nhận");
        btnCreate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if ("create".equals(mode)) {
        			createTopic();        			
        		} else {
        			updateTopic();
        		}
        	}
        });
        btnCreate.setBackground(Color.CYAN);
        btnCreate.setBounds(507, 288, 97, 33);
        getContentPane().add(btnCreate);
        
        JLabel lblParentTopic = new JLabel("Topic cha:");
        lblParentTopic.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblParentTopic.setBounds(87, 216, 74, 27);
        getContentPane().add(lblParentTopic);
        
        cbxParentTopic = new JComboBox();
        cbxParentTopic.setFont(new Font("Verdana", Font.PLAIN, 12));
        cbxParentTopic.setBounds(202, 210, 404, 39);
        getContentPane().add(cbxParentTopic);
        
        JButton btnCancel = new JButton("Hủy");
        btnCancel.setBackground(Color.CYAN);
        btnCancel.setBounds(393, 288, 85, 33);
        getContentPane().add(btnCancel);
        this.setTitle(title);
        this.topicPanel = topicPanel;
        this.mode = mode;
        this.updatedTopic = updatedTopic;
        initComponents();
        this.setLocationRelativeTo(null);
        
        loadTopicCombobox();
        
        if ("update".equals(mode) && updatedTopic != null) {
            txtTitle.setText(updatedTopic.getTpTitle());

            for (int i = 0; i < cbxParentTopic.getItemCount(); i++) {
                Object item = cbxParentTopic.getItemAt(i);
                
                if (item instanceof String && updatedTopic.getTpParent() == 0) {
                    cbxParentTopic.setSelectedIndex(i);
                    break;
                }

                if (item instanceof Topic_DTO topic && updatedTopic.getTpParent() == topic.getTpID()) {
                    cbxParentTopic.setSelectedIndex(i);
                    break;
                }
            }
        }

    }

	private void initComponents() {
		// TODO Auto-generated method stub
		setSize(700,600);
	}
	
	private Topic_DTO getSelectedParentTopic() {
	    Object selectedItem = cbxParentTopic.getSelectedItem();
	    if (selectedItem instanceof Topic_DTO topic) {
	        return topic;
	    }
	    return null;
	}

	
	public void createTopic() {
	    try {
	        String title = txtTitle.getText().trim();
	        Topic_DTO parentTopic = getSelectedParentTopic();
	        int parentId = (parentTopic != null) ? parentTopic.getTpID() : 0;

	        if (title.isEmpty()) {
	            showMessage("Tên topic không được để trống.");
	            return;
	        }

	        Topic_DTO newTopic = new Topic_DTO();
	        newTopic.setTpTitle(title);
	        newTopic.setTpParent(parentId);

	        boolean isCreated = topicBLL.create(newTopic);
	        if (isCreated) {
	            showMessage("Tạo topic thành công!");
//	            topicPanel.reloadTopics();
	            dispose();
	        } else {
	            showMessage("Tạo topic thất bại.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        showMessage("Đã xảy ra lỗi khi tạo topic.");
	    }
	}

	public void updateTopic() {
	    try {
	        if (updatedTopic == null) {
	            showMessage("Không tìm thấy topic cần cập nhật.");
	            return;
	        }

	        String title = txtTitle.getText().trim();
	        Topic_DTO parentTopic = getSelectedParentTopic();
	        int parentId = (parentTopic != null) ? parentTopic.getTpID() : null;

	        if (title.isEmpty()) {
	            showMessage("Tên topic không được để trống.");
	            return;
	        }

	        updatedTopic.setTpTitle(title);
	        updatedTopic.setTpParent(parentId);

	        boolean isUpdated = topicBLL.update(updatedTopic);
	        if (isUpdated) {
	            showMessage("Cập nhật topic thành công!");
//	            topicPanel.reloadTopics();
	            dispose();
	        } else {
	            showMessage("Cập nhật topic thất bại.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        showMessage("Đã xảy ra lỗi khi cập nhật topic.");
	    }
	}

	private void showMessage(String message) {
	    javax.swing.JOptionPane.showMessageDialog(this, message);
	}

	private void loadTopicCombobox() {
	    cbxParentTopic.removeAllItems();
	    cbxParentTopic.addItem("Không có topic cha");

	    ArrayList<Topic_DTO> topics = topicBLL.getTopics();
	    
	    for (Topic_DTO topic : topics) {
	        cbxParentTopic.addItem(topic);
	    }
	}

}
