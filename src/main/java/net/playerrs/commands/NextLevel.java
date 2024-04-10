package net.playerrs.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import static net.playerrs.config.ConfigGen.config;
import static net.playerrs.datahandler.FileManager.getFileData;
import static net.playerrs.datahandler.FileManager.saveFileData;

public class NextLevel implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        int lvl;
        Player player;

        //verifications
        if (strings.length == 0) {
            player = (Player) sender;
        } else {
            player = Bukkit.getPlayer(strings[0]);
            if (Bukkit.getPlayer(strings[0]) == null) {
                sender.sendMessage(ChatColor.RED + "Player not found");
                return false;
            }
        }

        try {
            lvl = Integer.parseInt(getFileData("/data/", sender.getName() + ".json", "plevel"));
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            sender.sendMessage(ChatColor.RED + "[RankPlaceHolders]: You don't have a premium level :(");
            return false;
        }

        if (lvl < config.ranks) {
            lvl++;
        }

        //sender.sendMessage("Your time played in hours: " + String.valueOf(playedTime));     //debug
        //sender.sendMessage("Your level: " + lvl);                           //debug
        sender.sendMessage(ChatColor.AQUA + "[RankPlaceHolders]: You are now level: " + lvl + "!");

        saveFileData("/data/", sender.getName() + ".json", "plevel", String.valueOf(lvl));
        return true;
    }
}
