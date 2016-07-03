package com.example.android.spacequiz;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by wolfgang on 03.07.16.
 */
public class QuizCardText extends QuizCard
{
    EditText mAnswerView;
    String mAnswer;

    public QuizCardText(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.QuizCardText,
                0, 0);


        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = layoutInflater.inflate(R.layout.quiz_card_text_content, this, false);
        mAnswerView = (EditText) contentView.findViewById(R.id.answer_view);
        mAnswer = a.getString(R.styleable.QuizCardText_answer);
        mContentView.addView(contentView);
    }

    @Override
    boolean isAnswerCorrect()
    {
        Log.i("QuizCardText", "Checking answer!");
        String userAnswer = mAnswerView.getText().toString().toLowerCase().trim();
        String correctAnswer = mAnswer.toLowerCase().trim();
        return userAnswer.equals(correctAnswer);
    }
}
