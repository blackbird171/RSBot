package scripts.crisisplanker.tasks.banking;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import scripts.crisisplanker.Main;
import scripts.crisisplanker.tasks.Task;

public class Deposit extends Task {

	Main m;
	public Deposit(ClientContext ctx, Main m) {
		super(ctx);
		this.m = m;

	}

	@Override
	public boolean activate() {
		return ctx.bank.opened()
				&& !ctx.inventory.select().id(m.log.getPlankId())
						.isEmpty();
	}

	@Override
	public void execute() {
		m.status = "Depositing...";
		ctx.bank.deposit(m.log.getPlankId(), 0);
		Condition.wait(new Callable<Boolean>() {
		     @Override
		     public Boolean call() {
		         return ctx.inventory.select().id(m.log.getPlankId()).isEmpty();
		     }
		}, 3000, 2);

	}

}
