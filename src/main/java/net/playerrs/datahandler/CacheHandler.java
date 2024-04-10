package net.playerrs.datahandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static net.playerrs.datahandler.FileManager.getFileData;


public abstract class CacheHandler {

    public static Map<String, String> variable = new HashMap<>();

    public static List<String> time = new ArrayList<>();
    public static List<String> lvls = new ArrayList<>();
    public static List<String> quests = new ArrayList<>();

    public static Map<String, Map<String, String>> playersData = new HashMap<>();

    public static void loadInCache(String path, String fileName) {
        String fileObject = FileManager.getPluginFolder() + path + fileName;
        JsonObject readData = new JsonObject();

        File fileToRead = new File(fileObject);
        if (fileToRead.exists()) {
            try(FileReader reader = new FileReader(fileToRead)) {
                Gson gson = new Gson();
                readData =  gson.fromJson(reader, JsonObject.class);
            }
            catch (IOException e) {
                e.printStackTrace();
                return;
            }

            for ( int i = 0; i < readData.size(); i++ ) {
                if (fileName.startsWith("lvls")) {
                    lvls.add(readData.get(String.valueOf(i)).getAsString());
                } else if (fileName.startsWith("quests")) {
                    quests.add(readData.get(String.valueOf(i)).getAsString());
                } else if (fileName.startsWith("time")) {
                    time.add(readData.get(String.valueOf(i)).getAsString());
                }
                //table.add(readData.get(String.valueOf(i)).getAsString()); //readData.size()
            }
            //return        readData.get(dataIndex).getAsString();
        }
    }

    public static void loadPlayersData(String playerName) {
        if (playerName.length() < 2) {
            File dataDir = new File(FileManager.getPluginFolder() + "/data/");

            if (dataDir.exists()) {
                for (File file : Objects.requireNonNull(dataDir.listFiles())) {
                    if (file.getName().endsWith(".json")) {

                        variable.clear();
                        variable.put("plevel", getFileData("/data/", file.getName(), "plevel"));
                        // Feito manualmente por conta de nao ter uma função de leitura de json com multimplos indexes

                        playersData.put(file.getName().substring(0, file.getName().length() - 5), variable);

                    }
                }
            }
        } else {
            File playerFile = new File(FileManager.getPluginFolder() + "/data/" + playerName); // TIREI O .json

            //if (playerFile.exists()) {

                variable.clear();
                variable.put("plevel", getFileData("/data/", playerFile.getName(), "plevel"));
                // Feito manualmente por conta de nao ter uma função de leitura de json com multimplos indexes

                //playersData.replace(String.valueOf(playerName), variable);
                playersData.put(playerName, variable);

            //}
        }
    }
}
