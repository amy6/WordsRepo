package com.example.mahima.wordsrepo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private Context context;
    private List<Word> words;

    public WordListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new WordViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_recyclerview_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {
        if (words != null) {
            Word word = words.get(i);
            wordViewHolder.wordTextView.setText(word.getWord());
        } else {
            wordViewHolder.wordTextView.setText("No word");
        }
    }

    @Override
    public int getItemCount() {
        return words != null ? words.size() : 0;
    }

    public void setWords(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    public Word getWord(int position) {
        return words.get(position);
    }


    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public static final String EXTRA_WORD = "extra_word";

        public TextView wordTextView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordTextView = itemView.findViewById(R.id.word);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Word word = words.get(getAdapterPosition());
            Intent intent = new Intent(context, NewWordActivity.class);
            intent.putExtra(Intent.EXTRA_INTENT, word.getId());
            intent.putExtra(EXTRA_WORD, word.getWord());
            context.startActivity(intent);
        }
    }
}
