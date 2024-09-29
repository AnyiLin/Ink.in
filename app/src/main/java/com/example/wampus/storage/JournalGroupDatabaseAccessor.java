package com.example.wampus.storage;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class JournalGroupDatabaseAccessor {
    private JournalGroupDatabase database;
    private JournalGroupDAO dao;

    public JournalGroupDatabaseAccessor(Context context) {
        database = Room.databaseBuilder(context,
                JournalGroupDatabase.class, "journal_group_table").build();
        dao = database.journalGroupDAO();
    }

    public void insert(JournalGroup group) {
        dao.insert(group);
    }

    public void update(JournalGroup group) {
        dao.update(group);
    }

    public void delete(JournalGroup group) {
        dao.delete(group);
    }

    public List<JournalGroup> getAllJournalGroups() {
        return dao.getAllJournalGroups();
    }

    public JournalGroup getJournalGroupById(int id) {
        return dao.getJournalGroupById(id);
    }

    public JournalGroup getJournalGroupByTitle(String title) {
        return dao.getJournalGroupByTitle(title);
    }
}
