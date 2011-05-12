package net.jnet.network;

import net.jnet.Server;
import net.jnet.network.io.*;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * Handles channel events.
 * @author Thomas Nappo
 */
public class ChannelHandler extends SimpleChannelHandler {
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		String msg = e.getMessage().toString();
		
		String[] args = msg.split(Server.SPLITTER); // %jnet% is the separator
		
		if (args.length < 3) return;
		
		// basic security check
		String password = args[0]; // we get password that client sent us.
		
		if (!Server.properties.getProperty("password").equalsIgnoreCase(password)) {
			//ctx.getChannel().write("bad password");
			return;
		}
		
		// security passed!
		
		int opCode = Integer.parseInt(args[1]);
		
		String data = args[2];
		
		String[] arg = data.split(Server.ARG_SPLITTER);
		
		PacketDistributor.handle(new Packet(opCode, data, ctx.getChannel()), arg);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		System.out.println("Something went wrong! Contact vendor: " + e.getCause());
	}

}