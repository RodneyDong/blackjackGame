package blackjack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Dialogue for player name configuration and seats
 * ICS4U
 * @author Rodney Dong
 * @date January 20 2022
 * Student Number:350706008
 */
public class ConfigPlayerDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the dialogue for player name and seat configuration
	 * @param MainFrame
	 */
	ConfigPlayerDialog(MainFrame parent) {
		super(parent, true); 
		setSize(300, 200); 
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);

		JPanel namePnl = new JPanel();
		JLabel nameLbl = new JLabel("Player name: ");
		namePnl.add(nameLbl);
		JTextField nameTxt = new JTextField(10);
		namePnl.add(nameTxt);
		add(namePnl, BorderLayout.NORTH);
		
		JPanel seatPnl = new JPanel();
		JLabel seatLbl = new JLabel("Select Seat: ");
		seatPnl.add(seatLbl);
		String seats[] = { "WEST", "SOUTH", "EAST" };
		
		JComboBox<String> seatCbx = new JComboBox<>(seats);
		seatCbx.setPreferredSize(new Dimension(110, 25));;
		seatPnl.add(seatCbx);
		add(seatPnl, BorderLayout.CENTER);

		
		JButton setBtn = new JButton("Set");
		add(setBtn, BorderLayout.SOUTH);
		setBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { // sets player's name
				String seat = seatCbx.getSelectedItem().toString(); // get the seat name from selected item
				String name = nameTxt.getText(); // get the name from text
				if (name != null  && name.trim().length() != 0) { // only set the name if name has a value
					parent.setPlayerName(seat, name); // set player's name	
				}
			}
			
		});
	}
}
