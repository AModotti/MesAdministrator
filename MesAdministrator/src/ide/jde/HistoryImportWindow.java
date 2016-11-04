package ide.jde;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import bin.DBMySqlManager;
import bin.DBOracleManager;
import bin.Settings;

import java.awt.Font;

import javax.swing.JLabel;

import java.awt.Color;

public class HistoryImportWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public HistoryImportWindow() {
		setResizable(false);
		
		String image ="image.gif";
		
		Image icon = Toolkit.getDefaultToolkit().getImage(image);
		
		setIconImage(icon);
		setTitle("Eurolls M.E.S. - MES Administrator - Storico Importazione Dati in JD Edwards");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				
		setBounds(50, 150, 1130, 600);
		getContentPane().setLayout(new BorderLayout());

		JPanel pnlnorth = new JPanel();
		pnlnorth.setPreferredSize(new Dimension(1070, 25));
		pnlnorth.setMaximumSize(new Dimension(1070, 25));
		getContentPane().add(pnlnorth, BorderLayout.NORTH);
		
		JScrollPane scrstoricoimportazioni = new JScrollPane();
		getContentPane().add(scrstoricoimportazioni, BorderLayout.CENTER);
		
		HistoryImportTable tblstoricoimportazioni = new HistoryImportTable();
		HistoryImportModel hm = new HistoryImportModel(null,null);
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			hm.setDataVector(DBMySqlManager.getHistoryImport(),tblstoricoimportazioni.getHeaders());
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			hm.setDataVector(DBOracleManager.getHistoryImport(),tblstoricoimportazioni.getHeaders());
		}
		
		tblstoricoimportazioni.setModel(hm);
		tblstoricoimportazioni.setLayout();
		scrstoricoimportazioni.setViewportView(tblstoricoimportazioni);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setPreferredSize(new Dimension(1070, 40));
		buttonPane.setMaximumSize(new Dimension(1070, 40));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(null);
		
		JLabel lblnumerorecord = new JLabel();
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			lblnumerorecord.setText("Numero righe: " + Integer.toString(DBMySqlManager.historyImportRecordCount()));
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			lblnumerorecord.setText("Numero righe: " + Integer.toString(DBOracleManager.historyImportRecordCount()));
		}
		
		lblnumerorecord.setBounds(30, 10, 180, 19);
		lblnumerorecord.setForeground(Color.BLUE);
		lblnumerorecord.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonPane.add(lblnumerorecord);

		JButton btnannulla = new JButton("Annulla");
		btnannulla.setBounds(1030, 6, 86, 26);
		btnannulla.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonPane.add(btnannulla);
		getRootPane().setDefaultButton(btnannulla);
		
		//EVENTI PULSANTE ANNULLA
		btnannulla.addActionListener(new EventHistoryImportBtnCancel(this));
		
	
	}

}
