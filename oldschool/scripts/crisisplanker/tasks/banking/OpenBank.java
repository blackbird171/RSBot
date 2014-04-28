package scripts.crisisplanker.tasks.banking;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

import scripts.crisisplanker.Main;
import scripts.crisisplanker.tasks.Task;

public class OpenBank extends Task {

	Main m;
	public OpenBank(ClientContext ctx, Main m) {
		super(ctx);
		this.m = m;
	}

	@Override
	public boolean activate() {
		Npc banker = ctx.npcs.select().id(2532).nearest().poll();
		return !ctx.bank.opened()
				&& ctx.inventory.select().id(m.log.getLogId())
						.isEmpty() && banker.valid();
	}

	@Override
	public void execute() {
		m.status = "Opening Bank...";
		Npc banker = ctx.npcs.select().id(2532).nearest().poll();
		if (banker.inViewport()) {
			banker.interact("Bank");
			Condition.wait(new Callable<Boolean>() {
			     @Override
			     public Boolean call() {
			         return ctx.bank.opened();
			     }
			}, 3000, 2);
		} else {
			ctx.camera.turnTo(banker);
			ctx.movement.step(banker);
		}

	}

}
