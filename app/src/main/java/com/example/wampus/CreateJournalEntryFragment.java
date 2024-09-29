package com.example.wampus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wampus.databinding.FragmentCreateJournalBinding;
import com.example.wampus.databinding.FragmentCreateJournalEntryBinding;
import com.example.wampus.databinding.FragmentFirstBinding;
import com.example.wampus.storage.Journal;
import com.example.wampus.storage.JournalGroup;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateJournalEntryFragment#} factory method to
 * create an instance of this fragment.
 */
public class CreateJournalEntryFragment extends Fragment {
    private FragmentCreateJournalEntryBinding binding;
    private JournalGroup title;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCreateJournalEntryBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = ((MainActivity) getActivity()).currentTitle;

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CreateJournalEntryFragment.this).navigate(R.id.action_createJournalEntryFragment_to_FirstFragment);
            }
        });
        binding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Journal journal = new Journal();
                journal.journalTitle = title.title;
                journal.journalEntry = binding.journalEntryEdit.getText().toString();
                journal.dayOfWeek = "Saturday";
                journal.date = "09-28-2024";
                journal.prompt = "";
                journal.mood = binding.moodInputEdit.getText().toString();
                binding.moodInputEdit.getText().clear();
                binding.journalEntryEdit.getText().clear();
                Executors.newSingleThreadExecutor().execute(() -> {
                    ((MainActivity) getActivity()).journalDatabaseAccessor.insert(journal);
                    ((MainActivity) getActivity()).journalList = ((MainActivity) getActivity()).journalDatabaseAccessor.getAllJournals();
                });
                NavHostFragment.findNavController(CreateJournalEntryFragment.this).navigate(R.id.action_createJournalEntryFragment_to_FirstFragment);
            }
        });
    }
}
