package com.iskren.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private int[] numericButtons = {
            R.id.btnZero,
            R.id.btnOne, R.id.btnTwo, R.id.btnThree,
            R.id.btnFour, R.id.btnFive, R.id.btnSix,
            R.id.btnSeven, R.id.btnEight, R.id.btnNine};

    private int[] operatorButtons = {
            R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide
    };

    private TextView txtScreen;
    private boolean lastNumeric;
    private boolean stateError;
    private boolean lastDot;
    private float value1, value2;
    private char operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtScreen = findViewById(R.id.txtScreen);

        View.OnClickListener ListenerNumber = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (stateError) {
                    txtScreen.setText(button.getText());
                    stateError = false;
                } else if(!lastDot & !lastNumeric){
                        txtScreen.setText(button.getText());
                } else {
                    txtScreen.append(button.getText());
                }
                lastNumeric = true;
            }
        };
        // Assign the listener to all the numeric buttons
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(ListenerNumber);
        }

        View.OnClickListener listenerOperator = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError) {
                    Button button = (Button) v;
                    value1 = Float.parseFloat(txtScreen.getText().toString());
                    lastNumeric = false;
                    lastDot = false;
                    operator = button.getText().charAt(0);
                }
            }
        };
        // Assign the listener to all the operator buttons
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listenerOperator);
        }

        // Decimal point
        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError && !lastDot) {
                    txtScreen.append(".");
                    lastNumeric = false;
                    lastDot = true;
                }
            }
        });
        // Clear button
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtScreen.setText("0");
                lastNumeric = false;
                stateError = false;
                lastDot = false;
                value1 = 0;
                value2 = 0;
                operator = ' ';
            }
        });
        // Equal button
        findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = Float.parseFloat(txtScreen.getText().toString());
                System.out.println("==== " + value1 + " " + operator + " " + value2);
                float result = 0;
                switch (operator) {
                    case '+':
                        result=value1+value2;
                        break;
                    case '-':
                        result=value1-value2;
                        break;
                    case '*':
                        result=value1*value2;
                        break;
                    case '/':
                        if(value2 != 0) {
                            result=value1/value2;
                        }
                        break;
                    default:
                        result=0;
                }
                txtScreen.setText(String.valueOf(result));
                operator = ' ';
                value1 = 0;
                value2 = 0;

                Intent myIntent = new Intent(MainActivity.this, InfoActivity.class);
                myIntent.putExtra("result", String.valueOf(result));
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
