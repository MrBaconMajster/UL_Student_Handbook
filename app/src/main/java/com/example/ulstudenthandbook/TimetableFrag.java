package com.example.ulstudenthandbook;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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
    private int dayDisplayed;

    private TextView measureOfHeight;
    private TextView measureOfHeight2;
    private TextView dayIndicator;
    private TextView entry9;
    private TextView entry10;
    private TextView entry11;
    private TextView entry12;
    private TextView entry13;
    private TextView entry14;
    private TextView entry15;
    private TextView entry16;
    private TextView entry17;
    private TextView entry93;
    private TextView entry103;
    private TextView entry113;
    private TextView entry123;
    private TextView entry133;
    private TextView entry143;
    private TextView entry153;
    private TextView entry163;
    private TextView entry173;

    private ArrayList<TextView> textViewArray = new ArrayList<>();

    int entryDefaultHeight;

    private ImageView leftArrow;
    private ImageView rightArrow;

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

        measureOfHeight = view.findViewById(R.id.measureofheight);
        measureOfHeight2 = view.findViewById(R.id.measureofheight2);
        dayIndicator = view.findViewById(R.id.dayIndicator);
        entry9 = view.findViewById(R.id.entry9);
        entry93 = view.findViewById(R.id.entry93);
        entry10 = view.findViewById(R.id.entry10);
        entry103 = view.findViewById(R.id.entry103);
        entry11 = view.findViewById(R.id.entry11);
        entry113 = view.findViewById(R.id.entry113);
        entry12 = view.findViewById(R.id.entry12);
        entry123 = view.findViewById(R.id.entry123);
        entry13 = view.findViewById(R.id.entry13);
        entry133 = view.findViewById(R.id.entry133);
        entry14 = view.findViewById(R.id.entry14);
        entry143 = view.findViewById(R.id.entry143);
        entry15 = view.findViewById(R.id.entry15);
        entry153 = view.findViewById(R.id.entry153);
        entry16 = view.findViewById(R.id.entry16);
        entry163 = view.findViewById(R.id.entry163);
        entry17 = view.findViewById(R.id.entry17);
        entry173 = view.findViewById(R.id.entry173);

        textViewArray.add(entry9);
        textViewArray.add(entry93);
        textViewArray.add(entry10);
        textViewArray.add(entry103);
        textViewArray.add(entry11);
        textViewArray.add(entry113);
        textViewArray.add(entry12);
        textViewArray.add(entry123);
        textViewArray.add(entry13);
        textViewArray.add(entry133);
        textViewArray.add(entry14);
        textViewArray.add(entry143);
        textViewArray.add(entry15);
        textViewArray.add(entry153);
        textViewArray.add(entry16);
        textViewArray.add(entry163);
        textViewArray.add(entry17);
        textViewArray.add(entry173);

        entryDefaultHeight = entry9.getMeasuredHeight();

        leftArrow = view.findViewById(R.id.leftArrow);
        rightArrow = view.findViewById(R.id.rightArrow);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDay("left");
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDay("right");
            }
        });

        //Displays current day
        Calendar calendar = Calendar.getInstance();
        String dayLongName = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        //Sunday == 1 , Saturday == 7 (1-7)
        dayDisplayed = calendar.get(Calendar.DAY_OF_WEEK);
        dayIndicator.setText(dayLongName);


        // Sets spinner button
        final String[] modeArray = {"Day", "Week"};
        timetableViewModeSpinner = view.findViewById(R.id.TimetableViewModeSpinner);

        ArrayAdapter<String> adapterTimeStart = new ArrayAdapter<String>
                (getActivity().getBaseContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        modeArray);

        adapterTimeStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timetableViewModeSpinner.setAdapter(adapterTimeStart);
        timetableViewModeSpinner.setOnItemSelectedListener(this);


        //Reads Timetable info from json
        timetableInfo = ReadTimetableInfoFromFile("Example4.txt");
        ScreenText = view.findViewById(R.id.textView3);


        //Sample entries !!!TO BE DELETED LATER!!!
        TimetableEntry entry = new TimetableEntry();
        entry.timeStart = 900;
        entry.timeEnd = 1100;
        entry.building = "CS";
        entry.roomNo = "G001";
        entry.module = "CS4457";
        entry.colour = "Purple";

        timetableInfo.SundayEntries.add(entry);

        TimetableEntry entry2 = new TimetableEntry();
        entry2.timeStart = 1500;
        entry2.timeEnd = 1650;
        entry2.building = "CS";
        entry2.roomNo = "G001";
        entry2.module = "CS4457";
        entry2.colour = "Green";

        timetableInfo.SundayEntries.add(entry2);

        TimetableEntry entry3 = new TimetableEntry();
        entry3.timeStart = 1200;
        entry3.timeEnd = 1300;
        entry3.building = "CS";
        entry3.roomNo = "G001";
        entry3.module = "CS4457";
        entry3.colour = "Purple";

        timetableInfo.SaturdayEntries.add(entry3);

        TimetableEntry entry4 = new TimetableEntry();
        entry4.timeStart = 900;
        entry4.timeEnd = 1000;
        entry4.building = "CS";
        entry4.roomNo = "G001";
        entry4.module = "CS4457";
        entry4.colour = "Blue";

        timetableInfo.ThursdayEntries.add(entry4);

        TimetableEntry entry5 = new TimetableEntry();
        entry5.timeStart = 1600;
        entry5.timeEnd = 1700;
        entry5.building = "CS";
        entry5.roomNo = "G001";
        entry5.module = "CS4457";
        entry5.colour = "Red";

        timetableInfo.FridayEntries.add(entry5);

        saveTimetableInfoToJSON("Example3.txt"); //Save json

        // TimetableInfo timetableInfo = ReadTimetableInfoFromFile("Example3.txt");  //Read JSON

        updateTimetable(dayDisplayed);

        return view;
    }

    private void updateTimetable(int dayDisplayed) {

        entryDefaultHeight = measureOfHeight.getMeasuredHeight()+measureOfHeight2.getMeasuredHeight();

        for ( TextView entry : textViewArray)
        {
            entry.setText("");
            entry.setBackgroundResource(R.color.Grey);
            entry.getLayoutParams().height = entryDefaultHeight;
        }

        if (dayDisplayed == 1)
        {
            for ( TimetableEntry e : timetableInfo.SundayEntries)
            {
                updateEntry(e);
            }
        }
        else if (dayDisplayed == 2)
        {
            for ( TimetableEntry e : timetableInfo.MondayEntries)
            {
                updateEntry(e);
            }
        }
        else if (dayDisplayed == 3)
        {
            for ( TimetableEntry e : timetableInfo.TuesdayEntries)
            {
                updateEntry(e);
            }
        }
        else if (dayDisplayed == 4)
        {
            for ( TimetableEntry e : timetableInfo.WednesdayEntries)
            {
                updateEntry(e);
            }
        }
        else if (dayDisplayed == 5)
        {
            for ( TimetableEntry e : timetableInfo.ThursdayEntries)
            {
                updateEntry(e);
            }
        }
        else if (dayDisplayed == 6)
        {
            for ( TimetableEntry e : timetableInfo.FridayEntries)
            {
                updateEntry(e);
            }
        }
        else if (dayDisplayed == 7)
        {
            for ( TimetableEntry e : timetableInfo.SaturdayEntries)
            {
                updateEntry(e);
            }
        }
    }

    private void updateEntry(TimetableEntry e)
    {
        int startingTime = e.timeStart;
        TextView entrySlot = findEntryWithTime(startingTime);
        entrySlot.setText( e.module + "\n" + e.building + e.roomNo);
        int slotsTaken = calculateSlotsTaken(e);
        int indexOfE = textViewArray.indexOf(entrySlot);
        entrySlot.getLayoutParams().height = entryDefaultHeight*slotsTaken;
        for (int i=1; i<slotsTaken ; i++)
        {
            textViewArray.get(indexOfE+i).getLayoutParams().height= 0;
        }
        changeColour(entrySlot , e.colour);
    }

    private int calculateSlotsTaken(TimetableEntry e)
    {
        int start = e.timeStart;
        int end = e.timeEnd;

        return (end-start)/50;
    }

    private void changeColour(TextView entrySlot, String colour) {

        if(colour.equals("Red"))
        {
            entrySlot.setBackgroundResource(R.drawable.entry_drawable_red);
        }
        else if (colour.equals("Blue"))
        {
            entrySlot.setBackgroundResource(R.drawable.entry_drawable_blue);
        }
        else if (colour.equals("Green"))
        {
            entrySlot.setBackgroundResource(R.drawable.entry_drawable_green);
        }
        else if (colour.equals("Orange"))
        {
            entrySlot.setBackgroundResource(R.drawable.entry_drawable_orange);
        }
        else if (colour.equals("Pink"))
        {
            entrySlot.setBackgroundResource(R.drawable.entry_drawable_pink);
        }
        else if (colour.equals("Black"))
        {
            entrySlot.setBackgroundResource(R.drawable.entry_drawable_black);
        }
        else if (colour.equals("Purple"))
        {
            entrySlot.setBackgroundResource(R.drawable.entry_drawable_purple);
        }
        else
        {
            entrySlot.setBackgroundResource(R.color.Grey);
        }
    }


    private TextView findEntryWithTime(int startingTime) {
        TextView entrySlot = entry9;
        if(startingTime == 900)
        {
            entrySlot = entry9;
        }
        else if (startingTime == 1000)
        {
            entrySlot = entry10;
        }
        else if (startingTime == 1100)
        {
            entrySlot = entry11;
        }
        else if (startingTime == 1200)
        {
            entrySlot = entry12;
        }
        else if (startingTime == 1300)
        {
            entrySlot = entry13;
        }
        else if (startingTime == 1400)
        {
            entrySlot = entry14;
        }
        else if (startingTime == 1500)
        {
            entrySlot = entry15;
        }
        else if (startingTime == 1600)
        {
            entrySlot = entry16;
        }
        else if (startingTime == 1700)
        {
            entrySlot = entry17;
        }
        else if (startingTime == 950)
        {
            entrySlot = entry93;
        }
        else if (startingTime == 1050)
        {
            entrySlot = entry103;
        }
        else if (startingTime == 1150)
        {
            entrySlot = entry113;
        }
        else if (startingTime == 1250)
        {
            entrySlot = entry123;
        }
        else if (startingTime == 1350)
        {
            entrySlot = entry133;
        }
        else if (startingTime == 1450)
        {
            entrySlot = entry143;
        }
        else if (startingTime == 1550)
        {
            entrySlot = entry153;
        }
        else if (startingTime == 1650)
        {
            entrySlot = entry163;
        }
        else if (startingTime == 1750)
        {
            entrySlot = entry173;
        }

        return entrySlot ;
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void changeDay(String direction)
    {
        if (direction.equals("left"))
        {
            if(dayDisplayed == 1)
            {
                dayDisplayed = 7;
            }
            else dayDisplayed--;
        }
        else
        {
            if(dayDisplayed == 7)
            {
                dayDisplayed = 1;
            }
            else dayDisplayed++;
        }

        //Sunday == 1 , Saturday == 7 (1-7)
        String[] DaysArray = new DateFormatSymbols().getInstance().getWeekdays();
        dayIndicator.setText(DaysArray[dayDisplayed]);
        updateTimetable(dayDisplayed);
    }

}