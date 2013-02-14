package mmorpg.userInterface.output.GUI.utils;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class SpriteFactory{

	private HashMap spriteMap;										// All Sprites HashMap.
	private static SpriteFactory instance = new SpriteFactory();	// Singleton pattern.


	// get the Singleton
	public static SpriteFactory get() {
		return instance;
	}
	
	private SpriteFactory() {
		buildSprites();
	}
	

	// Concrete Sprite builder
	@SuppressWarnings("unchecked")
	private void buildSprites() {
		this.spriteMap = new HashMap();
		
		BufferedImageLoader loader = new BufferedImageLoader();	// Para cargar imagenes
		String path = null;	// El Path de las imagenes
		BufferedImage singleImage;
		BufferedImage spriteSheetImage;
		SpriteSheet spriteSheet;
	
		// Player sprites	
		path = "player_spritesheet.png";
		spriteSheetImage = loader.loadImage(path); // Loads image from path - Player SpriteSheet
		spriteSheet = new SpriteSheet(spriteSheetImage);
		
		// Player_Front	
		Animation animationFront = new Animation();
		singleImage = spriteSheet.getSubSprite(0, 0, 32, 32);   // gets sub-image from Spritesheet at 00; 00 coordinates with 32x32 size - Player_Front_right
		animationFront.addFrame(singleImage, (long) 250);
		singleImage = spriteSheet.getSubSprite(32, 0, 32, 32);  // gets sub-image from Spritesheet at 32; 00 coordinates with 32x32 size - Player_Front_stand
		Animation animationAux1 = new Animation();
		animationAux1.addFrame(singleImage, (long) 0);
		this.spriteMap.put(new String("Player_front_stand"), new Sprite(animationAux1));	// Agrego la IMAGEN del personaje estatico de frente
		animationFront.addFrame(singleImage, (long) 250);
		singleImage = spriteSheet.getSubSprite(64, 0, 32, 32);  // gets sub-image from Spritesheet at 64; 00 coordinates with 32x32 size - Player_Front_left
		animationFront.addFrame(singleImage, (long) 250);
		this.spriteMap.put(new String("Player_front_animation"), new Sprite(animationFront));		
		
		// Player_Left		
		Animation animationLeft = new Animation();
		singleImage = spriteSheet.getSubSprite(0, 32, 32, 32);  // gets sub-image from Spritesheet at 00; 32 coordinates with 32x32 size - Player_Left_right
		animationLeft.addFrame(singleImage, (long) 250);
		singleImage = spriteSheet.getSubSprite(32, 32, 32, 32); // gets sub-image from Spritesheet at 32; 32 coordinates with 32x32 size - Player_Left_stand
		Animation animationAux2 = new Animation();
		animationAux2.addFrame(singleImage, (long) 0);
		this.spriteMap.put(new String("Player_left_stand"), new Sprite(animationAux2));	// Agrego la IMAGEN del personaje estatico de costado izquierdo
		animationLeft.addFrame(singleImage, (long) 250);
		singleImage = spriteSheet.getSubSprite(64, 32, 32, 32); // gets sub-image from Spritesheet at 64; 32 coordinates with 32x32 size - Player_Left_left
		animationLeft.addFrame(singleImage, (long) 250);
		this.spriteMap.put(new String("Player_left_animation"), new Sprite(animationLeft));
		
		// Player_Right	
		Animation animationRight = new Animation();
		singleImage = spriteSheet.getSubSprite(0, 64, 32, 32);  // gets sub-image from Spritesheet at 00; 64 coordinates with 32x32 size - Player_Right_right
		animationRight.addFrame(singleImage, (long) 250);
		singleImage = spriteSheet.getSubSprite(32, 64, 32, 32); // gets sub-image from Spritesheet at 32; 64 coordinates with 32x32 size - Player_Right_stand
		Animation animationAux3 = new Animation();
		animationAux3.addFrame(singleImage, (long) 0);
		this.spriteMap.put(new String("Player_right_stand"), new Sprite(animationAux3));	// Agrego la IMAGEN del personaje estatico de costado izquierdo
		animationRight.addFrame(singleImage, (long) 250);
		singleImage = spriteSheet.getSubSprite(64, 64, 32, 32); // gets sub-image from Spritesheet at 64; 64 coordinates with 32x32 size - Player_Right_left
		animationRight.addFrame(singleImage, (long) 250);
		this.spriteMap.put(new String("Player_right_animation"), new Sprite(animationRight));
		
		// Player_Back
		Animation animationBack = new Animation();
		singleImage = spriteSheet.getSubSprite(0, 96, 32, 32);  // gets sub-image from Spritesheet at 00; 96 coordinates with 32x32 size - Player_Back_right
		animationBack.addFrame(singleImage, (long) 250);
		singleImage = spriteSheet.getSubSprite(32, 96, 32, 32); // gets sub-image from Spritesheet at 32; 96 coordinates with 32x32 size - Player_Back_stand
		Animation animationAux4 = new Animation();
		animationAux4.addFrame(singleImage, (long) 0);
		this.spriteMap.put(new String("Player_back_stand"), new Sprite(animationAux4));	// Agrego la IMAGEN del personaje estatico de costado izquierdo
		animationBack.addFrame(singleImage, (long) 250);
		singleImage = spriteSheet.getSubSprite(64, 96, 32, 32); // gets sub-image from Spritesheet at 64; 96 coordinates with 32x32 size - Player_Back_left
		animationBack.addFrame(singleImage, (long) 250);
		this.spriteMap.put(new String("Player_back_animation"), new Sprite(animationBack));
		
		//Background Sprite
		Animation animationBG = new Animation();
		path = "woods.jpg";
		spriteSheetImage = loader.loadImage(path); // Loads image from path - Background Sprite
		SpriteSheet spriteSheet2;
		spriteSheet2 = new SpriteSheet(spriteSheetImage);		
		singleImage = spriteSheet2.getFullSprite(); // gets full sprite for the BackGround
		animationBG.addFrame(singleImage, (long) 250);
		this.spriteMap.put(new String("background"), new Sprite(animationBG));
		
		// Launcher Image
		Animation animationLauncher = new Animation();
		path = "launcher_splash.jpg";
		spriteSheetImage = loader.loadImage(path); // Loads image from path - Background Sprite
		SpriteSheet spriteSheet3;
		spriteSheet3 = new SpriteSheet(spriteSheetImage);
		singleImage = spriteSheet3.getFullSprite(); // gets full sprite for the BackGround
		animationBG.addFrame(singleImage, (long) 250);
		this.spriteMap.put(new String("launcher_splash"), new Sprite(animationLauncher));
	}

	// Gets one sprite from hashmap
	public Sprite getSprite(String codename) {
		return (Sprite) this.spriteMap.get(codename);
	}

	
}