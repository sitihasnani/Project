package com.example.yasmin.redditclone.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yasmin.redditclone.R;
import com.google.android.material.snackbar.Snackbar;

public class AddTopicDialog extends AppCompatActivity {

    EditText titleEt, descriptionEt;
    Button saveBtn, cancelBtn;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_topic_dialog);

        myDB = new DatabaseHelper(this);

        titleEt = (EditText) findViewById(R.id.titleEt);
        descriptionEt = (EditText) findViewById(R.id.descriptionEt);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDB.insertData(titleEt.getText().toString(), descriptionEt.getText().toString(), 0, 0);

                if (isInserted = true)
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

                    else
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
//

            }
        });
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

    }

}
