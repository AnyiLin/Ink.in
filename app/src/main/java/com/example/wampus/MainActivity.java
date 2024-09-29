package com.example.wampus;

import android.os.Bundle;

import com.example.wampus.storage.Journal;
import com.example.wampus.storage.JournalDatabaseAccessor;
import com.example.wampus.storage.JournalGroup;
import com.example.wampus.storage.JournalGroupDatabaseAccessor;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.wampus.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    public ListRefreshListener getListRefreshListener() {
        return listRefreshListener;
    }

    public void setListRefreshListener(ListRefreshListener listRefreshListener) {
        this.listRefreshListener = listRefreshListener;
    }

    private ListRefreshListener listRefreshListener;

    public interface ListRefreshListener {
        void onListRefresh();
    }

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    public JournalDatabaseAccessor journalDatabaseAccessor;
    public JournalGroupDatabaseAccessor journalGroupDatabaseAccessor;
    public List<Journal> journalList;
    public List<JournalGroup> titles;
    public JournalGroup currentTitle = null;
    public Journal currentJournal = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Deleted journals", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
//                    Executors.newSingleThreadExecutor().execute(() -> {
//                        for (Journal journal : journalList) {
//                            journalDatabaseAccessor.delete(journal);
//                        }
//                        for (JournalGroup title : titles) {
//                            journalGroupDatabaseAccessor.delete(title);
//                        }
//                        journalList = journalDatabaseAccessor.getAllJournals();
//                        titles = journalGroupDatabaseAccessor.getAllJournalGroups();
//                        if (getListRefreshListener() != null) {
//                            getListRefreshListener().onListRefresh();
//                        }
//                    });
//            }
//        });
//
        journalDatabaseAccessor = new JournalDatabaseAccessor(this);
        journalGroupDatabaseAccessor = new JournalGroupDatabaseAccessor(this);
        updateJournalList();
    }

    public void updateJournalList() {
        Executors.newSingleThreadExecutor().execute(() -> {
           journalList = journalDatabaseAccessor.getAllJournals();
           titles = journalGroupDatabaseAccessor.getAllJournalGroups();
        });
    }
}