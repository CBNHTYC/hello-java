package ru.utair.kubsu.hellojava.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jokes")
public class Joke {
    private String site;
    private String category;
    private String text;
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCategory() {
        return category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "site='" + site + '\'' +
                ", category='" + category + '\'' +
                ", text='" + text + '\'' +
                ", id=" + id +
                '}';
    }
}
