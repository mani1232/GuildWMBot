package ua.mani123.database.DatabaseObjects;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bson.types.ObjectId;
import ua.mani123.localization.Lang;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class WMUser {

    private ObjectId id;
    private Long discordId;
    private Long balance = 0L;
    private Lang lang = Lang.EN;
    private boolean inGuild = false;
    private boolean privateUser = false;
    private String displayName;
    private Long gameBalance = 0L;
    private Long experience = 0L;
    private List<Long> savedRoles = new ArrayList<>();

    public WMUser() {}

    public Document toDocument() {
        Document document = new Document();
        document.append("_id", this.id);
        document.append("discordId", this.discordId);
        document.append("lang", this.lang);
        document.append("balance", this.balance);
        document.append("inGuild", this.inGuild);
        document.append("displayName", this.displayName);
        document.append("experience", this.experience);
        document.append("gameBalance", this.gameBalance);
        document.append("privateUser", this.privateUser);
        document.append("savedRoles", this.savedRoles);
        return document;
    }

    //public static WMUser fromDocument(Document document) {
    //    WMUser user = new WMUser();
    //    user.setId(document.getObjectId("_id"));
    //    user.setDiscordId(document.getLong("discordId"));
    //    user.setLang(Lang.valueOf(document.getString("lang")));
    //    user.setBalance(document.getLong("balance"));
    //    user.setInGuild(document.getBoolean("inGuild"));
    //    return user;
    //}
}
