package ide.insert;

import ide.main.MainTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

public class InsertWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtreparto;
	private JTextField txtisola;
	private JTextField txtdatatransazione;
	private JTextField txtstato;
	private JTextField txttipo;
	private JTextField txtbranch;
	private JTextField txtrisorsa;
	private JTextField txtoperatore;
	private JTextField txtworkorder;
	private JTextField txtoperazione;
	private JTextField e1;
	private JTextField e2;
	private JTextField e3;
	private JTextField e4;
	private JTextField e5;
	private JTextField e6;
	private JTextField e7;
	private JTextField e8;
	private JTextField e9;
	private JTextField e10;

	public InsertWindow(MainTable tblDatiTransazioni,JLabel lblnumerorecord) {

		String image ="image.gif";
		
		Image icon = Toolkit.getDefaultToolkit().getImage(image);
		
		setIconImage(icon);
		setTitle("Eurolls M.E.S. - MES Administrator - Inserisci Transazioni");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				
		setBounds(100, 100, 650, 500);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel buttonPane = new JPanel();
		buttonPane.setPreferredSize(new Dimension(436, 40));
		buttonPane.setMaximumSize(new Dimension(436, 40));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(null);
		
		JButton btnsalva = new JButton("Salva");
		btnsalva.setBounds(434, 4, 90, 30);
		btnsalva.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonPane.add(btnsalva);
		getRootPane().setDefaultButton(btnsalva);
		
		JButton btnannulla = new JButton("Annulla");
		btnannulla.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnannulla.setBounds(534, 4, 90, 30);
		buttonPane.add(btnannulla);
			
		JPanel pnlmodificarecords = new JPanel();
		pnlmodificarecords.setBorder(new TitledBorder(null, "Inserisci transazioni:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlmodificarecords, BorderLayout.CENTER);
		pnlmodificarecords.setLayout(null);
		
		JLabel lblreparto = new JLabel("Reparto:");
		lblreparto.setBounds(26, 35, 64, 20);
		pnlmodificarecords.add(lblreparto);
		
		txtreparto = new JTextField();
		txtreparto.setColumns(10);
		txtreparto.setForeground(Color.BLUE);
		txtreparto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtreparto.setBounds(137, 35, 207, 20);
		pnlmodificarecords.add(txtreparto);
		
		JLabel lblisola = new JLabel("Isola:");
		lblisola.setBounds(26, 70, 64, 20);
		pnlmodificarecords.add(lblisola);
		
		txtisola = new JTextField();
		txtisola.setForeground(Color.BLUE);
		txtisola.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtisola.setColumns(10);
		txtisola.setBounds(137, 70, 207, 20);
		pnlmodificarecords.add(txtisola);
		
		JLabel lbldatatransazione = new JLabel("Data Transazione:");
		lbldatatransazione.setBounds(26, 105, 111, 20);
		pnlmodificarecords.add(lbldatatransazione);
		
		txtdatatransazione = new JTextField();
		txtdatatransazione.setForeground(Color.BLUE);
		txtdatatransazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdatatransazione.setColumns(10);
		txtdatatransazione.setBounds(137, 105, 207, 20);
		pnlmodificarecords.add(txtdatatransazione);
		
		JLabel lblstato = new JLabel("Stato:");
		lblstato.setBounds(26, 175, 111, 20);
		pnlmodificarecords.add(lblstato);
		
		txtstato = new JTextField();
		txtstato.setForeground(Color.BLUE);
		txtstato.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtstato.setColumns(10);
		txtstato.setBounds(137, 175, 207, 20);
		pnlmodificarecords.add(txtstato);
		
		txttipo = new JTextField();
		txttipo.setForeground(Color.BLUE);
		txttipo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txttipo.setColumns(10);
		txttipo.setBounds(137, 140, 207, 20);
		pnlmodificarecords.add(txttipo);
		
		JLabel lbltipo = new JLabel("Tipo:");
		lbltipo.setBounds(26, 140, 111, 20);
		pnlmodificarecords.add(lbltipo);
		
		JLabel lblbranch = new JLabel("Branch:");
		lblbranch.setBounds(26, 210, 111, 20);
		pnlmodificarecords.add(lblbranch);
		
		txtbranch = new JTextField();
		txtbranch.setForeground(Color.BLUE);
		txtbranch.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtbranch.setColumns(10);
		txtbranch.setBounds(137, 210, 207, 20);
		pnlmodificarecords.add(txtbranch);
		
		JLabel lblrisorsa = new JLabel("Risorsa:");
		lblrisorsa.setBounds(26, 245, 111, 20);
		pnlmodificarecords.add(lblrisorsa);
		
		txtrisorsa = new JTextField();
		txtrisorsa.setForeground(Color.BLUE);
		txtrisorsa.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtrisorsa.setColumns(10);
		txtrisorsa.setBounds(137, 245, 207, 20);
		pnlmodificarecords.add(txtrisorsa);
		
		JLabel lbloperatore = new JLabel("Operatore:");
		lbloperatore.setBounds(26, 280, 111, 20);
		pnlmodificarecords.add(lbloperatore);
		
		txtoperatore = new JTextField();
		txtoperatore.setForeground(Color.BLUE);
		txtoperatore.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtoperatore.setColumns(10);
		txtoperatore.setBounds(137, 280, 207, 20);
		pnlmodificarecords.add(txtoperatore);
		
		JLabel lblworkorder = new JLabel("Work Order:");
		lblworkorder.setBounds(26, 315, 111, 20);
		pnlmodificarecords.add(lblworkorder);
		
		txtworkorder = new JTextField();
		txtworkorder.setForeground(Color.BLUE);
		txtworkorder.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtworkorder.setColumns(10);
		txtworkorder.setBounds(137, 315, 207, 20);
		pnlmodificarecords.add(txtworkorder);
		
		JLabel lbloperazione = new JLabel("Operazione:");
		lbloperazione.setBounds(26, 350, 111, 20);
		pnlmodificarecords.add(lbloperazione);
		
		txtoperazione = new JTextField();
		txtoperazione.setForeground(Color.BLUE);
		txtoperazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtoperazione.setColumns(10);
		txtoperazione.setBounds(137, 350, 207, 20);
		pnlmodificarecords.add(txtoperazione);
		
		JLabel lbldepterror = new JLabel("");
		lbldepterror.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbldepterror.setForeground(Color.RED);
		lbldepterror.setBounds(355, 35, 244, 20);
		pnlmodificarecords.add(lbldepterror);
		
		JLabel lblislanderror = new JLabel("");
		lblislanderror.setForeground(Color.RED);
		lblislanderror.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblislanderror.setBounds(354, 70, 245, 20);
		pnlmodificarecords.add(lblislanderror);
		
		JLabel lbldateerror = new JLabel("");
		lbldateerror.setForeground(Color.RED);
		lbldateerror.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbldateerror.setBounds(354, 105, 245, 20);
		pnlmodificarecords.add(lbldateerror);
		
		JLabel lbltypeerror = new JLabel("");
		lbltypeerror.setForeground(Color.RED);
		lbltypeerror.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbltypeerror.setBounds(354, 140, 245, 20);
		pnlmodificarecords.add(lbltypeerror);
		
		JLabel lblstateerror = new JLabel("");
		lblstateerror.setForeground(Color.RED);
		lblstateerror.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblstateerror.setBounds(354, 175, 245, 20);
		pnlmodificarecords.add(lblstateerror);
		
		JLabel lblbrancherror = new JLabel("");
		lblbrancherror.setForeground(Color.RED);
		lblbrancherror.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblbrancherror.setBounds(354, 210, 245, 20);
		pnlmodificarecords.add(lblbrancherror);
		
		JLabel lblresourceerror = new JLabel("");
		lblresourceerror.setForeground(Color.RED);
		lblresourceerror.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblresourceerror.setBounds(354, 245, 245, 20);
		pnlmodificarecords.add(lblresourceerror);
		
		JLabel lbloperatorerror = new JLabel("");
		lbloperatorerror.setForeground(Color.RED);
		lbloperatorerror.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbloperatorerror.setBounds(354, 280, 245, 20);
		pnlmodificarecords.add(lbloperatorerror);
		
		JLabel lblworkordererror = new JLabel("");
		lblworkordererror.setForeground(Color.RED);
		lblworkordererror.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblworkordererror.setBounds(354, 315, 245, 20);
		pnlmodificarecords.add(lblworkordererror);
		
		JLabel lbloperationerror = new JLabel("");
		lbloperationerror.setForeground(Color.RED);
		lbloperationerror.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbloperationerror.setBounds(355, 350, 244, 20);
		pnlmodificarecords.add(lbloperationerror);
		
		e1 = new JTextField("1");
		e1.setEnabled(false);
		e1.setHorizontalAlignment(SwingConstants.CENTER);
		e1.setForeground(Color.BLUE);
		e1.setFont(new Font("Tahoma", Font.BOLD, 12));
		e1.setBounds(609, 34, 15, 20);
		pnlmodificarecords.add(e1);
		e1.setColumns(10);
		
		e2 = new JTextField("1");
		e2.setEnabled(false);
		e2.setHorizontalAlignment(SwingConstants.CENTER);
		e2.setForeground(Color.BLUE);
		e2.setFont(new Font("Tahoma", Font.BOLD, 12));
		e2.setColumns(10);
		e2.setBounds(609, 70, 15, 20);
		pnlmodificarecords.add(e2);
		
		e3 = new JTextField("1");
		e3.setEnabled(false);
		e3.setHorizontalAlignment(SwingConstants.CENTER);
		e3.setForeground(Color.BLUE);
		e3.setFont(new Font("Tahoma", Font.BOLD, 12));
		e3.setColumns(10);
		e3.setBounds(609, 105, 15, 20);
		pnlmodificarecords.add(e3);
		
		e4 = new JTextField("1");
		e4.setEnabled(false);
		e4.setHorizontalAlignment(SwingConstants.CENTER);
		e4.setForeground(Color.BLUE);
		e4.setFont(new Font("Tahoma", Font.BOLD, 12));
		e4.setColumns(10);
		e4.setBounds(609, 140, 15, 20);
		pnlmodificarecords.add(e4);
		
		e5 = new JTextField("1");
		e5.setEnabled(false);
		e5.setHorizontalAlignment(SwingConstants.CENTER);
		e5.setForeground(Color.BLUE);
		e5.setFont(new Font("Tahoma", Font.BOLD, 12));
		e5.setColumns(10);
		e5.setBounds(609, 175, 15, 20);
		pnlmodificarecords.add(e5);
		
		e6 = new JTextField("1");
		e6.setEnabled(false);
		e6.setHorizontalAlignment(SwingConstants.CENTER);
		e6.setForeground(Color.BLUE);
		e6.setFont(new Font("Tahoma", Font.BOLD, 12));
		e6.setColumns(10);
		e6.setBounds(609, 210, 15, 20);
		pnlmodificarecords.add(e6);
		
		e7 = new JTextField("1");
		e7.setEnabled(false);
		e7.setHorizontalAlignment(SwingConstants.CENTER);
		e7.setForeground(Color.BLUE);
		e7.setFont(new Font("Tahoma", Font.BOLD, 12));
		e7.setColumns(10);
		e7.setBounds(609, 245, 15, 20);
		pnlmodificarecords.add(e7);
		
		e8 = new JTextField("1");
		e8.setEnabled(false);
		e8.setHorizontalAlignment(SwingConstants.CENTER);
		e8.setForeground(Color.BLUE);
		e8.setFont(new Font("Tahoma", Font.BOLD, 12));
		e8.setColumns(10);
		e8.setBounds(609, 280, 15, 20);
		pnlmodificarecords.add(e8);
		
		e9 = new JTextField("1");
		e9.setEnabled(false);
		e9.setHorizontalAlignment(SwingConstants.CENTER);
		e9.setForeground(Color.BLUE);
		e9.setFont(new Font("Tahoma", Font.BOLD, 12));
		e9.setColumns(10);
		e9.setBounds(609, 315, 15, 20);
		pnlmodificarecords.add(e9);
		
		e10 = new JTextField("1");
		e10.setEnabled(false);
		e10.setHorizontalAlignment(SwingConstants.CENTER);
		e10.setForeground(Color.BLUE);
		e10.setFont(new Font("Tahoma", Font.BOLD, 12));
		e10.setColumns(10);
		e10.setBounds(609, 350, 15, 20);
		pnlmodificarecords.add(e10);

		txtreparto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				InsertVerifierForDept iv = new InsertVerifierForDept(txtreparto,e1);
				lbldepterror.setText(iv.verify());
			}
		});
		
		txtisola.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				InsertVerifierForIsland iv = new InsertVerifierForIsland(txtisola,e2);
				lblislanderror.setText(iv.verify());
			}
		});
		
		txtdatatransazione.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				InsertVerifierForDate iv = new InsertVerifierForDate(txtdatatransazione,e3);
				lbldateerror.setText(iv.verify());
			}
		});
		
		txttipo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				InsertVerifierForType iv = new InsertVerifierForType(txttipo,e4,e5,e6,e7,e8,e9,e10,txtstato,txtbranch,txtrisorsa,txtoperatore,txtworkorder,txtoperazione,lblbrancherror,lblresourceerror,lbloperatorerror,lblstateerror,lblworkordererror,lbloperationerror);
				lbltypeerror.setText(iv.verify());
			}
		});
		
		txtstato.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				InsertVerifierForState iv = new InsertVerifierForState(txttipo,txtstato,e5);
				lblstateerror.setText(iv.verify());
			}
		});
		
		txtbranch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				InsertVerifierForBranch iv = new InsertVerifierForBranch(txtbranch,e6);
				lblbrancherror.setText(iv.verify());
			}
		});
		
		txtrisorsa.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				InsertVerifierForResource iv = new InsertVerifierForResource(txtrisorsa,e7);
				lblresourceerror.setText(iv.verify());
			}
		});
		
		txtoperatore.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				InsertVerifierForOperator iv = new InsertVerifierForOperator(txtoperatore,e8);
				lbloperatorerror.setText(iv.verify());
			}
		});
		
		txtworkorder.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				InsertVerifierForWorkOrder iv = new InsertVerifierForWorkOrder(txttipo,txtbranch,txtworkorder,e9);
				lblworkordererror.setText(iv.verify());
			}
		});
		
		txtoperazione.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				InsertVerifierForOperation iv = new InsertVerifierForOperation(txttipo,txtworkorder,txtoperazione,e10);
				lbloperationerror.setText(iv.verify());
			}
		});
		
		//EVENTI PULSANTE ANNULLA
		btnannulla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
								
		//EVENTI PULSANTE SALVA TRANSAZIONI
		btnsalva.addActionListener(new EventInsertBtnSave(this,tblDatiTransazioni,txtreparto,txtisola,txtdatatransazione,txtstato,txttipo,txtbranch,txtrisorsa,txtoperatore,txtworkorder,txtoperazione,lblnumerorecord,e1,e2,e3,e4,e5,e6,e7,e8,e9,e10));
			
								
	}
}
