package com.example.wampus.storage;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Journal.class}, version = 1)
public abstract class JournalDatabase extends RoomDatabase {
    public abstract JournalDAO journalDAO();
}
