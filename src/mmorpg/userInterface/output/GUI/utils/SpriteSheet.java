package mmorpg.userInterface.output.GUI.utils;

import java.awt.image.BufferedImage;

/**
 * SpriteSheet: Es una imagen con muchos personajes/entidades dibujados en ella. Esto evita tener que cargar un archivo por cada imagen del juego. En lugar de eso, simplemente
 * obtenemos una "sub imagen" del SpriteSheet que corresponda al personaje que deseamos.
 * 
 */
public class SpriteSheet {

	private BufferedImage spriteSheet;

	
	public SpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
	
	/**
	 * Este metodo es la clave de los Sprites. Recibe una imagen (normlamente un SpriteSheet) Y en base a los parametros obtiene una sub imagen, la cual devuelve como una imagen
	 * nueva.
	 * 
	 * @param x
	 *            - Coordenada X de la subimagen
	 * @param y
	 *            - Coordenada Y de la subimagen
	 * @param width
	 *            - Ancho de la subimagen
	 * @param height
	 *            - Alto de la subimagen
	 * @return un nuevo BufferedImage.
	 */
	public BufferedImage getSubSprite(int x, int y, int width, int height) {
		BufferedImage sprite = spriteSheet.getSubimage(x, y, width, height);
		return sprite;
	}

	public BufferedImage getFullSprite() {
		return this.spriteSheet;
	}

}
