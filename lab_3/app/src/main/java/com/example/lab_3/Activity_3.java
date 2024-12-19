package com.example.lab_3;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_3 extends AppCompatActivity {

    EditText editStartPoint, editEndPoint, editDepartureTime, editArriveTime, editPrice, editDistanse;
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layer_three_activity);

        editStartPoint = findViewById(R.id.editStartPoint);
        editEndPoint = findViewById(R.id.editEndPoint);
        editDepartureTime = findViewById(R.id.editDepartureTime);
        editArriveTime = findViewById(R.id.editArriveTime);
        editPrice = findViewById(R.id.editPrice);
        editDistanse = findViewById(R.id.editDistanse);

        btnOk = findViewById(R.id.btnOk);
        btnOk.setEnabled(false);

        editStartPoint.setInputType(InputType.TYPE_CLASS_TEXT);
        editEndPoint.setInputType(InputType.TYPE_CLASS_TEXT);
        editDepartureTime.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editArriveTime.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editDistanse.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFieldsForEmptyValues();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };

        editStartPoint.addTextChangedListener(textWatcher);
        editEndPoint.addTextChangedListener(textWatcher);
        editDepartureTime.addTextChangedListener(textWatcher);
        editArriveTime.addTextChangedListener(textWatcher);
        editPrice.addTextChangedListener(textWatcher);
        editDistanse.addTextChangedListener(textWatcher);

        btnOk.setOnClickListener(v -> {
            String route = "Точка начала: " + editStartPoint.getText().toString() +
                    ", Точка конца: " + editEndPoint.getText().toString() +
                    ", Время отправления: " + editDepartureTime.getText().toString() +
                    ", Время пути: " + editArriveTime.getText().toString() +
                    ", Цена: " + editPrice.getText().toString() + " BYN" +
                    ", Дистанция: " + editDistanse.getText().toString() + " km";
            Intent resultIntent = new Intent();
            resultIntent.putExtra("Route", route);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        Log.d("Lifecycle", "onCreate called in ThirdActivity");
    }

    private void checkFieldsForEmptyValues() {
        String startPoint = editStartPoint.getText().toString();
        String endPoint = editEndPoint.getText().toString();
        String distance = editDepartureTime.getText().toString();
        String time = editArriveTime.getText().toString();
        String price = editPrice.getText().toString();
        String traffic = editDistanse.getText().toString();

        btnOk.setEnabled(!startPoint.isEmpty() && !endPoint.isEmpty() &&
                !distance.isEmpty() && !time.isEmpty() &&
                !price.isEmpty() && !traffic.isEmpty());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "onStart called in Activity_3");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "onResume called in Activity_3");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "onPause called in Activity_3");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "onStop called in Activity_3");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "onDestroy called in Activity_3");
    }
}
