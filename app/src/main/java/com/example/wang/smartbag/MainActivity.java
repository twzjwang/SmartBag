package com.example.wang.smartbag;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements
        android.widget.CompoundButton.OnCheckedChangeListener {

    ListView lv;
    ArrayList<Info> List;
    CheckboxAdapter cbAdapter;
    SQLiteImplement database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new SQLiteImplement(this);
        List = new ArrayList<Info>();
        lv = (ListView) findViewById(R.id.listview);
        displayList();
    }

    public void NewButtonClicked(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, NewitemActivity.class);
        for (int i = 0; i < List.size(); i++) {
            if (List.get(i).isSelected()) {
                intent.putExtra("Id", database.getData(List.get(i).getId()).getId());
                intent.putExtra("Name", database.getData(List.get(i).getId()).getName());
                intent.putExtra("Day", database.getData(List.get(i).getId()).getDay());
                intent.putExtra("Tag", database.getData(List.get(i).getId()).getTag());
                intent.putExtra("State", database.getData(List.get(i).getId()).getState());
                intent.putExtra("Remark1", database.getData(List.get(i).getId()).getRemark1());
                intent.putExtra("Remark2", database.getData(List.get(i).getId()).getRemark1());
                startActivity(intent);
                return;
            }
        }
        intent.putExtra("Id", (long) -1);
        startActivity(intent);
    }

    public void DeleteButtonClicked(View v) {
        for (int i = 0; i < List.size(); i++) {
            if (List.get(i).isSelected()) {
                database.deleteData(List.get(i).getId());
                List.remove(i);
                i--;
            }
        }
        displayList();
        Toast.makeText(
                this,
                "Deleted!", Toast.LENGTH_SHORT).show();
    }

    public void RefreshButtonClicked(View v) {
        displayList();
    }

    public void TestButtonClicked(View v) {
        for (int i = 0; i < List.size(); i++) {
            if (List.get(i).isSelected()) {
                Data temp = database.getData(List.get(i).getId());
                switch (temp.getState()) {
                    case -1:
                        temp.setState(1);
                        break;
                    case 0:
                        temp.setState(1);
                        break;
                    case 1:
                        temp.setState(0);
                        break;
                }
                database.updateData(temp);
            }
        }
        displayList();
    }

    public void StateButtonClicked(View v) {
        int pos = lv.getPositionForView(v);
        if (pos != ListView.INVALID_POSITION) {
            Info i = List.get(pos);
            Data temp = database.getData(i.getId());
            switch (temp.getState()) {
                case -1:
                    temp.setState(1);
                    break;
                case 0:
                    temp.setState(1);
                    break;
                case 1:
                    temp.setState(0);
                    break;
            }
            database.updateData(temp);
            displayList();
        }
    }

    private void displayList() {
        List.clear();
        for (int i = 1; i <= database.getCount(); i++) {
            Data temp = database.getData(i);
            if (temp.getName() != null)
                List.add(new Info(temp.getId(), temp.getName(), temp.getDay(), temp.getState(), temp.getRemark1()));
        }

        cbAdapter = new CheckboxAdapter(List, this);
        lv.setAdapter(cbAdapter);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = lv.getPositionForView(buttonView);
        if (pos != ListView.INVALID_POSITION) {
            Info p = List.get(pos);
            p.setSelected(isChecked);
        }
    }
}
