package app.GUI.Component;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class CountdownTimer extends JPanel {

	private static final long serialVersionUID = 1L;
	private int timeRemaining; 
    private JLabel labelTime;
    private Timer timer;
	public CountdownTimer(int minutes) {
	            timeRemaining = minutes * 60;
	            setSize(80, 25);
	            setLayout(new BorderLayout());
	
	            labelTime = new JLabel(formatTime(timeRemaining), SwingConstants.CENTER);
	            labelTime.setFont(new Font("Verdana", Font.BOLD, 24));
	            add(labelTime, BorderLayout.CENTER);
	            startCountdown();
	
	            setVisible(true);
	        }

			private void startCountdown() {
				timer = new Timer(1000, e -> {
				    if (timeRemaining > 0) {
				        timeRemaining--;
				        labelTime.setText(formatTime(timeRemaining));
				    } else {
				        timer.stop();
				        JOptionPane.showMessageDialog(null, "Time's up!");
				    }
		        });
		        timer.start();
			}

        private String formatTime(int seconds) {
            int minutes = seconds / 60;
            int sec = seconds % 60;
            return String.format("%02d:%02d", minutes, sec);
        }
}
