package com.example.wampus.storage;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class JournalDatabaseAccessor {
    private JournalDatabase database;
    private JournalDAO dao;

    public JournalDatabaseAccessor(Context context) {
        database = Room.databaseBuilder(context,
                JournalDatabase.class, "journal_table").build();
        dao = database.journalDAO();
    }

    public void insert(Journal journal) {
        dao.insert(journal);
    }

    public void update(Journal journal) {
        dao.update(journal);
    }

    public void delete(Journal journal) {
        dao.delete(journal);
    }

    public List<Journal> getAllJournals() {
        return dao.getAllJournals();
    }

    public Journal getJournalByDate(String date) {
        return dao.getJournalByDate(date);
    }

    public Journal getJournalByDayOfWeek(String dayOfWeek) {
        return dao.getJournalByDayOfWeek(dayOfWeek);
    }

    public Journal getJournalByTitle(String title) {
        return dao.getJournalByTitle(title);
    }

    public Journal getJournalByPrompt(String prompt) {
        return dao.getJournalByPrompt(prompt);
    }
}
