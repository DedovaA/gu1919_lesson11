package com.example.gu1919_lesson11;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class NoteListFragment extends Fragment {
    private RecyclerView recyclerViewNoteList;
    private ArrayList<Note> notes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewNoteList = view.findViewById(R.id.rv_notes);
        notes.add(new Note("note 1", "desc 1", "Mon", 1));
        notes.add(new Note("note 2", "desc 2", "Tue", 2));
        NotesAdapter adapter = new NotesAdapter(notes);
        recyclerViewNoteList.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewNoteList.setAdapter(adapter);
    }
}