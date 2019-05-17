package com.example.myapplication.mvx;

import android.os.Bundle;

import com.example.myapplication.mvx.Recycle.RecycleActivity;
import com.example.myapplication.mvx.bb.AboutActivity;
import com.example.myapplication.mvx.character.ActivityCharacters;
import com.example.myapplication.mvx.coin.ui.home.CoinHomeActivity;
import com.example.myapplication.mvx.ret.RetActivity;
import com.example.myapplication.mvx.rxjava.RxJavaOperatorMainActivity;
import com.example.myapplication.mvx.rxjava.RxMainActivity;
import com.example.myapplication.mvx.stride.ActivityTwitter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.mvx.mvc.MVCActivity;
import com.example.myapplication.mvx.mvp.MVPActivity;
import com.example.myapplication.mvx.mvvm.MvvmActivity;
import com.example.myapplication.mvx.mvvmRoom.RoomActivity;

public class ArcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onMVC(View view){
        startActivity(MVCActivity.getIntent(this));
    }
    public void onMVP(View view){
        startActivity(MVPActivity.getIntent(this));
    }
    public void onMVVM(View view){
        startActivity(MvvmActivity.getIntent(this));
    }
    public void onMVVMRoom(View view){
        startActivity(RoomActivity.getIntent(this));
    }
    public void onRet(View view){
        startActivity(RetActivity.getIntent(this));
    }
    public void onBB(View view){
        startActivity(AboutActivity.getIntent(this));
    }
    public void onRec(View view){
        startActivity(RecycleActivity.getIntent(this));
    }
    public void onCoin(View view){
        startActivity(CoinHomeActivity.getIntent(this));
    }
    public void onTwt(View view){
        startActivity(ActivityTwitter.getIntent(this));
    }
    public void onRxJava(View view){
        startActivity(RxJavaOperatorMainActivity.getIntent(this));
    }

    public void onChar(View view){
        startActivity(ActivityCharacters.getIntent(this));
    }

}
