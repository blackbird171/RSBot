package scripts.crisisplanker.tasks.walking;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

import scripts.crisisplanker.Main;
import scripts.crisisplanker.data.Log;
import scripts.crisisplanker.tasks.Task;

public class GoToGuy extends Task {

	Main m;
	public GoToGuy(ClientContext ctx, Main m) {
		super(ctx);
		this.m = m;
	}
	
	Tile[] BANK_TO_GUY = new Tile[]{ 
			new Tile(3252, 3422, 0), new Tile(3256, 3427, 0), 
			new Tile(3262, 3428, 0), new Tile(3269, 3428, 0), 
			new Tile(3274, 3432, 0), new Tile(3275, 3438, 0), 
			new Tile(3280, 3443, 0), new Tile(3281, 3449, 0), 
			new Tile(3283, 3455, 0), new Tile(3286, 3461, 0), 
			new Tile(3286, 3468, 0), new Tile(3291, 3472, 0), 
			new Tile(3293, 3478, 0), new Tile(3297, 3483, 0), 
			new Tile(3300, 3488, 0)};

	@Override
	public boolean activate() {
		Npc sawmillGuy = ctx.npcs.select().id(2406).nearest().poll();
		return !sawmillGuy.valid()
				&& !ctx.inventory.select().id(Log.logIdChosen.getLogId())
						.isEmpty() && !ctx.bank.opened();
	}

	@Override
	public void execute() {
		m.status = "Going to Guy...";
		ctx.movement.newTilePath(BANK_TO_GUY).traverse();

	}

}
