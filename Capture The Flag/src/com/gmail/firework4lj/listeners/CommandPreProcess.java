package com.gmail.firework4lj.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import com.gmail.firework4lj.main.Main;

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
