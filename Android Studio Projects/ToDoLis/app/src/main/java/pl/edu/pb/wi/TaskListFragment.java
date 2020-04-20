package pl.edu.pb.wi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskListFragment extends Fragment {
    private TaskAdapter adapter;
    private  RecyclerView recyclerView;
    private TextView nameTextView;
    private  TextView dateTextView;
    private ImageView iconImageView;

    private boolean subtitleVisible;
    public static final String KEY_EXTRA_TASK_ID="";

    @Override
    public void onCreate(@Nullable Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_task_menu,menu);
        MenuItem subtitleItem=menu.findItem(R.id.show_subtitle);
        if(subtitleVisible){
            subtitleItem.setTitle(R.string.hide_subtitle);
        }else{
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_task:
                Task task=new Task();
                TaskStorage.getInstance().addTask(task);
                Intent intent=new Intent(getActivity(),MainActivity.class);
                intent.putExtra(TaskListFragment.KEY_EXTRA_TASK_ID,task.getId());
                startActivity(intent);
                return true;
            case R.id.show_subtitle:
                subtitleVisible=!subtitleVisible;
                getActivity().invalidateOptionsMenu();//rekonstrukcja opcji menu
                updateSubtitle();
                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateSubtitle(){
        TaskStorage taskStorage=TaskStorage.getInstance();
        List<Task> tasks=taskStorage.getTasks();
        int todoTaskCount=0 ;
        for(Task task:tasks){
            if(!task.isDone()){
                todoTaskCount++;
            }
        }
        String subtitle=getString(R.string.subtitle_format,todoTaskCount);
        if(!subtitleVisible){
            subtitle=null;
        }
        AppCompatActivity appCompatActivity=(AppCompatActivity) getActivity();
        appCompatActivity.getSupportActionBar().setSubtitle(subtitle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_task_list,container,false);
        recyclerView= view.findViewById(R.id.task_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateView();
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        updateView();
    }

    private void updateView(){
        TaskStorage taskStorage=TaskStorage.getInstance();
        List<Task> tasks=taskStorage.getTasks();


        if(adapter==null){
            adapter=new TaskAdapter(tasks);
            recyclerView.setAdapter(adapter);

        }else {
            adapter.notifyDataSetChanged();
        }
        updateSubtitle();
    }
    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Task task;
//        private final LinearLayout itemElement=itemView.findViewById(R.id.item_element);


        public TaskHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_task ,parent,false));
            itemView.setOnClickListener(this);
            iconImageView=itemView.findViewById(R.id.imageView);

            nameTextView=itemView.findViewById((R.id.task_item_name));
            dateTextView=itemView.findViewById(R.id.task_item_date);


        }
        public void bind(final Task task){
            this.task=task;
//            itemElement.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), MainActivity.class);
//                    intent.putExtra(KEY_EXTRA_TASK_ID, task.getId());
//                    startActivity(intent);
//                }
//            });
            nameTextView.setText(task.getName());
            dateTextView.setText(task.getDate().toString());
            if(task.isDone()){
                iconImageView.setImageResource(R.drawable.ic_check_box);
            }
            else{
                iconImageView.setImageResource(R.drawable.ic_check_box_outline_blank);
            }
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(getActivity(),MainActivity.class);
            intent.putExtra(KEY_EXTRA_TASK_ID, task.getId());
            startActivity(intent);//29
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder>
    {
        private List<Task> tasks;

        public TaskAdapter(List<Task> tasks)
        {
            this.tasks=tasks;
        }


        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            return new TaskHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task=tasks.get(position);
            holder.bind(task);
        }

        @Override
        public int getItemCount() {
            return tasks.size();
        }
    }
}
