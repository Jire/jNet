package net.jnet.network.io.handlers;

import net.jnet.network.io.Packet;
import net.jnet.network.io.PacketHandler;

public class ParrotPacketHandler implements PacketHandler {

	@Override
	public void perform(Packet packet, String data, String... args) {
		// ok ill show u what I mean
		// now that the decoding system is done =)
		
		packet.getChannel().write("Parrot: " + data);
	}

}