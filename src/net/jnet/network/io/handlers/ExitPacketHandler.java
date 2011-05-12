package net.jnet.network.io.handlers;

import org.jfunc.Interpreter;

import net.jnet.Server;
import net.jnet.cmds.Exit;
import net.jnet.network.io.Packet;
import net.jnet.network.io.PacketHandler;

public class ExitPacketHandler implements PacketHandler {

	@Override
	public void perform(Packet packet, String data, String... args) {
		Interpreter.call(Server.interp, new Exit());
	}

}