	package app.GUI;
	
	import java.awt.CardLayout;
	import java.awt.Font;
	import java.awt.GridLayout;
	import java.sql.SQLException;
	
	import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JRadioButton;
	import javax.swing.JTextArea;
	import javax.swing.border.EmptyBorder;
	
	import app.DAL.Log_DAL;
	import app.DTO.User_DTO;
	import app.GUI.Component.CountdownTimer;
import app.Helper.QuestionItemInList;

import java.awt.Color;
	import javax.swing.SwingConstants;
	import javax.swing.JList;
import javax.swing.JScrollPane;
	
	public class ExamScreen extends JFrame {
	
		private static final long serialVersionUID = 1L;
		private JPanel cardPanel; 
		private JPanel cardPanel_1;
	    private CardLayout cardLayout;
	    private int currentQuestion = 0;
	    private String[] questions;
	    
	    private String[][] options ;
	
	    private String[] correctAnswers;
	    private String[] userAnswers;
	    private User_DTO currentUser;
	    private String currentExCode;
	    private DefaultListModel<String> listModel;
	    private JList<String> listCauHoi;
	
	    public ExamScreen(User_DTO currentUser,String[] questions, String[][] options, String[] correctAnswers, String[] userAnswers,String exCode ) {
	    	getContentPane().setBackground(Color.WHITE);
	        setTitle("Màn hình làm bài");
	        setSize(1000,600);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        
	        this.questions = questions;
	    	this.options = options;
	    	this.correctAnswers = correctAnswers;
	    	this.userAnswers = userAnswers;
	    	this.currentUser = currentUser;
	    	this.currentExCode = exCode;
	        
	        cardLayout = new CardLayout();
	        cardPanel = new JPanel(cardLayout);
	        cardPanel.setBackground(Color.WHITE);
	        listModel = new DefaultListModel<String>();
			
			
	
	        cardLayout = new CardLayout();
	        cardPanel_1 = new JPanel(cardLayout);
	        cardPanel_1.setBounds(274, 0, 712, 563);
	        cardPanel_1.setBackground(Color.WHITE);
	
	        for (int i = 0; i < questions.length; i++) {
	        	listModel.addElement(questions[i]);
	            cardPanel_1.add(createQuestionPanel(i), "Question" + i);
	        }
	        getContentPane().setLayout(null);
	
	        getContentPane().add(cardPanel_1);
	        
	        JList list = new JList();
	        cardPanel_1.add(list, "name_340240842288100");
	        
	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(255, 255, 255));
	        panel.setBounds(0, 0, 274, 563);
	        getContentPane().add(panel);
	        panel.setLayout(null);
	        
	        JLabel lblNewLabel = new JLabel("Thời gian làm bài");
	        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 14));
	        lblNewLabel.setBounds(14, 23, 138, 25);
	        panel.add(lblNewLabel);
	        
	        JPanel countdownTimer = new CountdownTimer(60);
	        countdownTimer.setBackground(new Color(255, 255, 255));
	        countdownTimer.setBorder(new EmptyBorder(2, 2, 2, 2));
	        countdownTimer.setBounds(162, 23, 96, 25);
	        panel.add(countdownTimer);
	        
	        JLabel lblNewLabel_2 = new JLabel("Danh sách câu hỏi");
	        lblNewLabel_2.setBounds(10, 81, 158, 13);
	        panel.add(lblNewLabel_2);
	        
	        listCauHoi = new JList(listModel);
	        listCauHoi.setSelectedIndex(0);
	        listCauHoi.setVisibleRowCount(10);
	        listCauHoi.setFont(new Font("Noto Sans", Font.PLAIN, 10));
	        listCauHoi.setBounds(20, 104, 238, 414);
	        panel.add(listCauHoi);
	        
	        changeCardLayout();
	    }
	
	    private JPanel createQuestionPanel(int index) {
	        JPanel panel = new JPanel();
	        panel.setBackground(Color.WHITE);
	        panel.setSize(700,600);
	        panel.setLayout(null);
	        
	        
	        JTextArea txtrDayLaCau = new JTextArea();
			txtrDayLaCau.setFont(new Font("Noto Sans", Font.PLAIN, 14));
			txtrDayLaCau.setWrapStyleWord(true);
			txtrDayLaCau.setEditable(false);
			txtrDayLaCau.setLineWrap(true);
			txtrDayLaCau.setText(questions[index]);
			txtrDayLaCau.setColumns(10);
			txtrDayLaCau.setRows(4);
			txtrDayLaCau.setBounds(53, 47, 585, 57);
			panel.add(txtrDayLaCau);
	        
	        
	        JLabel questionLabel = new JLabel(questions[index]);
	        questionLabel.setFont(new Font("Noto Sans", Font.PLAIN, 12));
	        questionLabel.setHorizontalAlignment(SwingConstants.LEFT);
	        questionLabel.setBounds(105, 71, 760, 33);
	        panel.add(questionLabel);
	        
	        JLabel lblNewLabel_1 = new JLabel("Tương lai tươi đẹp đang chờ đón em");
			lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 14));
			lblNewLabel_1.setBounds(66, 20, 538, 18);
			panel.add(lblNewLabel_1);
	
	        
	
	        JPanel optionPanel = new JPanel();
			optionPanel.setForeground(Color.GRAY);
			optionPanel.setBackground(Color.WHITE);
			optionPanel.setBounds(66, 337, 585, 182);
			panel.add(optionPanel);
			optionPanel.setLayout(new GridLayout(3,2));
	        ButtonGroup group = new ButtonGroup();
	        JRadioButton[] buttons = new JRadioButton[options[index].length];
	
	        for (int i = 0; i < options[index].length; i++) {
	            buttons[i] = new JRadioButton(options[index][i]);
	            buttons[i].setBackground(Color.WHITE);
	            optionPanel.add(buttons[i]);
	            group.add(buttons[i]);
	
	            int finalI = i;
	            buttons[i].addActionListener(e -> {
	            	userAnswers[index] = options[index][finalI].substring(3);
//	            	insertLog(, finalI, i, currentExCode);
	            });
	        }
	        
	        panel.add(optionPanel);
		
	        JPanel imagepanel = new JPanel();
	        imagepanel.setBackground(Color.WHITE);
	        imagepanel.setBounds(138, 114, 402, 204);
			panel.add(imagepanel);
			
			JLabel image = new JLabel("image");
			imagepanel.add(image);
			
	
	        
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setBackground(Color.LIGHT_GRAY);
	        buttonPanel.setBounds(210, 529, 272, 43);
			panel.add(buttonPanel);
			buttonPanel.setLayout(null);
	        
	        JButton prevButton = new JButton("Quay lại");
	        prevButton.setBounds(10, 10, 115, 21);
	        
	        JButton nextButton = new JButton(index == questions.length - 1 ? "Hoàn thành" : "Tiếp theo");
	        nextButton.setBounds(147, 10, 115, 21);
	        prevButton.addActionListener(e -> showPreviousQuestion());
	        nextButton.addActionListener(e -> {
	            if (index == questions.length - 1) {
	                try {
						showResults();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            } else {
	                showNextQuestion();
	            }
	        });
	
	        
	        buttonPanel.add(prevButton);
	        buttonPanel.add(nextButton);
	
	        panel.add(buttonPanel);
	        return panel;
	    }
	
	    private void showNextQuestion() {
	    	if (currentQuestion + 1 < listModel.size()) {
	            currentQuestion++; 
	            listCauHoi.setSelectedIndex(currentQuestion); 
	            cardLayout.show(cardPanel_1, "Question" + currentQuestion);
	        } else {
	            JOptionPane.showMessageDialog(this, "Bạn đã xem hết các câu hỏi!");
	        }
	    }
	
	    private void showPreviousQuestion() {
	    	if (currentQuestion - 1 >= 0 ) {
	            currentQuestion--; 
	            listCauHoi.setSelectedIndex(currentQuestion); 
	            cardLayout.show(cardPanel_1, "Question" + currentQuestion);
	        } else {
	            JOptionPane.showMessageDialog(this, "Không còn câu hỏi nào khác");
	        }
	    }
	    
	    private void changeCardLayout() {
	    	listCauHoi.addListSelectionListener(e-> {
	    		currentQuestion = listCauHoi.getSelectedIndex();
	    		cardLayout.show(cardPanel_1, "Question" + currentQuestion);
	    	});
	    }
	
	    private void showResults() throws SQLException {
	        int score = 0;
	        StringBuilder resultMessage = new StringBuilder("Kết quả bài thi:\n\n");
	        
	        for (int i = 0; i < questions.length; i++) {
	            String correct = correctAnswers[i];
	            String user = userAnswers[i] != null ? userAnswers[i] : "Chưa chọn"; 
	
	            if (correct.equals(user)) {
	                score++;
	            }
	
	            
	            resultMessage.append("Câu ").append(i + 1).append(": ")
	                         .append(user.equals(correct) ? "✅ Đúng" : "❌ Sai")
	                         .append("\nĐáp án của bạn: ").append(user)
	                         .append("\nĐáp án đúng: ").append(correct).append("\n\n");
	        }
	
	        resultMessage.append("Bạn trả lời đúng ").append(score).append("/").append(questions.length).append(" câu.");
	        insertLog(score,questions.length, currentUser.getUserID(),currentExCode);
	        
	        JOptionPane.showMessageDialog(this, resultMessage.toString(), "Kết quả", JOptionPane.INFORMATION_MESSAGE);
	    }
	    
	    private void insertLog(int score,int length, int userID, String logExCode) throws SQLException {
	    	Log_DAL dal = new Log_DAL();
	    	String log = "Bạn đã hoàn thành bài kiểm tra "+ logExCode + " với số điểm: " + score + "/" + length;
	    	dal.insertLog(userID, log, logExCode);
	    }
	}
