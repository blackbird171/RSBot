package scripts.crisisplanker.tasks.banking;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import scripts.crisisplanker.Main;
import scripts.crisisplanker.tasks.Task;

public class CloseBank extends Task {

	Main m;
	public CloseBank(ClientContext ctx, Main m) {
		super(ctx);
		this.m = m;
	}

	@Override
	public boolean activate() {
		return ctx.bank.opened()
				&& !ctx.inventory.select().id(m.log.getLogId())
						.isEmpty();
	}

	@Override
	public void execute() {
		m.status = "Closing Bank....";
		ctx.bank.close();
		Condition.wait(new Callable<Boolean>() {
		     @Override
		     public Boolean call() {
		         return !ctx.bank.opened();
		     }
		}, 3000, 2);

	}

}
