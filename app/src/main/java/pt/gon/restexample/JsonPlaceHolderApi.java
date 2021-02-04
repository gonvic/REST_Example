package pt.gon.restexample;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    // GET Public Thingspeak
    @GET("https://thingspeak.com/channels/935349/feed.json") // full URL
    Call<JsonElement> getGenericJSON();

    // GET My Thingspeak
    @GET("channels/1297434/feeds.json") // full URL // ex: https://api.thingspeak.com/channels/1297434/feeds.json?results=2
    Call<JsonElement> getFeeds();

    // POST My Thingspeak
    @FormUrlEncoded
    @POST("update?api_key=86604O09RLHSRQZE") // https://api.thingspeak.com/update?api_key=86604O09RLHSRQZE&field1=temperature&field2=humidity&field3=pressure&field4=concentration&field5=pwrsrc&field6=airQuality
    Call<JsonElement> createFeedFormUrlEncoded(
            @Field("field1") int temperature,
            @Field("field2") int humidity,
            @Field("field3") int pressure,
            @Field("field4") double concentration,
            @Field("field5") int pwrsrc,
            @Field("field6") int airQuality
    );

    // POST My Thingspeak
    @POST("update?api_key=86604O09RLHSRQZE")
    Call<JsonElement> createFeed(@Body Feed feed);

    // PUT My Thingspeak

    // DELETE My Thingspeak



}
