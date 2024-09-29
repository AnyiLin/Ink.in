package com.example.wampus.storage;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {JournalGroup.class}, version = 1)
public abstract class JournalGroupDatabase extends RoomDatabase {
    public abstract JournalGroupDAO journalGroupDAO();
}
