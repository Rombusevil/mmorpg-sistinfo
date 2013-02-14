package mmorpg.userInterface.output.GUI.utils;

import java.awt.Image;

public class Sprite {

	private Animation a;

	private float x;
	private float y;
	private float velocityX;
	private float velocityY;

	// Puedo construir un Sprite en base a una animacion
	public Sprite(Animation a) {
		this.a = a;
	}

	public void update(long timePassed) {
		// Calcula la posicion en la pantalla
		this.x += this.velocityX * timePassed;
		this.y += this.velocityY * timePassed;
		// Calcula la animacion
		this.a.update(timePassed);
	}
	
	public Image getImage(){
		return this.a.getImage();
	}

	/* Getters And Setters */
	public int getWidth(){
		return this.a.getImage().getWidth(null);
	}
	
	public int getHeight(){
		return this.a.getImage().getHeight(null);
	}
	
	
	public Animation getAnimation(){
		return this.a;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}
	
	

}
