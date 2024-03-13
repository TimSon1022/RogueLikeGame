package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/gold_obtained.wav");
		soundURL[1] = getClass().getResource("/sound/item_obtained.wav");
		soundURL[2] = getClass().getResource("/sound/key_obtained.wav");
		soundURL[3] = getClass().getResource("/sound/chest_open.wav");
		soundURL[4] = getClass().getResource("/sound/health_potion_obtained.wav");
	}
	
	public void setFile(int i) {
		
		try {
			AudioInputStream ais  = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		
		}
		catch (Exception e) {
			
		}
		
	}
	
	public void play() {
		
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	

}
