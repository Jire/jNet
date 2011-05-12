package net.jnet.network.io;

import org.jboss.netty.channel.Channel;

/**
 * A network packet.
 * @author Thomas Nappo
 */
public class Packet {
	
	private Object data;
	private Channel channel;
	private int opCode = -1;
	
	public Object getData() {
		return data;
	}
	
	public int getOpCode() {
		return opCode;
	}
	
	public Channel getChannel() {
		return channel;
	}
	
	public Packet(int opCode, Object data, Channel channel) {
		this.opCode = opCode; this.data = data; this.channel = channel;
	}

}