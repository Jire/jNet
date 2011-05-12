package net.jnet.network.io.handlers;

import java.awt.Robot;
import java.awt.event.InputEvent;

import net.jnet.network.io.Packet;
import net.jnet.network.io.PacketHandler;

public class RobotPacketHandler implements PacketHandler {

	@Override
	public void perform(Packet packet, String data, String... args) throws Exception {
		final int op = Integer.parseInt(args[0]);
		
		final Robot robot = new Robot();
		switch (op) {
		case 0: // mouse move
			robot.mouseMove(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			packet.getChannel().write("Moved mouse to: " + args[1] + "," + args[2]);
			break;
		case 1: // left click
			click(robot, InputEvent.BUTTON1_DOWN_MASK);
			packet.getChannel().write("Left clicked mouse.");
			break;
		case 2: // right click
			click(robot, InputEvent.BUTTON2_DOWN_MASK);
			packet.getChannel().write("Right clicked mouse.");
			break;
		case 3: // type
			int key = Integer.parseInt(args[1]);
			type(robot, key);
			packet.getChannel().write("Typed key: " + key + " (" + args[1] + ")");
			break;
		}
	}
	
	private void click(Robot robot, int button) {
		robot.mousePress(button);
		robot.mouseRelease(button);
	}
	
	private void type(Robot robot, int key) {
		robot.keyPress(key);
		robot.keyRelease(key);
	}

}