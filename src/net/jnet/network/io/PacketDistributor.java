package net.jnet.network.io;

import net.jnet.network.io.handlers.*;

/**
 * Distributes packets to their appropriate handler.
 * @author Thomas Nappo
 */
public final class PacketDistributor {
	
	public static final void feed() {
		assign(new LoginPacketHandler(), 0);
		assign(new RobotPacketHandler(), 1);
	}
	
	public static final PacketHandler[] mass = new PacketHandler[255];
	
	public static final void assign(PacketHandler handler, int opCode) {
		mass[opCode] = handler;
	}
	
	public static final void handle(Packet packet, String[] arg) {
		if (packet.getOpCode() == -1) {
			new DefaultPacketHandler().perform(packet, packet.getData().toString(), arg);
			return;
		}
		try {
			mass[packet.getOpCode()].perform(packet, packet.getData().toString(), arg);
		} catch (Exception e) {
		}
	}

}