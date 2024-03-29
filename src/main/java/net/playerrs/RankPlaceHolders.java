package net.playerrs;


import net.playerrs.commands.SetPremiumPass;
import net.playerrs.placeholders.PlaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class RankPlaceHolders extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic


        getCommand("setpremiumpass").setExecutor(new SetPremiumPass());


        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) { //
            new PlaceHolder(this).register(); //
        }
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        // Perform any one-time setup
        getLogger().info("   §4_____§5_   __§9__________§3_   ______§b_______  __");
        getLogger().info("  §4/_  _§5/ | / §9/ ____/  _§3/ | / /  _§b/_  __| \\/ /");
        getLogger().info("   §4/ /§5/  |/ §9/ /_   / /§3/  |/ // /  §b/ /   \\  / ");
        getLogger().info(" §4_/ /§5/ /|  §9/ __/ _/ /§3/ /|  // /  §b/ /    / /  ");
        getLogger().info("§4/___§5/_/ |_§9/_/   /___§3/_/ |_/___/ §b/_/    /_/   ");
        getLogger().info("§b          Infinity Nexus Plugin");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        getLogger().info("§bGoodbye!");
    }
}
