package com.gmail.firework4lj.commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.firework4lj.main.Main;
import com.gmail.firework4lj.util.IconMenu;

public class Classes implements CommandExecutor{

	private Main Plugin;
	private Main main;
	public Classes(Main Main) {
		this.main = Main;
	}

	/* How this class works:
	* Player enters '/class example'
	* if example is found in the main config file, the command then pulls information from the
	* class folder, where a file named example is found. 
	* --Null pointer if the file is not there, although that wouldn't happen unless it was manually deleted.--
	* example file contains inventory information for all armor and items in the class. 
	* class inventory is put into players inventory.
	* red or blue flag is "layered" over players helmet to allow others to see what team they are on.
	* Players name is put into a hashmap along with the class name. This allows the class to be re-applied after the player is killed.
	* 
	* Player enters '/classes' and no arguments. If classes have been created by the admin and exist in the config file, 
	* player receives a message listing all available classes.
	* If no classes exist, player receives an error message telling them to ask the server admin to setup some classes.
	*
	*/
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		final Player p = (Player) sender;
		
		// Main classes command
		if(cmd.getName().equalsIgnoreCase("classes")){
			if(Main.ctfingame.containsKey(p.getName())){
				if(args.length == 1){
						YamlConfiguration c = YamlConfiguration.loadConfiguration(new File("plugins/Capture-the-Flag/classes/"+args[0]+".yml"));
						// Try statement is in place for if the class is not valid.
						try{
							ItemStack[] content = ((List<ItemStack>) c.get("Classes."+args[0]+".armor")).toArray(new ItemStack[0]);
							p.getInventory().setArmorContents(content);
							content = ((List<ItemStack>) c.get("Classes."+args[0]+".items")).toArray(new ItemStack[0]);
							p.getInventory().setContents(content);
							main.ctfclass.put(p.getName(), args[0]);
							if(main.teamblue.containsKey(p.getName())){
								p.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (short) 11));
							}else if(main.teamred.containsKey(p.getName())){
								p.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (short) 14));
							}
							main.msg(p, ChatColor.GOLD+"Class changed to: "+args[0]);
							// This means the class was not found in the config file.
						} catch(NullPointerException exception){
							main.msg(p, ChatColor.DARK_RED+"That is not a valid class!");
						}
				}else{
					try {
						main.getConfig().getConfigurationSection("Classes").getKeys(false);
						main.msg(p, ChatColor.GREEN+"Available classes are:");
						p.sendMessage(ChatColor.BLACK+"["+ChatColor.GOLD+"Ctf"+ChatColor.BLACK+"] "+ChatColor.GOLD+main.getConfig().getConfigurationSection("Classes").getKeys(false));
						main.msg(p, ChatColor.GREEN+"Please use /classes (Class Name) to choose a class");
					} catch (NullPointerException exception) {
						main.msg(p, ChatColor.DARK_RED+"There are no classes setup yet! Please ask your server admin to set one up!");
					}
				}
			}else{
				main.msg(p, ChatColor.DARK_RED+"You must join the game first! Use /ctf join");
			}
		}
		return false;
	}
}
