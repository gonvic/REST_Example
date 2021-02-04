package pt.gon.restexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult; // for the text result
    private JsonPlaceHolderApi jsonPlaceHolderApi; // turn JsonPlaceHolderApi into a member variable - to use for different methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result); // to display the response

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(); // to know what is happening on both the request and response
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // the most verbose level

        OkHttpClient okHttpClient = new OkHttpClient.Builder() // need to create a client to add the logging interceptor since retrofit by default does not have one
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder() // new instance of Retrofit called retrofit
                // .baseUrl("https://thingspeak.com/") // base URL either one works
                .baseUrl("https://api.thingspeak.com/") // base URL
                .addConverterFactory(GsonConverterFactory.create()) // use GSON to parse the response
                .client(okHttpClient) // add client to enable logging
                .build();

        // create an implementation
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        // buttons for REST requests

        final Button buttonGet = findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getGenericJSON();
            }
        });

        final Button buttonGet2 = findViewById(R.id.buttonGet2);
        buttonGet2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getFeeds();
            }
        });

        final Button buttonPost = findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createFeedFormUrlEncoded();
            }
        });

        final Button buttonPost2 = findViewById(R.id.buttonPost2);
        buttonPost2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createFeed();
            }
        });

        final Button buttonPut = findViewById(R.id.buttonPut);
        buttonPut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // putFeed();
            }
        });

        final Button buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // deleteFeed();
            }
        });
    }

    // public thingspeak channel
    private void getGenericJSON() {

        Call<JsonElement> call = jsonPlaceHolderApi.getGenericJSON();
        call.enqueue(new Callback<JsonElement>() {

            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("GENERIC_FEED_RESPONSE", response.body().toString());
                    textViewResult.setText("Response:\n");
                    textViewResult.append("Code:" + response.code() + "\n");
                    textViewResult.append(response.body().toString() + "\n");
                    textViewResult.append("End of Response:");
                } else {
                    Log.e("GENERIC_FEED_RESPONSE", "Error in getGenericJson:" + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("GENERIC_FEED_FAILURE", "Response Error Generic:\n" + t.getMessage());
                textViewResult.setText(t.getMessage());
            }
        });
    }

    // my public thingspeak channel
    private void getFeeds() {

        Call<JsonElement> call = jsonPlaceHolderApi.getFeeds();
        call.enqueue(new Callback<JsonElement>() {

            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("GET_FEEDs_RESPONSE", response.body().toString());
                    textViewResult.setText("Response:\n");
                    textViewResult.append("Code:" + response.code() + "\n");
                    textViewResult.append(response.body().toString() + "\n");
                    textViewResult.append("End of Response:");
                } else {
                    Log.e("GET_FEEDS_RESPONSE", "Error in getFeeds:" + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("GET_FEEDS_FAILURE", "Response Error Get Feeds:\n" + t.getMessage());
                textViewResult.setText(t.getMessage());
            }
        });
    }

    // post on my public thingspeak channel
    private void createFeed() {

        // hardcoded parameters for presentation purposes
        Feed feed = new Feed(32, 21, 1022, 87.203506, 1, 1); // feed object

        Call<JsonElement> call = jsonPlaceHolderApi.createFeed(feed); // pass the body of the request

        // to execute asynchronously
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("CREATE_FEED_RESPONSE", response.body().toString());
                    textViewResult.setText("Response:\n");
                    textViewResult.append("Code:" + response.code() + "\n");
                    textViewResult.append(response.body().toString() + "\n");
                    textViewResult.append("End of Response:");
                } else {
                    Log.e("CREATE_FEED_RESPONSE", "Error in createFeed:" + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("CREATE_FEED_FAILURE", "Response Error Create Feed:\n" + t.getMessage());
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void createFeedFormUrlEncoded() {

        Call<JsonElement> call = jsonPlaceHolderApi.createFeedFormUrlEncoded(27, 35, 999, 83.943604, 1, 1);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("TAG Generic", response.body().toString());
                    textViewResult.setText("Response:\n");
                    textViewResult.append("Code:" + response.code() + "\n");
                    textViewResult.append(response.body().toString() + "\n");
                    textViewResult.append("End of Response:");
                } else {
                    Log.e("TAG", "Error in createFeedFormUrlEncoded:" + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("TAG", "Response Error Create Feed FormUrlEncoded:\n" + t.getMessage());
            }
        });
    }
}
