package com.example.ulstudenthandbook;

public class TimetableEntry implements Comparable<TimetableEntry> {

    //Single entry that gets added to one of the TimetableInfo Arraylists
    public String module;
    public int timeStart;
    public int timeEnd;
    public String building;
    public String roomNo;
    public String colour;

    TimetableEntry()
    {

    }

    @Override
    public int compareTo(TimetableEntry e)
    {
        return this.timeStart-e.timeStart;
    }


}
