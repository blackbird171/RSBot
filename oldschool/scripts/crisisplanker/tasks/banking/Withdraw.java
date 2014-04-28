package scripts.crisisplanker.tasks.banking;

import java.util.concurrent.Callable;

import javax.swing.JOptionPane;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import scripts.crisisplanker.Main;
import scripts.crisisplanker.tasks.Task;

public class Withdraw extends Task {

	Main m;

	public Withdraw(ClientContext ctx, Main m) {
		super(ctx);
		this.m = m;
	}

	@Override
	public boolean activate() {
		return ctx.bank.opened() && ctx.inventory.select().count() <= 1;
	}

	@Override
	public void execute() {
		m.status = "Withdrawing...";
		if (!ctx.bank.select().id(m.log.getLogId()).isEmpty()) {
			ctx.bank.withdraw(m.log.getLogId(), 27);
			Condition.wait(new Callable<Boolean>() {
				@Override
				public Boolean call() {
					return !ctx.inventory.select()
							.id(m.log.getLogId()).isEmpty();
				}
			}, 3000, 2);
		} else {
			ctx.controller.stop();
			JOptionPane.showMessageDialog(null, "Out of logs! Thank you for using CrisisPlanker!");

		}

	}

}
