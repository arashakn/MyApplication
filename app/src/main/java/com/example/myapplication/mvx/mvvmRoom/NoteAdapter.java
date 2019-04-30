package com.example.myapplication.mvx.mvvmRoom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.mvx.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    List<Note>  notes = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.textViewDesc.setText(currentNote.getDescription());
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void  setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position){
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewDesc;
        private TextView textViewPriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle  = itemView.findViewById(R.id.text_view_title);
            textViewDesc  = itemView.findViewById(R.id.text_view_desc);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int postion = getAdapterPosition();
                    if(listener != null && postion != RecyclerView.NO_POSITION) {
                        listener.onItemClick(notes.get(postion));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener  = listener;
    }

}
