package app.GUI.Component.Dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.BLL.Answer_BLL;
import app.BLL.User_BLL;
import app.DTO.Answer_DTO;
import app.DTO.User_DTO;
import app.GUI.Component.AdminUI.AnswerPanel;
import app.GUI.Component.AdminUI.UserPanel;

public class AnswerCRUDDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton btnSave;
	
	private Answer_DTO currentAnswer;
	private Answer_BLL ans_BLL;
	private JTextField txtQues;
	private JLabel lblTnngNhp;
	private JComboBox cbbTrueFalse;
	private JComboBox cbbSelectQuestion;
	private JComboBox cbbStatus;

	public AnswerCRUDDialog(Frame parent, boolean modal, String title, AnswerPanel userPanel, String mode,  Answer_DTO currentAnswer) {
		super(parent, modal);
		
		this.currentAnswer = currentAnswer;
		ans_BLL = new Answer_BLL();
		
		setSize(700,600);
		getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);
        
        JLabel lblTitle = new JLabel("Đúng/Sai:");
        lblTitle.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblTitle.setBounds(87, 182, 103, 39);
        getContentPane().add(lblTitle);
        
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
        
        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnCancel.setBackground(Color.CYAN);
        btnCancel.setBounds(401, 409, 85, 33);
        getContentPane().add(btnCancel);
        
        JLabel lblHTn = new JLabel("Nội dung");
        lblHTn.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblHTn.setBounds(87, 110, 74, 39);
        getContentPane().add(lblHTn);
        
        txtQues = new JTextField();
        txtQues.setColumns(10);
        txtQues.setBounds(200, 111, 404, 39);
        getContentPane().add(txtQues);
        
        lblTnngNhp = new JLabel("Câu hỏi:");
        lblTnngNhp.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblTnngNhp.setBounds(87, 41, 108, 39);
        getContentPane().add(lblTnngNhp);
        
        cbbTrueFalse = new JComboBox();
        cbbTrueFalse.setModel(new DefaultComboBoxModel(new String[] {"Đúng", "Sai"}));
        cbbTrueFalse.setSelectedIndex(1);
        cbbTrueFalse.setFont(new Font("Verdana", Font.PLAIN, 10));
        cbbTrueFalse.setBounds(199, 182, 204, 31);
        getContentPane().add(cbbTrueFalse);
        
        cbbSelectQuestion = new JComboBox();
        cbbSelectQuestion.setFont(new Font("Verdana", Font.PLAIN, 10));
        cbbSelectQuestion.setBounds(200, 49, 404, 31);
        getContentPane().add(cbbSelectQuestion);
        
        JLabel lblTrngThi = new JLabel("Trạng thái:");
        lblTrngThi.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblTrngThi.setBounds(87, 255, 103, 39);
        getContentPane().add(lblTrngThi);
        
        cbbStatus = new JComboBox();
        cbbStatus.setModel(new DefaultComboBoxModel(new String[] {"Ẩn", "Hiện"}));
        cbbStatus.setSelectedIndex(1);
        cbbStatus.setFont(new Font("Verdana", Font.PLAIN, 10));
        cbbStatus.setBounds(199, 263, 204, 31);
        getContentPane().add(cbbStatus);
        this.setTitle(title);
        
        this.setLocationRelativeTo(null);
        
        if ("update".equals(mode) && currentAnswer != null) {
        	txtQues.setText(currentAnswer.getAwContent());
//            cbbSelectQuestion.setSelectedIndex(currentAnswer.getqID());
        	cbbSelectQuestion.setEditable(false);
            cbbTrueFalse.setSelectedIndex(currentAnswer.getIsRight() == 1 ? 1 : 0);
            cbbStatus.setSelectedIndex(currentAnswer.getAwStatus() == 1? 0 : 1);
        }
	}

	public void create() {
	    try {
	    	String txtQuestion = txtQues.getText().trim();
//	    	int idQues = cbbSelectQuestion.getSelectedItem();
	    	int isTrue = cbbTrueFalse.getSelectedIndex();
	        
	        if (txtQuestion.isEmpty()) {
	            showMessage("Nội dung câu hỏi không được để trống.");
	            return;
	        }

	        Answer_DTO newAns = new Answer_DTO();
	        newAns.setAwContent(txtQuestion);
	        newAns.setAwPictures("");
	        newAns.setIsRight(isTrue);
//	        newAns.setqID(password);

	        boolean isCreated = ans_BLL.create(newAns);
	        if (isCreated) {
	            showMessage("Thêm đáp án thành công!");
//	            topicPanel.reloadTopics();
	            dispose();
	        } else {
	            showMessage("Thêm đáp án thất bại.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        showMessage("Có lỗi xảy ra. Vui lòng thử lại!");
	    }
	}

	public void update() {
	    try {
	        if (currentAnswer == null) {
	            showMessage("Không tìm thấy câu hỏi cần cập nhật.");
	            return;
	        }

	        String txtQuestion = txtQues.getText().trim();
	        int isTrue = cbbTrueFalse.getSelectedIndex();
	        
	        if (txtQuestion.isEmpty()) {
	            showMessage("Nội dung đáp án không được để trống.");
	            return;
	        }
	        
	        Answer_DTO updatedAnswer = new Answer_DTO();
	        updatedAnswer.setAwContent(txtQuestion);
	        updatedAnswer.setIsRight(isTrue);

	        boolean isUpdated = ans_BLL.update(updatedAnswer);
	        if (isUpdated) {
	            showMessage("Cập nhật đáp án thành công!");
//	            topicPanel.reloadTopics();
	            dispose();
	        } else {
	            showMessage("Cập nhật đáp án thất bại.");
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
