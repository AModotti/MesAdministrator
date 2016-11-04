package bin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Color;

public class Notifications extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public Notifications(String module,String message,String error) {
		
		setTitle("Eurolls M.E.S. - " + module);
		
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneMessage = new JScrollPane();
		contentPanel.add(scrollPaneMessage, BorderLayout.NORTH);
		
		JTextArea textAreaMessage = new JTextArea(message);
		textAreaMessage.setRows(5);
		scrollPaneMessage.setViewportView(textAreaMessage);
		textAreaMessage.setLineWrap(true);
		textAreaMessage.setWrapStyleWord(true);
		textAreaMessage.setEditable(false);
		
		JScrollPane scrollPaneError = new JScrollPane();
		contentPanel.add(scrollPaneError, BorderLayout.CENTER);
		
		JTextArea textAreaError = new JTextArea(error);
		textAreaError.setForeground(Color.RED);
		scrollPaneError.setViewportView(textAreaError);
		textAreaError.setLineWrap(true);
		textAreaError.setWrapStyleWord(true);
		textAreaError.setEditable(false);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

	}

}
