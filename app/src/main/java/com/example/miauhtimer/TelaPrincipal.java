package com.example.miauhtimer;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TelaPrincipal extends AppCompatActivity {

    private EditText editTextDate1, editTextTime1, editTextDate2, editTextTime2;
    private TextView textViewDiferenca, textViewResultado;
    private ImageView imageViewMostrarResultado;
    private Button buttonCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextDate1 = findViewById(R.id.editTextDate1);
        editTextTime1 = findViewById(R.id.editTextTime1);
        editTextDate2 = findViewById(R.id.editTextDate2);
        editTextTime2 = findViewById(R.id.editTextTime2);
        textViewDiferenca = findViewById(R.id.diferenca);
        textViewResultado = findViewById(R.id.resultado);
        imageViewMostrarResultado = findViewById(R.id.mostrarResultado);
        buttonCalcular = findViewById(R.id.calcular);

        setCurrentDateTime(editTextDate1, editTextTime1);
        setCurrentDateTime(editTextDate2, editTextTime2);

        editTextDate1.setOnClickListener(v -> showDatePickerDialog(editTextDate1));
        editTextTime1.setOnClickListener(v -> showTimePickerDialog(editTextTime1));
        editTextDate2.setOnClickListener(v -> showDatePickerDialog(editTextDate2));
        editTextTime2.setOnClickListener(v -> showTimePickerDialog(editTextTime2));

        textViewDiferenca.setVisibility(View.INVISIBLE);
        textViewResultado.setVisibility(View.INVISIBLE);
        imageViewMostrarResultado.setVisibility(View.INVISIBLE);

        buttonCalcular.setOnClickListener(v -> calcularDiferenca());
    }

    private void setCurrentDateTime(EditText dateEditText, EditText timeEditText) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        dateEditText.setText(dateFormat.format(calendar.getTime()));
        timeEditText.setText(timeFormat.format(calendar.getTime()));
    }

    private void showDatePickerDialog(EditText dateEditText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            dateEditText.setText(selectedDate);
        }, year, month, day);
        datePickerDialog.show();
    }

    private void showTimePickerDialog(EditText timeEditText) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute1) -> {
            String selectedTime = hourOfDay + ":" + minute1;
            timeEditText.setText(selectedTime);
        }, hour, minute, true);
        timePickerDialog.show();
    }

    private void calcularDiferenca() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

        try {
            Date dateTime1 = dateTimeFormat.parse(editTextDate1.getText().toString() + " " + editTextTime1.getText().toString());
            Date dateTime2 = dateTimeFormat.parse(editTextDate2.getText().toString() + " " + editTextTime2.getText().toString());

            if (dateTime1 != null && dateTime2 != null) {
                long differenceInMillis = dateTime2.getTime() - dateTime1.getTime();
                long differenceInMinutes = differenceInMillis / (1000 * 60);
                long days = differenceInMinutes / (60 * 24);
                long hours = (differenceInMinutes % (60 * 24)) / 60;
                long minutes = differenceInMinutes % 60;

                String result = days + " Dias, " + hours + " Horas e " + minutes + " Minutos";
                textViewResultado.setText(result);

                textViewDiferenca.setVisibility(View.VISIBLE);
                textViewResultado.setVisibility(View.VISIBLE);
                imageViewMostrarResultado.setVisibility(View.VISIBLE);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}