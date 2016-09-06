package com.example.wang.smartbag;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class NewitemActivity extends AppCompatActivity {

    SQLiteImplement database;
    long id;
    int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newitem);
        database = new SQLiteImplement(this);
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("Id");

        if (id > -1) {
            Data old = database.getData(id);
            EditText temp_et;
            CheckBox temp_cb;
            String temp_s;

            temp_et = (EditText) findViewById(R.id.editTextNAME);
            temp_et.setText(extras.getString("Name"));

            temp_s = extras.getString("Day");
            if (temp_s.indexOf("1") != -1) {
                temp_cb = (CheckBox) findViewById(R.id.checkBox1);
                temp_cb.setChecked(true);
            }

            if (temp_s.indexOf("2") != -1) {
                temp_cb = (CheckBox) findViewById(R.id.checkBox2);
                temp_cb.setChecked(true);
            }

            if (temp_s.indexOf("3") != -1) {
                temp_cb = (CheckBox) findViewById(R.id.checkBox3);
                temp_cb.setChecked(true);
            }
            if (temp_s.indexOf("4") != -1) {
                temp_cb = (CheckBox) findViewById(R.id.checkBox4);
                temp_cb.setChecked(true);
            }
            if (temp_s.indexOf("5") != -1) {
                temp_cb = (CheckBox) findViewById(R.id.checkBox5);
                temp_cb.setChecked(true);
            }

            if (temp_s.indexOf("6") != -1) {
                temp_cb = (CheckBox) findViewById(R.id.checkBox6);
                temp_cb.setChecked(true);
            }

            if (temp_s.indexOf("7") != -1) {
                temp_cb = (CheckBox) findViewById(R.id.checkBox7);
                temp_cb.setChecked(true);
            }

            state = extras.getInt("State");

            temp_et = (EditText) findViewById(R.id.editTextTAG);
            temp_et.setText(extras.getString("Tag"));

            temp_et = (EditText) findViewById(R.id.editTextREMARK1);
            temp_et.setText(extras.getString("Remark1"));
        }
    }

    public void SaveButtonClicked(View v) {
        EditText temp_et;
        CheckBox temp_cb;
        String name, day, tag, remark;

        temp_et = (EditText) findViewById(R.id.editTextNAME);
        name = new String(temp_et.getText().toString());
        if (name.matches("")) {
            Toast.makeText(
                    this, "Error Name!", Toast.LENGTH_SHORT).show();
            return;
        }
        temp_et.setText(null);

        day = new String("");
        temp_cb = (CheckBox) findViewById(R.id.checkBox1);
        if (temp_cb.isChecked())
            day += "1";
        temp_cb = (CheckBox) findViewById(R.id.checkBox2);
        if (temp_cb.isChecked())
            day += "2";
        temp_cb = (CheckBox) findViewById(R.id.checkBox3);
        if (temp_cb.isChecked())
            day += "3";
        temp_cb = (CheckBox) findViewById(R.id.checkBox4);
        if (temp_cb.isChecked())
            day += "4";
        temp_cb = (CheckBox) findViewById(R.id.checkBox5);
        if (temp_cb.isChecked())
            day += "5";
        temp_cb = (CheckBox) findViewById(R.id.checkBox6);
        if (temp_cb.isChecked())
            day += "6";
        temp_cb = (CheckBox) findViewById(R.id.checkBox7);
        if (temp_cb.isChecked())
            day += "7";

        temp_et = (EditText) findViewById(R.id.editTextTAG);
        tag = new String(temp_et.getText().toString());

        temp_et = (EditText) findViewById(R.id.editTextREMARK1);
        remark = new String(temp_et.getText().toString());

        Data test1 = new Data(name, day, tag, -1, remark, null);

        if (id == -1) {
            test1 = database.insertData(test1);
            Toast.makeText(
                    this, name + " Saved!", Toast.LENGTH_SHORT).show();
        } else {
            test1.setId(id);
            test1.setState(state);
            if (database.updateData(test1)) {
                Toast.makeText(
                        this, name + " Saved!", Toast.LENGTH_SHORT).show();
            }
        }
        NewitemActivity.this.finish();
    }

    private void Alert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Unknown Name");
        AlertDialog about_dialog = builder.create();
        about_dialog.show();
    }

    public void CancelButtonClicked(View v) {
        NewitemActivity.this.finish();
    }
}
