package net.playerrs.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        };



        try {
            lvl = Integer.parseInt(getFileData("/data/", sender.getName() + ".json", "plevel"));
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            sender.sendMessage(ChatColor.RED + "[RankPlaceHolders]: You don't have a premium level :(");
            return false;
        }

        int total = config.ranks;
        List<Integer> time = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            time.add(Integer.parseInt(getFileData("/configs/", "time.json", String.valueOf(i))));
        }

        List<Integer> lvls = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            lvls.add(Integer.parseInt(getFileData("/configs/", "lvls.json", String.valueOf(i))));
        }

        List<Integer> quests = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            quests.add(Integer.parseInt(getFileData("/configs/", "quests.json", String.valueOf(i))));
        }


        int playedTime = ((player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20) / 60) / 60;


        for(int i = 0; i < time.size(); i++) {
            if(playedTime >= time.get(i)) {
                lvl = i;

                sender.sendMessage(String.valueOf(quests.get(i))); //TODO unlock quest

            }
        }

        sender.sendMessage("Your time played in hours: " + String.valueOf(playedTime));     //debug
        sender.sendMessage("Your level: " + String.valueOf(lvl));                           //debug

        saveFileData("/data/", sender.getName() + ".json", "plevel", String.valueOf(lvl));
        return true;
    }
}
