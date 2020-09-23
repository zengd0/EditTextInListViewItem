package com.zengd.edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Button mButton;
    private ListViewAdapter mAdapter;
    private List<ItemBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list_view);
        mButton = (Button) findViewById(R.id.button);
        mData = new ArrayList<ItemBean>();
        mAdapter = new ListViewAdapter(this, mData);
        mListView.setAdapter(mAdapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.add(new ItemBean());
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
