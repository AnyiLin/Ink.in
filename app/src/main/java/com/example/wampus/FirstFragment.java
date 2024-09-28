package com.example.wampus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.wampus.databinding.FragmentFirstBinding;
import com.example.wampus.storage.Journal;

import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(v ->
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment)
        );

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = binding.titleInputEditText;
                EditText entry = binding.entryInputEditText;
                Journal journal = new Journal();
                journal.journalTitle = title.getText().toString();
                journal.journalEntry = entry.getText().toString();
                journal.dayOfWeek = "Saturday";
                journal.date = "09-28-2024";
                journal.prompt = "";
                journal.mood = "happy";
                title.getText().clear();
                entry.getText().clear();
                Executors.newSingleThreadExecutor().execute(() -> {
                    ((MainActivity) getActivity()).journalDatabaseAccessor.insert(journal);
                    ((MainActivity) getActivity()).updateJournalList();
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}