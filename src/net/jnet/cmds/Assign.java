package net.jnet.cmds;

import static org.jfunc.Utilities.out;

import net.jnet.network.io.PacketDistributor;
import net.jnet.network.io.PacketHandler;

import org.jfunc.Command;
import org.jfunc.Interpreter;

public class Assign implements Command {

	@Override
	public void handle(Interpreter interp, String line, String... args) {
		if (args.length < 2) {
			out("Usage: assign [op-code] [class name (full or shortened)]");
			return;
		}

		int opCode = Integer.parseInt(args[0]);
		String name = args[1];
		
		Class<?> check = null;

		try {
			try {
				check = Class.forName(name);
			} catch (ClassNotFoundException e) {
				if((check = Class.forName("net.jnet.network.io.handlers." + name)) == null) {
					invalid(name);
					return;
				}
			}

			Object check2 = check.newInstance();

			if (!(check2 instanceof PacketHandler)) {
				invalid(name);
				return;
			}

			PacketDistributor.assign((PacketHandler) check2, opCode);
			out("Assigned operation " + opCode + " to " + name);
		} catch (Exception e) {
			invalid(name);
		}
	}
	
	private void invalid(String name) {
		out("Class: " + name + " is not a valid packet handler!");
	}

	@Override
	public String getDescription() {
		return "Assigns a packet handler to an op code.";
	}

}