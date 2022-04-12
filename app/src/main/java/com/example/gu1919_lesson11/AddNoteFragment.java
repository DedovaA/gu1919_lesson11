package com.example.gu1919_lesson11;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNoteFragment extends Fragment {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerDayOfWeek;
    private RadioGroup radioGroupPriority;
    private MainViewModel viewModel;


    public static AddNoteFragment newInstance() {
        return new AddNoteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new MainViewModel(requireActivity().getApplication());

        editTextTitle = view.findViewById(R.id.et_title);
        editTextDescription = view.findViewById(R.id.et_description);
        spinnerDayOfWeek = view.findViewById(R.id.spn_dayOfWeek);
        radioGroupPriority = view.findViewById(R.id.rg_priority);

        view.findViewById(R.id.btn_saveNote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                String title = editTextTitle.getText().toString().trim();
                String description = editTextDescription.getText().toString().trim();
                int dayOfWeek = spinnerDayOfWeek.getSelectedItemPosition() + 1;
                int radioButtonId = radioGroupPriority.getCheckedRadioButtonId();
                RadioButton radioButton = view.findViewById(radioButtonId);
                int priority = Integer.parseInt(radioButton.getText().toString());
                if (isFilled(title, description)){
                    Note note = new Note(title, description, dayOfWeek, priority);
                    viewModel.insertNote(note);
                    requireActivity().onBackPressed();
                } else {
                    Toast.makeText(requireContext(),
                            R.string.warning_fill_fields,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isFilled(String title, String description){
        return !title.isEmpty() && !description.isEmpty();
    }
}