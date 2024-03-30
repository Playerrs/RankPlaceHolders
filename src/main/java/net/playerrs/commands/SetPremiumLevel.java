package net.playerrs.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.playerrs.datahandler.FileManager.getFileData;
import static net.playerrs.datahandler.FileManager.saveFileData;

public class SetPremiumLevel implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        // verifications
        if (strings.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /setpremiumlevel <player> <level>");
            return false;
        }

        if (Bukkit.getPlayer(strings[0]) == null) {
            sender.sendMessage(ChatColor.RED + "Player not found");
            return false;
        }

        //

        String player = strings[0];
        String lvl = strings[1];

        saveFileData("/data/", player + ".json", "plevel", lvl);

        sender.sendMessage(ChatColor.AQUA + "[RankPlaceHolders]: The premium level of " + player + " is now " + lvl);


        return true;
    }
}
