package com.example.kosta.fileexam02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, String> data;

    private EditText title;
    private EditText body;

    private ListView list;

    private ArrayAdapter<String> adapter;
    private String[] titles;

    private FileManager fileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileManager = new FileManager(this);
        data = fileManager.load();

        title = (EditText)findViewById(R.id.title);
        body = (EditText)findViewById(R.id.body);
        list = (ListView)findViewById(R.id.list);

        if(data == null) {
            data = new HashMap<>();
        }

        dataSetup();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = titles[position];
                String bodyData = data.get(key);
                title.setText(key);
                body.setText(bodyData);
            }
        });
    }

    private void dataSetup() {
        Set<String> keySet = data.keySet();
        titles = new String[keySet.size()];

//        titles = keySet.toArray(titles);
        keySet.toArray(titles);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);

        list.setAdapter(adapter);
    }

    public void clickEvent(View view) {
        data.put(title.getText().toString(), body.getText().toString());

        title.setText("");
        body.setText("");

        dataSetup();

        fileManager.save(data);
    }
}
