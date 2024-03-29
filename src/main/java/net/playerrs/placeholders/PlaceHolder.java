package net.playerrs.placeholders;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.playerrs.RankPlaceHolders;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import static net.playerrs.datahandler.FileManager.getPlayerData;

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
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {

        if(params.equalsIgnoreCase("premiumpass")){
            return getPlayerData(player.getName(), "premiumpass");
        }
        return null;
    }
}
