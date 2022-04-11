package com.example.gu1919_lesson11;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoteFragment extends Fragment {
    private Note note;
    private TextView title;
    private TextView description;

    public NoteFragment() {}

//    public static NoteFragment newInstance(Note note) {
//        Bundle args = new Bundle();
//        args.putParcelable("NOTE", note);
//        NoteFragment fragment = new NoteFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        note = requireArguments().getParcelable("NOTE");

        title = view.findViewById(R.id.tv_title);
        description = view.findViewById(R.id.tv_description);
        title.setText(note.getTitle());
        description.setText(note.getDescription());

    }
}