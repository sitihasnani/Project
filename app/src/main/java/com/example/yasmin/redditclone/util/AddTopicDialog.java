package com.example.yasmin.redditclone.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yasmin.redditclone.MainActivity;
import com.example.yasmin.redditclone.R;
import com.google.android.material.snackbar.Snackbar;

public class AddTopicDialog extends AppCompatActivity {

    EditText titleEt, descriptionEt;
    TextView countTxt;
    Button saveBtn, cancelBtn;
    DatabaseHelper myDB;
    TextWatcher mTextEditorWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_new_topic_dialog);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add New Topic");

        myDB = new DatabaseHelper(this);

        countTxt = (TextView) findViewById(R.id.countTxt);

        titleEt = (EditText) findViewById(R.id.titleEt);
        descriptionEt = (EditText) findViewById(R.id.descriptionEt);

        saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog pd = new ProgressDialog(AddTopicDialog.this);
                pd.setMessage("Loading ...");
                boolean isInserted;
                Handler handler = new Handler();

                if (titleEt.getText().toString().equals("")){
                    showDialog("Title");

                } else if (descriptionEt.getText().toString().equals("")){
                    showDialog("Description");
                }
                else if (titleEt.getText().toString().equals("")&& descriptionEt.getText().toString().equals("")){
                    showDialog("all information required");
                } else {
                    pd.show();

                    isInserted = myDB.insertData(titleEt.getText().toString(), descriptionEt.getText().toString(), 0, 0);

                if (isInserted = true){
//                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            pd.dismiss();

                            finish();
                        }
                    }, 2000);

                }
                    else{
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            pd.dismiss();
                        }
                    }, 3000);

                }

                }

            }
        });
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mTextEditorWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //This sets a textview to the current length
                countTxt.setText(""+String.valueOf(s.length())+"/255");
            }

            public void afterTextChanged(Editable s) {
            }
        };

        descriptionEt.addTextChangedListener(mTextEditorWatcher);


    }

    private void showDialog(String message){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Please fill in your '" + message +"'");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });



        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
