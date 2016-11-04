package ide.links;

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

public class LinksWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtfiltrinomeisola;
	private JTextField txtfiltririsorsa;
	private JTextField txtrisorsa;
	private JTextField txtsequenza;
	private JTextField txtnomeisola;

	public LinksWindow() {

		String image ="image.gif";
		
		Image icon = Toolkit.getDefaultToolkit().getImage(image);
		
		setIconImage(icon);
		setTitle("Eurolls M.E.S. - MES Administrator - Gestione Abbinamento Isola - Risorsa");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				
		setBounds(40, 60, 1200, 800);
		getContentPane().setLayout(new BorderLayout());
				
		JPanel pnlgestioneabbinamenti = new JPanel();
		pnlgestioneabbinamenti.setBorder(new TitledBorder(null, "Gestione Abbinamento Isola - Risorsa:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlgestioneabbinamenti.setPreferredSize(new Dimension(1180, 290));
		pnlgestioneabbinamenti.setMaximumSize(new Dimension(1180, 290));
		pnlgestioneabbinamenti.setLayout(null);
				
		JScrollPane scrgestioneabbinamenti = new JScrollPane(pnlgestioneabbinamenti);
		scrgestioneabbinamenti.setPreferredSize(new Dimension(1210, 310));
		scrgestioneabbinamenti.setMaximumSize(new Dimension(1210, 310));
		getContentPane().add(scrgestioneabbinamenti, BorderLayout.NORTH);
		
		UIManager.put("ComboBox.background", new ColorUIResource(Color.WHITE));
		
		LinksTable tbllinks = new LinksTable();
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			LinksTableModel tm = new LinksTableModel(DBMySqlManager.getLinks(""),tbllinks.getHeaders());
			tbllinks.setModel(tm);
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			LinksTableModel tm = new LinksTableModel(DBOracleManager.getLinks(""),tbllinks.getHeaders());
			tbllinks.setModel(tm);
		}
		
		tbllinks.setLayout();
		tbllinks.setRowSelectionAllowed(true);
				
		JScrollPane scrabbinamenti = new JScrollPane();
		scrabbinamenti.setPreferredSize(new Dimension(1010, 450));
		scrabbinamenti.setMaximumSize(new Dimension(1010, 450));
		scrabbinamenti.setViewportView(tbllinks);
		getContentPane().add(scrabbinamenti, BorderLayout.CENTER);
		
		JPanel bottompane = new JPanel();
		bottompane.setLayout(null);
		bottompane.setPreferredSize(new Dimension(1010, 40));
		bottompane.setMaximumSize(new Dimension(1010, 40));
		getContentPane().add(bottompane, BorderLayout.SOUTH);
		
		JLabel lblnumerorecord = new JLabel();
		
		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			lblnumerorecord.setText("Numero righe: " + Integer.toString(DBMySqlManager.linksRecordCount()));
		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			lblnumerorecord.setText("Numero righe: " + Integer.toString(DBOracleManager.linksRecordCount()));
		}
				
		lblnumerorecord.setForeground(Color.BLUE);
		lblnumerorecord.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblnumerorecord.setBounds(30, 10, 180, 19);
		bottompane.add(lblnumerorecord);
		
		JPanel pnlfiltri = new JPanel();
		pnlfiltri.setBorder(new TitledBorder(null, "Filtri:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlfiltri.setBounds(781, 24, 379, 252);
		pnlgestioneabbinamenti.add(pnlfiltri);
		pnlfiltri.setLayout(null);
		
		JLabel lblfitrinomeisola = new JLabel("Nome Isola:");
		lblfitrinomeisola.setBounds(23, 30, 95, 23);
		pnlfiltri.add(lblfitrinomeisola);
		
		txtfiltrinomeisola = new JTextField();
		txtfiltrinomeisola.setForeground(Color.BLUE);
		txtfiltrinomeisola.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfiltrinomeisola.setBounds(122, 30, 235, 20);
		pnlfiltri.add(txtfiltrinomeisola);
		txtfiltrinomeisola.setColumns(10);
		
		JLabel lblfiltririsorsa = new JLabel("Risorsa:");
		lblfiltririsorsa.setBounds(23, 60, 95, 23);
		pnlfiltri.add(lblfiltririsorsa);
		
		txtfiltririsorsa = new JTextField();
		txtfiltririsorsa.setForeground(Color.BLUE);
		txtfiltririsorsa.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfiltririsorsa.setColumns(10);
		txtfiltririsorsa.setBounds(122, 60, 235, 20);
		pnlfiltri.add(txtfiltririsorsa);
		
		JButton btnfiltritrova = new JButton("Trova");
		btnfiltritrova.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnfiltritrova.setBounds(231, 205, 126, 30);
		pnlfiltri.add(btnfiltritrova);
		
		JButton btnfiltripuliscicampi = new JButton("Pulisci campi");
		btnfiltripuliscicampi.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnfiltripuliscicampi.setBounds(95, 205, 126, 30);
		pnlfiltri.add(btnfiltripuliscicampi);
		
		JPanel pnldati = new JPanel();
		pnldati.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dati:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnldati.setBounds(19, 24, 752, 252);
		pnlgestioneabbinamenti.add(pnldati);
		pnldati.setLayout(null);
				
		txtnomeisola = new JTextField();
		txtnomeisola.setForeground(Color.BLUE);
		txtnomeisola.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnomeisola.setColumns(10);
		txtnomeisola.setBounds(147, 30, 287, 20);
		pnldati.add(txtnomeisola);
		
		txtsequenza = new JTextField();
		txtsequenza.setForeground(Color.BLUE);
		txtsequenza.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtsequenza.setColumns(10);
		txtsequenza.setBounds(147, 60, 287, 20);
		pnldati.add(txtsequenza);
		
		txtrisorsa = new JTextField();
		txtrisorsa.setForeground(Color.BLUE);
		txtrisorsa.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtrisorsa.setColumns(10);
		txtrisorsa.setBounds(147, 90, 287, 20);
		pnldati.add(txtrisorsa);
		
		JLabel lblnomeisola = new JLabel("Nome Isola:");
		lblnomeisola.setBounds(20, 30, 117, 23);
		pnldati.add(lblnomeisola);
		
		JLabel lblsequenza = new JLabel("Sequenza:");
		lblsequenza.setBounds(20, 60, 117, 23);
		pnldati.add(lblsequenza);
		
		JLabel lblrisorsa = new JLabel("Risorsa:");
		lblrisorsa.setBounds(20, 90, 117, 23);
		pnldati.add(lblrisorsa);
		
		JLabel lblabilitazione = new JLabel("Abilitazione:");
		lblabilitazione.setBounds(20, 124, 117, 23);
		pnldati.add(lblabilitazione);
		
		JComboBox<String> cmbabilitazione = new JComboBox<String>();
		cmbabilitazione.setForeground(Color.BLUE);
		cmbabilitazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbabilitazione.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "0"}));
		cmbabilitazione.setBounds(147, 124, 65, 20);
		pnldati.add(cmbabilitazione);
		
		JButton btnannulla = new JButton("Annulla");
		btnannulla.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnannulla.setBounds(560, 205, 170, 30);
		pnldati.add(btnannulla);
		
		JButton btneliminaisola = new JButton("Elimina abbinamento");
		btneliminaisola.setFont(new Font("Tahoma", Font.BOLD, 12));
		btneliminaisola.setBounds(381, 205, 170, 30);
		pnldati.add(btneliminaisola);
		
		JButton btnsalvaisola = new JButton("Salva modifiche");
		btnsalvaisola.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnsalvaisola.setBounds(201, 205, 170, 30);
		pnldati.add(btnsalvaisola);
		
		JButton btninserisciisola = new JButton("Inserisci abbinamento");
		btninserisciisola.setFont(new Font("Tahoma", Font.BOLD, 12));
		btninserisciisola.setBounds(20, 205, 170, 30);
		pnldati.add(btninserisciisola);
		
		//EVENTI SELEZIONI TABELLA
		tbllinks.getSelectionModel().addListSelectionListener(new EventLinksTableSelection(this,tbllinks,txtnomeisola,txtsequenza,txtrisorsa,cmbabilitazione));
		
		//EVENTI PULSANTE INSERISCI NUOVA ISOLA
		btninserisciisola.addActionListener(new EventLinksBtnInsert(this,tbllinks,txtnomeisola,txtsequenza,txtrisorsa,cmbabilitazione,lblnumerorecord));
		
		//EVENTI PULSANTE SALVA MODIFICHE ISOLA
		btnsalvaisola.addActionListener(new EventLinksBtnUpdate(this,tbllinks,txtnomeisola,txtsequenza,txtrisorsa,cmbabilitazione));
		
		//EVENTI PULSANTE CANCELLA ISOLA
		btneliminaisola.addActionListener(new EventLinksBtnDelete(this,tbllinks,txtnomeisola,txtsequenza,txtrisorsa,cmbabilitazione,lblnumerorecord));
		
		//EVENTI PULSANTE TROVA
		btnfiltritrova.addActionListener(new EventLinksBtnFindFilter(this,tbllinks,txtnomeisola,txtsequenza,txtrisorsa,txtfiltrinomeisola,txtfiltririsorsa,cmbabilitazione,lblnumerorecord));
				
		//EVENTI CANCELLA FILTRI
		btnfiltripuliscicampi.addActionListener(new EventLinksBtnClearFilter(this,txtfiltrinomeisola,txtfiltririsorsa));
		
		//EVENTI PULSANTE ANNULLA
		btnannulla.addActionListener(new EventLinksBtnCancel(this));
		
	}
}
