package com.example.wampus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wampus.databinding.FragmentJournalBinding;
import com.example.wampus.storage.Journal;
import com.example.wampus.storage.JournalGroup;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JournalFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class JournalFragment extends Fragment {
    private List<Journal> journals;
    private JournalGroup title;
    private FragmentJournalBinding binding;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param journals Parameter 1.
     * @return A new instance of fragment JournalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JournalFragment newInstance(List<Journal> journals, JournalGroup title) {
        JournalFragment fragment = new JournalFragment(journals, title);
        return fragment;
    }

    public JournalFragment() {
        // Required empty public constructor
    }

    public JournalFragment(List<Journal> journals, JournalGroup title) {
        this.journals = journals;
        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJournalBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the FragmentManager
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                // Begin the transaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // Replace the current Fragment with the target Fragment
                fragmentTransaction.remove(JournalFragment.this);

                // Commit the transaction
                fragmentTransaction.commit();
            }
        });

        binding.create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the FragmentManager
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                // Begin the transaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // Replace the current Fragment with the target Fragment
                fragmentTransaction.remove(JournalFragment.this);
                // Commit the transaction
                fragmentTransaction.commit();

                ((MainActivity) getActivity()).currentTitle = title;
                NavHostFragment.findNavController(JournalFragment.this).navigate(R.id.action_FirstFragment_to_createJournalEntryFragment);
            }
        });

        binding.journalTitle.setText(title.title);

        binding.journalLayout.removeAllViews();
        for (Journal journal : journals) {
            JournalListEntry entry = new JournalListEntry(journal);
            binding.journalLayout.addView(entry.getListEntry(getContext(), () -> {
                // Get the FragmentManager
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                // Begin the transaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // Replace the current Fragment with the target Fragment
                fragmentTransaction.remove(JournalFragment.this);
                // Commit the transaction
                fragmentTransaction.commit();

                ((MainActivity) getActivity()).currentJournal = journal;
                NavHostFragment.findNavController(JournalFragment.this).navigate(R.id.action_FirstFragment_to_journalViewFragment);
            }));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}