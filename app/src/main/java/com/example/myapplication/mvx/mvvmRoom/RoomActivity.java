package com.example.myapplication.mvx.mvvmRoom;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.mvx.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class RoomActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    private  NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomActivity.this,AddNoteActivity.class);
                startActivityForResult(intent,ADD_NOTE_REQUEST);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        NoteAdapter adapter = new  NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                //update Recycle view
                adapter.setNotes(notes);

            }
        });
    }

    public static Intent getIntent(Context context){
        return new Intent(context, RoomActivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String title = data.getExtras().getString(AddNoteActivity.EXTRA_TTILE);
            String desc = data.getExtras().getString(AddNoteActivity.EXTRA_DESC);
            int priority = data.getExtras().getInt(AddNoteActivity.EXTRA_PRIORITY,1);
            Note note = new Note(title, desc,priority);
            noteViewModel.insert(note);
            Toast.makeText(this,"Note saved",Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this,"Note Not saved",Toast.LENGTH_SHORT).show();

        }
    }
}
