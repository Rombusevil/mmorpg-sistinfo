package mmorpg.servidor;

import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GestorComandos {

	private Map <Actor, Socket> socketList;	
	private Object hashMonitor;
	
	/* Constructor */
	public GestorComandos(){
		socketList = new HashMap<Actor, Socket>(); // init HashMap
	}

	
	
	public void agregarPjSocket(Actor pj, Socket socket){			
		hashMonitor = new Object();
		synchronized (this.hashMonitor){
			this.getSocketList().put(pj, socket);
		}
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	/* Getters y Setters */
	private Map<Actor, Socket> getSocketList() {
		return socketList;
	}

	private void setSocketList(Map<Actor, Socket> socketList) {
		this.socketList = socketList;
	}
	
	public void printMap() {
		Map mp = this.socketList;
	    Iterator it = mp.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        System.out.println(pairs.getKey() + " = " + pairs.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}

}
