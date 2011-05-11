package net.jnet.model;

import org.jboss.netty.channel.Channel;

/**
 * A client which is connected to the server.
 * @author Thomas Nappo
 */
public class Client {
	
	/**
	 * The client's connection channel.
	 */
	private Channel channel;
	
	/**
	 * Gets the client's connection channel.
	 * @return The client's connection channel.
	 */
	public Channel getChannel() {
		return channel;
	}
	
	/**
	 * Constructs a new client.
	 * @param channel The client's connection channel.
	 */
	public Client(Channel channel) {
		this.channel = channel;
	}
	
	/**
	 * Writes towards the client's connection {@link #channel}
	 * @param o The data to write.
	 */
	public void write(Object o) {
		channel.write(o);
	}

}