package scripts.crisisplanker.tasks.walking;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import org.powerbot.script.rt4.TilePath;

import scripts.crisisplanker.Main;
import scripts.crisisplanker.tasks.Task;

public class GoToBank extends Task {

	Main m;
	public GoToBank(ClientContext ctx, Main m) {
		super(ctx);
		this.m = m;
	}
	
	
	@Override
	public boolean activate() {
		Npc banker = ctx.npcs.select().id(2532).nearest().poll();
		return ctx.inventory.select().id(m.log.getLogId()).isEmpty()
				&& !banker.valid();
	}

	@Override
	public void execute() {
		m.status = "Going to Bank...";
		
		TilePath path = m.walkToBank();
		path.traverse();
		
		
	}

}
