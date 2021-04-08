package com.example.ulstudenthandbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainBuildingMapFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainBuildingMapFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView mapZoomViewG;
    private com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView mapZoomView0;
    private com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView mapZoomViewM;
    private com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView mapZoomView1;
    private com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView mapZoomView2;
    private com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView mapZoomView3;

    private TextView textViewG;
    private TextView textView0;
    private TextView textViewM;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;




    public MainBuildingMapFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainBuildingMapFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static MainBuildingMapFrag newInstance(String param1, String param2) {
        MainBuildingMapFrag fragment = new MainBuildingMapFrag();
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
        View view = inflater.inflate(R.layout.fragment_main_building_map, container, false);

        mapZoomViewG = view.findViewById(R.id.mainBuildingMapZoomViewG);
        mapZoomView0 = view.findViewById(R.id.mainBuildingMapZoomView0);
        mapZoomViewM = view.findViewById(R.id.mainBuildingMapZoomViewM);
        mapZoomView1 = view.findViewById(R.id.mainBuildingMapZoomView1);
        mapZoomView2 = view.findViewById(R.id.mainBuildingMapZoomView2);
        mapZoomView3 = view.findViewById(R.id.mainBuildingMapZoomView3);

        textViewG = view.findViewById(R.id.TextViewG);
        textView0 = view.findViewById(R.id.TextView0);
        textViewM = view.findViewById(R.id.TextViewM);
        textView1 = view.findViewById(R.id.TextView1);
        textView2 = view.findViewById(R.id.TextView2);
        textView3 = view.findViewById(R.id.TextView3);

        textViewG.setBackgroundResource(R.color.Green);

        textViewG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisible("G");
            }
        });

        textView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisible("0");
            }
        });

        textViewM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisible("M");
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisible("1");
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisible("2");
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisible("3");
            }
        });

        return view;
    }

    private void setVisible(String floor)
    {
        textViewG.setBackgroundResource(R.color.darkGreen);
        textView0.setBackgroundResource(R.color.darkGreen);
        textViewM.setBackgroundResource(R.color.darkGreen);
        textView1.setBackgroundResource(R.color.darkGreen);
        textView2.setBackgroundResource(R.color.darkGreen);
        textView3.setBackgroundResource(R.color.darkGreen);

        mapZoomViewG.setVisibility(View.INVISIBLE);
        mapZoomView0.setVisibility(View.INVISIBLE);
        mapZoomViewM.setVisibility(View.INVISIBLE);
        mapZoomView1.setVisibility(View.INVISIBLE);
        mapZoomView2.setVisibility(View.INVISIBLE);
        mapZoomView3.setVisibility(View.INVISIBLE);

        if(floor.equals("G"))
        {
            textViewG.setBackgroundResource(R.color.Green);
            mapZoomViewG.setVisibility(View.VISIBLE);
        }
        else if(floor.equals("0"))
        {
            textView0.setBackgroundResource(R.color.Green);
            mapZoomView0.setVisibility(View.VISIBLE);
        }
        else if(floor.equals("M"))
        {
            textViewM.setBackgroundResource(R.color.Green);
            mapZoomViewM.setVisibility(View.VISIBLE);
        }
        else if(floor.equals("1"))
        {
            textView1.setBackgroundResource(R.color.Green);
            mapZoomView1.setVisibility(View.VISIBLE);
        }
        else if(floor.equals("2"))
        {
            textView2.setBackgroundResource(R.color.Green);
            mapZoomView2.setVisibility(View.VISIBLE);
        }
        else if(floor.equals("3"))
        {
            textView3.setBackgroundResource(R.color.Green);
            mapZoomView3.setVisibility(View.VISIBLE);
        }

    }
}