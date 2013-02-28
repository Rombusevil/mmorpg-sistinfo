package mmorpg.acciones.chat;

import mmorpg.userInterface.output.GUI.ChatGUI;

public interface MostrarEnChat {
	public void actuar(String a, String b);
		
	public ChatGUI getChat() ;
		
	public void setChat(ChatGUI chat);
		

}
