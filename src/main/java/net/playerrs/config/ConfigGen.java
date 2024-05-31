package net.playerrs.config;

import com.focamacho.sealconfig.SealConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileWriter;
import net.playerrs.datahandler.CacheHandler;
import net.playerrs.datahandler.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ConfigGen {
    public static final String configsPath = FileManager.getPluginFolder() + "/configs/";
    static int[] time = new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 22, 26, 30, 34, 38, 42, 46, 50, 54, 58, 72};
    static int[] lvls = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    static int[] quests = new int[]{1123, 2456, 3789, 4321, 5654, 6987, 7369, 8258, 9147, 10741, 11852, 12963, 13753, 14951, 15147, 16369, 17258, 18369, 19258, 20147, 21369};
    private static SealConfig sealConfig;
    public static RankPHConfig config;

    public ConfigGen() {
    }

    private static void generator(String fileName, int[] list, boolean strings) {
        File folder = new File(configsPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File fileToRead = new File(configsPath + fileName + ".json");
        if (!fileToRead.exists()) {
            JsonObject toSave = new JsonObject();

            for(int i = 0; i < list.length; ++i) {
                if (strings) {
                    toSave.addProperty(String.valueOf(i), String.valueOf(list[i]));
                } else {
                    toSave.addProperty(String.valueOf(i), list[i]);
                }
            }

            Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
            String json = gson.toJson(toSave);

            try {
                FileWriter file = new FileWriter(fileToRead);

                try {
                    file.write(json);
                } catch (Throwable var12) {
                    try {
                        file.close();
                    } catch (Throwable var11) {
                        var12.addSuppressed(var11);
                    }

                    throw var12;
                }

                file.close();
            } catch (Exception var13) {
                Exception e = var13;
                e.printStackTrace();
            }

            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "[RankPlaceHolders]: Generated config of \"" + fileName + ".json\", modify it at \"" + fileToRead.getPath() + "\"");
        } else {
            CacheHandler.loadInCache("/configs/", fileName + ".json");
        }

    }

    public static void generateConfigs() {
        generator("time", time, false);
        generator("lvls", lvls, true);
        generator("quests", quests, false);
        CacheHandler.loadPlayersData("");
        loadConfig();
    }

    public static void loadConfig() {
        sealConfig = new SealConfig();
        config = (RankPHConfig)sealConfig.getConfig(new File("./" + FileManager.getPluginFolder() + "/configs/config.json5"), RankPHConfig.class);
        saveConfig();
    }

    public static void reloadConfig() {
        sealConfig.reload();
    }

    public static void saveConfig() {
        sealConfig.save();
    }
}
