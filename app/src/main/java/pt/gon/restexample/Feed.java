package pt.gon.restexample;

import com.google.gson.annotations.SerializedName;

public class Feed { // @GET only

    @SerializedName("field1")
    private int temperature;

    @SerializedName("field2")
    private int humidity;

    @SerializedName("field3")
    private int pressure;

    @SerializedName("field4")
    private double concentraion; // double/float

    @SerializedName("field5")
    private int pwrsrc;

    @SerializedName("field6")
    private int airQuality;

    public Feed(int temperature, int humidity, int pressure, double concentraion, int pwrsrc, int airQuality) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.concentraion = concentraion;
        this.pwrsrc = pwrsrc;
        this.airQuality = airQuality;
    }
}
