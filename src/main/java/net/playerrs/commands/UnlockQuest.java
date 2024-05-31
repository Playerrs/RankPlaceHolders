
package net.playerrs.commands;

import net.playerrs.config.ConfigGen;
import net.playerrs.datahandler.CacheHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnlockQuest implements CommandExecutor {
    public UnlockQuest() {
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player player;
        if (strings.length == 0) {
            player = (Player)sender;
        } else {
            player = Bukkit.getPlayer(strings[0]);
            if (Bukkit.getPlayer(strings[0]) == null) {
                sender.sendMessage(ChatColor.RED + "Player not found");
                return false;
            }
        }

        int total = ConfigGen.config.ranks;
        int playedTime = player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20 / 60 / 60;
        int newQts = 0;
        int nextQuest = 0;

        for(int i = 0; i < CacheHandler.time.size(); ++i) {
            ++newQts;
            if (playedTime < Integer.parseInt((String)CacheHandler.time.get(i))) {
                nextQuest = Integer.parseInt((String)CacheHandler.time.get(i));
                break;
            }

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ftbquests change_progress " + player.getName() + " complete " + (String)CacheHandler.quests.get(i));
        }

        player.sendMessage(ChatColor.AQUA + "[RankPlaceHolders]: " + newQts + " quests estão disponíveis!");
        player.sendMessage(ChatColor.GOLD + "[RankPlaceHolders]: Jogue por mais " + (nextQuest - playedTime) + " horas para desbloquear a próxima quest!");
        return true;
    }
}
