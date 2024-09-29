package com.example.wampus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wampus.databinding.FragmentFirstBinding;
import com.example.wampus.databinding.FragmentLoadingBinding;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LoadingFragment extends Fragment {
    private FragmentLoadingBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLoadingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoadingFragment.this).navigate(R.id.action_loadingFragment_to_FirstFragment);
            }
        });
    }
}