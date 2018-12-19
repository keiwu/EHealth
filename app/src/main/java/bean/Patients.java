package bean;

import java.util.ArrayList;

/*
Patients class to hold data retrieve from web service
 */
public class Patients {
    ArrayList<EPatient> entry;
    public ArrayList<EPatient> getEntry() {
        return entry;
    }

    public void setEntry(ArrayList<EPatient> entry) {
        this.entry = entry;
    }

    public class EPatient{
        Resource resource;

        public Resource getResource() {
            return resource;
        }

        public void setResource(Resource resource) {
            this.resource = resource;
        }
    }

    public class Resource{
        String id;
        String gender;
        String birthDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }
    }
}
