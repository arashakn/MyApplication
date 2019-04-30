
package com.example.myapplication.mvx.mvvmRoom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.myapplication.mvx.R;

public class AddNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_TTILE = "EXTRA_TITLE";
    public static final String EXTRA_DESC = "EXTRA_DESC";
    public static final String EXTRA_PRIORITY = "EXTRA_PRIORITY";
    private EditText editTextTitle;
    private EditText editTextDesc;
    private NumberPicker numberPickerPriority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTextDesc = findViewById(R.id.edit_text_desc);
        editTextTitle = findViewById(R.id.edit_text_title);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TTILE));
            editTextDesc.setText(intent.getStringExtra(EXTRA_DESC));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_ID,1));

        }
        else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=  getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note :
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String des = editTextTitle.getText().toString();
        int priority = numberPickerPriority.getValue();
        if(title.trim().isEmpty() || des.trim().isEmpty()){
            Toast.makeText(this,"Please insert title and description " , Toast.LENGTH_SHORT);
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TTILE,title);
        data.putExtra(EXTRA_DESC,des);
        data.putExtra(EXTRA_PRIORITY,priority);


        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK,data);
        finish();
    }
}
