package mmorpg.userInterface.output.GUI.utils;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * Screen Manager permite comunicacion con la VGA (Placa de Video). Maneja los Display Modes compatibles y el FullScreen.
 * 
 * INTERFACE:
 * 
 * public ScreenManager(); Constructor
 * 
 * public DisplayMode[] getCompatibleDisplayModes(); Devuelve una lista, con los DM compatibles con la VGA
 * 
 * public DisplayMode findFirstCompatibleDisplayMode(DisplayMode modes[]); Encuentra el primer DM compatible y lo retorna.
 * 
 * public DisplayMode getCurrentDisplayMode(); Obtiene el DM actual y lo retorna.
 * 
 * public void setFullScreen(DisplayMode dm); Cambia a fullScreen mode
 * 
 * public Graphics2D getGraphics2D(); Obtiene los Graficos de la pantalla, se usa en el draw() method
 * 
 * public Window getFullScreenWindow();
 * 
 * public int getWidth();
 * 
 * public int getHeight();
 * 
 * public void restoreScreen();
 * 
 * public BufferedImage createCompatibleImage(int w, int h, int alpha)
 * 
 * @author cyberpunx
 * 
 */
public class ScreenManager {

	private GraphicsDevice vga;

	public ScreenManager() {
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vga = e.getDefaultScreenDevice();	// La VGA obtiene acceso al monitor
	}

	// Obtiene todo los DisplayModes compatibles
	public DisplayMode[] getCompatibleDisplayModes() {
		return this.vga.getDisplayModes();
	}

	// Compara el DisplayMode recibido con el
	// DisplayMode seteado en la VGA y se fija si coinciden
	public DisplayMode findFirstCompaitbleMode(DisplayMode modes[]) {
		DisplayMode goodModes[] = this.vga.getDisplayModes();
		for (int x = 0; x < modes.length; x++) {
			for (int y = 0; y < goodModes.length; y++) {
				if (isDisplayModesMatch(modes[x], goodModes[y])) {
					return modes[x];
				}
			}
		}
		return null;
	}

	// Compara dos DM entre si y se fija si son iguales
	private boolean isDisplayModesMatch(DisplayMode m1, DisplayMode m2) {
		// Compara la resolucion
		if (m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight()) {
			return false;
		}
		// Compara los colores
		if (m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth() != m2.getBitDepth()) {
			return false;
		}
		// Compara el Refresco
		if (m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate() != m2.getRefreshRate()) {
			return false;
		}
		return true;
	}

	// get current DM
	public DisplayMode getCurrentDisplayMode() {
		return this.vga.getDisplayMode();
	}

	// Make Frame fullscreen
	public void setFullScreen(DisplayMode dm) {
		JFrame f = new JFrame();
		f.setUndecorated(true);
		f.setIgnoreRepaint(true);
		f.setResizable(false);
		this.vga.setFullScreenWindow(f);

		if (dm != null && this.vga.isDisplayChangeSupported()) {
			try {
				this.vga.setDisplayMode(dm);
			} catch (Exception e) {

			}
			f.createBufferStrategy(2);
		}
	}

	public Graphics2D getGraphics2D() {
		Window w = this.vga.getFullScreenWindow();
		if (w != null) {
			BufferStrategy s = w.getBufferStrategy();
			return (Graphics2D) s.getDrawGraphics();
		} else {
			return null;
		}
	}
	
	//updates display
	public void update(){
		Window w = this.vga.getFullScreenWindow();
		if(w != null){
			BufferStrategy s = w.getBufferStrategy();
			if(!s.contentsLost()){
				s.show();
			}
		}
	}
	
	//returns full screen window
	public Window getFullScreenWindow(){
		return this.vga.getFullScreenWindow();
	}
	
	public int getWidth(){
		Window w = this.vga.getFullScreenWindow();
		if(w != null){
			return w.getWidth();
		}else{
			return 0;
		}		
	}
	
	public int getHeight(){
		Window w = this.vga.getFullScreenWindow();
		if(w != null){
			return w.getHeight();
		}else{
			return 0;
		}		
	}
	
	public void restoreScreen(){
		Window w = this.vga.getFullScreenWindow();
		if (w != null){
			w.dispose();
		}
		this.vga.setFullScreenWindow(null);
	}
	
	
	// Create a image compatible with monitor
	public BufferedImage createCompatibleImage(int w, int h, int alpha){
		Window win = this.vga.getFullScreenWindow();
		if(win != null){
			GraphicsConfiguration gc = win.getGraphicsConfiguration();
			return gc.createCompatibleImage(w, h, alpha);
		}
		return null;
	}
	
}
