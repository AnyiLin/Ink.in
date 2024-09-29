package com.example.wampus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import com.example.wampus.databinding.FragmentFirstBinding;
import com.example.wampus.storage.Journal;
import com.example.wampus.storage.JournalGroup;

import java.util.ArrayList;
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

        binding.newJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_createJournalFragment);
            }
        });

        ((MainActivity)getActivity()).setListRefreshListener(new MainActivity.ListRefreshListener() {
            @Override
            public void onListRefresh() {
//                buildList();
            }
        });
        buildList();
    }

    public void buildList() {
        for (JournalGroup title : ((MainActivity) getActivity()).titles) {
            Button category = new Button(getContext());
            category.setText(title.title);
            category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<Journal> journals = new ArrayList<Journal>();
                    for (Journal journal : ((MainActivity) getActivity()).journalList) {
                        if (journal.journalTitle.equals(category.getText().toString())) {
                            journals.add(journal);
                        }
                    }

                    // Create an instance of the target Fragment
                    JournalFragment targetFragment = new JournalFragment(journals, title);

                    // Get the FragmentManager
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                    // Begin the transaction
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    // Replace the current Fragment with the target Fragment
                    fragmentTransaction.replace(((View)getView().getParent()).getId(), targetFragment); // Replace with your container ID// Add to back stack (optional)
                    fragmentTransaction.addToBackStack(null);

                    // Commit the transaction
                    fragmentTransaction.commit();
                }
            });
            binding.journalsLayout.addView(category);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}