package mmorpg.servidor;

import java.util.Scanner;

import javax.swing.JFrame;

public class _LauncherConsola {

	
	

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int opcion;
		
//		_ServerChat server;
//		_ClientChat client;
		
		__ClienteTest__deprecated client;
		_ServerViejo server;
		

		System.out.println("******************************\n");
		System.out.println("Elija una opcion:");
		System.out.println("1. Server");
		System.out.println("2. Cliente");
		opcion=in.nextInt();
		in.close();
		
		if(opcion == 1){
			System.out.println("Ejecutando el Server");
			
			
			server = new _ServerViejo();
			//server.startRuning();
			
//			server = new _ServerChat(); 
//        	server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        	server.startRunning();
		}
		if(opcion == 2){
			System.out.println("Ejecutando el Cliente");
			
			client = new __ClienteTest__deprecated("127.0.0.1");
			client.startRunning();
			
//			client = new _ClientChat("127.0.0.1");
//        	client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        	client.startRunning();
			
		}

	}

}
