package mmorpg.acciones.chat;

import java.io.Serializable;

import mmorpg.userInterface.output.GUI.ChatGUI;

public class ImpMostrarEnChat implements MostrarEnChat {
	//TODO conoce a la GUI para imprimir en su chat.
	private ChatGUI chat;
	
	public void actuar(String a, String b){
		getChat().mostrarEnChat(a,b);
	}
	public ChatGUI getChat() {
		return chat;
	}
	public void setChat(ChatGUI chat) {
		this.chat = chat;
	}
	

}
