package scripts.crisisplanker.tasks.planking;

import java.util.concurrent.Callable;

import javax.swing.JOptionPane;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Npc;

import scripts.crisisplanker.Main;
import scripts.crisisplanker.tasks.Task;

public class OpenInterface extends Task {

	Main m;
	public OpenInterface(ClientContext ctx, Main m) {
		super(ctx);
		this.m = m;
	}

	@Override
	public boolean activate() {
		Npc sawmillGuy = ctx.npcs.select().id(2406).nearest().poll();
		Component plankInterface = ctx.widgets.widget(403).component(3);
		return sawmillGuy.valid()
				&& !ctx.inventory.select().id(m.log.getLogId())
						.isEmpty() && !plankInterface.valid();
	}

	@Override
	public void execute() {
		m.status = "Planking...";
		Npc sawmillGuy = ctx.npcs.select().id(2406).nearest().poll();
		final Component plankInterface = ctx.widgets.widget(403).component(m.log.getChild());
		Component outOfCoins = ctx.widgets.widget(242).component(2);
		
		if(outOfCoins.valid()) {
			ctx.controller.stop();
			JOptionPane.showMessageDialog(null, "Out of Coins! Thank you for using CrisisPlanker!");
		}
		
		if (sawmillGuy.inViewport()) {
			sawmillGuy.interact("Buy-plank");
			Condition.wait(new Callable<Boolean>() {
			     @Override
			     public Boolean call() {
			         return plankInterface.valid();
			     }
			}, 3000, 2);
		} else {
			ctx.camera.turnTo(sawmillGuy);
			ctx.movement.step(sawmillGuy);
		}
	

	}

}
