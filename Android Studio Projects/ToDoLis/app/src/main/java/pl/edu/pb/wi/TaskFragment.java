package pl.edu.pb.wi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.UUID;

public class TaskFragment extends Fragment {

    private TextView nameField;
    private Button dateButton;
    private CheckBox doneCheckBox;
    Task task;
    public static final String ARG_TASK_ID="pl.edu.pb.wi.task.id";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        UUID taskId=(UUID) getArguments().getSerializable(ARG_TASK_ID);
        task= TaskStorage.getInstance().getTasks(taskId);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState)
    {
        //super.onCreateView(inflater,container,savedInstanceState);
        View view= inflater.inflate(R.layout.fragment_task,container,false);

        nameField=view.findViewById(R.id.task_name);
        dateButton=view.findViewById(R.id.task_date);
        doneCheckBox=view.findViewById(R.id.task_done);

        nameField.setText(task.getName());
        nameField.addTextChangedListener(new TextWatcher()
        {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                task.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        dateButton.setText(task.getDate().toString());
        dateButton.setEnabled(false);

        doneCheckBox.setChecked(task.isDone());
        doneCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.setDone(isChecked);
            }
        });
        return view;
    }


    public static TaskFragment newInstance(UUID taskId)
    {
        Bundle bundle=new Bundle();
        bundle.putSerializable(ARG_TASK_ID,taskId);
        TaskFragment taskFragment=new TaskFragment();
        taskFragment.setArguments(bundle);
        return taskFragment;
    }
}
