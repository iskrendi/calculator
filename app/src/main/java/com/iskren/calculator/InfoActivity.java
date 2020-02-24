package com.iskren.calculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private Button button1;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent i = getIntent();
        String value = i.getStringExtra("result");

        this.button1 = findViewById(R.id.button1);
        this.textView = findViewById(R.id.textView1);

        textView.append("\n"+value);
        button1.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), MainActivity.class);
                    view.getContext().startActivity(i);
                }
        });
    }

    public void pop(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Delete result")
                .setMessage("Do you want to delete result?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText("No result!");
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}

