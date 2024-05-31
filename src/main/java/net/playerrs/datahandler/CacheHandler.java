package net.playerrs.datahandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class CacheHandler {
    public static Map<String, String> variable = new HashMap();
    public static List<String> time = new ArrayList();
    public static List<String> lvls = new ArrayList();
    public static List<String> quests = new ArrayList();
    public static Map<String, Map<String, String>> playersData = new HashMap();

    public CacheHandler() {
    }

    public static void loadInCache(String path, String fileName) {
        String fileObject = FileManager.getPluginFolder() + path + fileName;
        new JsonObject();
        File fileToRead = new File(fileObject);
        if (fileToRead.exists()) {
            JsonObject readData;
            try {
                FileReader reader = new FileReader(fileToRead);

                try {
                    Gson gson = new Gson();
                    readData = (JsonObject)gson.fromJson(reader, JsonObject.class);
                } catch (Throwable var9) {
                    try {
                        reader.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }

                    throw var9;
                }

                reader.close();
            } catch (IOException var10) {
                IOException e = var10;
                e.printStackTrace();
                return;
            }

            for(int i = 0; i < readData.size(); ++i) {
                if (fileName.startsWith("lvls")) {
                    lvls.add(readData.get(String.valueOf(i)).getAsString());
                } else if (fileName.startsWith("quests")) {
                    quests.add(readData.get(String.valueOf(i)).getAsString());
                } else if (fileName.startsWith("time")) {
                    time.add(readData.get(String.valueOf(i)).getAsString());
                }
            }
        }

    }

    public static void loadPlayersData(String playerName) {
        File dataDir;
        if (playerName.length() < 2) {
            dataDir = new File(FileManager.getPluginFolder() + "/data/");
            if (dataDir.exists()) {
                File[] var2 = (File[])Objects.requireNonNull(dataDir.listFiles());
                int var3 = var2.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    File file = var2[var4];
                    if (file.getName().endsWith(".json")) {
                        variable.clear();
                        variable.put("plevel", FileManager.getFileData("/data/", file.getName(), "plevel"));
                        playersData.put(file.getName().substring(0, file.getName().length() - 5), variable);
                    }
                }
            }
        } else {
            dataDir = new File(FileManager.getPluginFolder() + "/data/" + playerName);
            variable.clear();
            variable.put("plevel", FileManager.getFileData("/data/", dataDir.getName(), "plevel"));
            playersData.put(playerName.substring(0, playerName.length() - 5), variable);
        }

    }
}