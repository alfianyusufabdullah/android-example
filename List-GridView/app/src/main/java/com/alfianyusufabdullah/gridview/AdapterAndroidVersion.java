package com.alfianyusufabdullah.gridview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jonesrandom on 12/14/17.
 * <p>
 * AA
 */

public class AdapterAndroidVersion extends ArrayAdapter<ModelAndroidVersion> {

    private List<ModelAndroidVersion> data;

    public AdapterAndroidVersion(@NonNull Context context, int resource, @NonNull List<ModelAndroidVersion> objects) {
        super(context, resource, objects);

        data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Holder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_android_version, parent, false);

            holder = new Holder();
            holder.codeName = convertView.findViewById(R.id.rowCodeName);
            holder.apiLevel = convertView.findViewById(R.id.rowLevel);
            holder.version = convertView.findViewById(R.id.rowVer);

            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();
        }

        ModelAndroidVersion android = data.get(position);

        holder.version.setText(android.version);
        holder.apiLevel.setText(android.apiLevel);
        holder.codeName.setText(android.codeName);

        return convertView;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    class Holder {
        TextView codeName;
        TextView version;
        TextView apiLevel;
    }
}
