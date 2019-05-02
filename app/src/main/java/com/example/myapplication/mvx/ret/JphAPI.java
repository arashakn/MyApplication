package com.example.myapplication.mvx.ret;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


/**
 * this interface will represent the API of the webServer
 */
public interface JphAPI {

    @GET("posts")//GET	/posts?userId=1
    Call<List<Post>> getPost(@Query("userId") Integer[] userId,//can be nullable
                             @Query("_sort") String sort,
                             @Query("_order") String order//adding multiple query
    );

    @GET("posts")
    Call<List<Post>> getPost(@QueryMap Map<String, String> parameters);

    @GET("posts/{id}/comments")///posts/1/comments
    Call<List<Comment>> getComments(@Path("id") int postId);//the postId will be turned in to String

    @GET
    Call<List<Comment>> getComments(@Url String url);//passing url


    @POST("posts")
    Call<Post> createPost(@Body Post post);// the Post object will be serializied  in to Json using Gson Format before sending to server
    // we are passing body directly

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@Field("userId") int userId , @Field("title") String title, @Field("body") String text);
    //it's encoded the same way the url is encoded , but will be added to the body of request

    @Headers({"Static-Header: 123","Stataic-Header2: 125"})
    @PUT("posts/{id}") // we are adding place holder in relative url
    Call<Post> putPost(@Header("Dynamic-Header") String header,
                       @Path("id") int putId, @Body Post post);

    @PATCH("posts/{id}") // we are adding place holder in relative url
    Call<Post> patchPost(@HeaderMap Map<String,String> headers, @Path("id") int putId, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int delId); // we ignore body of response
}
