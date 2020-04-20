package pl.edu.pb.sensorapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private List<Sensor> sensorList;

    private SensorAdapter adapter;


    private  RecyclerView recyclerView;
    private static final String TAG="SensorActivity";
    private boolean sizeVisible;
    public static final String KEY_EXTRA_SENSOR_ID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        recyclerView=findViewById(R.id.sensor_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        if(adapter==null){
            adapter=new SensorAdapter(sensorList);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
       inflater.inflate(R.menu.sensor_menu,menu);
        MenuItem sizeItem=menu.findItem(R.id.show_size);
        if(sizeVisible){
            sizeItem.setTitle(R.string.hide_size);

        }
        else{
            sizeItem.setTitle(R.string.show_size);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.show_size:
                sizeVisible=!sizeVisible;
                getLayoutInflater();
                updateSize();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateSize(){
        int sensorCount=sensorList.size();
        String size=getString(R.string.subtitle_format,sensorCount);
        if(!sizeVisible){
            size=null;
        }

        AppCompatActivity appCompatActivity=this;
        appCompatActivity.getSupportActionBar().setSubtitle(size);

    }

    private class SensorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       private Sensor sensor;
        private ImageView imageView;
        private TextView textView;


        public SensorHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.sensor_list_item, parent, false));
            itemView.setOnClickListener(this);

            imageView = itemView.findViewById(R.id.imageView);

            textView = itemView.findViewById((R.id.sensor_name));
        }
        public void bind(final Sensor sensor){
            this.sensor=sensor;

                textView.setText(sensor.getName());
                if(sensor.getType()==Sensor.TYPE_LIGHT || sensor.getType()==Sensor.TYPE_ROTATION_VECTOR)
                {
                    imageView.setImageResource(R.drawable.ic_sensor_selected);
                }
                else
                {
                    imageView.setImageResource(R.drawable.ic_sensor);
                }



            Log.d("TAG",sensor.getName());
            Log.d("TAG", String.valueOf(sensor.getMaximumRange()));
            Log.d("TAG",sensor.getVendor());
        }

        @Override
        public void onClick(View v) {
            if(sensor.getType()==Sensor.TYPE_LIGHT || sensor.getType()==Sensor.TYPE_ROTATION_VECTOR){
                Intent intent=new Intent(SensorActivity.this,SensorDetailsActivity.class);
                intent.putExtra(KEY_EXTRA_SENSOR_ID,sensor.getType());
                startActivity(intent);
            }

        }
    }


    private class SensorAdapter extends RecyclerView.Adapter<SensorHolder> {


         public SensorAdapter(List<Sensor> sensors)
        {
            sensorList= sensors;
        }




        @NonNull
        @Override
        public SensorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                LayoutInflater layoutInflater=LayoutInflater.from(this);
                return new SensorHolder(getLayoutInflater(),parent);

        }

        @Override
        public void onBindViewHolder(@NonNull SensorHolder holder, int position) {
                Sensor sensor=sensorList.get(position);
                holder.bind(sensor);
        }

        @Override
        public int getItemCount() {
            return sensorList.size();
        }

    }

}