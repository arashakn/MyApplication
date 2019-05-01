package com.example.myapplication.mvx.ret;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.mvx.R;

import java.util.List;

public class RetActivity extends AppCompatActivity {
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ret);
        tv_result = findViewById(R.id.tv_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JphAPI jphAPI  = retrofit.create(JphAPI.class);//going to create class for this API interface
        Call<List<Post>> call = jphAPI.getPost();//to execute network requestwe have to use call object
        call.enqueue(new Callback<List<Post>>() { // we want to make the call asyn. otherwise it's going to run on main thread. any thread it is executed.
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful())//http code is not between 200 and 300
                {
                    tv_result.setText(response.code());
                    return;
                }

                List<Post> posts = response.body();
                for(Post p : posts){
                    String con = "";
                    con+="ID "+p.getId() + "\n";
                    tv_result.append(con);
                }


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tv_result.setText(t.getMessage());

            }
        });
    }

    public static Intent getIntent(Context context){
        return new Intent(context, RetActivity.class);
    }
}
