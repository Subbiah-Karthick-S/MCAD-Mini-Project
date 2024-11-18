package com.example.agecalculator;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
public class MainActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private TextView ageTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePicker = findViewById(R.id.datePicker);
        Button calculateBtn = findViewById(R.id.calculateBtn);
        ageTextView = findViewById(R.id.ageTextView);
        calculateBtn.setOnClickListener(v -> calculateAge());
    }
    private void calculateAge() {
        int birthYear = datePicker.getYear();
        int birthMonth = datePicker.getMonth();
        int birthDay = datePicker.getDayOfMonth();
        Calendar currentDate = Calendar.getInstance();
        int currentYear = currentDate.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH);
        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
        int ageYears = currentYear - birthYear;
        int ageMonths = currentMonth - birthMonth;
        if (ageMonths < 0) {
            ageMonths += 12;
            ageYears--;
        }
        int ageDays = currentDay - birthDay;
        if (ageDays < 0) {
            Calendar tempCalendar = Calendar.getInstance();
            tempCalendar.set(currentYear, currentMonth, 0);
            ageDays += tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            ageMonths--;
            if (ageMonths < 0) {
                ageMonths += 12;
                ageYears--;
            }
        }
        String ageText = getString(R.string.age_text, ageYears, ageMonths, ageDays);
        ageTextView.setText(ageText);    
     }     
}
