package com.example.user.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import static com.example.user.recyclerview.R.id.word;

/**
 * Created by User on 09-Jan-18.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {


    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView wordItemView;
        final WordListAdapter mAdapter;

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = (TextView) itemView.findViewById(word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
            String element = wordList.get(mPosition);
            wordList.set(mPosition, "Clicked!"+element);
            mAdapter.notifyDataSetChanged();
        }
    }

    private final LinkedList<String> wordList;
    private LayoutInflater mInflater;

    public WordListAdapter(Context context, LinkedList<String> wordList){
        mInflater = LayoutInflater.from(context);
        this.wordList = wordList;
    }



    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.wordlist_item,parent,false);
        return new WordViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(WordListAdapter.WordViewHolder holder, int position) {
        String current = wordList.get(position);
        holder.wordItemView.setText(current);
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
