package net.playerrs.config;

import com.focamacho.sealconfig.relocated.blue.endless.jankson.api.annotation.Comment;

public class RankPHConfig {
    @Comment("How much ranks do you will use?\nMust be the same number of all other files\nlvls.json, quests.json, time.json. Default: 21")
    public int ranks = 21;
    @Comment("If you will use the token or not. Default: true")
    public boolean useToken = true;
    @Comment("Token for use the command NextLevel safely. Because you need to give permission to use this command.")
    public String token = "YOUR_TOKEN_HERE";
    @Comment("Default display level for players.")
    public String displayDefault = "";

    public RankPHConfig() {
    }
}
