package net.playerrs.commands;

import java.util.Map;
import net.playerrs.config.ConfigGen;
import net.playerrs.datahandler.CacheHandler;
import net.playerrs.datahandler.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NextLevel implements CommandExecutor {
    public NextLevel() {
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player player = (Player)sender;
        if (ConfigGen.config.useToken) {
            if (strings.length == 0) {
                player.sendMessage(ChatColor.RED + "[RankPlaceHolders]: Você precisa incluir o Token de verificação: /nextlevel <TOKEN>");
                return false;
            }

            if (!strings[0].equals(ConfigGen.config.token)) {
                player.sendMessage(ChatColor.RED + "[RankPlaceHolders]: Token inválido!");
                return false;
            }
        }

        int lvl;
        try {
            lvl = Integer.parseInt((String)((Map)CacheHandler.playersData.get(player.getName())).get("plevel"));
        } catch (NumberFormatException var8) {
            player.sendMessage(ChatColor.RED + "[RankPlaceHolders]: Você não tem um level premium :(");
            return false;
        }

        if (lvl < ConfigGen.config.ranks) {
            ++lvl;
        }

        player.sendMessage(ChatColor.AQUA + "[RankPlaceHolders]: Seu level Premium: " + lvl + " / " + ChatColor.RESET + (String)CacheHandler.lvls.get(lvl));
        FileManager.saveFileData("/data/", sender.getName() + ".json", "plevel", String.valueOf(lvl));
        return true;
    }
}