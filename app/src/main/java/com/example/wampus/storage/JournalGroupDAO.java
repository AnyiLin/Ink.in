package com.example.wampus.storage;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface JournalGroupDAO {
    @Insert
    void insert(JournalGroup group);

    @Update
    void update(JournalGroup group);

    @Delete
    void delete(JournalGroup group);

    @Query("SELECT * FROM journal_group_table WHERE id = :id")
    JournalGroup getJournalGroupById(int id);

    @Query("SELECT * FROM journal_group_table WHERE title = :title")
    JournalGroup getJournalGroupByTitle(String title);

    @Query("SELECT * FROM journal_group_table")
    List<JournalGroup> getAllJournalGroups();
}
