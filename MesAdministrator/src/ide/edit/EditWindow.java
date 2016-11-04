package ide.edit;

import ide.main.MainTable;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;

import bin.TableParser;

import java.awt.Font;

public class EditWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	public EditWindow(MainTable tblDatiTransazioni) {

		String image ="image.gif";
		
		Image icon = Toolkit.getDefaultToolkit().getImage(image);
		
		setIconImage(icon);
		setTitle("Eurolls M.E.S. - MES Administrator - Modifica Transazioni");
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
			
		JPanel pnlmodificarecords = new JPanel();
		pnlmodificarecords.setBorder(new TitledBorder(null, "Modifica transazioni:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlmodificarecords, BorderLayout.CENTER);
		pnlmodificarecords.setLayout(new BorderLayout(0, 0));
	
		JScrollPane srcmodificarecords = new JScrollPane();
		pnlmodificarecords.add(srcmodificarecords);
				
		EditTable tblmodificarecords = new EditTable();
		EditTableModel tm = new EditTableModel(tblDatiTransazioni,TableParser.getData(),tblmodificarecords.getHeaders());
		tblmodificarecords.setModel(tm);
		tblmodificarecords.setLayout();		
		srcmodificarecords.setViewportView(tblmodificarecords);
						
		//EVENTI PULSANTE SALVA TRANSAZIONI
		btnsalva.addActionListener(new EventEditBtnSave(this,tblmodificarecords,tblDatiTransazioni));
		
		//EVENTI PULSANTE ANNULLA
		btnannulla.addActionListener(new EventEditBtnCancel(this));
	}

}
