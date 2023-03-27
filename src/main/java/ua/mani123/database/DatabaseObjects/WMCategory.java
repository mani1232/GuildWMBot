package ua.mani123.database.DatabaseObjects;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WMCategory {

    private ObjectId id;
    private long categoryId;
    private int maxTextChannels = 1;
    private int maxVoiceChannels = 1;
    private int maxForumChannels = 0;
    private int maxTotalUsers = 12;
    private List<ObjectId> editorsCategory = new ArrayList<>();
    private ObjectId categoryOwner;

    public WMCategory() {
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("_id", this.id);
        document.append("categoryId", this.categoryId);
        document.append("maxTextChannels", this.maxTextChannels);
        document.append("maxVoiceChannels", this.maxVoiceChannels);
        document.append("maxForumChannels", this.maxForumChannels);
        document.append("maxTotalUsers", this.maxTotalUsers);
        document.append("editorsCategory", this.editorsCategory);
        document.append("categoryOwner", this.categoryOwner);
        return document;
    }
}
