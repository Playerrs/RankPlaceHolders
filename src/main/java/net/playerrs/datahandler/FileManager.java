package net.playerrs.datahandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import net.playerrs.RankPlaceHolders;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class FileManager {
    private static final File pluginFolder = new File("plugins/RankPlaceHolders");

    public FileManager() {
    }

    public static void saveFileData(String path, String fileName, String dataIndex, String data) {
        String fileObject = getPluginFolder() + path;
        File folder = new File(fileObject);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        fileObject = getPluginFolder() + path + "/" + fileName;
        JsonObject newData = new JsonObject();
        File fileToRead = new File(fileObject);
        if (fileToRead.exists()) {
            try {
                FileReader reader = new FileReader(fileToRead);

                try {
                    Gson gson = new Gson();
                    newData = (JsonObject)gson.fromJson(reader, JsonObject.class);
                } catch (Throwable var17) {
                    try {
                        reader.close();
                    } catch (Throwable var16) {
                        var17.addSuppressed(var16);
                    }

                    throw var17;
                }

                reader.close();
            } catch (IOException var18) {
                IOException e = var18;
                e.printStackTrace();
            }
        }

        newData.addProperty(dataIndex, data);
        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        String json = gson.toJson(newData);

        try {
            FileWriter file = new FileWriter(fileObject);

            try {
                file.write(json);
            } catch (Throwable var14) {
                try {
                    file.close();
                } catch (Throwable var13) {
                    var14.addSuppressed(var13);
                }

                throw var14;
            }

            file.close();
        } catch (Exception var15) {
            Exception e = var15;
            e.printStackTrace();
        }

        if (Objects.equals(path, "/data/")) {
            Bukkit.getScheduler().runTaskLater(RankPlaceHolders.getPlugin(RankPlaceHolders.class), () -> {
                CacheHandler.loadPlayersData(fileName);
            }, 60L);
        } else {
            Bukkit.getScheduler().runTaskLater(RankPlaceHolders.getPlugin(RankPlaceHolders.class), () -> {
                CacheHandler.loadInCache(path, fileName);
            }, 60L);
        }

    }

    public static String getFileData(String path, String fileName, String dataIndex) {
        String fileObject = getPluginFolder() + path + fileName;
        JsonObject readData = new JsonObject();
        File fileToRead = new File(fileObject);
        if (fileToRead.exists()) {
            try {
                FileReader reader = new FileReader(fileToRead);

                try {
                    Gson gson = new Gson();
                    readData = (JsonObject)gson.fromJson(reader, JsonObject.class);
                } catch (Throwable var10) {
                    try {
                        reader.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }

                    throw var10;
                }

                reader.close();
            } catch (IOException var11) {
            }

            return readData.get(dataIndex).getAsString();
        } else {
            return ChatColor.RED + "[RankPlaceHolders]: This file does not have any data or does not exist!";
        }
    }

    public static File getPluginFolder() {
        return pluginFolder;
    }
}
