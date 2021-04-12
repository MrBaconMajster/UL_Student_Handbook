package com.example.ulstudenthandbook;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditTimetableFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditTimetableFrag extends Fragment implements AdapterView.OnItemSelectedListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public EditTimetableFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditTimetableFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static EditTimetableFrag newInstance(String param1, String param2) {
        EditTimetableFrag fragment = new EditTimetableFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private LinearLayout entryCol;
    private LinearLayout deleteCol;
    private TimetableInfo timetableInfo;
    private String day;
    private TextView addEntryBtn;
    Spinner daySpinner;

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
        View view = inflater.inflate(R.layout.fragment_edit_timetable, container, false);

        entryCol = view.findViewById(R.id.entriesColumn);
        deleteCol = view.findViewById(R.id.deleteColumn);
        addEntryBtn = view.findViewById(R.id.addEntryButton);

        addEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Day", day);
                Navigation.findNavController(v).navigate(R.id.action_editTimetableFrag_to_addModuleFrag, bundle);
            }
        });

        timetableInfo = ReadTimetableInfoFromFile("timetablePrototype.txt");

        // Sets spinner button
        final String[] modeArray = {"Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        daySpinner = view.findViewById(R.id.daySpinner);

        ArrayAdapter<String> adapterDay = new ArrayAdapter<String>
                (getActivity().getBaseContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        modeArray);

        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(adapterDay);

        daySpinner.setOnItemSelectedListener(this);
        daySpinner.setSelection(0);

        if (getArguments() == null) {
            day = "Monday";
            displayEntries(day);
        }
        else
        {
            day = getArguments().getString("Day");
            daySpinner.setSelection(dayStringtoSpinnerPos(day));
        }
        return view;
    }

    private void displayEntries(String day) {
        entryCol.removeAllViews();
        deleteCol.removeAllViews();
        timetableInfo = ReadTimetableInfoFromFile("timetablePrototype.txt");

        if (day.equals("Monday"))
        {
            for (TimetableEntry e : timetableInfo.MondayEntries) {
                addEntryToColumn(e);
            }
        }
        else if (day.equals("Tuesday"))
        {
            for (TimetableEntry e : timetableInfo.TuesdayEntries) {
                addEntryToColumn(e);
            }
        }
        else if (day.equals("Wednesday"))
        {
            for (TimetableEntry e : timetableInfo.WednesdayEntries) {
                addEntryToColumn(e);
            }
        }
        else if (day.equals("Thursday"))
        {
            for (TimetableEntry e : timetableInfo.ThursdayEntries) {
                addEntryToColumn(e);
            }
        }
        else if (day.equals("Friday"))
        {
            for (TimetableEntry e : timetableInfo.FridayEntries) {
                addEntryToColumn(e);
            }
        }
        else if (day.equals("Saturday"))
        {
            for (TimetableEntry e : timetableInfo.SaturdayEntries) {
                addEntryToColumn(e);
            }
        }
        else if (day.equals("Sunday"))
        {
            for (TimetableEntry e : timetableInfo.SundayEntries) {
                addEntryToColumn(e);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView selectedText = (TextView) view;
        if(selectedText != null)
        {
            selectedText.setTextColor(Color.WHITE);
        }

            if (position == 0) {
                displayEntries("Monday");
                day = "Monday";
            } else if (position == 1) {
                displayEntries("Tuesday");
                day = "Tuesday";
            } else if (position == 2) {
                displayEntries("Wednesday");
                day = "Wednesday";
            } else if (position == 3) {
                displayEntries("Thursday");
                day = "Thursday";
            } else if (position == 4) {
                displayEntries("Friday");
                day = "Friday";
            } else if (position == 5) {
                displayEntries("Saturday");
                day = "Saturday";
            } else if (position == 6) {
                displayEntries("Sunday");
                day = "Sunday";
            }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

    private void addEntryToColumn(TimetableEntry e)
    {
        TextView tv = new TextView(getActivity());
        tv.setText(intToTimeDisplay(e.timeStart)+"-"+intToTimeDisplay(e.timeEnd));

        changeBackgroundColour(e,tv);

        tv.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,150);
        layoutParams.setMargins(6,6,6,6);
        tv.setLayoutParams(layoutParams);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change to edit entry
                Integer timeStartINT = new Integer(e.timeStart);
                Integer timeEndINT = new Integer(e.timeEnd);

                Bundle bundle = new Bundle();
                bundle.putString("Day", day);
                bundle.putString("module", e.module);
                bundle.putString("timeStart", timeStartINT.toString());
                bundle.putString("timeEnd", timeEndINT.toString());
                bundle.putString("building", e.building);
                bundle.putString("roomNo", e.roomNo);
                bundle.putString("colour", e.colour);
                bundle.putBoolean("edit", true);

                Navigation.findNavController(v).navigate(R.id.action_editTimetableFrag_to_addModuleFrag, bundle);
            }
        });

        entryCol.addView(tv);

        TextView line = new TextView(getActivity());
        line.setBackgroundResource(R.color.white);
        line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                1));

        entryCol.addView(line);

        TextView deleteBtn = new TextView(getActivity());
        deleteBtn.setText("X");
        deleteBtn.setTextSize(20);
        deleteBtn.setTextColor(getResources().getColor(R.color.Red));
        deleteBtn.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,150);
        layoutParams2.setMargins(6,6,6,6);
        deleteBtn.setLayoutParams(layoutParams2);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change to delete entry
                getEntryListOfDay(day).remove(e);
                saveTimetableInfoToJSON("timetablePrototype.txt");
                displayEntries(day);

            }
        });

        deleteCol.addView(deleteBtn);

        TextView line2 = new TextView(getActivity());
        line2.setBackgroundResource(R.color.white);
        line2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,1));

        deleteCol.addView(line2);
    }

    private ArrayList<TimetableEntry> getEntryListOfDay(String Day)
    {
        if (Day.equals("Monday"))
        {
            return timetableInfo.MondayEntries;
        }
        else if (Day.equals("Tuesday"))
        {
            return timetableInfo.TuesdayEntries;
        }
        else if (Day.equals("Wednesday"))
        {
            return timetableInfo.WednesdayEntries;
        }
        else if (Day.equals("Thursday"))
        {
            return timetableInfo.ThursdayEntries;
        }
        else if (Day.equals("Friday"))
        {
            return timetableInfo.FridayEntries;
        }
        else if (Day.equals("Saturday"))
        {
            return timetableInfo.SaturdayEntries;
        }
        else if (Day.equals("Sunday"))
        {
            return timetableInfo.SundayEntries;
        }

        return new ArrayList<TimetableEntry>();
    }

    private void changeBackgroundColour(TimetableEntry e , TextView tv)
    {
        String desiredColour = e.colour;
            if (desiredColour.equals("Red"))
            {
                tv.setBackgroundResource(R.color.Red);
            }
            else if (desiredColour.equals("Blue"))
            {
                tv.setBackgroundResource(R.color.Blue);
            }
            else if (desiredColour.equals("Green"))
            {
                tv.setBackgroundResource(R.color.Green);
            }
            else if (desiredColour.equals("Orange"))
            {
                tv.setBackgroundResource(R.color.Orange);
            }
            else if (desiredColour.equals("Pink"))
            {
                tv.setBackgroundResource(R.color.Pink);
            }
            else if (desiredColour.equals("Black"))
            {
                tv.setBackgroundResource(R.color.Black);
            }
            else if (desiredColour.equals("Purple"))
            {
                tv.setBackgroundResource(R.color.Purple);
            }
            else if (desiredColour.equals("Grey"))
            {
                tv.setBackgroundResource(R.color.Grey);
            }
    }

    String intToTimeDisplay(int t)
    {
        String s = "";

        if (t == 900)
        {
            s = "9:00";
        }
        else if (t == 950)
        {
            s = "9:30";
        }
        else if (t == 1000)
        {
            s = "10:00";
        }
        else if (t == 1050)
        {
            s = "10:30";
        }
        else if (t == 1100)
        {
            s = "11:00";
        }
        else if (t == 1150)
        {
            s = "11:30";
        }
        else if (t == 1200)
        {
            s = "12:00";
        }
        else if (t == 1250)
        {
            s = "12:30";
        }
        else if (t == 1300)
        {
            s = "13:00";
        }
        else if (t == 1350)
        {
            s = "13:30";
        }
        else if (t == 1400)
        {
            s = "14:00";
        }
        else if (t == 1450)
        {
            s = "14:30";
        }
        else if (t == 1500)
        {
            s = "15:00";
        }
        else if (t == 1550)
        {
            s = "15:30";
        }
        else if (t == 1600)
        {
            s = "16:00";
        }
        else if (t == 1650)
        {
            s = "16:30";
        }
        else if (t == 1700)
        {
            s = "17:00";
        }
        else if (t == 1750)
        {
            s = "17:30";
        }
        else if (t == 1800)
        {
            s = "18:00";
        }

        return s;
    }

    private int dayStringtoSpinnerPos(String day)
    {
        int position = 0;
        if (day.equals("Monday"))
        {
            position = 0;
        }
        else if (day.equals("Tuesday"))
        {
            position = 1;
        }
        else if (day.equals("Wednesday"))
        {
            position = 2;
        }
        else if (day.equals("Thursday"))
        {
            position = 3;
        }
        else if (day.equals("Friday"))
        {
            position = 4;
        }
        else if (day.equals("Saturday"))
        {
            position = 5;
        }
        else if (day.equals("Sunday"))
        {
            position = 6;
        }

        return position;
    }
}
