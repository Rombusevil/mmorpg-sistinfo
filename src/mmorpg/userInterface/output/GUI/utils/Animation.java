package mmorpg.userInterface.output.GUI.utils;

import java.awt.Image;
import java.util.ArrayList;


public class Animation {
	private ArrayList frameList;
	private int frameIndex;
	private long currentMovieTime;
	private long totalMovieTime;
	
	public Animation(){
		this.frameList = new ArrayList();
		totalMovieTime = 0;
		start();
	}

	// Agrega un frame a la animacion
	@SuppressWarnings("unchecked")
	public synchronized void addFrame(Image img, Long time){
		this.totalMovieTime += time;
		frameList.add(new AnimationFrame(img, this.totalMovieTime));
	}
	
	public synchronized void start(){
		this.currentMovieTime = 0;
		this.frameIndex = 0;
	}
	
	public synchronized void update(long timePassed){
		if(this.frameList.size() > 1){
			this.currentMovieTime += timePassed;
			if (this.currentMovieTime >= this.totalMovieTime){
				//Reinicia la animacion, porque llego al ultimo frame
				this.currentMovieTime = 0;
				this.frameIndex = 0;
			}
			while (this.currentMovieTime > getFrame(this.frameIndex).getEndTime() ){
				this.frameIndex++;
			}
		}
	}
	
	
	public synchronized Image getImage(){
		if(frameList.size() == 0){
			return null;
		}else{
			return getFrame(this.frameIndex).getImage();
		}
	}
	
	private AnimationFrame getFrame(int i){
		return (AnimationFrame) this.frameList.get(i);
	}
}
