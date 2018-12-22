package com.su.kei.ehealthrecordsretrieval;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Presenter.SearchPresenter;
import adapter.PatientAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bean.Patients;

public class MainActivity extends AppCompatActivity implements SearchPresenter.SearchView {
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
        SearchPresenter searchPresenter = new SearchPresenter(this);
        searchPresenter.getPatients();
    }

    @Override
    public void onItemRetrieved(ArrayList<Patients.EPatient> patientAl) {
        progressBar.setVisibility(View.INVISIBLE);
        if (patientAl!=null){
            PatientAdapter adapter = new PatientAdapter(getApplicationContext(), patientAl);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(linearLayoutManager);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    linearLayoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
        } else{
            failToRetrieveTv.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), getString(R.string.fail_to_retrieve_records), Toast.LENGTH_LONG).show();
        }
    }
}
