package com.example.wampus;

import android.os.Bundle;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wampus.databinding.FragmentCreateJournalBinding;
import com.example.wampus.databinding.FragmentFirstBinding;
import com.example.wampus.storage.JournalGroup;

import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateJournalFragment} factory method to
 * create an instance of this fragment.
 */
public class CreateJournalFragment extends Fragment {
    private FragmentCreateJournalBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCreateJournalBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CreateJournalFragment.this).navigate(R.id.action_createJournalFragment_to_FirstFragment);
            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JournalGroup title = new JournalGroup();
                title.title = binding.titleInputEdit.getText().toString();
                binding.titleInputEdit.getText().clear();
                Executors.newSingleThreadExecutor().execute(() -> {
                    ((MainActivity) getActivity()).journalGroupDatabaseAccessor.insert(title);
                    ((MainActivity) getActivity()).titles = ((MainActivity) getActivity()).journalGroupDatabaseAccessor.getAllJournalGroups();
                });
                NavHostFragment.findNavController(CreateJournalFragment.this).navigate(R.id.action_createJournalFragment_to_FirstFragment);
            }
        });
    }
}
