package net.playerrs.placeholders;

import java.util.Map;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.playerrs.RankPlaceHolders;
import net.playerrs.config.ConfigGen;
import net.playerrs.datahandler.CacheHandler;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PlaceHolder extends PlaceholderExpansion {
    private RankPlaceHolders plugin;

    public PlaceHolder(RankPlaceHolders plugin) {
        this.plugin = plugin;
    }

    public @NotNull String getIdentifier() {
        return "rankph";
    }

    public @NotNull String getAuthor() {
        return "Player_rs, Reavik";
    }

    public @NotNull String getVersion() {
        return "1.83";
    }

    public boolean persist() {
        return true;
    }

    public String onRequest(OfflinePlayer player, @NotNull String params) {
        String displayLevel;
        if (params.equalsIgnoreCase("plevel")) {
            try {
                displayLevel = (String)((Map)CacheHandler.playersData.get(player.getName())).get("plevel");
                return displayLevel;
            } catch (Exception var5) {
                return ConfigGen.config.displayDefault;
            }
        } else if (params.equalsIgnoreCase("displayplevel")) {
            try {
                displayLevel = (String)CacheHandler.lvls.get(Integer.parseInt((String)((Map)CacheHandler.playersData.get(player.getName())).get("plevel")));
                return displayLevel;
            } catch (Exception var6) {
                return ConfigGen.config.displayDefault;
            }
        } else {
            return null;
        }
    }
}