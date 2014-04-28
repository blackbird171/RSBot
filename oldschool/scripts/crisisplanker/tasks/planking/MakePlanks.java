package scripts.crisisplanker.tasks.planking;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

import scripts.crisisplanker.Main;
import scripts.crisisplanker.tasks.Task;

public class MakePlanks extends Task {

	Main m;
	public MakePlanks(ClientContext ctx, Main m) {
		super(ctx);
		this.m = m;
	}

	@Override
	public boolean activate() {
		Component plankInterface = ctx.widgets.widget(403).component(3);
		return plankInterface.valid();
	}

	@Override
	public void execute() {
		m.status = "Planking...";
		final Component plankInterface = ctx.widgets.widget(403).component(
				m.log.getChild());
		plankInterface.interact("Buy All");
		m.plankCount += 27;
		Condition.wait(new Callable<Boolean>() {
		     @Override
		     public Boolean call() {
		         return !plankInterface.valid();
		     }
		}, 3000, 2);

	}

}
