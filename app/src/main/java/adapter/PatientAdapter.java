package adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.su.kei.ehealthrecordsretrieval.DetailActivity;
import com.su.kei.ehealthrecordsretrieval.R;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import bean.Patients;
import static constants.Constant.PATIENT_BIRTHDATE;
import static constants.Constant.PATIENT_GENDER;
import static constants.Constant.PATIENT_ID;

/*
Adapter for feeding RecyclerView data
 */
public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {
    private final LayoutInflater layoutInflater;
    ArrayList<Patients.EPatient> patientAl;
    Context context;

    public PatientAdapter(Context context, ArrayList<Patients.EPatient> patientAl) {
        layoutInflater = LayoutInflater.from(context);
        this.patientAl = patientAl;
        this.context = context;
    }

    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        PatientViewHolder patientViewHolder = new PatientViewHolder(view);
        return patientViewHolder;
    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position) {
        final Patients.EPatient current = patientAl.get(position);
        holder.userNameTv.setText(context.getString(R.string.patient) + current.getResource().getId());
        holder.parentLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(PATIENT_ID, current.getResource().getId());
                intent.putExtra(PATIENT_GENDER, current.getResource().getGender());
                intent.putExtra(PATIENT_BIRTHDATE, current.getResource().getBirthDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientAl.size();
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout parentLl;
        private TextView userNameTv;

        public PatientViewHolder(View itemView) {
            super(itemView);
            userNameTv = itemView.findViewById(R.id.user_name);
            parentLl = itemView.findViewById(R.id.parentll);
        }
    }
}
