package com.example.wampus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.wampus.databinding.FragmentSecondBinding;
import com.example.wampus.storage.Journal;

import java.util.List;
import java.util.concurrent.Executors;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity)getActivity()).setListRefreshListener(new MainActivity.ListRefreshListener() {
            @Override
            public void onListRefresh() {
                refreshList();
            }
        });

        binding.buttonSecond.setOnClickListener(v ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );

        refreshList();
    }

    public void refreshList() {
        binding.journalLayout.removeAllViews();
        for (Journal journal : ((MainActivity) getActivity()).journalList) {
            TextView textView = new TextView(getActivity());
            textView.setText(journal.journalTitle + "\n" + journal.journalEntry);
            binding.journalLayout.addView(textView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}