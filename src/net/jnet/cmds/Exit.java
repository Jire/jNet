package net.jnet.cmds;

import static org.jfunc.Utilities.out;

import org.jfunc.Command;
import org.jfunc.Interpreter;

public class Exit implements Command {

	@Override
	public void handle(Interpreter interp, String line, String... args) {
		out("Goodbye!");
		try {
			Thread.sleep(400);
		} catch (Exception e) {}
		System.exit(1);
	}

	@Override
	public String getDescription() {
		return "Ends the server.";
	}

}