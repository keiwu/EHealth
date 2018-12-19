package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static constants.Constant.BASE_URL;

/*
Helper class for getting Retrofit instance
 */
public class RetrofitHelper {
    public static Retrofit getRetrofit(){
        Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        return retrofit;
    }


}
