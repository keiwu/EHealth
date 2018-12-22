package Presenter;

import com.su.kei.ehealthrecordsretrieval.MainActivity;

import java.util.ArrayList;
import Util.Utils;
import bean.Patients;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.EHealthApi;
import service.RetrofitHelper;
import static constants.Constant.RECORDS_TO_RETRIEVE;

public class SearchPresenter {
    private SearchView searchView;

    public SearchPresenter(MainActivity mainActivity) {
        this.searchView = mainActivity;
    }

    public void getPatients() {
        EHealthApi eHealthApi = RetrofitHelper.getRetrofit().create(EHealthApi.class);

        Call<Patients> call = eHealthApi.getPatients(RECORDS_TO_RETRIEVE);

        call.enqueue(new Callback<Patients>() {
                         @Override
                         public void onResponse(Call<Patients> call, Response<Patients> response) {
                             ArrayList<Patients.EPatient> patientAl;
                             patientAl = response.body().getEntry();
                             patientAl = Utils.sortPatientList(patientAl);
                             searchView.onItemRetrieved(patientAl);
                         }

                         @Override
                         public void onFailure(Call<Patients> call, Throwable t) {
                             searchView.onItemRetrieved(null);

                         }
                     }
        );
    }


    public interface SearchView {
       void onItemRetrieved(ArrayList<Patients.EPatient> patientsAl);
    }
}
