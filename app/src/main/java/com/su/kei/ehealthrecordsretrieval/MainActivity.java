package com.su.kei.ehealthrecordsretrieval;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import adapter.PatientAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bean.Patients;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.EHealthApi;
import service.RetrofitHelper;
import static constants.Constant.RECORDS_TO_RETRIEVE;

public class MainActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;
    private TextView failToRetrieveTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.getPatientPb);
        failToRetrieveTv = findViewById(R.id.failToRetrieveTv);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        getPatients();
    }

    public void getPatients() {
        EHealthApi eHealthApi = RetrofitHelper.getRetrofit().create(EHealthApi.class);

        Call<Patients> call = eHealthApi.getPatients(RECORDS_TO_RETRIEVE);

        call.enqueue(new Callback<Patients>() {
                         @Override
                         public void onResponse(Call<Patients> call, Response<Patients> response) {
                             progressBar.setVisibility(View.INVISIBLE);
                             ArrayList<Patients.EPatient> patientAl;
                             patientAl = response.body().getEntry();
                             PatientAdapter adapter = new PatientAdapter(getApplicationContext(), patientAl);
                             recyclerView.setAdapter(adapter);
                             recyclerView.setLayoutManager(linearLayoutManager);
                             DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                                     linearLayoutManager.getOrientation());
                             recyclerView.addItemDecoration(dividerItemDecoration);
                         }

                         @Override
                         public void onFailure(Call<Patients> call, Throwable t) {
                             progressBar.setVisibility(View.INVISIBLE);
                             failToRetrieveTv.setVisibility(View.VISIBLE);
                             Toast.makeText(getApplicationContext(), getString(R.string.fail_to_retrieve_records), Toast.LENGTH_LONG).show();
                         }
                     }
        );
    }
}
