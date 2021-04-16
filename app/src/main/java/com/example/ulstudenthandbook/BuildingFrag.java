package com.example.ulstudenthandbook;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuildingFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuildingFrag extends Fragment implements AdapterView.OnItemSelectedListener{

    private ImageView image;
    private Spinner buildingName,buildingCode;
    private Button button;


    Bundle bundle = new Bundle();


    private static final String[] buildingNameArray = {"","Analog","Computer Science","Engineering Research",
            "Foundation","Glucksman Library","Health Sciences","Irish World Academy","Kemmy Business School",
            "Languages","Lonsdale","Main","Medical School","PESS","Schrödinger","Schuman","Tierney"};

    private static final String[] buildingCodeArray = {"","AD","CS","ER",
            "F","GL","HS","IW","KB",
            "LC","L","A,B,C,D,E","GEMS","P","SR","S"};


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BuildingFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuildingFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static BuildingFrag newInstance(String param1, String param2) {
        BuildingFrag fragment = new BuildingFrag();
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
        View view = inflater.inflate(R.layout.fragment_building, container, false);

        //spinner Building name
        buildingName = (Spinner)view.findViewById(R.id.buildingName);
        ArrayAdapter<String> adapterBuildingName = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,buildingNameArray);

        adapterBuildingName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buildingName.setAdapter(adapterBuildingName);
        buildingName.setOnItemSelectedListener(this);

        //spinner Building code
        buildingCode = (Spinner)view.findViewById(R.id.buildingCode);
        ArrayAdapter<String> adapterBuildingCode = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,buildingCodeArray);

        adapterBuildingCode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buildingCode.setAdapter(adapterBuildingCode);
        buildingCode.setOnItemSelectedListener(this);


        //Image
        image = (ImageView) view.findViewById(R.id.buildingImage);
        ImageView buildingImage = (ImageView) view.findViewById(R.id.buildingImage);
        buildingImage.setImageResource(R.drawable.book);


        //Button
        button = view.findViewById(R.id.button);




        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent == buildingName && position != 16){
            buildingCode.setSelection(position);

        }else if(parent == buildingCode){
            buildingName.setSelection(position);
        }
        if(position == 0)
        {
            image.setImageResource(R.drawable.book);
        }
        if(position == 1)
        {
            image.setImageResource(R.drawable.analog);
            bundle.putString("marker", "analogDevicesBuildingMarker");
        }
        else if(position == 2){
            image.setImageResource(R.drawable.computer);
            bundle.putString("marker", "csBuildingMarker");
        }
        else if(position == 3){
            image.setImageResource(R.drawable.engineering);
            bundle.putString("marker", "engineeringBuildingMarker");
        }
        else if(position == 4){
            image.setImageResource(R.drawable.foundation);
            bundle.putString("marker", "concertHallMarker");
        }
        else if(position == 5){
            image.setImageResource(R.drawable.library);
            bundle.putString("marker", "libraryMarker");
        }
        else if(position == 6){
            image.setImageResource(R.drawable.health);
            bundle.putString("marker", "healthScienceBuildingMarker");
        }
        else if(position == 7){
            image.setImageResource(R.drawable.irish);
            bundle.putString("marker", "irishWorldAcademy");
        }
        else if(position == 8){
            image.setImageResource(R.drawable.kemmy);
            bundle.putString("marker", "kemmyBusinessMarker");
        }
        else if(position == 9){
            image.setImageResource(R.drawable.languages);
            bundle.putString("marker", "languagesBuildingMarker");
        }
        else if(position == 10){
            image.setImageResource(R.drawable.lonsdale);
            bundle.putString("marker", "lonsdaleBuildingMarker");
        }
        else if(position == 11){
            image.setImageResource(R.drawable.main);
            bundle.putString("marker", "mainBuildingMarker");
        }
        else if(position == 12){
            image.setImageResource(R.drawable.medical);
            bundle.putString("marker", "schoolOfMedicineBuildingMarker");
        }
        else if(position == 13){
            image.setImageResource(R.drawable.pess);
            bundle.putString("marker", "pessBuildingMarker");
        }
        else if(position == 14){
            image.setImageResource(R.drawable.schrodinger);
            bundle.putString("marker", "schrodingerBuildingMarker");
        }
        else if(position == 15){
            image.setImageResource(R.drawable.schuman);
            bundle.putString("marker", "schumannBuildingMarker");
        }
        else if(parent == buildingName && position == 16){
            image.setImageResource(R.drawable.tierney);
            bundle.putString("marker", "leroBuildingMarker");
        }
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (position != 0)
                {
                    Navigation.findNavController(view).navigate(R.id.action_buildingFrag_to_mapsFrag, bundle);
                }
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}