package ide.island;

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

public class IslandWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtindirizzoip;
	private JTextField txtnomeisola;
	private JTextField txtdescrizioneisola;
	private JTextField txtreparto;

	public IslandWindow() {

		String image ="image.gif";
		
		Image icon = Toolkit.getDefaultToolkit().getImage(image);
		
		setIconImage(icon);
		setTitle("Eurolls M.E.S. - MES Administrator - Gestione Isole");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				
		setBounds(25, 60, 1230, 800);
		getContentPane().setLayout(new BorderLayout());
				
		JPanel pnlgestioneisole = new JPanel();
		pnlgestioneisole.setBorder(new TitledBorder(null, "Gestione isole:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlgestioneisole.setPreferredSize(new Dimension(750, 320));
		pnlgestioneisole.setMaximumSize(new Dimension(750, 320));
		pnlgestioneisole.setLayout(null);
				
		JScrollPane scrgestioneisole = new JScrollPane(pnlgestioneisole);
		scrgestioneisole.setPreferredSize(new Dimension(780, 340));
		scrgestioneisole.setMaximumSize(new Dimension(780, 340));
		getContentPane().add(scrgestioneisole, BorderLayout.NORTH);
		
		txtindirizzoip = new JTextField();
		txtindirizzoip.setForeground(Color.BLUE);
		txtindirizzoip.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtindirizzoip.setBounds(264, 30, 467, 20);
		pnlgestioneisole.add(txtindirizzoip);
		txtindirizzoip.setColumns(10);
		
		txtnomeisola = new JTextField();
		txtnomeisola.setForeground(Color.BLUE);
		txtnomeisola.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnomeisola.setColumns(10);
		txtnomeisola.setBounds(264, 64, 467, 20);
		pnlgestioneisole.add(txtnomeisola);
		
		txtdescrizioneisola = new JTextField();
		txtdescrizioneisola.setForeground(Color.BLUE);
		txtdescrizioneisola.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdescrizioneisola.setColumns(10);
		txtdescrizioneisola.setBounds(264, 98, 467, 20);
		pnlgestioneisole.add(txtdescrizioneisola);
		
		JLabel lbldescrizionereparto = new JLabel("Reparto:");
		lbldescrizionereparto.setBounds(21, 132, 233, 23);
		pnlgestioneisole.add(lbldescrizionereparto);
		
		txtreparto = new JTextField();
		txtreparto.setForeground(Color.BLUE);
		txtreparto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtreparto.setColumns(10);
		txtreparto.setBounds(264, 132, 467, 20);
		pnlgestioneisole.add(txtreparto);
		
		UIManager.put("ComboBox.background", new ColorUIResource(Color.WHITE));
		
		JComboBox<String> cmbabilitazione = new JComboBox<String>();
		cmbabilitazione.setForeground(Color.BLUE);
		cmbabilitazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbabilitazione.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "0"}));
		cmbabilitazione.setBounds(264, 166, 65, 20);
		pnlgestioneisole.add(cmbabilitazione);
		
		JLabel lblricercaworkorderperarticolo = new JLabel("Ricerca work order per codice articolo:");
		lblricercaworkorderperarticolo.setBounds(423, 200, 233, 23);
		pnlgestioneisole.add(lblricercaworkorderperarticolo);
		
		JComboBox<String> cmbricercaworkorderperarticolo = new JComboBox<String>();
		cmbricercaworkorderperarticolo.setForeground(Color.BLUE);
		cmbricercaworkorderperarticolo.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbricercaworkorderperarticolo.setModel(new DefaultComboBoxModel<String>(new String[] {"0", "1"}));
		cmbricercaworkorderperarticolo.setBounds(666, 205, 65, 20);
		pnlgestioneisole.add(cmbricercaworkorderperarticolo);
		
		JLabel lblmodalitamultioperatore = new JLabel("Modalit\u00E0 multioperatore:");
		lblmodalitamultioperatore.setBounds(423, 166, 233, 23);
		pnlgestioneisole.add(lblmodalitamultioperatore);
		
		JComboBox<String> cmbmodalitamultioperatore = new JComboBox<String>();
		cmbmodalitamultioperatore.setForeground(Color.BLUE);
		cmbmodalitamultioperatore.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbmodalitamultioperatore.setModel(new DefaultComboBoxModel<String>(new String[] {"0", "1"}));
		cmbmodalitamultioperatore.setBounds(666, 166, 65, 20);
		pnlgestioneisole.add(cmbmodalitamultioperatore);
		
		JLabel lbldichiarazionemultiordine = new JLabel("Dichiarazione multiordine:");
		lbldichiarazionemultiordine.setBounds(21, 200, 233, 23);
		pnlgestioneisole.add(lbldichiarazionemultiordine);
		
		JComboBox<String> cmbdichiarazionemultiordine = new JComboBox<String>();
		cmbdichiarazionemultiordine.setForeground(Color.BLUE);
		cmbdichiarazionemultiordine.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbdichiarazionemultiordine.setModel(new DefaultComboBoxModel<String>(new String[] {"0", "1"}));
		cmbdichiarazionemultiordine.setBounds(264, 200, 65, 20);
		pnlgestioneisole.add(cmbdichiarazionemultiordine);
		
		IslandTable tblisole = new IslandTable();
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			IslandTableModel tm = new IslandTableModel(DBMySqlManager.getIsland(),tblisole.getHeaders());
			tblisole.setModel(tm);
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			IslandTableModel tm = new IslandTableModel(DBOracleManager.getIsland(),tblisole.getHeaders());
			tblisole.setModel(tm);
		}
		
		tblisole.setLayout();
		tblisole.setRowSelectionAllowed(true);
				
		JScrollPane scrisole = new JScrollPane();
		scrisole.setPreferredSize(new Dimension(1010, 420));
		scrisole.setMaximumSize(new Dimension(1010, 420));
		scrisole.setViewportView(tblisole);
		getContentPane().add(scrisole, BorderLayout.CENTER);
		
		JPanel bottompane = new JPanel();
		bottompane.setLayout(null);
		bottompane.setPreferredSize(new Dimension(1010, 40));
		bottompane.setMaximumSize(new Dimension(1010, 40));
		getContentPane().add(bottompane, BorderLayout.SOUTH);
		
		JLabel lblnumerorecord = new JLabel();
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			lblnumerorecord.setText("Numero righe: " + Integer.toString(DBMySqlManager.islandRecordCount()));
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			lblnumerorecord.setText("Numero righe: " + Integer.toString(DBOracleManager.islandRecordCount()));
		}
		
		lblnumerorecord.setForeground(Color.BLUE);
		lblnumerorecord.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblnumerorecord.setBounds(30, 10, 180, 19);
		bottompane.add(lblnumerorecord);
		
		JLabel lblindirizzoip = new JLabel("Indirizzo Ip:");
		lblindirizzoip.setBounds(21, 30, 233, 23);
		pnlgestioneisole.add(lblindirizzoip);
		
		JLabel lblnomeisola = new JLabel("Nome Isola:");
		lblnomeisola.setBounds(21, 64, 233, 23);
		pnlgestioneisole.add(lblnomeisola);
				
		JLabel lbldescrizioneisola = new JLabel("Descrizione Isola:");
		lbldescrizioneisola.setBounds(21, 98, 233, 23);
		pnlgestioneisole.add(lbldescrizioneisola);
		
		JLabel lblabilitazione = new JLabel("Abilitazione:");
		lblabilitazione.setBounds(21, 166, 233, 23);
		pnlgestioneisole.add(lblabilitazione);
				
		JButton btninserisciisola = new JButton("Inserisci isola");
		btninserisciisola.setFont(new Font("Tahoma", Font.BOLD, 12));
		btninserisciisola.setBounds(21, 280, 170, 30);
		pnlgestioneisole.add(btninserisciisola);
		
		JButton btnsalvaisola = new JButton("Salva modifiche");
		btnsalvaisola.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnsalvaisola.setBounds(201, 280, 170, 30);
		pnlgestioneisole.add(btnsalvaisola);
		
		JButton btneliminaisola = new JButton("Elimina isola");
		btneliminaisola.setFont(new Font("Tahoma", Font.BOLD, 12));
		btneliminaisola.setBounds(381, 280, 170, 30);
		pnlgestioneisole.add(btneliminaisola);
		
		JButton btnannulla = new JButton("Annulla");
		btnannulla.setBounds(561, 280, 170, 30);
		btnannulla.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnlgestioneisole.add(btnannulla);
		
		//EVENTI SELEZIONI TABELLA
		tblisole.getSelectionModel().addListSelectionListener(new EventIslandTableSelection(this,tblisole,txtindirizzoip,txtnomeisola,txtdescrizioneisola,txtreparto,cmbabilitazione,cmbmodalitamultioperatore,cmbdichiarazionemultiordine,cmbricercaworkorderperarticolo));
							
		//EVENTI PULSANTE INSERISCI NUOVA ISOLA
		btninserisciisola.addActionListener(new EventIslandBtnInsert(this,tblisole,txtindirizzoip,txtnomeisola,txtdescrizioneisola,txtreparto,cmbabilitazione,cmbmodalitamultioperatore,cmbdichiarazionemultiordine,cmbricercaworkorderperarticolo,lblnumerorecord));
		
		//EVENTI PULSANTE SALVA MODIFICHE ISOLA
		btnsalvaisola.addActionListener(new EventIslandBtnUpdate(this,tblisole,txtindirizzoip,txtnomeisola,txtdescrizioneisola,txtreparto,cmbabilitazione,cmbmodalitamultioperatore,cmbdichiarazionemultiordine,cmbricercaworkorderperarticolo));
		
		//EVENTI PULSANTE CANCELLA ISOLA
		btneliminaisola.addActionListener(new EventIslandBtnDelete(this,tblisole,txtindirizzoip,txtnomeisola,txtdescrizioneisola,txtreparto,cmbabilitazione,cmbmodalitamultioperatore,cmbdichiarazionemultiordine,cmbricercaworkorderperarticolo,lblnumerorecord));
						
		//EVENTI PULSANTE ANNULLA
		btnannulla.addActionListener(new EventIslandBtnCancel(this));
		
		
	}
}
