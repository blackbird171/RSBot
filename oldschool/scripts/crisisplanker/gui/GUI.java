package scripts.crisisplanker.gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

import scripts.crisisplanker.Main;
import scripts.crisisplanker.data.Log;
import scripts.crisisplanker.tasks.Task;
import scripts.crisisplanker.tasks.banking.CloseBank;
import scripts.crisisplanker.tasks.banking.Deposit;
import scripts.crisisplanker.tasks.banking.OpenBank;
import scripts.crisisplanker.tasks.banking.Withdraw;
import scripts.crisisplanker.tasks.planking.MakePlanks;
import scripts.crisisplanker.tasks.planking.OpenInterface;
import scripts.crisisplanker.tasks.walking.GoToBank;
import scripts.crisisplanker.tasks.walking.GoToGuy;

public class GUI extends ClientAccessor {

	private JFrame frame;
	private final JComboBox<Log> logTypes = new JComboBox<Log>(Log.values());
	private final JLabel description = new JLabel("Select Log:");
	private final JButton start = new JButton("Start");
	
	private Object lock;
	private List<Task> taskList;
	Main m;

	public GUI(ClientContext ctx, Main m, List<Task> taskList, Object lock) {
		super(ctx);
		this.m = m;
		this.taskList = taskList;
		this.lock= lock;
		
		
		initializeFrame();

	}

	private ActionListener startEvent = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			m.log = (Log) logTypes.getSelectedItem();

			taskList.add(new MakePlanks(ctx, m));
			taskList.add(new CloseBank(ctx, m));
			taskList.add(new OpenBank(ctx, m));
			taskList.add(new Withdraw(ctx, m));
			taskList.add(new Deposit(ctx, m));
			taskList.add(new GoToBank(ctx, m));
			taskList.add(new GoToGuy(ctx, m));
			taskList.add(new OpenInterface(ctx, m));
			frame.dispose();

			synchronized (lock) {
				lock.notify();
			}

		}
	};

	private void initializeFrame() {
		frame = new JFrame("CrisisPlanker");
		frame.setLayout(new FlowLayout());
		frame.setSize(256, 69);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		frame.add(description);
		frame.add(logTypes);
		frame.add(start);
		
		start.addActionListener(startEvent);
	}

}