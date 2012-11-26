package mmorpg.servidor;

import java.util.Scanner;

import javax.swing.JFrame;

public class _LauncherConsola {
	
	public static void main(String[] args) {		
		Scanner in = new Scanner(System.in);
		int opcion;			
		__ClienteTest__deprecated client;		
		Server s;		

		System.out.println("******************************\n");
		System.out.println("Elija una opcion:");
		System.out.println("1. Server");
		System.out.println("2. Cliente");
		opcion=in.nextInt();
		in.close();
		
		if(opcion == 1){
			System.out.println("Ejecutando el Server");	
			
			s = new Server();
			s.mainServer();			
		}
		if(opcion == 2){
			System.out.println("Ejecutando el Cliente");
			
			client = new __ClienteTest__deprecated("127.0.0.1");
			client.startRunning();
		}

	}

}
