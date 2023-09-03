package com.example.car_guide_ray;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    int currentYear = 0;
    int currentMonth = 0;
    int currentDay = 0;

    int daysIndex = 0;

    int[] days;
    int[] months;
    int[] years;

    List<String> calendarStrings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView calendarView = findViewById(R.id.calendar_view);

        calendarStrings = new ArrayList<>();

        int numberOfDays = 2000;

        days = new int[numberOfDays];
        months = new int[numberOfDays];
        years = new int[numberOfDays];

        readInfo();

        EditText textInput = findViewById(R.id.textInput);
        View dayContent = findViewById(R.id.dayContent);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                currentYear = year;
                currentMonth = month;
                currentDay = dayOfMonth;

                if(dayContent.getVisibility() == View.GONE){
                    dayContent.setVisibility(View.VISIBLE);
                }

                for(int i = 0; i < daysIndex; i++){
                    if(years[i] == currentYear){
                        for(int j = 0; j < daysIndex; j++){
                            if(days[j] == currentDay){
                                for(int k = 0; k < daysIndex; k++){
                                    if(months[k] == currentMonth && days[k] == currentDay && years[k] == currentYear){
                                        textInput.setText(calendarStrings.get(k));
                                    }
                                }
                            }
                        }
                    }
                }
                textInput.setText("");
            }
        });


        Button saveTextButton = findViewById(R.id.saveTextButton);

        saveTextButton.setOnClickListener(v1 -> {
            days[daysIndex] = currentDay;
            months[daysIndex] = currentMonth;
            years[daysIndex] = currentYear;
            calendarStrings.add(daysIndex, textInput.getText().toString());
            textInput.setText("");
            daysIndex++;
            dayContent.setVisibility(View.GONE);
        });

        Button todayButton = findViewById(R.id.todayButton);

        todayButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                calendarView.setDate(calendarView.getDate());
            }
        });

    }

    public void onPause(){
        super.onPause();
        saveInfo();
    }

    private void saveInfo(){
        File file = new File(this.getFilesDir(), "saved");
        File daysFile = new File(this.getFilesDir(), "days");
        File monthsFile = new File(this.getFilesDir(), "months");
        File yearsFile = new File(this.getFilesDir(), "years");

        try{
            FileOutputStream fOut = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fOut));

            FileOutputStream fOutDays = new FileOutputStream(daysFile);
            BufferedWriter bwDays = new BufferedWriter(new OutputStreamWriter(fOutDays));

            FileOutputStream fOutMonths = new FileOutputStream(monthsFile);
            BufferedWriter bwMonths = new BufferedWriter(new OutputStreamWriter(fOutMonths));

            FileOutputStream fOutYears = new FileOutputStream(yearsFile);
            BufferedWriter bwYears = new BufferedWriter(new OutputStreamWriter(fOutYears));

            for(int i = 0; i < daysIndex; i++){
                bw.write(calendarStrings.get(i));
                bw.newLine();
                bwDays.write(days[i]);
                bwMonths.write(months[i]);
                bwYears.write(years[i]);
            }
            bw.close();
            fOut.close();
            bwDays.close();
            fOutDays.close();
            bwMonths.close();
            fOutMonths.close();
            bwYears.close();
            fOutYears.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void readInfo(){
        File file = new File(this.getFilesDir(), "saved");
        File daysFile = new File(this.getFilesDir(), "days");
        File monthsFile = new File(this.getFilesDir(), "months");
        File yearsFile = new File(this.getFilesDir(), "years");

        if(!file.exists()){
            return;
        }
        try{
            FileInputStream is = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            FileInputStream isDays = new FileInputStream(daysFile);
            BufferedReader readerDays = new BufferedReader(new InputStreamReader(isDays));
            FileInputStream isMonths = new FileInputStream(monthsFile);
            BufferedReader readerMonths = new BufferedReader(new InputStreamReader(isMonths));
            FileInputStream isYears = new FileInputStream(yearsFile);
            BufferedReader readerYears = new BufferedReader(new InputStreamReader(isYears));

            int i = 0;
            String line = reader.readLine();

            while(line != null){
                calendarStrings.add(line);
                line = reader.readLine();
                days[i] = readerDays.read();
                months[i] = readerMonths.read();
                years[i] = readerYears.read();
                i++;
            }
            daysIndex = i;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}