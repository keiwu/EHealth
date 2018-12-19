package service;
import bean.Patients;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
Retrofit api implementation
 */
public interface EHealthApi {
    @GET("Patient?")
    Call<Patients> getPatients(@Query("_count") int count);
}
