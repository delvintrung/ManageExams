package app.GUI.Component.Dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SelectExamToExport extends JDialog {

	private JComboBox<String> comboBox;
    private String selectedExamCode;

    public SelectExamToExport(JPanel parent) {
        super();
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        
        JLabel label = new JLabel("Chọn mã đề để in:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        
        String[] examCodes = {"A", "B", "C", "D"};
        comboBox = new JComboBox<>(examCodes);
        add(comboBox, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        JButton printButton = new JButton("In Đề");
        JButton cancelButton = new JButton("Hủy");

        buttonPanel.add(printButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Xử lý sự kiện khi nhấn nút "In Đề"
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedExamCode = (String) comboBox.getSelectedItem();
                JOptionPane.showMessageDialog(SelectExamToExport.this, "Đã chọn mã đề: " + selectedExamCode);
                dispose(); // Đóng dialog
            }
        });

        
        cancelButton.addActionListener(e -> dispose());
    }

    public String getSelectedExamCode() {
        return selectedExamCode;
    }

    
}
