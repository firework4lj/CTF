package com.gmail.firework4lj.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import com.gmail.firework4lj.main.Main;

	/* What this class does:
	 * 
	 * This is an event handler class. However, it does deal with commands.
	 * What this class does is listens for a '/reload' command from an in game player, or an administrator issues a 'reload' command from the console.
	 * When this is done, the server forces all players in the ctf game to use '/ctf leave'
	 * 
	 * This class is here because there are no efficient methods for executing commands on reload.
	 */

public class CommandPreProcess implements Listener{

	private Main main;
	public CommandPreProcess(Main Main) {
		this.main = Main;
	}
	
	@EventHandler
	public void onCommandPreProcessEvent(PlayerCommandPreprocessEvent e) {
		if(e.getMessage().equalsIgnoreCase("/reload")){
			for(String pl : Main.ctfingame.keySet()){
				Bukkit.getPlayerExact(pl).performCommand("ctf leave");
			}
		}
	}	
	
	@EventHandler
	public void onConsoleCommandPreprocessEvent(ServerCommandEvent e){
		if(e.getCommand().equalsIgnoreCase("reload")){
			for(String pl : Main.ctfingame.keySet()){
				Bukkit.getPlayerExact(pl).performCommand("ctf leave");
			}
		}
	}
}
