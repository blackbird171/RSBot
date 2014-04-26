package scripts.crisisplanker;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script.Manifest;
import org.powerbot.script.rt4.ClientContext;

import scripts.crisisplanker.data.Log;
import scripts.crisisplanker.gui.GUI;
import scripts.crisisplanker.tasks.Task;
import scripts.crisisplanker.tasks.banking.CloseBank;
import scripts.crisisplanker.tasks.banking.Deposit;
import scripts.crisisplanker.tasks.banking.OpenBank;
import scripts.crisisplanker.tasks.banking.Withdraw;
import scripts.crisisplanker.tasks.planking.MakePlanks;
import scripts.crisisplanker.tasks.planking.OpenInterface;
import scripts.crisisplanker.tasks.walking.GoToBank;
import scripts.crisisplanker.tasks.walking.GoToGuy;

@Manifest(description = "Planks all logs at the Sawmill [Money Making]", name = "CrisisPlanker", properties = "authors = Crisis; version = 0.01; topic = 0")
public class Main extends PollingScript<ClientContext> implements PaintListener {

	// int
	private int logPrice = 0;
	private int plankPrice = 0;
	private int profitPerLog = 0;
	@SuppressWarnings("unused")
	private int totalProfit = 0;
	public int plankCount = 0;

	// String
	public String status = "Loading...";
	
	//boolean
	public static boolean done = false;

	 List<Task> taskList = new ArrayList<Task>();

	
	@Override
	public void poll() {
		if(done) {
			for (Task task : taskList) {
				if (task.activate()) {
					task.execute();
				}
		  }
		}
	}

	@Override
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GUI(ctx);
			}
		});
		taskList.add(new MakePlanks(ctx, this));
		taskList.add(new CloseBank(ctx, this));
		taskList.add(new OpenBank(ctx, this));
		taskList.add(new Withdraw(ctx, this));
		taskList.add(new Deposit(ctx, this));
		taskList.add(new GoToBank(ctx, this));
		taskList.add(new GoToGuy(ctx, this));
		taskList.add(new OpenInterface(ctx, this));
		

	}

	private final Color color1 = new Color(0, 0, 0, 167);
	private final Color color2 = new Color(255, 255, 255);

	private final BasicStroke stroke1 = new BasicStroke(1);

	private final Font font1 = new Font("Perpetua", 1, 15);
	private final Font font2 = new Font("Eras Medium ITC", 1, 19);
	private final Font font3 = new Font("Consolas", 1, 14);
	private final Font font4 = new Font("Lao UI", 1, 13);

	public void repaint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		profitPerLog = (logPrice + Log.feeChosen.getFee()) - plankPrice;
		totalProfit = profitPerLog * plankCount;
		g.setColor(color1);
		g.fillRect(351, 100, 164, 242);//45
		g.setColor(color2);
		g.setStroke(stroke1);
		g.drawRect(351, 100, 164, 242);
		g.drawLine(352, 133, 515, 133);
		g.setFont(font1);
		g.drawString("Time: " + formated(this.getRuntime()), 363, 161);
		g.drawString("Profit: Soon™", 363, 188);
		g.drawString("Profit/hr: Soon™", 363,212);
		g.drawString(
				"Planks: " + NumberFormat.getInstance().format(plankCount),
				363, 237);
		g.drawString(
				"Planks/hr: "
						+ NumberFormat.getInstance().format(
								getPerHour(plankCount, getRuntime())), 363, 264);
		g.drawString("Status: " + status, 364, 289);
		g.setFont(font2);
		g.drawString("CrisisPlanker", 378, 127);
		g.setFont(font3);
		g.drawString("0.01", 478, 338);
		g.setFont(font4);
		g.drawString("Script by: Crisis", 354, 339);
		
		g.setColor(Color.WHITE);
		Point p = ctx.mouse.getLocation();
		g.drawLine(p.x - 1000, p.y, p.x + 1000, p.y);
		g.drawLine(p.x, p.y - 1000, p.x, p.y + 1000);

	}

	String formated(long time) {
		final int sec = (int) (time / 1000), h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
		return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":"
				+ (s < 10 ? "0" + s : s);
	}

	public static int getPerHour(int in, long time) {
		return (int) ((in) * 3600000D / time);
	}
	//getprice

}
