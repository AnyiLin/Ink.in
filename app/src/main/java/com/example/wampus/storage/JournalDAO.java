package com.example.wampus.storage;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JournalDAO {
    @Insert
    void insert(Journal journal);

    @Update
    void update(Journal journal);

    @Delete
    void delete(Journal journal);

    @Query("SELECT * FROM journal_table WHERE id = :id")
    Journal getJournalById(int id);

    @Query("SELECT * FROM journal_table WHERE date = :date")
    Journal getJournalByDate(String date);

    @Query("SELECT * FROM journal_table WHERE day_of_week = :dayOfWeek")
    Journal getJournalByDayOfWeek(String dayOfWeek);

    @Query("SELECT * FROM journal_table WHERE journal_title = :title")
    Journal getJournalByTitle(String title);

    @Query("SELECT * FROM journal_table WHERE prompt = :prompt")
    Journal getJournalByPrompt(String prompt);

    @Query("SELECT * FROM journal_table")
    List<Journal> getAllJournals();
}
