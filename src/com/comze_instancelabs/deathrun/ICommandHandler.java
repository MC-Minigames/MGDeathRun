package com.comze_instancelabs.deathrun;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.comze_instancelabs.minigamesapi.MinigamesAPI;
import com.comze_instancelabs.minigamesapi.PluginInstance;
import com.comze_instancelabs.minigamesapi.commands.CommandHandler;
import com.comze_instancelabs.minigamesapi.util.Util;

public class ICommandHandler extends CommandHandler {

	@Override
	public boolean setSpawn(PluginInstance pli, CommandSender sender, String[] args, String uber_permission, String cmd, String action, JavaPlugin plugin, Player p) {
		if (!sender.hasPermission(uber_permission + ".setup")) {
			sender.sendMessage(pli.getMessagesConfig().no_perm);
			return true;
		}
		if (args.length > 1) {
			int i = pli.arenaSetup.autoSetSpawn(plugin, args[1], p.getLocation());
			sender.sendMessage(pli.getMessagesConfig().successfully_set.replaceAll("<component>", "spawn " + Integer.toString(i)));
		} else {
			sender.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "-" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Usage: " + cmd + " " + action + " <arena>");
		}
		return true;
	}


}
