package com.example.myapplication.mvx.ret;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetActivity extends AppCompatActivity {
    private TextView tv_result;
    JphAPI jphAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ret);
        tv_result = findViewById(R.id.tv_result);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request newRequest = originalRequest.newBuilder()
                                .header("Int-Header","xyz")
                                .build()
                                ;
                        return chain.proceed(newRequest);
                    }
                })
                .addInterceptor(loggingInterceptor)
                .build();//underlying library for Retrofit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        jphAPI  = retrofit.create(JphAPI.class);//going to create class for this API interface
        getPosts();
        //getComments();
        //createPost();
        //updatePost();
        //deletePost();
    }

    private void deletePost() {
        Call<Void> call = jphAPI.deletePost(5);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                tv_result.setText("Code : "+response.code());
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                tv_result.setText(t.getMessage());
            }
        });
    }

    private void updatePost() {
        Post post = new Post(12, null,"new text");
        Map<String,String> headers = new HashMap<>();
        headers.put("Map-header2", "def");

        Call<Post> call = jphAPI.patchPost(headers,5, post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    tv_result.setText(response.code());
                    return;
                }
                else{
                    Post postResponse = response.body();

                    String con = "";
                    con+="Code : "+response.code()+ "\n";
                    con+="ID "+postResponse.getId() + "\n";
                    con+="UserId "+postResponse.getUserId() + "\n";
                    con+="Text"+postResponse.getText() + "\n";
                    tv_result.append(con);
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tv_result.setText(t.getMessage());

            }
        });
    }

    private void createPost() {
        Post post = new Post(23, "New Title", "New Post");
        Call<Post> call = jphAPI.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    tv_result.setText(response.code());
                    return;
                }
                else{
                    Post postResponse = response.body();

                    String con = "";
                    con+="Code : "+response.code()+ "\n";
                    con+="ID "+postResponse.getId() + "\n";
                    con+="UserId "+postResponse.getUserId() + "\n";
                    con+="Text"+postResponse.getText() + "\n";
                    tv_result.append(con);
                }

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tv_result.setText(t.getMessage());

            }
        });
    }

    private void getComments() {
        Call<List<Comment>> call = jphAPI.getComments(3);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){//http code is not between 200- 300
                    tv_result.setText(response.code());
                }
                else{
                    List<Comment> comments = response.body();
                    for(Comment comment : comments){
                        String c = comment.getText();
                        c+="\n";
                        tv_result.append(c);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }


    public void getPosts(){
        Map<String,String> parameters = new HashMap<>();
        parameters.put("userId","1");
        parameters.put("_sort","id");
        parameters.put("_order","desc");
       // Call<List<Post>> calls = jphAPI.getPost(parameters); we can use map

        Call<List<Post>> call = jphAPI.getPost(new Integer[]{2,3},"id","des");//to execute network request we have to use call object
        //we can pass null in query
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
                    con+="UserId "+p.getUserId() + "\n";
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
