package com.example.ulstudenthandbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Modules extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText textModule,textRoom;
    private TextView textViewBlock,textViewRoom,textViewBuilding;
    private Spinner spinnerTimeStart,spinnerTimeEnd,spinnerBuilding,spinnerBlock,spinnerColor;

    private static final String[] timeArray = {"9:00", "9:30", "10:00", "10:30", "11:00"
            ,"11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00"
            , "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00"
            , "19:30", "20:00"};

    private static final String[] buildingArray = {"Analog(AD)","Computer Science(CS)","Engineering Research(ER)",
            "Foundation(F)","Glucksman Library(GL)","Health Sciences(HS)","Irish World Academy(IW)","Kemmy Business School(KB)",
            "Languages(LC)","Lonsdale(L)","Main(A,B,C,D,E)","Medical School(GEMS)","PESS(P)","Schrödinger(SR)","Schuman(S)"};


    private static final String[] blockArray ={"A Block","B Block","C Block","D Block","E Block"};

    private static final String[] colorArray ={"Red","Blue","Green","Orange","Pink","Black","Purple"};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);

        //texts we will hide and show
        textViewBlock = findViewById(R.id.blockText);
        textViewBlock.setVisibility(View.GONE);
        textViewRoom = findViewById(R.id.roomText);

        // module
        textModule = findViewById(R.id.module);
        textRoom = findViewById(R.id.room);

        //spinner time start
        spinnerTimeStart = (Spinner)findViewById(R.id.timeStart);
        ArrayAdapter<String>adapterTimeStart = new ArrayAdapter<String>(Modules.this,
                android.R.layout.simple_spinner_item,timeArray);

        adapterTimeStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTimeStart.setAdapter(adapterTimeStart);
        spinnerTimeStart.setOnItemSelectedListener(this);

        //spinner time end
        spinnerTimeEnd = (Spinner)findViewById(R.id.timeEnd);
        ArrayAdapter<String>adapterTimeEnd = new ArrayAdapter<String>(Modules.this,
                android.R.layout.simple_spinner_item,timeArray);

        adapterTimeEnd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTimeEnd.setAdapter(adapterTimeEnd);
        spinnerTimeEnd.setOnItemSelectedListener(this);

        //spinner building
        spinnerBuilding = (Spinner)findViewById(R.id.building);
        ArrayAdapter<String>adapterBuilding = new ArrayAdapter<String>(Modules.this,
                android.R.layout.simple_spinner_item,buildingArray);

        adapterBuilding.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBuilding.setAdapter(adapterBuilding);
        spinnerBuilding.setOnItemSelectedListener(this);

        //spinner block
        spinnerBlock = (Spinner)findViewById(R.id.block);
        ArrayAdapter<String>adapterBlock = new ArrayAdapter<String>(Modules.this,
                android.R.layout.simple_spinner_item,blockArray);

        adapterBlock.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBlock.setAdapter(adapterBlock);
        spinnerBlock.setOnItemSelectedListener(this);
        spinnerBlock.setVisibility(View.GONE);

        //spinner color
        spinnerColor = (Spinner)findViewById(R.id.color);
        ArrayAdapter<String>adapterColor = new ArrayAdapter<String>(Modules.this,
                android.R.layout.simple_spinner_item,colorArray);

        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(adapterColor);
        spinnerColor.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        if(parent == spinnerBuilding && position == 10){
            spinnerBlock.setVisibility(View.VISIBLE);
            textViewBlock.setVisibility(View.VISIBLE);
        }else if(parent == spinnerBuilding && position != 10){
            spinnerBlock.setVisibility(View.GONE);
            textViewBlock.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

}