package app.GUI.Component.Dialog;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;

import app.BLL.Question_BLL;
import app.DTO.Question_DTO;
import app.GUI.Component.AdminUI.QuestionsPanel;
import jakarta.persistence.criteria.Path;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddRemoveQuestionDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	QuestionsPanel panelQuestionsPanel;
	private String mode;
	Question_DTO qEdit;
	private Question_BLL qBll = new Question_BLL();
	private JTextField txtQues;
	private JTextField txtLink;
	private JTextField txtTopic;
	private JTextField txtStatus;
	JComboBox cbbLevel;
	JButton btnAccept;
	Question_DTO newQues;
	 private String imageURL;
	 private int newQuesID;
	 private boolean imageChanged;
	 public javax.swing.JLabel hinhAnhSP;
	 JButton uploadImage = new JButton();

	public AddRemoveQuestionDialog(java.awt.Frame parent, boolean modal, String title, QuestionsPanel panelQuestion, String mode, Question_DTO spEdit) {
        super(parent, modal);
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
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Trạng thái");
        lblNewLabel_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblNewLabel_1_1_1.setBounds(87, 290, 74, 27);
        getContentPane().add(lblNewLabel_1_1_1);
        
        txtQues = new JTextField();
        txtQues.setBounds(200, 64, 404, 39);
        getContentPane().add(txtQues);
        txtQues.setColumns(10);
        
        txtLink = new JTextField();
        txtLink.setColumns(10);
        txtLink.setBounds(200, 120, 312, 39);
        getContentPane().add(txtLink);
        
        txtTopic = new JTextField();
        txtTopic.setColumns(10);
        txtTopic.setBounds(200, 223, 404, 39);
        getContentPane().add(txtTopic);
        
        txtStatus = new JTextField();
        txtStatus.setColumns(10);
        txtStatus.setBounds(200, 274, 404, 39);
        getContentPane().add(txtStatus);
        
        btnAccept = new JButton("Xác nhận");
        btnAccept.setBackground(Color.CYAN);
        btnAccept.setBounds(285, 367, 85, 21);
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
        this.setTitle(title);
        this.panelQuestionsPanel = panelQuestionsPanel;
        this.mode = mode;
        this.qEdit = spEdit;
        initComponents();
        initComponentsCustom();
        this.setLocationRelativeTo(null);
    }

	private void initComponents() {
		// TODO Auto-generated method stub
		setSize(700,600);
		
	}
	
	public Question_DTO getNewQues() {
        String content = txtQues.getText();
        String pictureLink = addImage(imageURL);
        String level = (String) cbbLevel.getSelectedItem();
        int topicID = Integer.parseInt(txtTopic.getText());
        
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
        this.qEdit.setqTopicID(Integer.parseInt(txtTopic.getText()));
        this.qEdit.getqStatus();
	    
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
                        Logger.getLogger(AddRemoveQuestionDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (mode.equals("edit")) {
                    imageChanged = true;
                }
            }

        });

        if (mode.equals("edit")) {
            imageURL = "/image/product/" + this.qEdit.getqPicture();
//            hinhAnhSP.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/image/question/" + this.qEdit.getqPicture())).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
            txtQues.setText(this.qEdit.getqContent());
            txtTopic.setText(Float.toString(this.qEdit.getqTopicID()));
            txtLink.setText(this.qEdit.getqPicture());
            txtStatus.setText(Integer.toString(qEdit.getqStatus()));
            txtQues.setEnabled(false);
            txtTopic.setEnabled(false);
            btnAccept.setText("Lưu thông tin");
        }
    }
}
