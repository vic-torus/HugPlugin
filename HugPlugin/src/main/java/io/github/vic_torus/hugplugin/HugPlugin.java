package io.github.vic_torus.hugplugin;

import java.util.UUID;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class HugPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		this.getLogger().info("Hug Plugin Activated!");
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if (label.equalsIgnoreCase("hug")) {
			
			// If sender is a player who has permission to use the "hug" command:
			if ((sender instanceof Player && sender.hasPermission("hugplugin.hug")) || sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender) {
				
				// If the number of arguments attached to the "hug" command is not 1 (one target name):
				if (arguments.length != 1) {
					// Send a chat message to sender saying that they must specify one target to hug
					sender.sendMessage(ChatColor.RED + "Sorry, you must choose one player to hug at a time!");
					return false;
				}
				
				// Else (Assuming the number of arguments attached to the "hug" command is exactly zero or one):
				else {
					// Retrieve target entered into command and place in variable
					Player target = this.getServer().getPlayer(arguments[0]);
					
					// If target is online:
					if (target != null) {
							// Send a chat message to target saying that they have been hugged by sender
							target.sendMessage(ChatColor.GREEN + "You have been hugged by " + sender.getName() + "!");
							// Send a chat message to sender saying that they have hugged target
							sender.sendMessage(ChatColor.GREEN + "You have hugged " + target.getDisplayName() + "!");
							return true;
						}	
						
					// If target is offline:
					else {
						// Send a chat message to sender saying that target is not available to hug
						sender.sendMessage(ChatColor.RED + "Sorry, that player is not online to hug at this time.");
						return false;
					}	
				}
			}
			
				// else if sender does NOT have permission to use the "hug" command:
				else {
					// Send a red "No Permission." message in chat to sender
					sender.sendMessage(ChatColor.RED + "No permission.");
					return false;
				}	
		}
		
		return true;
	}
	
	@Override
	public void onDisable() {
		this.getLogger().info("Hug Plugin Shutting Down!");
	}

}
