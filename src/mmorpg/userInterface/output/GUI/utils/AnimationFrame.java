package mmorpg.userInterface.output.GUI.utils;

import java.awt.Image;

public class AnimationFrame {
	
	private Image img;
	private long endTime;
	
	public AnimationFrame(Image pic, long endTime){
		this.img = pic;
		this.endTime = endTime;
	}
	
	public Image getImage(){
		return this.img;
	}
	
	public long getEndTime(){
		return this.endTime;
	}

}
