package net.jnet.cmds;

import static java.lang.System.out;

import org.jfunc.Command;
import org.jfunc.Interpreter;

public class About implements Command {

	@Override
	public void handle(Interpreter interp, String line, String... args) {
		out.println("A Java remote client manager created by Thomas Nappo.");
	}

	@Override
	public String getDescription() {
		return "Shows information about jNet.";
	}

}