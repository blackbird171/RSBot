package scripts.crisisplanker;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Tile;
import org.powerbot.script.Script.Manifest;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

import scripts.crisisplanker.data.Log;
import scripts.crisisplanker.gui.GUI;
import scripts.crisisplanker.tasks.Task;

@Manifest(description = "Planks all logs at the Sawmill [Money Making]", name = "CrisisPlanker", properties = "authors = Crisis; version = 0.02; topic = 0")
public class Main extends PollingScript<ClientContext> implements PaintListener {

	// int
	public int plankCount = 0;
	/*
	 * private int logPrice = 0; private int plankPrice = 0; private int
	 * profitPerLog = 0; private int totalProfit = 0;
	 */

	// String
	public String status = "Loading...";

	//List
	private List<Task> taskList = new ArrayList<Task>();

	//Object
	final Object lock = new Object();

	// Tile
	private final Tile[] GUY_TO_BANK = new Tile[] { new Tile(3300, 3488, 0),
			new Tile(3300, 3481, 0), new Tile(3297, 3475, 0),
			new Tile(3297, 3468, 0), new Tile(3296, 3462, 0),
			new Tile(3292, 3457, 0), new Tile(3289, 3451, 0),
			new Tile(3289, 3444, 0), new Tile(3290, 3438, 0),
			new Tile(3286, 3433, 0), new Tile(3281, 3429, 0),
			new Tile(3274, 3429, 0), new Tile(3267, 3429, 0),
			new Tile(3260, 3429, 0), new Tile(3254, 3426, 0),
			new Tile(3253, 3421, 0) };

	private final Tile[] BANK_TO_GUY = new Tile[] { new Tile(3252, 3422, 0),
			new Tile(3256, 3427, 0), new Tile(3262, 3428, 0),
			new Tile(3269, 3428, 0), new Tile(3274, 3432, 0),
			new Tile(3275, 3438, 0), new Tile(3280, 3443, 0),
			new Tile(3281, 3449, 0), new Tile(3283, 3455, 0),
			new Tile(3286, 3461, 0), new Tile(3286, 3468, 0),
			new Tile(3291, 3472, 0), new Tile(3293, 3478, 0),
			new Tile(3297, 3483, 0), new Tile(3300, 3488, 0) };

	// Log
	public Log log = Log.NONE;

	@Override
	public void poll() {

		for (Task task : taskList) {
			if (task.activate()) {
				task.execute();

			}
		}
	}

	@Override
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GUI(ctx, Main.this, taskList, lock);
			}
		});

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
		g.setColor(color1);
		g.fillRect(351, 100, 164, 242);// 45
		g.setColor(color2);
		g.setStroke(stroke1);
		g.drawRect(351, 100, 164, 242);
		g.drawLine(352, 133, 515, 133);
		g.setFont(font1);
		g.drawString("Time: " + formated(this.getRuntime()), 363, 161);
		g.drawString("Profit: Soon™", 363, 188);
		g.drawString("Profit/hr: Soon™", 363, 212);
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
		g.drawString("0.02", 478, 338);
		g.setFont(font4);
		g.drawString("Script by: Crisis", 354, 339);

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

	// TilePath's
	public TilePath walkToBank() {
		return ctx.movement.newTilePath(GUY_TO_BANK);
	}

	public TilePath walkToGuy() {
		return ctx.movement.newTilePath(BANK_TO_GUY);
	}
}
