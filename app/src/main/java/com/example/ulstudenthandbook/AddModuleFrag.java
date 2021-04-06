package com.example.ulstudenthandbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddModuleFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddModuleFrag extends Fragment implements AdapterView.OnItemSelectedListener{


    private EditText textModule,textRoom;
    private TextView textViewBlock,textViewRoom,textViewBuilding;
    private Spinner spinnerTimeStart,spinnerTimeEnd,spinnerBuilding,spinnerBlock,spinnerColor;

    private static final String[] timeArray = {"","9:00", "9:30", "10:00", "10:30", "11:00"
            ,"11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00"
            , "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00"
            , "19:30", "20:00"};

    private static final String[] buildingArray = {"","Analog(AD)","Computer Science(CS)","Engineering Research(ER)",
            "Foundation(F)","Glucksman Library(GL)","Health Sciences(HS)","Irish World Academy(IW)","Kemmy Business School(KB)",
            "Languages(LC)","Lonsdale(L)","Main(A,B,C,D,E)","Medical School(GEMS)","PESS(P)","Schrödinger(SR)","Schuman(S)"};


    private static final String[] blockArray ={"","A Block","B Block","C Block","D Block","E Block"};

    private static final String[] colorArray ={"","Red","Blue","Green","Orange","Pink","Black","Purple"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddModuleFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddModuleFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static AddModuleFrag newInstance(String param1, String param2) {
        AddModuleFrag fragment = new AddModuleFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_module, container, false);



        //texts we will hide and show
        textViewBlock = view.findViewById(R.id.blockText);
        textViewBlock.setVisibility(View.GONE);
        textViewRoom = view.findViewById(R.id.roomText);

        // module
        textModule = view.findViewById(R.id.module);
        textRoom = view.findViewById(R.id.room);

        //spinner time start
        spinnerTimeStart = (Spinner)view.findViewById(R.id.timeStart);
        ArrayAdapter<String> adapterTimeStart = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,timeArray);

        adapterTimeStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTimeStart.setAdapter(adapterTimeStart);
        spinnerTimeStart.setOnItemSelectedListener(this);

        //spinner time end
        spinnerTimeEnd = (Spinner)view.findViewById(R.id.timeEnd);
        ArrayAdapter<String>adapterTimeEnd = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,timeArray);

        adapterTimeEnd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTimeEnd.setAdapter(adapterTimeEnd);
        spinnerTimeEnd.setOnItemSelectedListener(this);

        //spinner building
        spinnerBuilding = (Spinner)view.findViewById(R.id.building);
        ArrayAdapter<String>adapterBuilding = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,buildingArray);

        adapterBuilding.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBuilding.setAdapter(adapterBuilding);
        spinnerBuilding.setOnItemSelectedListener(this);

        //spinner block
        spinnerBlock = (Spinner)view.findViewById(R.id.block);


        ArrayAdapter<String>adapterBlock = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,blockArray);
        adapterBlock.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBlock.setAdapter(adapterBlock);



        spinnerBlock.setOnItemSelectedListener(this);
        spinnerBlock.setVisibility(View.GONE);



        //spinner color
        spinnerColor = (Spinner)view.findViewById(R.id.color);
        ArrayAdapter<String>adapterColor = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,colorArray);

        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(adapterColor);
        spinnerColor.setOnItemSelectedListener(this);


        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {



        if(parent == spinnerBuilding && parent.getItemAtPosition(position).equals("Main(A,B,C,D,E)")){
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