package net.playerrs.datahandler;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private static final File pluginFolder = new File("plugins/RankPlaceHolders");

    public static void savePlayerData(String playerName, String dataIndex, String data) {
        String playerDataJSON = getPluginFolder() + "/data";
        File folder = new File(playerDataJSON);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        playerDataJSON = getPluginFolder() + "/data/" + playerName + ".json";
        JsonObject playerData = new JsonObject();

        File fileToRead = new File(playerDataJSON);
        if (fileToRead.exists()) {
            try(FileReader reader = new FileReader(fileToRead)) {
                Gson gson = new Gson();
                playerData =  gson.fromJson(reader, JsonObject.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        playerData.addProperty(dataIndex, data);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(playerData);

        try (FileWriter file = new FileWriter(playerDataJSON)) {
            file.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getPlayerData(String playerName, String dataIndex) {
        String playerDataJSON = getPluginFolder() + "/data/" + playerName + ".json";
        JsonObject playerData = new JsonObject();

        File fileToRead = new File(playerDataJSON);
        if (fileToRead.exists()) {
            try(FileReader reader = new FileReader(fileToRead)) {
                Gson gson = new Gson();
                playerData =  gson.fromJson(reader, JsonObject.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return playerData.get(dataIndex).getAsString();
        }

        return ChatColor.RED + "[RankPlaceHolders]: This player does not have any data!";
    };
    public static File getPluginFolder() {
        return pluginFolder;
    }

}