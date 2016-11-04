package ide.copy;

import ide.main.MainTable;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;

import bin.TableParser;

import java.awt.Font;

public class CopyWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	public CopyWindow(MainTable tblDatiTransazioni,JLabel lblnumerorecord) {

		String image ="image.gif";
		
		Image icon = Toolkit.getDefaultToolkit().getImage(image);
		
		setIconImage(icon);
		setTitle("Eurolls M.E.S. - MES Administrator - Copia Transazioni");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				
		setBounds(100, 100, 1000, 500);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnsalva = new JButton("Salva");
		btnsalva.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonPane.add(btnsalva);
		getRootPane().setDefaultButton(btnsalva);
		
		JButton btnannulla = new JButton("Annulla");
		btnannulla.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonPane.add(btnannulla);
			
		JPanel pnlcopiarecords = new JPanel();
		pnlcopiarecords.setBorder(new TitledBorder(null, "Copia transazioni:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlcopiarecords, BorderLayout.CENTER);
		pnlcopiarecords.setLayout(new BorderLayout(0, 0));
	
		JScrollPane srccopiarecords = new JScrollPane();
		pnlcopiarecords.add(srccopiarecords);
			
		CopyTable tblcopiarecords = new CopyTable();
		
		CopyTableModel tm = new CopyTableModel(TableParser.getData(),tblcopiarecords.getHeaders());
		tblcopiarecords.setModel(tm);
		tblcopiarecords.setLayout();		
		srccopiarecords.setViewportView(tblcopiarecords);
				
		//EVENTI PULSANTE SALVA TRANSAZIONI
	    btnsalva.addActionListener(new EventCopyBtnSave(this,tblcopiarecords,tblDatiTransazioni,lblnumerorecord));
		
		//EVENTI PULSANTE ANNULLA
		btnannulla.addActionListener(new EventCopyBtnCancel(this));
	}

}
