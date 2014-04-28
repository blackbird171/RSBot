package scripts.crisisplanker.tasks.walking;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import org.powerbot.script.rt4.TilePath;

import scripts.crisisplanker.Main;
import scripts.crisisplanker.tasks.Task;

public class GoToGuy extends Task {

	Main m;
	public GoToGuy(ClientContext ctx, Main m) {
		super(ctx);
		this.m = m;
	}
	

	@Override
	public boolean activate() {
		Npc sawmillGuy = ctx.npcs.select().id(2406).nearest().poll();
		return !sawmillGuy.valid()
				&& !ctx.inventory.select().id(m.log.getLogId())
						.isEmpty() && !ctx.bank.opened();
	}

	@Override
	public void execute() {
		m.status = "Going to Guy...";
		
		TilePath path = m.walkToGuy();
		path.traverse();
	}

}
