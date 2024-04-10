package net.playerrs.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.playerrs.RankPlaceHolders;
import net.playerrs.datahandler.CacheHandler;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

import static net.playerrs.config.ConfigGen.config;
import static net.playerrs.datahandler.FileManager.getFileData;
import static org.bukkit.Bukkit.getLogger;

public class PlaceHolder extends PlaceholderExpansion {

    private RankPlaceHolders plugin;

    public PlaceHolder (RankPlaceHolders plugin) {
        this.plugin = plugin;
    }
    @Override
    public @NotNull String getIdentifier() {
        return "rankph";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Player_rs, Reavik";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.83";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {

        if(params.equalsIgnoreCase("plevel")){
            //return getFileData("/data/", player.getName() + ".json", "plevel");
            return CacheHandler.playersData.get(player.getName()).get("plevel");
        }

        if(params.equalsIgnoreCase("displayplevel")){
            String displayLevel;


            try {
                //displayLevel = getFileData("/configs/", "lvls.json", getFileData("/data/", player.getName() + ".json", "plevel"));
                displayLevel = CacheHandler.lvls.get(Integer.parseInt(CacheHandler.playersData.get(player.getName()).get("plevel")));
            } catch (Exception e) {
                //e.printStackTrace();
                return config.displayDefault;
            }

            return displayLevel;
        }


        return null;
    }
}
