package com.example.wampus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wampus.databinding.FragmentCreateJournalBinding;
import com.example.wampus.databinding.FragmentCreateJournalEntryBinding;
import com.example.wampus.databinding.FragmentJournalViewBinding;
import com.example.wampus.storage.Journal;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JournalViewFragment#} factory method to
 * create an instance of this fragment.
 */
public class JournalViewFragment extends Fragment {
    private FragmentJournalViewBinding binding;
    private Journal journal;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentJournalViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        journal = ((MainActivity) getActivity()).currentJournal;
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(JournalViewFragment.this).navigate(R.id.action_journalViewFragment_to_FirstFragment);
            }
        });
        binding.header.setText(journal.dayOfWeek.substring(0, 3)
                + " " + Integer.valueOf(journal.date.substring(0, 2)) + "/" + Integer.valueOf(journal.date.substring(3, 5))
                + " - " + journal.mood);
        binding.header.setPadding(15,10,15,10);
        MaterialShapeDrawable headerShapeDrawable = new MaterialShapeDrawable(new ShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED,15)
                .build());
        headerShapeDrawable.setFillColor(ContextCompat.getColorStateList(getContext(),R.color.background_grey));
        binding.header.setBackground(headerShapeDrawable);
        binding.body.setText(journal.journalEntry);
    }
}