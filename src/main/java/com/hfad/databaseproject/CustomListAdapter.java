package com.hfad.databaseproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

private int resourceLayout;
private Context mContext;

public TextView firstTV;
public TextView secondTV;
public TextView thirdTV;
public TextView fourthTV;
public TextView fifthTV;


    public CustomListAdapter(@NonNull Context context, int resource, int resourceLayout, Context mContext) {
        super(context, resource);
        this.resourceLayout = resourceLayout;
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }






        return v;
    }
}