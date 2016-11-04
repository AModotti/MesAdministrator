package ide.dept;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import bin.DBMySqlManager;
import bin.DBOracleManager;
import bin.Settings;

import java.awt.Font;
import java.awt.Color;

public class DeptWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtnomereparto;
	private JTextField txtdescrizionereparto;

	public DeptWindow() {

		String image ="image.gif";
		
		Image icon = Toolkit.getDefaultToolkit().getImage(image);
		
		setIconImage(icon);
		setTitle("Eurolls M.E.S. - MES Administrator - Gestione Reparti");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				
		setBounds(150, 60, 835, 650);
		getContentPane().setLayout(new BorderLayout());
				
		JPanel pnlgestionereparti = new JPanel();
		pnlgestionereparti.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Gestione reparti:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlgestionereparti.setPreferredSize(new Dimension(750, 200));
		pnlgestionereparti.setMaximumSize(new Dimension(750, 200));
		pnlgestionereparti.setLayout(null);
				
		JScrollPane scrgestionereparti = new JScrollPane(pnlgestionereparti);
		scrgestionereparti.setPreferredSize(new Dimension(780, 220));
		scrgestionereparti.setMaximumSize(new Dimension(780, 220));
		getContentPane().add(scrgestionereparti, BorderLayout.NORTH);
		
		txtnomereparto = new JTextField();
		txtnomereparto.setForeground(Color.BLUE);
		txtnomereparto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnomereparto.setBounds(264, 30, 467, 20);
		pnlgestionereparti.add(txtnomereparto);
		txtnomereparto.setColumns(10);
		
		txtdescrizionereparto = new JTextField();
		txtdescrizionereparto.setForeground(Color.BLUE);
		txtdescrizionereparto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdescrizionereparto.setColumns(10);
		txtdescrizionereparto.setBounds(264, 64, 467, 20);
		pnlgestionereparti.add(txtdescrizionereparto);
		
		UIManager.put("ComboBox.background", new ColorUIResource(Color.WHITE));
		
		JComboBox<String> cmbabilitazione = new JComboBox<String>();
		cmbabilitazione.setForeground(Color.BLUE);
		cmbabilitazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbabilitazione.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "0"}));
		cmbabilitazione.setBounds(264, 98, 65, 20);
		pnlgestionereparti.add(cmbabilitazione);
		
		DeptTable tblreparti = new DeptTable();
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			DeptTableModel tm = new DeptTableModel(DBMySqlManager.getDept(),tblreparti.getHeaders());
			tblreparti.setModel(tm);
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			DeptTableModel tm = new DeptTableModel(DBOracleManager.getDept(),tblreparti.getHeaders());
			tblreparti.setModel(tm);
		}
		
		tblreparti.setLayout();
		tblreparti.setRowSelectionAllowed(true);
				
		JScrollPane scrreparti = new JScrollPane();
		scrreparti.setPreferredSize(new Dimension(1010, 540));
		scrreparti.setMaximumSize(new Dimension(1010, 540));
		scrreparti.setViewportView(tblreparti);
		getContentPane().add(scrreparti, BorderLayout.CENTER);
		
		JPanel bottompane = new JPanel();
		bottompane.setLayout(null);
		bottompane.setPreferredSize(new Dimension(1010, 40));
		bottompane.setMaximumSize(new Dimension(1010, 40));
		getContentPane().add(bottompane, BorderLayout.SOUTH);
		
		JLabel lblnumerorecord = new JLabel();
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			lblnumerorecord.setText("Numero righe: " + Integer.toString(DBMySqlManager.deptRecordCount()));
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			lblnumerorecord.setText("Numero righe: " + Integer.toString(DBOracleManager.deptRecordCount()));
		}
		
		lblnumerorecord.setForeground(Color.BLUE);
		lblnumerorecord.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblnumerorecord.setBounds(30, 10, 180, 19);
		bottompane.add(lblnumerorecord);
		
		JLabel lblnomereparto = new JLabel("Nome Reparto:");
		lblnomereparto.setBounds(21, 30, 233, 23);
		pnlgestionereparti.add(lblnomereparto);
		
		JLabel lbldescrizionereparto = new JLabel("Descrizione Reparto:");
		lbldescrizionereparto.setBounds(21, 64, 233, 23);
		pnlgestionereparti.add(lbldescrizionereparto);
		
		JLabel lblabilitazione = new JLabel("Abilitazione:");
		lblabilitazione.setBounds(21, 98, 233, 23);
		pnlgestionereparti.add(lblabilitazione);
				
		JButton btninseriscireparto = new JButton("Inserisci reparto");
		btninseriscireparto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btninseriscireparto.setBounds(21, 162, 170, 30);
		pnlgestionereparti.add(btninseriscireparto);
		
		JButton btnsalvareparto = new JButton("Salva modifiche");
		btnsalvareparto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnsalvareparto.setBounds(201, 162, 170, 30);
		pnlgestionereparti.add(btnsalvareparto);
		
		JButton btneliminareparto = new JButton("Elimina reparto");
		btneliminareparto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btneliminareparto.setBounds(381, 162, 170, 30);
		pnlgestionereparti.add(btneliminareparto);
		
		JButton btnannulla = new JButton("Annulla");
		btnannulla.setBounds(561, 162, 170, 30);
		btnannulla.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnlgestionereparti.add(btnannulla);
		
		//EVENTI SELEZIONI TABELLA
		tblreparti.getSelectionModel().addListSelectionListener(new EventDeptTableSelection(this,tblreparti,txtnomereparto,txtdescrizionereparto,cmbabilitazione));
							
		//EVENTI PULSANTE INSERISCI NUOVA ISOLA
		btninseriscireparto.addActionListener(new EventDeptBtnInsert(this,tblreparti,txtnomereparto,txtdescrizionereparto,cmbabilitazione,lblnumerorecord));
		
		//EVENTI PULSANTE SALVA MODIFICHE ISOLA
		btnsalvareparto.addActionListener(new EventDeptBtnUpdate(this,tblreparti,txtnomereparto,txtdescrizionereparto,cmbabilitazione));
		
		//EVENTI PULSANTE CANCELLA ISOLA
		btneliminareparto.addActionListener(new EventDeptBtnDelete(this,tblreparti,txtnomereparto,txtdescrizionereparto,cmbabilitazione,lblnumerorecord));
						
		//EVENTI PULSANTE ANNULLA
		btnannulla.addActionListener(new EventDeptBtnCancel(this));
		
		
	}
}
