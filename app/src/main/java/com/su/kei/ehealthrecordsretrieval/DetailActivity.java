package com.su.kei.ehealthrecordsretrieval;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import static constants.Constant.PATIENT_BIRTHDATE;
import static constants.Constant.PATIENT_GENDER;
import static constants.Constant.PATIENT_ID;

/*
Activity to display patient detail page
 */
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Button backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra(PATIENT_ID) && getIntent().hasExtra(PATIENT_GENDER) && getIntent().hasExtra(PATIENT_BIRTHDATE)){
            String patientId = getIntent().getStringExtra(PATIENT_ID);
            String patientGender = getIntent().getStringExtra(PATIENT_GENDER);
            String patientBirthdate = getIntent().getStringExtra(PATIENT_BIRTHDATE);
            setData(patientId, patientGender, patientBirthdate);
        }
    }

    private void setData(String patientId, String patientGender, String patientBirthdate) {
        TextView patientIdTv = findViewById(R.id.patientIdTv);
        TextView patientGenderTv = findViewById(R.id.patientGenderTv);
        TextView patientBirthdateTv = findViewById(R.id.patientBirthdateTv);
        patientIdTv.setText(getResources().getString(R.string.patient_id) + patientId);
        patientGenderTv.setText(getResources().getString(R.string.patient_gender) + patientGender);
        patientBirthdateTv.setText(getResources().getString(R.string.patient_birthdate) + patientBirthdate);
    }
}
