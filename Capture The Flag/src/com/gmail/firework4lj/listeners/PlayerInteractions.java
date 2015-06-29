package com.gmail.firework4lj.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import com.gmail.firework4lj.main.Main;

public class PlayerInteractions implements Listener{

	private Main main;
	public PlayerInteractions(Main Main) {
		this.main = Main;
	}
	
	
	// BLOCK INTERACTIONS BEGIN
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player p = event.getPlayer();
		if (p.isOp()) {
			event.setCancelled(false);
		}else if(Main.ctfingame.containsKey(p.getName())){
			event.setCancelled(true);
		}else{
			event.setCancelled(false);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		if (p.isOp()) {
			event.setCancelled(false);
		}else if(Main.ctfingame.containsKey(p.getName())){
			event.setCancelled(true);
		}else{
			event.setCancelled(false);
		}
	}
	// BLOCK INTERACTIONS END
	
	
	// INVENTORY INTERACTIONS BEGIN
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		Player p = event.getPlayer();
		if(Main.ctfingame.containsKey(p.getName())){
			event.setCancelled(true);
		}else{
			event.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onPlayerMoveInventory(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(main.ctfingame.containsKey(p.getName())){
			if(e.getSlot() == 39){
				e.setCancelled(true);
			}
		}else{
			e.setCancelled(false);
		}
	}
}
