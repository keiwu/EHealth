package Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import bean.Patients;

public class Utils {
    /*
    Sort patient array list in alphabetical order by id;
     */
    public static ArrayList<Patients.EPatient> sortPatientList(ArrayList<Patients.EPatient> patientAl){
        Collections.sort(patientAl, new Comparator<Patients.EPatient>() {
            @Override
            public int compare(Patients.EPatient p1, Patients.EPatient p2) {
                return p1.getResource().getId().compareTo(p2.getResource().getId());
            }
        });
        return patientAl;
    }
}
