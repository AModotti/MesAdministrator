package ide.jde;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

import bin.DBMySqlManager;
import bin.DBOracleManager;
import bin.Settings;

import java.awt.Font;
import java.util.HashMap;

import javax.swing.SwingConstants;


public class ImportWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtreparto;
	private JTextField txtdadata;
	private JTextField txtadata;
	private JTextField txtidfinaletransazione;
	private JTextField txtrecordimportatitransazione;
	private JTextField txtidinizialetransazione;
	private JTextField txtadatatransazione;
	private JTextField txtdadatatransazione;
	private JTextField txtrepartotransazione;
	private JTextField txtdataimportazione;
	private JTextField txtutente;
	private JTextField txtidfinaletransazioneattuale;
	private JTextField txtrecordimportatitransazioneattuale;
	private JTextField txtidinizialetransazioneattuale;
	private JTextField txtadatatransazioneattuale;
	private JTextField txtdadatatransazioneattuale;
	private JTextField txtrepartotransazioneattuale;
	private JTextField txtdataimportazioneattuale;
	private JTextField txtutenteattuale;
	private HashMap<Integer, String> importrows = new HashMap<>();
	private JTextField txtdefault;
	
	public ImportWindow() {
		
		String image ="image.gif";
		
		Image icon = Toolkit.getDefaultToolkit().getImage(image);
		
		setIconImage(icon);
		setTitle("Eurolls M.E.S. - MES Administrator - Importazione Dati in JD Edwards");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				
		setBounds(150, 100, 920, 590);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Importazione dati in JD Edwards:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblreparto = new JLabel("Reparto:");
		lblreparto.setBounds(51, 51, 130, 20);
		panel.add(lblreparto);
		
		txtdefault = new JTextField();
		txtdefault.setForeground(Color.BLUE);
		txtdefault.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdefault.setBounds(190, 50, 1, 20);
		panel.add(txtdefault);
		
		txtreparto = new JTextField();
		txtreparto.setHorizontalAlignment(SwingConstants.CENTER);
		txtreparto.setForeground(Color.BLUE);
		txtreparto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtreparto.setBounds(191, 50, 234, 20);
		panel.add(txtreparto);
		txtreparto.setColumns(10);
		txtreparto.requestFocus();
		
		JLabel lbldadata = new JLabel("Da Data:");
		lbldadata.setBounds(51, 78, 130, 20);
		panel.add(lbldadata);
		
		txtdadata = new JTextField();
		txtdadata.setHorizontalAlignment(SwingConstants.CENTER);
		txtdadata.setForeground(Color.BLUE);
		txtdadata.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdadata.setColumns(10);
		txtdadata.setBounds(191, 78, 234, 20);
		panel.add(txtdadata);
		
		JLabel lbladata = new JLabel("A Data:");
		lbladata.setBounds(51, 106, 130, 20);
		panel.add(lbladata);
		
		txtadata = new JTextField();
		txtadata.setHorizontalAlignment(SwingConstants.CENTER);
		txtadata.setForeground(Color.BLUE);
		txtadata.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtadata.setColumns(10);
		txtadata.setBounds(191, 106, 234, 20);
		panel.add(txtadata);
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			importrows = DBMySqlManager.getLastImport();
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			importrows = DBOracleManager.getLastImport();
		}
		
		JPanel pnlimportazioni = new JPanel();
		pnlimportazioni.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Importazioni:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlimportazioni.setBounds(10, 216, 881, 325);
		panel.add(pnlimportazioni);
		pnlimportazioni.setLayout(null);
		
		JPanel pnlultimaimportazione = new JPanel();
		pnlultimaimportazione.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ultima Importazione:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlultimaimportazione.setBounds(10, 27, 427, 287);
		pnlimportazioni.add(pnlultimaimportazione);
		pnlultimaimportazione.setLayout(null);
		
		JLabel lblutenteimportazione = new JLabel("Utente:");
		lblutenteimportazione.setBounds(21, 35, 143, 20);
		pnlultimaimportazione.add(lblutenteimportazione);
		
		JLabel lbldataimportazione = new JLabel("Data Importazione:");
		lbldataimportazione.setBounds(21, 63, 143, 20);
		pnlultimaimportazione.add(lbldataimportazione);
		
		UIManager.put("TextField.inactiveBackground", Color.WHITE);
		
		txtutente = new JTextField(importrows.get(0));
		txtutente.setEditable(false);
		txtutente.setHorizontalAlignment(SwingConstants.CENTER);
		txtutente.setForeground(Color.BLUE);
		txtutente.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtutente.setColumns(10);
		txtutente.setBounds(171, 35, 234, 20);
		pnlultimaimportazione.add(txtutente);
		
		txtdataimportazione = new JTextField(importrows.get(1));
		txtdataimportazione.setEditable(false);
		txtdataimportazione.setHorizontalAlignment(SwingConstants.CENTER);
		txtdataimportazione.setForeground(Color.BLUE);
		txtdataimportazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdataimportazione.setColumns(10);
		txtdataimportazione.setBounds(171, 63, 234, 20);
		pnlultimaimportazione.add(txtdataimportazione);
		
		txtrepartotransazione = new JTextField(importrows.get(2));
		txtrepartotransazione.setEditable(false);
		txtrepartotransazione.setHorizontalAlignment(SwingConstants.CENTER);
		txtrepartotransazione.setForeground(Color.BLUE);
		txtrepartotransazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtrepartotransazione.setColumns(10);
		txtrepartotransazione.setBounds(171, 94, 234, 20);
		pnlultimaimportazione.add(txtrepartotransazione);
		
		txtdadatatransazione = new JTextField(importrows.get(3));
		txtdadatatransazione.setEditable(false);
		txtdadatatransazione.setHorizontalAlignment(SwingConstants.CENTER);
		txtdadatatransazione.setForeground(Color.BLUE);
		txtdadatatransazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdadatatransazione.setColumns(10);
		txtdadatatransazione.setBounds(171, 125, 234, 20);
		pnlultimaimportazione.add(txtdadatatransazione);
		
		txtadatatransazione = new JTextField(importrows.get(4));
		txtadatatransazione.setEditable(false);
		txtadatatransazione.setHorizontalAlignment(SwingConstants.CENTER);
		txtadatatransazione.setForeground(Color.BLUE);
		txtadatatransazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtadatatransazione.setColumns(10);
		txtadatatransazione.setBounds(171, 156, 234, 20);
		pnlultimaimportazione.add(txtadatatransazione);
		
		txtidinizialetransazione = new JTextField(importrows.get(5));
		txtidinizialetransazione.setEditable(false);
		txtidinizialetransazione.setHorizontalAlignment(SwingConstants.CENTER);
		txtidinizialetransazione.setForeground(Color.BLUE);
		txtidinizialetransazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtidinizialetransazione.setColumns(10);
		txtidinizialetransazione.setBounds(171, 187, 234, 20);
		pnlultimaimportazione.add(txtidinizialetransazione);
		
		txtrecordimportatitransazione = new JTextField(importrows.get(6));
		txtrecordimportatitransazione.setEditable(false);
		txtrecordimportatitransazione.setHorizontalAlignment(SwingConstants.CENTER);
		txtrecordimportatitransazione.setForeground(Color.BLUE);
		txtrecordimportatitransazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtrecordimportatitransazione.setColumns(10);
		txtrecordimportatitransazione.setBounds(171, 217, 234, 20);
		pnlultimaimportazione.add(txtrecordimportatitransazione);
		
		txtidfinaletransazione = new JTextField(importrows.get(7));
		txtidfinaletransazione.setEditable(false);
		txtidfinaletransazione.setHorizontalAlignment(SwingConstants.CENTER);
		txtidfinaletransazione.setForeground(Color.BLUE);
		txtidfinaletransazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtidfinaletransazione.setColumns(10);
		txtidfinaletransazione.setBounds(171, 248, 234, 20);
		pnlultimaimportazione.add(txtidfinaletransazione);
		
		JLabel lblrepartoimportazione = new JLabel("Reparto :");
		lblrepartoimportazione.setBounds(21, 94, 143, 20);
		pnlultimaimportazione.add(lblrepartoimportazione);
		
		JLabel lbldadataimportazione = new JLabel("Da Data:");
		lbldadataimportazione.setBounds(21, 125, 143, 20);
		pnlultimaimportazione.add(lbldadataimportazione);
		
		JLabel lbladataimportazione = new JLabel("A Data:");
		lbladataimportazione.setBounds(21, 156, 143, 20);
		pnlultimaimportazione.add(lbladataimportazione);
		
		JLabel lblidinizialeimportazione = new JLabel("Id Iniziale:");
		lblidinizialeimportazione.setBounds(21, 187, 143, 20);
		pnlultimaimportazione.add(lblidinizialeimportazione);
		
		JLabel lblrecordimportatiimportazione = new JLabel("Record Importati:");
		lblrecordimportatiimportazione.setBounds(21, 217, 143, 20);
		pnlultimaimportazione.add(lblrecordimportatiimportazione);
		
		JLabel lblidfinaleimportazione = new JLabel("Id Finale:");
		lblidfinaleimportazione.setBounds(21, 248, 143, 20);
		pnlultimaimportazione.add(lblidfinaleimportazione);
		
		JPanel pnlimportazioneattuale = new JPanel();
		pnlimportazioneattuale.setLayout(null);
		pnlimportazioneattuale.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Importazione Attuale:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlimportazioneattuale.setBounds(444, 27, 427, 287);
		pnlimportazioni.add(pnlimportazioneattuale);
		
		JLabel lblutenteattuale = new JLabel("Utente:");
		lblutenteattuale.setBounds(21, 35, 143, 20);
		pnlimportazioneattuale.add(lblutenteattuale);
		
		JLabel lbldataimportazioneattuale = new JLabel("Data Importazione:");
		lbldataimportazioneattuale.setBounds(21, 63, 143, 20);
		pnlimportazioneattuale.add(lbldataimportazioneattuale);
		
		txtutenteattuale = new JTextField();
		txtutenteattuale.setHorizontalAlignment(SwingConstants.CENTER);
		txtutenteattuale.setEditable(false);
		txtutenteattuale.setForeground(Color.BLUE);
		txtutenteattuale.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtutenteattuale.setColumns(10);
		txtutenteattuale.setBounds(171, 35, 234, 20);
		pnlimportazioneattuale.add(txtutenteattuale);
		
		txtdataimportazioneattuale = new JTextField();
		txtdataimportazioneattuale.setHorizontalAlignment(SwingConstants.CENTER);
		txtdataimportazioneattuale.setEditable(false);
		txtdataimportazioneattuale.setForeground(Color.BLUE);
		txtdataimportazioneattuale.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdataimportazioneattuale.setColumns(10);
		txtdataimportazioneattuale.setBounds(171, 63, 234, 20);
		pnlimportazioneattuale.add(txtdataimportazioneattuale);
		
		txtrepartotransazioneattuale = new JTextField();
		txtrepartotransazioneattuale.setHorizontalAlignment(SwingConstants.CENTER);
		txtrepartotransazioneattuale.setEditable(false);
		txtrepartotransazioneattuale.setForeground(Color.BLUE);
		txtrepartotransazioneattuale.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtrepartotransazioneattuale.setColumns(10);
		txtrepartotransazioneattuale.setBounds(171, 94, 234, 20);
		pnlimportazioneattuale.add(txtrepartotransazioneattuale);
		
		txtdadatatransazioneattuale = new JTextField();
		txtdadatatransazioneattuale.setHorizontalAlignment(SwingConstants.CENTER);
		txtdadatatransazioneattuale.setEditable(false);
		txtdadatatransazioneattuale.setForeground(Color.BLUE);
		txtdadatatransazioneattuale.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdadatatransazioneattuale.setColumns(10);
		txtdadatatransazioneattuale.setBounds(171, 125, 234, 20);
		pnlimportazioneattuale.add(txtdadatatransazioneattuale);
		
		txtadatatransazioneattuale = new JTextField();
		txtadatatransazioneattuale.setHorizontalAlignment(SwingConstants.CENTER);
		txtadatatransazioneattuale.setEditable(false);
		txtadatatransazioneattuale.setForeground(Color.BLUE);
		txtadatatransazioneattuale.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtadatatransazioneattuale.setColumns(10);
		txtadatatransazioneattuale.setBounds(171, 156, 234, 20);
		pnlimportazioneattuale.add(txtadatatransazioneattuale);
		
		txtidinizialetransazioneattuale = new JTextField();
		txtidinizialetransazioneattuale.setHorizontalAlignment(SwingConstants.CENTER);
		txtidinizialetransazioneattuale.setEditable(false);
		txtidinizialetransazioneattuale.setForeground(Color.BLUE);
		txtidinizialetransazioneattuale.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtidinizialetransazioneattuale.setColumns(10);
		txtidinizialetransazioneattuale.setBounds(171, 187, 234, 20);
		pnlimportazioneattuale.add(txtidinizialetransazioneattuale);
		
		txtrecordimportatitransazioneattuale = new JTextField();
		txtrecordimportatitransazioneattuale.setHorizontalAlignment(SwingConstants.CENTER);
		txtrecordimportatitransazioneattuale.setEditable(false);
		txtrecordimportatitransazioneattuale.setForeground(Color.BLUE);
		txtrecordimportatitransazioneattuale.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtrecordimportatitransazioneattuale.setColumns(10);
		txtrecordimportatitransazioneattuale.setBounds(171, 217, 234, 20);
		pnlimportazioneattuale.add(txtrecordimportatitransazioneattuale);
		
		txtidfinaletransazioneattuale = new JTextField();
		txtidfinaletransazioneattuale.setHorizontalAlignment(SwingConstants.CENTER);
		txtidfinaletransazioneattuale.setEditable(false);
		txtidfinaletransazioneattuale.setForeground(Color.BLUE);
		txtidfinaletransazioneattuale.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtidfinaletransazioneattuale.setColumns(10);
		txtidfinaletransazioneattuale.setBounds(171, 248, 234, 20);
		pnlimportazioneattuale.add(txtidfinaletransazioneattuale);
						
		JLabel lblrepartoimportazioneattuale = new JLabel("Reparto Transazione:");
		lblrepartoimportazioneattuale.setBounds(21, 94, 143, 20);
		pnlimportazioneattuale.add(lblrepartoimportazioneattuale);
		
		JLabel lbldadataimportazioneattuale = new JLabel("Da Data:");
		lbldadataimportazioneattuale.setBounds(21, 125, 143, 20);
		pnlimportazioneattuale.add(lbldadataimportazioneattuale);
		
		JLabel lbladataimportazioneattuale = new JLabel("A Data:");
		lbladataimportazioneattuale.setBounds(21, 156, 143, 20);
		pnlimportazioneattuale.add(lbladataimportazioneattuale);
		
		JLabel lblidinizialeimportazioneattuale = new JLabel("Id Iniziale:");
		lblidinizialeimportazioneattuale.setBounds(21, 187, 143, 20);
		pnlimportazioneattuale.add(lblidinizialeimportazioneattuale);
		
		JLabel lblrecordimportatiimportazioneattuale = new JLabel("Record Importati:");
		lblrecordimportatiimportazioneattuale.setBounds(21, 217, 143, 20);
		pnlimportazioneattuale.add(lblrecordimportatiimportazioneattuale);
		
		JLabel lblidfinaleimportazioneattuale = new JLabel("Id Finale:");
		lblidfinaleimportazioneattuale.setBounds(21, 248, 143, 20);
		pnlimportazioneattuale.add(lblidfinaleimportazioneattuale);
		
		JButton btneseguiimportazione = new JButton("Esegui Importazione");
		btneseguiimportazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		btneseguiimportazione.setBounds(51, 166, 170, 30);
		panel.add(btneseguiimportazione);
		
		JButton btnvisualizzastorico = new JButton("Visualizza Storico");
		btnvisualizzastorico.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnvisualizzastorico.setBounds(233, 166, 170, 30);
		panel.add(btnvisualizzastorico);
		
		JButton btnannulla = new JButton("Annulla");
		btnannulla.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnannulla.setBounds(413, 166, 170, 30);
		panel.add(btnannulla);
		
		//EVENTI PULSANTE ESEGUI IMPORTAZIONE
		btneseguiimportazione.addActionListener(new EventImportBtnExecuteImport(this,txtreparto,txtdadata,txtadata,txtutenteattuale,txtdataimportazioneattuale,txtrepartotransazioneattuale,txtdadatatransazioneattuale,txtadatatransazioneattuale,txtidinizialetransazioneattuale,txtrecordimportatitransazioneattuale,txtidfinaletransazioneattuale));
		
		//EVENTI PULSANTE VISUALIZZA STORICO
		btnvisualizzastorico.addActionListener(new EventImportBtnHistoryImport(this));
		
		//EVENTI PULSANTE ANNULLA
		btnannulla.addActionListener(new EventImportBtnCancel(this,txtutenteattuale,txtdataimportazioneattuale,txtrepartotransazioneattuale,txtdadatatransazioneattuale,txtadatatransazioneattuale,txtidinizialetransazioneattuale,txtrecordimportatitransazioneattuale,txtidfinaletransazioneattuale));
				

	}
}
