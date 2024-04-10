package net.playerrs.config;

import com.focamacho.sealconfig.relocated.blue.endless.jankson.api.annotation.Comment;

public class RankPHConfig {

    @Comment("How much ranks do you will use?\n" +
            "Must be the same number of all other files\n" +
            "lvls.json, quests.json, time.json. Default: 21")
    public int ranks = 21;

    @Comment("Default display level for players.")
    public String displayDefault = "0";
}
