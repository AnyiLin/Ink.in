package com.example.wampus.storage;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "journal_table")
public class Journal {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "journal_title")
    public String journalTitle;

    @ColumnInfo(name = "journal_entry")
    public String journalEntry;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "day_of_week")
    public String dayOfWeek;

    @ColumnInfo(name = "prompt")
    public String prompt;

    @ColumnInfo(name = "mood")
    public String mood;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
