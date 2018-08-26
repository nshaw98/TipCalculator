package com.nate.tipcalculator.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

//implmenting view.onclick so need to make onclick method
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText enteredAmount;
    private SeekBar seekBar;
    private Button calcButton;
    private TextView totalResultTextView;
    private TextView textViewSeekbar;
    private int seekbarPercentage;
    private float enteredBillFloat;
    private TextView totalBillTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredAmount = (EditText) findViewById(R.id.billAmountId);
        seekBar = (SeekBar) findViewById(R.id.percentageSeekbar);
        calcButton = findViewById(R.id.calculateButtonId);
        totalResultTextView = findViewById(R.id.resultId);
        textViewSeekbar = findViewById(R.id.textViewSeekbar);
        totalBillTextView = findViewById(R.id.totalBillTextView);

        calcButton.setOnClickListener(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            //shows how things are happening when they are happening
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewSeekbar.setText(String.valueOf(seekBar.getProgress())+ "%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            //shows what happens when user lifts finger
            public void onStopTrackingTouch(SeekBar seekBar) {
                //will give just perecentage once they stop trying to figure it out
                seekbarPercentage = seekBar.getProgress();
            }
        });
    }
    @Override
    public void onClick(View view){
        calculate();
    }

    public void calculate(){
        float result = 0.0f;
        //if not empty string
        if (!enteredAmount.getText().toString().equals("")){
            //take the entered amount converting to a string then converting to a float
            enteredBillFloat = Float.parseFloat(enteredAmount.getText().toString());
            result = enteredBillFloat * seekbarPercentage / 100;
            totalResultTextView.setText("Your tip will be: $" +String.format(java.util.Locale.US,"%.2f",result));
            totalBillTextView.setText("Your total bill is: $" + String.format(java.util.Locale.US,"%.2f",enteredBillFloat + result));
        }else{
            Toast.makeText(MainActivity.this, "Please enter a bill amount.", Toast.LENGTH_LONG).show();
        }

    }

}
