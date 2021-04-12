package com.example.ulstudenthandbook;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddModuleFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddModuleFrag extends Fragment implements AdapterView.OnItemSelectedListener{


    private EditText textModule,textRoom;
    private TextView textViewBlock,textViewRoom,textViewBuilding, errorTextView;
    private Spinner spinnerTimeStart,spinnerTimeEnd,spinnerBuilding,spinnerBlock,spinnerColor;
    private String day,module,timeStart,timeEnd,building,roomNo,colour;
    private int timeStartInt, timeEndInt, initialTimeStart;
    private TimetableInfo timetableInfo;
    private Button addButton;
    private ArrayList<TimetableEntry> selectedDayEntryArrayList;
    private boolean editMode = false;
    private TimetableEntry entryToAdd = new TimetableEntry();

    private static final String[] timeArray = {"","9:00", "9:30", "10:00", "10:30", "11:00"
            ,"11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00"
            , "15:30", "16:00", "16:30", "17:00", "17:30", "18:00"};

    private static final String[] buildingArray = {"","Analog(AD)","Computer Science(CS)","Engineering Research(ER)",
            "Foundation(F)","Glucksman Library(GL)","Health Sciences(HS)","Irish World Academy(IW)","Kemmy Business School(KB)",
            "Languages(LC)","Lonsdale(L)","Main(A,B,C,D,E)","Medical School(GEMS)","PESS(P)","Schrödinger(SR)","Schuman(S)","Tierney"};


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
        textViewRoom = view.findViewById(R.id.roomText);


        // module
        textModule = view.findViewById(R.id.module);
        textRoom = view.findViewById(R.id.room);
        errorTextView = view.findViewById(R.id.errorText);

        addButton = view.findViewById(R.id.submit);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEntry();
            }
        });

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


        //spinner color
        spinnerColor = (Spinner)view.findViewById(R.id.color);
        ArrayAdapter<String>adapterColor = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,colorArray);

        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(adapterColor);
        spinnerColor.setOnItemSelectedListener(this);

        timetableInfo = ReadTimetableInfoFromFile("timetablePrototype.txt");


        if(getArguments()!= null)
        {
            day = getArguments().getString("Day");
            module = getArguments().getString("module");
            timeStart = getArguments().getString("timeStart");
            timeEnd = getArguments().getString("timeEnd");
            building = getArguments().getString("building");
            roomNo = getArguments().getString("roomNo");
            colour = getArguments().getString("colour");
            editMode = getArguments().getBoolean("edit");

        }

        selectedDayEntryArrayList = selectDayEntries(day);
        for (TimetableEntry e : selectedDayEntryArrayList)
        {
            if (timeStart != null)
            {

            if ( e.timeStart == Integer.parseInt(timeStart))
            {
                initialTimeStart = e.timeStart;
                entryToAdd = e;
            }
            }
        }

        if( timeStart != null )
        {
            prepareFields();
        }

        return view;
    }

    private void saveEntry() {
        entryToAdd.module = textModule.getText().toString();
        entryToAdd.roomNo = textRoom.getText().toString();
        entryToAdd.building = spinnerBuilding.getSelectedItem().toString();
        entryToAdd.timeStart = setTimeWithSpinnerValue(spinnerTimeStart.getSelectedItem().toString());
        entryToAdd.timeEnd = setTimeWithSpinnerValue(spinnerTimeEnd.getSelectedItem().toString());
        entryToAdd.colour = spinnerColor.getSelectedItem().toString();

        //Checking user input
        if(entryToAdd.module.isEmpty() || entryToAdd.module.length() > 8 )
        {
            textModule.setError("Module has to have a value and be shorter than 8 characters");
            errorTextView.setText("Module has to have a value and be shorter than 8 characters");
            return;
        }

        if(entryToAdd.roomNo.isEmpty() || entryToAdd.roomNo.length() > 8 )
        {
            textRoom.setError("Module has to have a value and be shorter than 8 characters");
            errorTextView.setText("Module has to have a value and be shorter than 8 characters");
            return;
        }

        if(entryToAdd.building.length() < 1)
        {
            TextView errorText = (TextView)spinnerBuilding.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(getResources().getColor(R.color.Red));//just to highlight that this is an error
            errorText.setText("Select a building for the entry");//changes the selected item text to this
            errorTextView.setText("Select a building for the entry");
            return;
        }

        selectedDayEntryArrayList = selectDayEntries(day);
        //check times conflicts
        if (checkTimeAvailability( entryToAdd ) == false)
        {
            return;
        }

        selectedDayEntryArrayList.remove(entryToAdd);
        selectedDayEntryArrayList.add(entryToAdd);
        Collections.sort(selectedDayEntryArrayList);
        saveTimetableInfoToJSON("timetablePrototype.txt");
        Bundle bundle = new Bundle();
        bundle.putString("Day", day);
        Navigation.findNavController(getView()).navigate(R.id.action_addModuleFrag_to_editTimetableFrag, bundle);
    }

    private boolean checkTimeAvailability(TimetableEntry entryToAdd) {

        ArrayList<TimetableEntry> ArraylistClone = (ArrayList)selectedDayEntryArrayList.clone();

        if (editMode == true)
        {
            for (int i = 0 ; i < ArraylistClone.size(); i ++)
            {
                if (ArraylistClone.get(i).timeStart == initialTimeStart)
                {
                    ArraylistClone.remove(ArraylistClone.get(i));
                    i--;
                }
            }
        }

        if (entryToAdd.timeEnd <= entryToAdd.timeStart)
        {
            TextView errorText = (TextView)spinnerTimeEnd.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(getResources().getColor(R.color.Red));//just to highlight that this is an error
            errorText.setText("Class has to end after the start time");//changes the selected item text to this
            errorTextView.setText("Class has to end after the start time");
            return false;
        }

        if (editMode == true)
        {
            for (TimetableEntry e : ArraylistClone) {
                //x1 <= y2 && y1 <= x2
                if (e.timeStart < entryToAdd.timeEnd && entryToAdd.timeStart < e.timeEnd) {
                    TextView errorText = (TextView) spinnerTimeEnd.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(getResources().getColor(R.color.Red));//just to highlight that this is an error
                    errorText.setText("Time conflicts with another entry");//changes the selected item text to this
                    errorTextView.setText("Time conflicts with another entry");
                    return false;
                }
            }
        }
        else {
            for (TimetableEntry e : selectedDayEntryArrayList) {
                //x1 <= y2 && y1 <= x2
                if (e.timeStart < entryToAdd.timeEnd && entryToAdd.timeStart < e.timeEnd) {
                    TextView errorText = (TextView) spinnerTimeEnd.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(getResources().getColor(R.color.Red));//just to highlight that this is an error
                    errorText.setText("Time conflicts with another entry");//changes the selected item text to this
                    errorTextView.setText("Time conflicts with another entry");
                    return false;
                }
            }
        }

        return true;
    }

    private void prepareFields() {
        textModule.setText(module);
        textRoom.setText(roomNo);
        spinnerBuilding.setSelection(setSpinnerWithBuilding(building));
        spinnerTimeStart.setSelection(setSpinnerWithTime(timeStart));
        spinnerTimeEnd.setSelection(setSpinnerWithTime(timeEnd));
        spinnerColor.setSelection(setSpinnerWithColour(colour));

    }

    private int setSpinnerWithBuilding(String building) {
        int position = 0;

        if (building.equals("Analog(AD)"))
        {
            position = 1;
        }
        else if (building.equals("Computer Science(CS)"))
        {
            position = 2;
        }
        else if (building.equals("Engineering Research(ER)"))
        {
            position = 3;
        }
        else if (building.equals("Foundation(F)"))
        {
            position = 4;
        }
        else if (building.equals("Glucksman Library(GL)"))
        {
            position = 5;
        }
        else if (building.equals("Health Sciences(HS)"))
        {
            position = 6;
        }
        else if (building.equals("Irish World Academy(IW)"))
        {
            position = 7;
        }
        else if (building.equals("Kemmy Business School(KB)"))
        {
            position = 8;
        }
        else if (building.equals("Languages(LC)"))
        {
            position = 9;
        }
        else if (building.equals("Lonsdale(L)"))
        {
            position = 10;
        }
        else if (building.equals("Main(A,B,C,D,E)"))
        {
            position = 11;
        }
        else if (building.equals("Medical School(GEMS)"))
        {
            position = 12;
        }
        else if (building.equals("PESS(P)"))
        {
            position = 13;
        }
        else if (building.equals("Schrödinger(SR)"))
        {
            position = 14;
        }
        else if (building.equals("Schuman(S)"))
        {
            position = 15;
        }
        else if (building.equals("Tierney"))
        {
            position = 16;
        }

        return position;
    }

    private int setSpinnerWithColour(String colour) {
        //  private static final String[] colorArray ={"","Red","Blue","Green","Orange","Pink","Black","Purple"};
        int position = 0;
        if (colour.equals("Red"))
        {
           position = 1;
        }
        else if (colour.equals("Blue"))
        {
            position = 2;
        }
        else if (colour.equals("Green"))
        {
            position = 3;
        }
        else if (colour.equals("Orange"))
        {
            position = 4;
        }
        else if (colour.equals("Pink"))
        {
            position = 5;
        }
        else if (colour.equals("Black"))
        {
            position = 6;
        }
        else if (colour.equals("Purple"))
        {
            position = 7;
        }

        return position;
    }

    private ArrayList<TimetableEntry> selectDayEntries(String day) {
        if (day.equals("Monday"))
        {
            return timetableInfo.MondayEntries;
        }
        else if (day.equals("Tuesday"))
        {
            return timetableInfo.TuesdayEntries;
        }
        else if (day.equals("Wednesday"))
        {
            return timetableInfo.WednesdayEntries;
        }
        else if (day.equals("Thursday"))
        {
            return timetableInfo.ThursdayEntries;
        }
        else if (day.equals("Friday"))
        {
            return timetableInfo.FridayEntries;
        }
        else if (day.equals("Saturday"))
        {
            return timetableInfo.SaturdayEntries;
        }
        else if (day.equals("Sunday"))
        {
            return timetableInfo.SundayEntries;
        }

        return new ArrayList<TimetableEntry>();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        if(parent == spinnerBuilding)
        {
            building = parent.getItemAtPosition(position).toString();
        }
        else if (parent == spinnerColor)
        {
            colour = parent.getItemAtPosition(position).toString();
        }
        else if (parent == spinnerTimeStart)
        {
            timeStartInt = setTimeWithSpinnerValue(parent.getItemAtPosition(position).toString());
        }
        else if (parent == spinnerTimeEnd)
        {
            timeEndInt = setTimeWithSpinnerValue(parent.getItemAtPosition(position).toString());
        }

    }




    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    private void saveTimetableInfoToJSON(String filename) {
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

    private TimetableInfo ReadTimetableInfoFromFile(String filename) {
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

    private int setSpinnerWithTime(String time)
    {
        int position = 0;
        if (time.equals("900"))
        {
            position = 1;
        }
        else if (time.equals("950"))
        {
            position = 2;
        }
        else if (time.equals("1000"))
        {
            position = 3;
        }
        else if (time.equals("1050"))
        {
            position = 4;
        }
        else if (time.equals("1100"))
        {
            position = 5;
        }
        else if (time.equals("1150"))
        {
            position = 6;
        }
        else if (time.equals("1200"))
        {
            position = 7;
        }
        else if (time.equals("1250"))
        {
            position = 8;
        }
        else if (time.equals("1300"))
        {
            position = 9;
        }
        else if (time.equals("1350"))
        {
            position = 10;
        }
        else if (time.equals("1400"))
        {
            position = 11;
        }
        else if (time.equals("1450"))
        {
            position = 12;
        }
        else if (time.equals("1500"))
        {
            position = 13;
        }
        else if (time.equals("1550"))
        {
            position = 14;
        }
        else if (time.equals("1600"))
        {
            position = 15;
        }
        else if (time.equals("1650"))
        {
            position = 16;
        }
        else if (time.equals("1700"))
        {
            position = 17;
        }
        else if (time.equals("1750"))
        {
            position = 18;
        }
        else if (time.equals("1800"))
        {
            position = 19;
        }

        return position;
    }

    private int setTimeWithSpinnerValue(String time)
    {
        int timeinInt = 0;
        if (time.equals("9:00"))
        {
            timeinInt = 900;
        }
        else if (time.equals("9:30"))
        {
            timeinInt = 950;
        }
        else if (time.equals("10:00"))
        {
            timeinInt = 1000;
        }
        else if (time.equals("10:30"))
        {
            timeinInt = 1050;
        }
        else if (time.equals("11:00"))
        {
            timeinInt = 1100;
        }
        else if (time.equals("11:30"))
        {
            timeinInt = 1150;
        }
        else if (time.equals("12:00"))
        {
            timeinInt = 1200;
        }
        else if (time.equals("12:30"))
        {
            timeinInt = 1250;
        }
        else if (time.equals("13:00"))
        {
            timeinInt = 1300;
        }
        else if (time.equals("13:30"))
        {
            timeinInt = 1350;
        }
        else if (time.equals("14:00"))
        {
            timeinInt = 1400;
        }
        else if (time.equals("14:30"))
        {
            timeinInt = 1450;
        }
        else if (time.equals("15:00"))
        {
            timeinInt = 1500;
        }
        else if (time.equals("15:30"))
        {
            timeinInt = 1550;
        }
        else if (time.equals("16:00"))
        {
            timeinInt = 1600;
        }
        else if (time.equals("16:30"))
        {
            timeinInt = 1650;
        }
        else if (time.equals("17:00"))
        {
            timeinInt = 1700;
        }
        else if (time.equals("17:30"))
        {
            timeinInt = 1750;
        }
        else if (time.equals("18:00"))
        {
            timeinInt = 1800;
        }

        return timeinInt;
    }


}