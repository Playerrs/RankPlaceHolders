
package net.playerrs.commands;

import net.playerrs.datahandler.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetPremiumLevel implements CommandExecutor {
    public SetPremiumLevel() {
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (strings.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /setpremiumlevel <player> <level>");
            return false;
        } else if (Bukkit.getPlayer(strings[0]) == null) {
            sender.sendMessage(ChatColor.RED + "Player not found");
            return false;
        } else {
            String player = strings[0];
            String lvl = strings[1];
            FileManager.saveFileData("/data/", player + ".json", "plevel", lvl);
            sender.sendMessage(ChatColor.AQUA + "[RankPlaceHolders]: The premium level of " + player + " is now " + lvl);
            return true;
        }
    }
}