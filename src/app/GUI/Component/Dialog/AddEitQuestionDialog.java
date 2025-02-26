package app.GUI.Component.Dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import app.BLL.Question_BLL;
import app.BLL.Topic_BLL;
import app.DTO.Question_DTO;
import app.GUI.Component.AdminUI.QuestionsPanel;
import app.Helper.ComboItem;
import app.Helper.Validator;
import jakarta.persistence.criteria.Path;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.GridLayout;

public class AddEitQuestionDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	QuestionsPanel panelQuestionsPanel;
	private String mode;
	Question_DTO qEdit;
	private Question_BLL qBll = new Question_BLL();
	private Topic_BLL tBLL = new Topic_BLL();
	private JTextField txtQues;
	private JTextField txtLink;
	JComboBox cbbLevel;
	JComboBox<ComboItem> cbbTopic;
	JComboBox cbbStatus;
	List<ComboItem> topicComboItems = new ArrayList<ComboItem>();
	JButton btnAccept;
	Question_DTO newQues;
	 private String imageURL;
	 private int newQuesID;
	 private boolean imageChanged;
	 public javax.swing.JLabel hinhAnhSP;
	 JButton uploadImage = new JButton();
	 JButton btnAcceptTypeAnswer;
	 private JTextField txtQuantityAnswer;
	 private JTextField textField_1;
	 private JTextField textField_2;
	 JPanel answerPanel;
	 Validator validate = new Validator();
	 private JTextField textField;
	 private JTextField textField_3;
	 JPanel answerTextPanel;
	 private List<JTextField> textFieldList = new ArrayList<>();
	 private List<ButtonGroup> buttonGroupList = new ArrayList<>();
	 ButtonGroup typeAnswers;
	 JPanel answerImagePanel;

	public AddEitQuestionDialog(java.awt.Frame parent, boolean modal, String title, QuestionsPanel panelQuestion, String mode, Question_DTO spEdit) {
        super(parent, modal);
        initComponents();
        this.setTitle(title);
        this.panelQuestionsPanel = panelQuestionsPanel;
        this.mode = mode;
        this.qEdit = spEdit;
        initComponentsCustom();
        this.setLocationRelativeTo(null);
        answerPanel.setVisible(false);
        btnAccept.addActionListener( new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		  openPanelAddAnswer();
        		  } 
        		} );
        
        btnAcceptTypeAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openInsertAnswerPanel(answerTextPanel);
			}
		});
    }

	private void initComponents() {
		// TODO Auto-generated method stub
		setSize(700,600);
		try {
			topicComboItems = tBLL.getChilTopic();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Nội dung");
        lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblNewLabel.setBounds(87, 69, 74, 27);
        getContentPane().add(lblNewLabel);
        
        JLabel lblLinknh = new JLabel("Link ảnh");
        lblLinknh.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblLinknh.setBounds(87, 132, 74, 27);
        getContentPane().add(lblLinknh);
        
        JLabel lblNewLabel_1_1 = new JLabel("Topic");
        lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblNewLabel_1_1.setBounds(87, 235, 74, 27);
        getContentPane().add(lblNewLabel_1_1);
        
        if(mode != null) {
        	JLabel lblNewLabel_1_1_1 = new JLabel("Trạng thái");
            lblNewLabel_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 12));
            lblNewLabel_1_1_1.setBounds(87, 290, 74, 27);
            getContentPane().add(lblNewLabel_1_1_1);
            cbbStatus = new JComboBox();
            cbbStatus.addItem(1);
            cbbStatus.addItem(0);
            cbbStatus.setFont(new Font("Verdana", Font.PLAIN, 12));
            cbbStatus.setBounds(200, 284, 275, 33);
            getContentPane().add(cbbStatus);
        }
        
        txtQues = new JTextField();
        txtQues.setBounds(200, 64, 404, 39);
        getContentPane().add(txtQues);
        txtQues.setColumns(10);
        
        txtLink = new JTextField();
        txtLink.setColumns(10);
        txtLink.setBounds(200, 120, 312, 39);
        getContentPane().add(txtLink);
        
        btnAccept = new JButton("Xác nhận");
        btnAccept.setBackground(Color.CYAN);
        btnAccept.setBounds(283, 327, 117, 21);
        getContentPane().add(btnAccept);
        
        JButton uploadImage = new JButton("Upload");
        uploadImage.setBounds(519, 118, 85, 39);
        getContentPane().add(uploadImage);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Mức độ");
        lblNewLabel_1_1_2.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblNewLabel_1_1_2.setBounds(87, 180, 74, 27);
        getContentPane().add(lblNewLabel_1_1_2);
        
        cbbLevel = new JComboBox();
        cbbLevel.setModel(new DefaultComboBoxModel(new String[] {"easy", "medium", "difficult"}));
        cbbLevel.setFont(new Font("Verdana", Font.PLAIN, 12));
        cbbLevel.setBounds(200, 180, 275, 27);
        getContentPane().add(cbbLevel);
        
        cbbTopic = new JComboBox<ComboItem>();
        for(ComboItem item: topicComboItems) {
        	cbbTopic.addItem(item);
        }
        cbbTopic.setFont(new Font("Verdana", Font.PLAIN, 12));
        cbbTopic.setBounds(200, 227, 275, 33);
        getContentPane().add(cbbTopic);
        
        
        
        answerPanel = new JPanel();
        answerPanel.setBackground(Color.WHITE);
        answerPanel.setBounds(34, 371, 630, 182);
        getContentPane().add(answerPanel);
        answerPanel.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(10, 10, 610, 29);
        answerPanel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Số lượng đáp án");
        lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 10));
        lblNewLabel_1.setBounds(10, 10, 93, 13);
        panel_1.add(lblNewLabel_1);
        
        txtQuantityAnswer = new JTextField();
        txtQuantityAnswer.setBounds(113, 4, 42, 26);
        panel_1.add(txtQuantityAnswer);
        txtQuantityAnswer.setColumns(4);
        
        JLabel lblNewLabel_1_2 = new JLabel("Loại đáp án");
        lblNewLabel_1_2.setFont(new Font("Verdana", Font.PLAIN, 10));
        lblNewLabel_1_2.setBounds(233, 10, 91, 13);
        panel_1.add(lblNewLabel_1_2);
        typeAnswers = new ButtonGroup();
        
        JRadioButton rdbtnNewRadioButton = new JRadioButton("Dạng chữ");
        rdbtnNewRadioButton.setSelected(true);
        typeAnswers.add(rdbtnNewRadioButton);
        rdbtnNewRadioButton.setFont(new Font("Verdana", Font.PLAIN, 10));
        rdbtnNewRadioButton.setBackground(Color.WHITE);
        rdbtnNewRadioButton.setBounds(332, 6, 83, 21);
        panel_1.add(rdbtnNewRadioButton);
        
        JRadioButton rdbtnHnhnh = new JRadioButton("HÌnh Ảnh");
        typeAnswers.add(rdbtnHnhnh);
        rdbtnHnhnh.setFont(new Font("Verdana", Font.PLAIN, 10));
        rdbtnHnhnh.setBackground(Color.WHITE);
        rdbtnHnhnh.setBounds(424, 6, 83, 21);
        panel_1.add(rdbtnHnhnh);
        
        btnAcceptTypeAnswer = new JButton("Xác nhận");
        btnAcceptTypeAnswer.setFont(new Font("Verdana", Font.PLAIN, 10));
        btnAcceptTypeAnswer.setBackground(Color.WHITE);
        btnAcceptTypeAnswer.setBounds(517, 6, 93, 21);
        panel_1.add(btnAcceptTypeAnswer);
        
        answerTextPanel = new JPanel();
        answerTextPanel.setBackground(Color.WHITE);
        answerTextPanel.setBounds(20, 49, 296, 123);
        answerPanel.add(answerTextPanel);
        answerTextPanel.setLayout(new GridLayout(5, 1, 10, 0));
        
        
        
        
        answerImagePanel = new JPanel();
        answerImagePanel.setBackground(Color.WHITE);
        answerImagePanel.setBounds(318, 49, 302, 123);
        answerPanel.add(answerImagePanel);
        answerImagePanel.setLayout(new GridLayout(5, 1, 10, 0));
        
        
		
	}
	
	public Question_DTO getNewQues() {
        String content = txtQues.getText();
        String pictureLink = addImage(imageURL);
        String level = (String) cbbLevel.getSelectedItem();
        ComboItem selectedItem = (ComboItem) cbbTopic.getSelectedItem();
        int topicID = selectedItem.getKey();
        
        return new Question_DTO(content,pictureLink,topicID,level,1);
    }
	
	public String addImage(String imageURL) {
        Random randomGenerator = new Random();
        int rand = randomGenerator.nextInt(1000);
        File sourceFile = new File(imageURL);
        String destPath = "./src/image/questions";
        File destFolder = new File(destPath);
        String newName = rand + sourceFile.getName();
        try {
            Path dest = (Path) Paths.get(destFolder.getPath(), newName);
            Files.copy(sourceFile.toPath(), (OutputStream) dest);
            if (mode.equals("edit")) {
                Path oldDest = (Path) Paths.get(destPath, this.qEdit.getqPicture());
                Files.delete((java.nio.file.Path) oldDest);
            }
        } catch (IOException e) {
        }
        return newName;
    }
	
	public void addQuestionsEvent() {
        this.newQues = getNewQues();
        if (qBll.add(newQues)) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công !");
            dispose();
        }
    }
	
	 public void editSPEvent() {
	        setEditedQuestion();
	        if(qBll.edit(qEdit)) {
	            JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công !");
	            dispose();
	        }
	    }

	private void setEditedQuestion() {
		// TODO Auto-generated method stub
		
        this.qEdit.setqContent(txtQues.getText());
        if (imageChanged) {
            this.qEdit.setqPicture(imageURL);
        }
        this.qEdit.setqLevel(cbbLevel.getSelectedItem().toString());
        ComboItem selectedItem = (ComboItem) cbbTopic.getSelectedItem();
        this.qEdit.setqTopicID(selectedItem.getKey());
        this.qEdit.getqStatus();
	    
	}
	
	public void openPanelAddAnswer() {
		if(txtQues.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng không bỏ trống nội dung câu hỏi");
		}else {
			answerPanel.setVisible(true);
			txtQues.setEditable(false);
		}
	}
	
	public String getSelectedButtonText(ButtonGroup group) {
	    for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
	        AbstractButton button = buttons.nextElement();
	        if (button.isSelected()) {
	            return button.getText(); 
	        }
	    }
	    return "";
	}

	
	public void openInsertAnswerPanel(JPanel answerTextPanel) {
		if(Validator.isEmpty(txtQuantityAnswer.getText())) {
			JOptionPane.showMessageDialog(this, "Vui lòng không bỏ trống số lượng câu hỏi");
		} else if(!Validator.isInteger(txtQuantityAnswer.getText())) {
			JOptionPane.showMessageDialog(this, "Vui lòng điền số nguyên");
		} else if(Integer.parseInt(txtQuantityAnswer.getText()) > 5 || Integer.parseInt(txtQuantityAnswer.getText()) < 2){
			JOptionPane.showMessageDialog(this, "Số lượng đáp án từ 2-5");
		}
		else{
			txtQuantityAnswer.setEditable(false);
			answerImagePanel.removeAll();
			answerTextPanel.removeAll();
			int num = Integer.parseInt(txtQuantityAnswer.getText());
			System.out.println(getSelectedButtonText(typeAnswers).equals("Đúng"));
			if(getSelectedButtonText(typeAnswers).equals("Dạng chữ")) {
			//	Set Visible cho panel
				createPanelAnswerTextItem(answerTextPanel,num);
				answerImagePanel.setVisible(false);
				answerTextPanel.setVisible(true);
				
			} else {
				createPanelAnswerImageItem(answerImagePanel,num);
				answerTextPanel.setVisible(false);
				answerImagePanel.setVisible(true);
				
			};
		}
	}
	public void initComponentsCustom() {

		hinhAnhSP = new javax.swing.JLabel();
        
		newQuesID = qBll.q_DAL.getAutoIncrement();
        uploadImage.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                JFileChooser jfc;
                jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and GIF images", "png", "gif", "jpg", "jpeg");
                jfc.addChoosableFileFilter(filter);
                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    imageURL = (String) jfc.getSelectedFile().getPath();
                    File file = jfc.getSelectedFile();
                    ImageIcon imageIcon = new ImageIcon(new javax.swing.ImageIcon(String.valueOf(jfc.getSelectedFile())).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                    BufferedImage b;
                    try {
                        b = ImageIO.read(file);
                        hinhAnhSP.setIcon(imageIcon);
                    } catch (IOException ex) {
                        Logger.getLogger(AddEitQuestionDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (mode.equals("edit")) {
                    imageChanged = true;
                }
            }

        });

        if (mode.equals("edit")) {
        	answerPanel.setVisible(false);
            imageURL = "/image/product/" + this.qEdit.getqPicture();
//            hinhAnhSP.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/image/question/" + this.qEdit.getqPicture())).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
            txtQues.setText(this.qEdit.getqContent());
            cbbLevel.setSelectedItem(this.qEdit.getqLevel());
            cbbTopic.setSelectedItem(this.qEdit.getqTopicID());
            txtLink.setText(this.qEdit.getqPicture());
            cbbStatus.setSelectedItem(qEdit.getqStatus());
            txtQues.setEnabled(false);
            cbbTopic.setEnabled(false);
            btnAccept.setText("Lưu thông tin");
        }
    }
	public void createPanelAnswerTextItem(JPanel parent, int qty) {
		for(int i=0 ; i < qty; i++) {
		JPanel answerItemPanel_1 = new JPanel();
        answerItemPanel_1.setLayout(null);
        answerItemPanel_1.setBackground(Color.WHITE);
        parent.add(answerItemPanel_1);
        
        JLabel lblNewLabel_2_1 = new JLabel("Đáp án:");
        lblNewLabel_2_1.setBounds(10, 10, 45, 13);
        answerItemPanel_1.add(lblNewLabel_2_1);
        
        JTextField textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(62, 4, 71, 19);
        answerItemPanel_1.add(textField);
//        Add to textFieldList
        textFieldList.add(textField);
        
        
        ButtonGroup groupIsRight = new ButtonGroup();
        
        JRadioButton radioIsRight_1 = new JRadioButton("Đúng");
        groupIsRight.add(radioIsRight_1);
        radioIsRight_1.setSelected(true);
        radioIsRight_1.setFont(new Font("Verdana", Font.PLAIN, 10));
        radioIsRight_1.setBackground(Color.WHITE);
        radioIsRight_1.setBounds(139, 6, 62, 17);
        answerItemPanel_1.add(radioIsRight_1);
        
        JRadioButton rdbtnNewRadioButton_1_1_1 = new JRadioButton("Sai");
        groupIsRight.add(rdbtnNewRadioButton_1_1_1);
        rdbtnNewRadioButton_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 10));
        rdbtnNewRadioButton_1_1_1.setBackground(Color.WHITE);
        rdbtnNewRadioButton_1_1_1.setBounds(212, 6, 62, 17);
        answerItemPanel_1.add(rdbtnNewRadioButton_1_1_1);
        
        buttonGroupList.add(groupIsRight);
		}
		 parent.revalidate(); 
	        parent.repaint();
	}
	
	public void createPanelAnswerImageItem(JPanel parent, int qty) {
		
		for(int i=0 ; i < qty; i++) {
			
			JPanel answerItemImagePanel = new JPanel();
	        answerItemImagePanel.setBackground(Color.WHITE);
	        answerImagePanel.add(answerItemImagePanel);
	        answerItemImagePanel.setLayout(null);
	        
	        textField_2 = new JTextField();
	        textField_2.setBounds(10, 0, 176, 19);
	        answerItemImagePanel.add(textField_2);
	        textField_2.setColumns(10);
	        
	        textFieldList.add(textField_2);
	        
	        JButton btnNewButton_1 = new JButton("Chọn ảnh");
	        btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 10));
	        btnNewButton_1.setBackground(Color.WHITE);
	        btnNewButton_1.setBounds(207, -1, 85, 21);
	        parent.add(btnNewButton_1);
		}
	        parent.revalidate(); 
	        parent.repaint();
	}
}
