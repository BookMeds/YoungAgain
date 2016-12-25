package com.bookmeds.youngagain;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;

public class TimeTableActivity extends AppCompatActivity {
    private ArrayList<String> list;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        GridView gridView = (GridView) findViewById(R.id.time_table_list);
        list = new ArrayList<>();

        list.add("6:30 : Wake UP");
        list.add("7:00: Breakfast");
        list.add("8:00 : Exercise");
        list.add("9:00 : Do any work");
        list.add("10:00 : Coffee");
        list.add("12:30 : Lunch");
        list.add("13:30 : House Work");
        list.add("14:00 : Nap");
        list.add("17:00 : Finish work");
        list.add("19:30 : Socialise");
        list.add("21:00 : Switch off TV");
        list.add("21:30 : Read/Do a crossword");
        list.add("22:00 : Sleep");

        adapter = new ArrayAdapter<>(this, R.layout.time_table_item, list);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void addSchedule(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.add_schedule, null);
        final EditText timeView = (EditText) view1.findViewById(R.id.add_time);
        final EditText textView = (EditText) view1.findViewById(R.id.add_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view1)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (timeView.getText().length() > 0 && textView.getText().length() > 0) {
                            list.add(timeView.getText() + ": " + textView.getText());
                            Collections.sort(list);
                            adapter.notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("Cancel", null);
        builder.create().show();
    }
}
