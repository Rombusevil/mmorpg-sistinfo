package mmorpg.userInterface.output.GUI.utils;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Window;

public abstract class Core {
	
	private static final DisplayMode modes[] = { 
		new DisplayMode(800, 600, 32, 0), 
		new DisplayMode(800, 600, 24, 0), 
		new DisplayMode(800, 600, 16, 0),
		new DisplayMode(640, 480, 32, 0), 
		new DisplayMode(640, 480, 24, 0), 
		new DisplayMode(640, 480, 16, 0), 
	};	
	private boolean running;
	protected ScreenManager screen;

	public void stop(){
		this.running = false;
	}
	
	public void run(){
		try{
			init();
			gameLoop();
		}finally{
			this.screen.restoreScreen();
		}
	}
	
	public void init(){
		this.screen = new ScreenManager();
		DisplayMode dm = this.screen.findFirstCompaitbleMode(this.modes);
		this.screen.setFullScreen(dm);
		
		Window w = this.screen.getFullScreenWindow();
		w.setFont(new Font("Arial", Font.PLAIN, 20));
		w.setBackground(Color.GREEN);
		w.setForeground(Color.WHITE);
		this.running = true;
	}
	
	public void gameLoop(){
		long startingTime = System.currentTimeMillis();
		long timeCounter = startingTime;

		while (running) {
			long timePassed = System.currentTimeMillis() - timeCounter;
			timeCounter += timePassed;
			
			update(timePassed);

			Graphics2D g = this.screen.getGraphics2D();
			draw(g);
			g.dispose();
			this.screen.update();
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(long timePassed){
	}
	
	public abstract void draw(Graphics2D g);
	
}
