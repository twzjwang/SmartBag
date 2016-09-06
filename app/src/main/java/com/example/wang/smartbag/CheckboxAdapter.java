package com.example.wang.smartbag;

/**
 * Created by Wang on 2016/9/2.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

class Info {
    long id;
    String name;
    String day;
    int state;
    String remark;
    boolean selected = false;

    public Info(long id, String name, String day, int state, String remark) {
        super();
        this.id = id;
        this.name = name;
        this.day = day;
        this.state = state;
        this.remark = remark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

public class CheckboxAdapter extends ArrayAdapter<Info> {

    private List<Info> List;
    private Context context;

    public CheckboxAdapter(List<Info> List, Context context) {
        super(context, R.layout.single_item, List);
        this.List = List;
        this.context = context;
    }

    private static class ListHolder {
        public TextView name;
        public TextView distView;
        public CheckBox chkBox;
        public Button bt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        ListHolder holder = new ListHolder();

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.single_item, null);

            holder.name = (TextView) v.findViewById(R.id.name);
            holder.distView = (TextView) v.findViewById(R.id.dist);
            holder.chkBox = (CheckBox) v.findViewById(R.id.chk_box);

            holder.chkBox.setOnCheckedChangeListener((MainActivity) context);

        } else {
            holder = (ListHolder) v.getTag();
        }

        Info i = List.get(position);
        holder.name.setText(i.getName());
        holder.distView.setText("" + i.getDay() + " " + i.getRemark());
        holder.chkBox.setChecked(i.isSelected());
        holder.chkBox.setTag(i);
        switch (i.state) {
            case -1:
                holder.bt = (Button) v.findViewById(R.id.button_state_q);
                break;
            case 0:
                holder.bt = (Button) v.findViewById(R.id.button_state_0);
                break;
            case 1:
                holder.bt = (Button) v.findViewById(R.id.button_state_1);
                break;
        }
        holder.bt.setVisibility(View.VISIBLE);
        return v;
    }
}