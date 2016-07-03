package com.example.android.spacequiz;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by wolfgang on 03.07.16.
 */
public class QuizCardMultipleChoice extends QuizCard
{
    static final int NUM_ANSWERS = 4;
    ViewGroup mButtonContainer;
    String[] mAnswers = new String[4];
    boolean[] mAnswerIsCorrect = new boolean[4];

    public QuizCardMultipleChoice(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.QuizCardMultipleChoice,
                0, 0);

        mAnswers[0] = a.getString(R.styleable.QuizCardMultipleChoice_answerA);
        mAnswers[1] = a.getString(R.styleable.QuizCardMultipleChoice_answerB);
        mAnswers[2] = a.getString(R.styleable.QuizCardMultipleChoice_answerC);
        mAnswers[3] = a.getString(R.styleable.QuizCardMultipleChoice_answerD);

        mAnswerIsCorrect[0] = a.getBoolean(R.styleable.QuizCardMultipleChoice_answerAIsCorrect, false);
        mAnswerIsCorrect[1] = a.getBoolean(R.styleable.QuizCardMultipleChoice_answerBIsCorrect, false);
        mAnswerIsCorrect[2] = a.getBoolean(R.styleable.QuizCardMultipleChoice_answerCIsCorrect, false);
        mAnswerIsCorrect[3] = a.getBoolean(R.styleable.QuizCardMultipleChoice_answerDIsCorrect, false);

        if (numCorrectAnswers() > 1)
        {
            loadCheckButtons(context);
        }
        else
        {
            loadRadioButtons(context);
        }
    }

    private int numCorrectAnswers()
    {
        int Result = 0;

        for (int i = 0; i < NUM_ANSWERS; i++)
        {
            if (mAnswerIsCorrect[i])
            {
                Result++;
            }
        }

        assert Result > 0;
        return Result;
    }

    private void loadCheckButtons(Context aContext)
    {
        assert numCorrectAnswers() > 1;
        LayoutInflater layoutInflater = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = layoutInflater.inflate(R.layout.quiz_card_multiple_choice_checkboxes_content, this, false);
        mContentView.addView(contentView);
        configureAnswers((ViewGroup)contentView);
    }

    private void loadRadioButtons(Context aContext)
    {
        assert numCorrectAnswers() > 1;
        LayoutInflater layoutInflater = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = layoutInflater.inflate(R.layout.quiz_card_multiple_choice_radiobuttons_content, this, false);
        mContentView.addView(contentView);
        configureAnswers((ViewGroup)contentView);
    }

    private void configureAnswers(ViewGroup aContentView)
    {
        assert aContentView.getChildCount() == NUM_ANSWERS;

        mButtonContainer = aContentView;

        for (int i = 0; i < NUM_ANSWERS; i++)
        {
            Button iButton = (Button) mButtonContainer.getChildAt(i);
            iButton.setText(mAnswers[i]);
        }
    }

    @Override
    boolean isAnswerCorrect()
    {
        assert mButtonContainer.getChildCount() == NUM_ANSWERS;

        for (int i = 0; i < NUM_ANSWERS; i++)
        {
            CompoundButton iButton = (CompoundButton) mButtonContainer.getChildAt(i);

            if (iButton.isChecked() != mAnswerIsCorrect[i])
            {
                return false;
            }
        }

        return true;
    }
}
