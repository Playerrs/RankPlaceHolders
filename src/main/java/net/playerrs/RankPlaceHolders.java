package net.playerrs;

import net.playerrs.commands.NextLevel;
import net.playerrs.commands.SetPremiumLevel;
import net.playerrs.commands.UnlockQuest;
import net.playerrs.config.ConfigGen;
import net.playerrs.placeholders.PlaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class RankPlaceHolders extends JavaPlugin implements Listener {
    public RankPlaceHolders() {
    }

    public void onEnable() {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            (new PlaceHolder(this)).register();
        }

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            this.getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        this.getCommand("setpremiumlevel").setExecutor(new SetPremiumLevel());
        this.getCommand("nextlevel").setExecutor(new NextLevel());
        this.getCommand("unlockquest").setExecutor(new UnlockQuest());
        ConfigGen.generateConfigs();
        this.getLogger().info("   §4_____§5_   __§9__________§3_   ______§b_______  __");
        this.getLogger().info("  §4/_  _§5/ | / §9/ ____/  _§3/ | / /  _§b/_  __| \\/ /");
        this.getLogger().info("   §4/ /§5/  |/ §9/ /_   / /§3/  |/ // /  §b/ /   \\  / ");
        this.getLogger().info(" §4_/ /§5/ /|  §9/ __/ _/ /§3/ /|  // /  §b/ /    / /  ");
        this.getLogger().info("§4/___§5/_/ |_§9/_/   /___§3/_/ |_/___/ §b/_/    /_/   ");
        this.getLogger().info("§b          Infinity Nexus Plugin: Ranks");
    }

    public void onDisable() {
        this.getLogger().info("§bGoodbye!");
    }
}