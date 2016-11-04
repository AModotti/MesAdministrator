package ide.main;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import java.awt.Font;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

import bin.ColumnManager;
import bin.FilterManager;
import bin.FiltersSerialization;
import bin.Settings;
import bin.TableSerialization;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MainTable tbldatitransazioni;
	HashMap<Integer, Integer> column =  new HashMap<>();
	HashMap<Integer, Object> filters =  new HashMap<>();
	private MainTableModel tm;
	private JTextField txtdadata;
	private JTextField txtadata;
	private JTextField txtreparto;
	private JTextField txtisola;
	private JTextField txtstato;
	private JTextField txttipo;
	private JTextField txtbranch;
	private JTextField txtrisorsa;
	private JTextField txtdescrizionerisorsa;
	private JTextField txtoperatore;
	private JTextField txtdescrizioneoperatore;
	private JTextField txtworkorder;
	private JTextField txtoperazione;
	private JTextField txtcommessa;
	private JTextField txtarticolo;
	private JTextField txtdescrizionearticolo;
	private JCheckBox chcvisualizzarecoreliminati;
	private JCheckBox chcvisualizzarecordprocessati;
	private JButton btntrova;
	
	@SuppressWarnings("unchecked")
	public MainWindow() {
		
		String image ="image.gif";
		
		Image icon = Toolkit.getDefaultToolkit().getImage(image);
		
		setIconImage(icon);
		setTitle("Eurolls M.E.S. - MES Administrator" + "   " + " [ Ambiente: " + Settings.getEnvironment() + " ( " + Settings.getDbConnectionString() + " )" + " | " + Settings.getParameters() + " ]");
		
		setBounds(5, 5, 1270, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
					
		JPanel pnlfiltri = new JPanel();
		pnlfiltri.setPreferredSize(new Dimension(250, 760));
		pnlfiltri.setMaximumSize(new Dimension(250, 760));
		pnlfiltri.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Filtri di ricerca:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlfiltri.setLayout(null);
	
		JScrollPane scrfiltri = new JScrollPane(pnlfiltri);
		scrfiltri.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrfiltri.setPreferredSize(new Dimension(270, 830));
		scrfiltri.setMaximumSize(new Dimension(270, 830));
		getContentPane().add(scrfiltri, BorderLayout.EAST);
		
		if(TableSerialization.deserialize() == null){
    		column = ColumnManager.getColumnInformation();
    	}else{
    		column = (HashMap<Integer, Integer>) TableSerialization.deserialize();
    		ColumnManager.setColumnInformation(column);
    	}
		tbldatitransazioni = new MainTable();	
					
        tm = new MainTableModel(null,tbldatitransazioni.getHeaders());
        tbldatitransazioni.setModel(tm);
        tbldatitransazioni.getTableLayout();
		tbldatitransazioni.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		tbldatitransazioni.getTableHeader().addMouseListener(new MainMouseAdapter());
				
		JScrollPane scrdati = new JScrollPane();
		scrdati.setBounds(10, 25, 452, 427);
		scrdati.setPreferredSize(new Dimension(1000, 830));
		scrdati.setMaximumSize(new Dimension(1000, 830));
		scrdati.setViewportView(tbldatitransazioni);
		getContentPane().add(scrdati, BorderLayout.CENTER);
	
		JPanel pnlsuperiore = new JPanel();
		pnlsuperiore.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlsuperiore.setPreferredSize(new Dimension(1270, 30));
		pnlsuperiore.setMaximumSize(new Dimension(1270, 30));
		getContentPane().add(pnlsuperiore, BorderLayout.NORTH);
		pnlsuperiore.setLayout(null);
		
		JPanel pnlnotifiche = new JPanel();
		pnlnotifiche.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlnotifiche.setPreferredSize(new Dimension(1270, 40));
		pnlnotifiche.setMaximumSize(new Dimension(1270, 40));
		getContentPane().add(pnlnotifiche, BorderLayout.SOUTH);
		pnlnotifiche.setLayout(null);
		
		JLabel lblnumerorecord = new JLabel("Numero righe: 0");
		lblnumerorecord.setForeground(Color.BLUE);
		lblnumerorecord.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblnumerorecord.setBounds(30, 10, 180, 19);
		pnlnotifiche.add(lblnumerorecord);
		
		JPanel pnlpulsanti = new JPanel();
		pnlpulsanti.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlpulsanti.setPreferredSize(new Dimension(50, 840));
		pnlpulsanti.setMaximumSize(new Dimension(50, 840));
		getContentPane().add(pnlpulsanti, BorderLayout.WEST);
		pnlpulsanti.setLayout(null);
		
		JLabel lbldadata = new JLabel("Da data:");
		lbldadata.setBounds(10, 28, 100, 14);
		pnlfiltri.add(lbldadata);
		
		txtdadata = new JTextField();
		txtadata = new JTextField();
		txtreparto = new JTextField();
		txtisola = new JTextField();
		txtstato = new JTextField();
		txttipo = new JTextField();
		txtbranch = new JTextField();
		txtrisorsa = new JTextField();
		txtdescrizionerisorsa = new JTextField();
		txtoperatore = new JTextField();
		txtdescrizioneoperatore = new JTextField();
		txtworkorder = new JTextField();
		txtoperazione = new JTextField();
		txtcommessa = new JTextField();
		txtarticolo = new JTextField();
		txtdescrizionearticolo = new JTextField();
		chcvisualizzarecoreliminati = new JCheckBox("Visualizza record eliminati [ D ]");
		chcvisualizzarecordprocessati = new JCheckBox("Visualizza record processati [ P ]");
		
		if(FiltersSerialization.deserialize() != null){
			filters = FiltersSerialization.deserialize();
			FilterManager.setFiltersInformation(filters);
			txtdadata.setText(filters.get(0).toString());	
			txtadata.setText(filters.get(1).toString());
			txtreparto.setText(filters.get(2).toString());
			txtisola.setText(filters.get(3).toString());
			txtstato.setText(filters.get(4).toString());
			txtstato.setText(filters.get(5).toString());
			txtbranch.setText(filters.get(6).toString());
			txtrisorsa.setText(filters.get(7).toString());
			txtdescrizionerisorsa.setText(filters.get(8).toString());
			txtoperatore.setText(filters.get(9).toString());
			txtdescrizioneoperatore.setText(filters.get(10).toString());
			txtworkorder.setText(filters.get(11).toString());
			txtoperazione.setText(filters.get(12).toString());
			txtcommessa.setText(filters.get(13).toString());
			txtarticolo.setText(filters.get(14).toString());
			txtdescrizionearticolo.setText(filters.get(15).toString());
			chcvisualizzarecoreliminati.setSelected((boolean) filters.get(16));
			chcvisualizzarecordprocessati.setSelected((boolean)filters.get(17));
		}
		
		txtdadata.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdadata.setForeground(Color.BLUE);
		txtdadata.setBounds(10, 44, 100, 20);
		pnlfiltri.add(txtdadata);
		txtdadata.setColumns(10);
		
		JLabel lbladata = new JLabel("A data:");
		lbladata.setBounds(140, 28, 100, 14);
		pnlfiltri.add(lbladata);
		
		txtadata.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtadata.setForeground(Color.BLUE);
		txtadata.setColumns(10);
		txtadata.setBounds(140, 44, 100, 20);
		pnlfiltri.add(txtadata);
		
		JLabel lblreparto = new JLabel("Reparto:");
		lblreparto.setBounds(10, 75, 100, 14);
		pnlfiltri.add(lblreparto);
		
		txtreparto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtreparto.setForeground(Color.BLUE);
		txtreparto.setColumns(10);
		txtreparto.setBounds(10, 91, 100, 20);
		pnlfiltri.add(txtreparto);
		
		JLabel lblisola = new JLabel("Isola di lavoro:");
		lblisola.setBounds(140, 75, 100, 14);
		pnlfiltri.add(lblisola);
		
		txtisola.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtisola.setForeground(Color.BLUE);
		txtisola.setColumns(10);
		txtisola.setBounds(140, 91, 100, 20);
		pnlfiltri.add(txtisola);
		
		JLabel lblstato = new JLabel("Stato:");
		lblstato.setBounds(10, 122, 100, 14);
		pnlfiltri.add(lblstato);
		
		txtstato.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtstato.setForeground(Color.BLUE);
		txtstato.setColumns(10);
		txtstato.setBounds(10, 138, 100, 20);
		pnlfiltri.add(txtstato);
		
		JLabel lbltipo = new JLabel("Tipo:");
		lbltipo.setBounds(140, 122, 100, 14);
		pnlfiltri.add(lbltipo);
		
		txttipo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txttipo.setForeground(Color.BLUE);
		txttipo.setColumns(10);
		txttipo.setBounds(140, 138, 100, 20);
		pnlfiltri.add(txttipo);

		JLabel lblbranch = new JLabel("Branch:");
		lblbranch.setBounds(10, 169, 100, 14);
		pnlfiltri.add(lblbranch);
		
		txtbranch.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtbranch.setForeground(Color.BLUE);
		txtbranch.setColumns(10);
		txtbranch.setBounds(10, 185, 100, 20);
		pnlfiltri.add(txtbranch);
		
		JLabel lblrisorsa = new JLabel("Risorsa:");
		lblrisorsa.setBounds(10, 216, 100, 14);
		pnlfiltri.add(lblrisorsa);
		
		txtrisorsa.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtrisorsa.setForeground(Color.BLUE);
		txtrisorsa.setColumns(10);
		txtrisorsa.setBounds(10, 232, 100, 20);
		pnlfiltri.add(txtrisorsa);
		
		JLabel lbldescrizionerisorsa = new JLabel("Descrizione risorsa:");
		lbldescrizionerisorsa.setBounds(10, 263, 133, 14);
		pnlfiltri.add(lbldescrizionerisorsa);
		
		txtdescrizionerisorsa.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdescrizionerisorsa.setForeground(Color.BLUE);
		txtdescrizionerisorsa.setColumns(10);
		txtdescrizionerisorsa.setBounds(10, 279, 230, 20);
		pnlfiltri.add(txtdescrizionerisorsa);
		
		JLabel lbloperatore = new JLabel("Operatore:");
		lbloperatore.setBounds(10, 310, 100, 14);
		pnlfiltri.add(lbloperatore);
		
		txtoperatore.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtoperatore.setForeground(Color.BLUE);
		txtoperatore.setColumns(10);
		txtoperatore.setBounds(10, 326, 100, 20);
		pnlfiltri.add(txtoperatore);
		
		JLabel lbldescrizioneoperatore = new JLabel("Descrizione operatore:");
		lbldescrizioneoperatore.setBounds(10, 357, 133, 14);
		pnlfiltri.add(lbldescrizioneoperatore);
		
		txtdescrizioneoperatore.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdescrizioneoperatore.setForeground(Color.BLUE);
		txtdescrizioneoperatore.setColumns(10);
		txtdescrizioneoperatore.setBounds(10, 373, 230, 20);
		pnlfiltri.add(txtdescrizioneoperatore);
		
		JLabel lblworkorder = new JLabel("Work order:");
		lblworkorder.setBounds(10, 404, 100, 14);
		pnlfiltri.add(lblworkorder);
		
		txtworkorder.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtworkorder.setForeground(Color.BLUE);
		txtworkorder.setColumns(10);
		txtworkorder.setBounds(10, 420, 100, 20);
		pnlfiltri.add(txtworkorder);
		
		JLabel lbloperazione = new JLabel("Operazione:");
		lbloperazione.setBounds(140, 404, 100, 14);
		pnlfiltri.add(lbloperazione);
		
		txtoperazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtoperazione.setForeground(Color.BLUE);
		txtoperazione.setColumns(10);
		txtoperazione.setBounds(140, 420, 100, 20);
		pnlfiltri.add(txtoperazione);
		
		JLabel lblcommessa = new JLabel("Commessa:");
		lblcommessa.setBounds(10, 451, 100, 14);
		pnlfiltri.add(lblcommessa);
		
		txtcommessa.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtcommessa.setForeground(Color.BLUE);
		txtcommessa.setColumns(10);
		txtcommessa.setBounds(10, 467, 100, 20);
		pnlfiltri.add(txtcommessa);
		
		JLabel lblarticolo = new JLabel("Articolo:");
		lblarticolo.setBounds(10, 498, 100, 14);
		pnlfiltri.add(lblarticolo);
		
		txtarticolo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtarticolo.setForeground(Color.BLUE);
		txtarticolo.setColumns(10);
		txtarticolo.setBounds(10, 514, 230, 20);
		pnlfiltri.add(txtarticolo);
		
		JLabel lbldescrizionearticolo = new JLabel("Descrizione articolo:");
		lbldescrizionearticolo.setBounds(10, 545, 133, 14);
		pnlfiltri.add(lbldescrizionearticolo);
		
		txtdescrizionearticolo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdescrizionearticolo.setForeground(Color.BLUE);
		txtdescrizionearticolo.setColumns(10);
		txtdescrizionearticolo.setBounds(10, 561, 230, 20);
		pnlfiltri.add(txtdescrizionearticolo);
		
		chcvisualizzarecoreliminati.setBounds(10, 599, 230, 23);
		pnlfiltri.add(chcvisualizzarecoreliminati);
		
		chcvisualizzarecordprocessati.setBounds(10, 625, 230, 23);
		pnlfiltri.add(chcvisualizzarecordprocessati);
				
		btntrova = new JButton("Trova");
		btntrova.setFont(new Font("Tahoma", Font.BOLD, 12));
		btntrova.setBounds(31, 661, 209, 30);
		pnlfiltri.add(btntrova);
				
		JButton btnpulisci = new JButton("Pulisci campi");
		btnpulisci.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnpulisci.setBounds(31, 704, 209, 30);
		pnlfiltri.add(btnpulisci);
				
		JMenuBar mainmenu = new JMenuBar();
		mainmenu.setToolTipText("");
		setJMenuBar(mainmenu);
		
		JMenu mntransazioni = new JMenu("Gestione Transazioni");
		mainmenu.add(mntransazioni);
		
		JMenuItem mninseriscitransazione = new JMenuItem("Inserisci Transazione");
		mntransazioni.add(mninseriscitransazione);
		
		JMenuItem mnmodificatransazione = new JMenuItem("Modifica Transazioni");
		mntransazioni.add(mnmodificatransazione);
				
		JMenuItem mncopiatransazione = new JMenuItem("Copia Transazione");
		mntransazioni.add(mncopiatransazione);
		
		JMenuItem mneliminatransazione = new JMenuItem("Elimina Transazione");
		mntransazioni.add(mneliminatransazione);
		
		JMenu mnimpostazioni = new JMenu("Impostazioni");
		mainmenu.add(mnimpostazioni);
		
		JMenuItem mngestionereparti = new JMenuItem("Gestione Reparti");
		mnimpostazioni.add(mngestionereparti);
		
		JMenuItem mngestioneisoladilavoro = new JMenuItem("Gestione Isola di Lavoro");
		mnimpostazioni.add(mngestioneisoladilavoro);
		
		JMenuItem mngestioneabbinamentoisolarisorsa = new JMenuItem("Gestione Abbinamento Isola - Risorsa");
		mnimpostazioni.add(mngestioneabbinamentoisolarisorsa);
		
		JMenu mntrasferimentodati = new JMenu("Trasferimento Dati");
		mainmenu.add(mntrasferimentodati);
		
		JMenuItem mnimportazionedatiinjde = new JMenuItem("Importazione Dati in JDE");
		mntrasferimentodati.add(mnimportazionedatiinjde);
		
		JMenu mnutilità = new JMenu("Utilit\u00E0");
		mainmenu.add(mnutilità);
		
		JMenuItem mnesportaexcel = new JMenuItem("Esporta in Excel");
		mnutilità.add(mnesportaexcel);
		
		JMenuItem mnsalvalarghezzacolonne = new JMenuItem("Salva Larghezza Colonne");
		mnutilità.add(mnsalvalarghezzacolonne);
		
		JMenuItem mnsalvaimpostazionifiltro = new JMenuItem("Salva Impostazioni Filtro");
		mnutilità.add(mnsalvaimpostazionifiltro);
		
		JMenu mnInfo = new JMenu("Info");
		mainmenu.add(mnInfo);
		
		JMenuItem mninfoaggiornamentoclient = new JMenuItem("Info Aggiornamento Client");
		mnInfo.add(mninfoaggiornamentoclient);
		
		JPopupMenu popupmenu = new JPopupMenu();
		JMenuItem puinseriscitransazione = new JMenuItem("Inserisci Transazione");
		JMenuItem pumodificatransazione = new JMenuItem("Modifica Transazioni");
		JMenuItem pucopiatransazione = new JMenuItem("Copia Transazione");
		JMenuItem pueliminatransazione = new JMenuItem("Elimina Transazione");
		
		popupmenu.add(puinseriscitransazione);
		popupmenu.add(pumodificatransazione);
		popupmenu.add(pucopiatransazione);
		popupmenu.add(pueliminatransazione);
		tbldatitransazioni.setComponentPopupMenu(popupmenu);
		
		JRootPane rootPane = getRootPane();
		rootPane.setDefaultButton(btntrova);

		
		//EVENTI MENU INSERISCI TRANSAZIONI
		mninseriscitransazione.addActionListener(new EventMainBtnInsertTransaction(tbldatitransazioni,lblnumerorecord));
		
		//EVENTI POPUP INSERISCI TRANSAZIONI
		puinseriscitransazione.addActionListener(new EventMainBtnInsertTransaction(tbldatitransazioni,lblnumerorecord));

		//EVENTI MENU MODIFICA TRANSAZIONI
		mnmodificatransazione.addActionListener(new EventMainBtnEditTransaction(tbldatitransazioni));
		
		//EVENTI POPUP MODIFICA TRANSAZIONI
		pumodificatransazione.addActionListener(new EventMainBtnEditTransaction(tbldatitransazioni));
		
		//EVENTI MENU DUPLICA TRANSAZIONI
		mncopiatransazione.addActionListener(new EventMainBtnCopyTransaction(tbldatitransazioni,lblnumerorecord));
				
		//EVENTI POPUP DUPLICA TRANSAZIONI
		pucopiatransazione.addActionListener(new EventMainBtnCopyTransaction(tbldatitransazioni,lblnumerorecord));
		
		//EVENTI MENU ELIMINA TRANSAZIONI
		mneliminatransazione.addActionListener(new EventMainBtnDeleteTransaction(this,tbldatitransazioni,lblnumerorecord));
				
		//EVENTI POPUP ELIMINA TRANSAZIONI
		pueliminatransazione.addActionListener(new EventMainBtnDeleteTransaction(this,tbldatitransazioni,lblnumerorecord));
		
		//EVENTI MENU GESTIONE REPARTO
		mngestionereparti.addActionListener(new EventMainBtnManageDept(this));
				
		//EVENTI MENU GESTIONE ISOLE DI LAVORO
		mngestioneisoladilavoro.addActionListener(new EventMainBtnManageIsland(this));
		
		//EVENTI MENU GESTIONE LEGAMI ISOLE DI LAVORO
		mngestioneabbinamentoisolarisorsa.addActionListener(new EventMainBtnManageLinks(this));
		
		//EVENTI MENU TRASFERIMENTO DATI A JDE
		mnimportazionedatiinjde.addActionListener(new EventMainBtnImportDataToJde(this));
		
		//EVENTI PULSANTE TROVA
		btntrova.addActionListener(new EventMainBtnFindFilter(this,tbldatitransazioni,lblnumerorecord,txtdadata,txtadata,txtreparto,txtisola,txtstato,txttipo,txtbranch,txtrisorsa,txtdescrizionerisorsa,txtoperatore,txtdescrizioneoperatore,txtworkorder,txtoperazione,txtcommessa,txtarticolo,txtdescrizionearticolo,chcvisualizzarecoreliminati,chcvisualizzarecordprocessati));
				
		//EVENTI PULSANTE PULISCI CAMPI
		btnpulisci.addActionListener(new EventMainBtnClearFilter(this,txtdadata,txtadata,txtreparto,txtisola,txtstato,txttipo,txtbranch,txtrisorsa,txtdescrizionerisorsa,txtoperatore,txtdescrizioneoperatore,txtworkorder,txtoperazione,txtcommessa,txtarticolo,txtdescrizionearticolo,chcvisualizzarecoreliminati,chcvisualizzarecordprocessati));
		
		//EVENTI ESPORTA DATI IN EXCEL
		mnesportaexcel.addActionListener(new EventMainBtnExportToExcelFile(this,tbldatitransazioni));
		
		//EVENTI SALVA TABELLA
		mnsalvalarghezzacolonne.addActionListener(new EventMainBtnSaveTableSettings(this));
			
		//EVENTI INFO AGGIORNAMENTI
		mninfoaggiornamentoclient.addActionListener(new EventMainBtnClientUpdateInfo(this));
		
		//EVENTI SALVA IMPOSTAZIONI FILTRO
		mnsalvaimpostazionifiltro.addActionListener(new EventMainBtnSaveFilterSettings(this,txtdadata,txtadata,txtreparto,txtisola,txtstato,txttipo,txtbranch,txtrisorsa,txtdescrizionerisorsa,txtoperatore,txtdescrizioneoperatore,txtworkorder,txtoperazione,txtcommessa,txtarticolo,txtdescrizionearticolo,chcvisualizzarecoreliminati,chcvisualizzarecordprocessati));
		
		//EVENTI CHIUSURA MAIN WINDOWS
	    addWindowListener(new EventsCloseProgram());
				
	}
	
	public JButton getDafaultButton(){
		return btntrova;
	}
}
