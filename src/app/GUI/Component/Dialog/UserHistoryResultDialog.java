package app.GUI.Component.Dialog;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import app.BLL.Result_BLL;
import app.DTO.Result_DTO;
import app.DTO.User_DTO;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class UserHistoryResultDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblResults;
	private DefaultTableModel tableModel;
	
	private User_DTO user;
	private Result_BLL resultBLL;

	public UserHistoryResultDialog(Frame main, boolean modal, User_DTO user, ArrayList<Result_DTO> results) {
		super(main, modal);
		this.user = user;
		resultBLL = new Result_BLL();
		
		setBounds(100, 100, 708, 421);
		setLocationRelativeTo(main);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LỊCH SỬ LÀM BÀI");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 25, 684, 26);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 674, 301);
		contentPane.add(scrollPane);
		
		tableModel = new DefaultTableModel(new Object[]{"ID", "Mã bài thi", "Ngày thi", "Điểm"}, 0) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		tblResults = new JTable(tableModel);
		scrollPane.setViewportView(tblResults);
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		loadTableData(results);
	}
	
	private void loadTableData(ArrayList<Result_DTO> results) {
		tableModel.setRowCount(0);
		
		for (Result_DTO result : results) {
			tableModel.addRow(new Object[] {
					result.getRs_num(),
					result.getExCode(),
					result.getRs_date(),
					result.getRs_mark()
			});
		}
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        for (int i = 0; i < tblResults.getColumnCount(); i++) {
            tblResults.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
	}
}
