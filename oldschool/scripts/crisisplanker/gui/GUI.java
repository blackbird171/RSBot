package scripts.crisisplanker.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

import scripts.crisisplanker.Main;
import scripts.crisisplanker.data.Log;

public class GUI extends ClientAccessor {

	private final JFrame frame;
	private final JComboBox<Log> logTypes = new JComboBox<Log>(Log.values());
	private final JLabel description = new JLabel("Select Log:");
	private final JButton start = new JButton("Start");
	private Log selectedEnum;

	public GUI(ClientContext arg0) {
		super(arg0);
		frame = new JFrame("CrisisPlanker");
		frame.setLayout(new FlowLayout());
		start.addActionListener(startEvent);
		addComponents();
		initializeFrame();
	}

	private ActionListener startEvent = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			selectedEnum = (Log) logTypes.getSelectedItem();
			Log.nameChosen = selectedEnum;
			Log.logIdChosen = selectedEnum;
			Log.plankIdChosen = selectedEnum;
			Log.feeChosen = selectedEnum;
			Log.childChosen = selectedEnum;
			System.out.println(selectedEnum);
			frame.dispose();
			Main.done = true;
		}
	};

	private void addComponents() {
		frame.add(description);
		frame.add(logTypes);
		frame.add(start);
	}

	private void initializeFrame() {
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}