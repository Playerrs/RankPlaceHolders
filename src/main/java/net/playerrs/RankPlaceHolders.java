package net.playerrs;


import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.PlaceholderHook;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.playerrs.commands.NextLevel;
import net.playerrs.commands.SetPremiumLevel;
import net.playerrs.commands.UnlockQuest;
import net.playerrs.config.ConfigGen;
import net.playerrs.placeholders.PlaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class RankPlaceHolders extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic


        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) { //
            new PlaceHolder(this).register(); //
        }
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        getCommand("setpremiumlevel").setExecutor(new SetPremiumLevel());
        getCommand("nextlevel").setExecutor(new NextLevel());
        getCommand("unlockquest").setExecutor(new UnlockQuest());
        ConfigGen.generateConfigs();

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
