package com.example.gu1919_lesson11;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class NoteListFragment extends Fragment {

    private RecyclerView recyclerViewNoteList;
    private List<Note> notes = new ArrayList<>();
    private NotesAdapter adapter;
    private NotesDatabase database;

    public static NoteListFragment newInstance() {
        return new NoteListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        database = NotesDatabase.getInstance(requireContext());

        view.findViewById(R.id.btnAddNote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerMain, AddNoteFragment.newInstance()).addToBackStack("").commit();
            }
        });

        recyclerViewNoteList = view.findViewById(R.id.rv_notes);
        getData();
        adapter = new NotesAdapter(notes);
        recyclerViewNoteList.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewNoteList.setAdapter(adapter);

        adapter.setOnNoteClickListener(new NotesAdapter.onNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
//                openNote(position);
            }

            @Override
            public void onLongClick(int position) {
                remove(position);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                remove(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewNoteList);

    }

    private void remove(int position){
        Note note = notes.get(position);
        database.notesDao().deleteNote(note);
        getData();
        adapter.notifyDataSetChanged();
    }

//    private void openNote(int position){
//        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
//            requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.containerNote,
//                    NoteFragment.newInstance(notes.get(position))).commit();
//        else
//            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerMain,
//                    NoteFragment.newInstance(notes.get(position))).addToBackStack("").commit();
//    }

    private void getData() {
        List<Note> notesFromDB = database.notesDao().getAllNotes();
        notes.clear();
        notes.addAll(notesFromDB);
    }

}