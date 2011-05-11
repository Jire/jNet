package net.jnet;

import static java.lang.System.out;

import java.io.File;
import java.io.FileReader;
import java.net.InetSocketAddress;
import java.util.Properties;
import java.util.concurrent.Executors;

import net.jnet.network.PipelineFactory;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jfunc.Interpreter;

/**
 * Maintains the server being.
 * @author Thomas Nappo
 */
public final class Server {
	
	// misc
	public static final Properties properties = new Properties();
	public static final Interpreter interp = new Interpreter("jNet", "data/cmd.jf");
	
	// networking fields
	public static final ServerBootstrap bootstrap = new ServerBootstrap(
			new NioServerSocketChannelFactory(
					Executors.newCachedThreadPool(), 
					Executors.newCachedThreadPool()));;;
	public static int port = 62;
	
	public static void printBanner() {
		out.println();
		out.println("        _ _   _      _");
		out.println("       (_) \\ | |    | |");
		out.println("        _|  \\| | ___| |_");
		out.println("       | | . ` |/ _ \\ __|");
		out.println("       | | |\\  |  __/ |_");
		out.println("       | |_| \\_|\\___|\\__|");
		out.println("      _/ |  A Java remote client");
		out.println("     |__/  manager by Thomas Nappo.");
		out.println();
	}
	
	public static void run() {
		port = Integer.parseInt(properties.getProperty("port"));
		
		bootstrap.setOption("keepAlive", true);
		bootstrap.setOption("reuseAddress", true);
		bootstrap.setOption("child.tcpNoDelay", true);
		
		bootstrap.setPipelineFactory(new PipelineFactory());
		
		bootstrap.bind(new InetSocketAddress(port));
	}
	
	public static void main(String[] args) {
		try {
			properties.load(new FileReader(new File("data/jnet.properties")));
		} catch (Exception e) { e.printStackTrace(); }
		
		printBanner();
		run();
		
		System.out.println();
		System.out.println("Type 'help list' to view available commands.");
		System.out.println("Type 'help info [command]' to view information about a command.");
		System.out.println();
		
		while (true) {
			/*
			 * don't worry about this being run infinitely.
			 * it sleeps until the console sends a message.
			 */
			interp.interpret();
		}
	}

}