package com.example.ulstudenthandbook;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimetableFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimetableFrag extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2-";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TimetableInfo timetableInfo;
    private TextView ScreenText;
    private Spinner timetableViewModeSpinner;

    public TimetableFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimetableFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static TimetableFrag newInstance(String param1, String param2) {
        TimetableFrag fragment = new TimetableFrag();
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
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);

        //WORKING ON SPINNER BUTTON

       //final String[] modeArray ={"Day","Week"};
       //timetableViewModeSpinner = view.findViewById(R.id.TimetableViewModeSpinner);
       //ArrayAdapter<String> adapterTimeStart = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,modeArray);

       //adapterTimeStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       //timetableViewModeSpinner.setAdapter(adapterTimeStart);
       //timetableViewModeSpinner.setOnItemSelectedListener(this);


        //Everything below in this function is temporary to check if file loading/saving works
        timetableInfo = ReadTimetableInfoFromFile("Example3.txt");
        ScreenText = view.findViewById(R.id.textView3);

        TimetableEntry entry = new TimetableEntry();
        timetableInfo.MondayEntries.add(entry);

        saveTimetableInfoToJSON("Example3.txt");

        TimetableInfo timetableInfo = ReadTimetableInfoFromFile("Example3.txt");

        return view;
    }

    private void saveTimetableInfoToJSON(String filename)
    {
        String TimetableInformationJSONString = new Gson().toJson(timetableInfo);

        //Write JSON file
        FileOutputStream outputStream;

        try {
            outputStream = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(TimetableInformationJSONString.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TimetableInfo ReadTimetableInfoFromFile(String filename)
    {
        TimetableInfo t = new TimetableInfo();
        FileInputStream fis = null;
        try {
            fis = getActivity().openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            String json = sb.toString();
            Gson gson = new Gson();
            t = gson.fromJson(json, TimetableInfo.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return t;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}