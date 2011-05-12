package net.jnet.network.io;

/**
 * Handles a packet operation.
 * @author Thomas Nappo
 */
public interface PacketHandler {
	
	/**
	 * Handles the packet's operation.
	 * @param packet The packet to handle.
	 * @param data The packet's data.
	 */
	public void perform(Packet packet, String data);

}