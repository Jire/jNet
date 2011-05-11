package net.jnet.model;

import java.util.ArrayList;
import java.util.TreeSet;

public final class World {
	
	public static final ArrayList<Client> clients = new ArrayList<Client>();
	public static void sort() {
		TreeSet<Client> set = new TreeSet<Client>();
		for (Client c : clients) {
			if (!c.getChannel().isConnected() || !c.getChannel().isOpen() || !c.getChannel().isBound()) {
				c.getChannel().close();
				set.add(c);
			}
		}
		for (Client c : set) clients.remove(c);
	}

}