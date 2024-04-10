package net.playerrs.commands;

import net.playerrs.datahandler.CacheHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.playerrs.config.ConfigGen.config;
import static net.playerrs.config.ConfigGen.saveConfig;
import static net.playerrs.datahandler.FileManager.saveFileData;

public class UnlockQuest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

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
        };

        int total = config.ranks;

//        List<Integer> time = new ArrayList<>();
//        for (int i = 0; i < total; i++) {
//            time.add(Integer.parseInt(getFileData("/configs/", "time.json", String.valueOf(i))));
//        }
//
//        List<Integer> lvls = new ArrayList<>();
//        for (int i = 0; i < total; i++) {
//            lvls.add(Integer.parseInt(getFileData("/configs/", "lvls.json", String.valueOf(i))));
//        }
//
//        List<Integer> quests = new ArrayList<>();
//        for (int i = 0; i < total; i++) {
//            quests.add(Integer.parseInt(getFileData("/configs/", "quests.json", String.valueOf(i))));
//        }




        int playedTime = ((player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20) / 60) / 60;
        int newQts = 0;
        for(int i = 0; i < CacheHandler.time.size(); i++) {
            newQts++;
            if(playedTime >= Integer.parseInt(CacheHandler.time.get(i))) {
                //ftbquests change_progress <player> complete <quest>
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ftbquests change_progress " + player.getName() + " complete " + CacheHandler.quests.get(i));
                //Bukkit.getConsoleSender().sendMessage("\n" + player.getName() + " " + CacheHandler.quests.get(i) + "\n"); //Debug
            }
        }
        sender.sendMessage(ChatColor.AQUA + "[RankPlaceHolders]: " + newQts + " quests are available!");

        return true;
    }
}
