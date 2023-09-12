package com.example.demo.C02OpenWeatherAPI;



//API KEY : b7a263e63bfe790ff0081e9b619e7c91


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/weather")
public class OpenWeatherController {

    private final String API_KEY = "b7a263e63bfe790ff0081e9b619e7c91";

    //35.808657 ,128.604748
    @GetMapping("/get/{lat}/{lon}")
    public Object getWeather(@PathVariable String lat, @PathVariable String lon){

        //URL
        String url = "https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid="+API_KEY;

        //Header
        //Params


//        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, null, String.class);
//        System.out.println(response);
//        System.out.println(response.getBody());
//        return response.getBody();


        RestTemplate rt = new RestTemplate();
        //Call_01
        ResponseEntity<String> r = rt.exchange(url, HttpMethod.GET, null, String.class);
        System.out.println(r);
        System.out.println(r.getBody());

        //Call_02
        WeatherResponse response =  rt.getForObject(url, WeatherResponse.class);

        System.out.println(response);
        return response;

    }
}


@Data
class WeatherResponse {
    Coord coord;
    ArrayList< Object > weather = new ArrayList < Object > ();
    private String base;
    Main main;
    private float visibility;
    Wind wind;
    Rain rain;
    Clouds clouds;
    private long dt;
    Sys sys;
    private float timezone;
    private float id;
    private String name;
    private float cod;

    @Data
    class Sys {
        private float type;
        private float id;
        private String country;
        private float sunrise;
        private float sunset;
    }
    @Data
    class Clouds {
        private float all;
    }
    @Data
    class Rain {
        @JsonProperty("1h") // Gson 사용 시 필요한 주석
        private float h1;
    }

    @Data
    class Wind {
        private float speed;
        private float deg;
    }
    @Data
    class Main {
        private float temp;
        private float feels_like;
        private float temp_min;
        private float temp_max;
        private float pressure;
        private float humidity;

    }
    @Data
    class Coord {
        private float lon;
        private float lat;
    }
}










