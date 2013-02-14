package mmorpg.userInterface.output.GUI.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Recibe el path relativo de una imagen y la carga.
 * @author cyberpunx
 * 
 */
public class BufferedImageLoader {

	public BufferedImage loadImage(String pathRelativeToThis){
		BufferedImage img = null;
		try{
			URL url = this.getClass().getClassLoader().getResource("\\mmorpg\\userInterface\\res\\" + pathRelativeToThis);
			if (url != null){
				img = ImageIO.read(url);
				return img;
			} else {
				System.out.println("No se pudo cargar la imagen: " + pathRelativeToThis + " - fp: " + url);
				return null;
			}
		}catch(IOException e){
			System.out.println("Recurso no encontrado en: res/" + pathRelativeToThis);
			e.printStackTrace();
		}
		return null;
	}

}

        