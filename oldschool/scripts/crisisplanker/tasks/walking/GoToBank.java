package scripts.crisisplanker.tasks.walking;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

import scripts.crisisplanker.Main;
import scripts.crisisplanker.data.Log;
import scripts.crisisplanker.tasks.Task;

public class GoToBank extends Task {

	Main m;
	public GoToBank(ClientContext ctx, Main m) {
		super(ctx);
		this.m = m;
	}
	
	Tile[] GUY_TO_BANK = new Tile[]{ 
			new Tile(3300, 3488, 0), new Tile(3300, 3481, 0), 
			new Tile(3297, 3475, 0), new Tile(3297, 3468, 0), 
			new Tile(3296, 3462, 0), new Tile(3292, 3457, 0), 
			new Tile(3289, 3451, 0), new Tile(3289, 3444, 0), 
			new Tile(3290, 3438, 0), new Tile(3286, 3433, 0), 
			new Tile(3281, 3429, 0), new Tile(3274, 3429, 0), 
			new Tile(3267, 3429, 0), new Tile(3260, 3429, 0), 
			new Tile(3254, 3426, 0), new Tile(3253, 3421, 0)};

	@Override
	public boolean activate() {
		Npc banker = ctx.npcs.select().id(2532).nearest().poll();
		return ctx.inventory.select().id(Log.logIdChosen.getLogId()).isEmpty()
				&& !banker.valid();
	}

	@Override
	public void execute() {
		m.status = "Going to Bank...";
		ctx.movement.newTilePath(GUY_TO_BANK).traverse();
		
	}

}
