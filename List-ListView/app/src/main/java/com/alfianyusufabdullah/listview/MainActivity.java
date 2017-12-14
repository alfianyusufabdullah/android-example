package com.alfianyusufabdullah.listview;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdapterAndroidVersion adapterAndroidVersion = new AdapterAndroidVersion(this, 0, getAndroidVer());

        ListView listView = findViewById(R.id.mainList);
        listView.setDividerHeight(0);
        listView.setAdapter(adapterAndroidVersion);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ModelAndroidVersion androidVersion = getAndroidVer().get(i);
                Snackbar.make(findViewById(R.id.rootView), androidVersion.codeName, Snackbar.LENGTH_SHORT).show();

            }
        });
    }

    private List<ModelAndroidVersion> getAndroidVer() {

        List<ModelAndroidVersion> data = new ArrayList<>();
        data.add(new ModelAndroidVersion("Oreo", "8.1", "API 27"));
        data.add(new ModelAndroidVersion("Oreo", "8.0", "API 26"));
        data.add(new ModelAndroidVersion("Nougat", "7.1", "API 25"));
        data.add(new ModelAndroidVersion("Nougat", "7.0", "API 24"));
        data.add(new ModelAndroidVersion("Marshmallow", "6.0", "API 23"));
        data.add(new ModelAndroidVersion("Lollipop", "5.1", "API 22"));
        data.add(new ModelAndroidVersion("Lollipop", "5.0", "API 21"));
        data.add(new ModelAndroidVersion("Kitkat", "4.4", "API 19"));
        data.add(new ModelAndroidVersion("Jelly Bean", "4.3", "API 18"));
        data.add(new ModelAndroidVersion("Jelly Bean", "4.2", "API 17"));
        data.add(new ModelAndroidVersion("Jelly Bean", "4.1", "API 16"));
        data.add(new ModelAndroidVersion("Ice Cream Sandwich", "4.0", "API 15"));
        data.add(new ModelAndroidVersion("Gingerbread", "2.3", "API 10"));
        return data;
    }
}
