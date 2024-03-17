package net.playerrs.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.playerrs.datahandler.FileManager.getPlayerData;
import static net.playerrs.datahandler.FileManager.savePlayerData;

public class SetPremiumPass implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        String player = strings[0];
        int lvl = Integer.parseInt(strings[1]);



        savePlayerData(player, "premiumpass", String.valueOf(lvl));
        sender.sendMessage(ChatColor.AQUA + "[RankPlaceHolders]: O nível de Premium Pass de " + player + " foi alterado para " + getPlayerData(player, "premiumpass"));

        return true;
    }
}
