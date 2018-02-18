package com.example.user.recyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> wordList = new LinkedList<>();
    private int count = 0;
    private RecyclerView recyclerView;
    private WordListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i=0; i<20; i++){
            wordList.addLast("Word "+count++);
            Log.d("WordList", wordList.getLast());
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new WordListAdapter(this, wordList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                int wordListSize = wordList.size();
                wordList.addLast("+ word "+ wordListSize);
                recyclerView.getAdapter().notifyItemInserted(wordListSize);
                recyclerView.smoothScrollToPosition(wordListSize);
            }
        });
    }



}
