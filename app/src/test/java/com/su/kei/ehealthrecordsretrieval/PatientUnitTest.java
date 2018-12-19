package com.su.kei.ehealthrecordsretrieval;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import bean.Patients;
import retrofit2.Call;
import retrofit2.Response;
import service.EHealthApi;
import service.RetrofitHelper;
import static org.junit.Assert.*;

public class PatientUnitTest {
    Patients.EPatient patient;

    @Before
    public void setup(){
        Patients patients = new Patients();
        patient = patients.new EPatient();
        Patients.Resource resource = patients.new Resource();
        resource.setBirthDate("1924-10-10");
        resource.setGender("Male");
        resource.setId("12345");
        patient.setResource(resource);
    }

    @Test
    public void testSetPatient(){
        assertNotNull(patient.getResource().getBirthDate());
        assertNotNull(patient.getResource().getGender());
        assertNotNull(patient.getResource().getId());
    }

    @Test
    public void testGetPatient(){
        assertEquals("12345", patient.getResource().getId());
        assertEquals("1924-10-10", patient.getResource().getBirthDate());
        assertEquals("Male", patient.getResource().getGender());
    }

    @Test
    public void testGetPatientsWebservice_Success() {
        EHealthApi apiEndpoints = RetrofitHelper.getRetrofit().create(EHealthApi.class);
        Call<Patients> call = apiEndpoints.getPatients(10);
        try {
            Response<Patients> response = call.execute();
            assertEquals(response.body().getEntry().size(), 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}