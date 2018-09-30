package com.example.mahima.wordsrepo;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import static com.example.mahima.wordsrepo.WordListAdapter.WordViewHolder.EXTRA_WORD;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.mahima.wordsrepo.REPLY";

    private EditText mEditText;
    private int existingWordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mEditText = findViewById(R.id.edit_word);

        Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            String newWord = mEditText.getText().toString();
            if (existingWordId == -1) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(newWord)) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    replyIntent.putExtra(EXTRA_REPLY, newWord);
                    setResult(RESULT_OK, replyIntent);
                }
            } else {
                WordViewModel viewModel = ViewModelProviders.of(NewWordActivity.this)
                        .get(WordViewModel.class);
                viewModel.update(new Word(existingWordId, newWord));
            }

            finish();
        });

        if (getIntent() != null) {
            if (getIntent().hasExtra(Intent.EXTRA_INTENT)) {
                existingWordId = getIntent().getIntExtra(Intent.EXTRA_INTENT, -1);
                mEditText.setText(getIntent().getStringExtra(EXTRA_WORD));
            }
        }
    }
}
