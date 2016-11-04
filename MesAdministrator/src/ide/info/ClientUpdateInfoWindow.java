package ide.info;

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

public class ClientUpdateInfoWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public ClientUpdateInfoWindow() {
		setResizable(false);
		
		String image ="image.gif";
		
		Image icon = Toolkit.getDefaultToolkit().getImage(image);
		
		setIconImage(icon);
		setTitle("Eurolls M.E.S. - MES Administrator - Informazioni Aggiornamento Client");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				
		setBounds(150, 120, 805, 600);
		getContentPane().setLayout(new BorderLayout());

		JPanel pnlnorth = new JPanel();
		pnlnorth.setPreferredSize(new Dimension(1070, 25));
		pnlnorth.setMaximumSize(new Dimension(1070, 25));
		getContentPane().add(pnlnorth, BorderLayout.NORTH);
		
		JScrollPane scrinfoaggiornamentoclient = new JScrollPane();
		getContentPane().add(scrinfoaggiornamentoclient, BorderLayout.CENTER);
		
		ClientUpdateTable tblinfoaggiornamentoclient = new ClientUpdateTable();
		ClientUpdateModel cm = new ClientUpdateModel(null,null);
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			cm.setDataVector(DBMySqlManager.getClientUpdateInfo(),tblinfoaggiornamentoclient.getHeaders());
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			cm.setDataVector(DBOracleManager.getClientUpdateInfo(),tblinfoaggiornamentoclient.getHeaders());
		}
		
		tblinfoaggiornamentoclient.setModel(cm);
		tblinfoaggiornamentoclient.setLayout();
		scrinfoaggiornamentoclient.setViewportView(tblinfoaggiornamentoclient);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setPreferredSize(new Dimension(1070, 40));
		buttonPane.setMaximumSize(new Dimension(1070, 40));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(null);
			
		JLabel lblnumerorecord = new JLabel();
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			lblnumerorecord.setText("Numero righe: " + Integer.toString(DBMySqlManager.clientUpdateInfoRecordCount()));
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			lblnumerorecord.setText("Numero righe: " + Integer.toString(DBOracleManager.clientUpdateInfoRecordCount()));
		}
		
		lblnumerorecord.setBounds(30, 10, 180, 19);
		lblnumerorecord.setForeground(Color.BLUE);
		lblnumerorecord.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonPane.add(lblnumerorecord);

		JButton btnannulla = new JButton("Annulla");
		btnannulla.setBounds(705, 6, 86, 26);
		btnannulla.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonPane.add(btnannulla);
		getRootPane().setDefaultButton(btnannulla);
		
		//EVENTI PULSANTE ANNULLA
		btnannulla.addActionListener(new EventClientUpdateBtnCancel(this));
		
	
	}
}
