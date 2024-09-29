package com.example.wampus.storage;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "journal_group_table")
public class JournalGroup {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    public String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
