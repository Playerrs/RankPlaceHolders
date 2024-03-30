package net.playerrs.datahandler;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private static final File pluginFolder = new File("plugins/RankPlaceHolders");

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
            try(FileReader reader = new FileReader(fileToRead)) {
                Gson gson = new Gson();
                newData =  gson.fromJson(reader, JsonObject.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        newData.addProperty(dataIndex, data);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(newData);

        try (FileWriter file = new FileWriter(fileObject)) {
            file.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getFileData(String path, String fileName, String dataIndex) {
        String fileObject = getPluginFolder() + path + fileName;
        JsonObject readData = new JsonObject();

        File fileToRead = new File(fileObject);
        if (fileToRead.exists()) {
            try(FileReader reader = new FileReader(fileToRead)) {
                Gson gson = new Gson();
                readData =  gson.fromJson(reader, JsonObject.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return readData.get(dataIndex).getAsString();
        }

        return ChatColor.RED + "[RankPlaceHolders]: This file does not have any data or does not exist!";
    };
    public static File getPluginFolder() {
        return pluginFolder;
    }

}