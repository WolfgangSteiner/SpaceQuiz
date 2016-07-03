package com.example.android.spacequiz;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wolfgang on 03.07.16.
 */
public class QuizCard extends LinearLayout
{
    ImageView mImageView;
    TextView mQuestionView;
    LinearLayout mContentView;

    public QuizCard(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.QuizCard,
                0, 0);


        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.quiz_card,this);

        Drawable imageDrawable = a.getDrawable(R.styleable.QuizCard_image);
        String question = a.getString(R.styleable.QuizCard_question);

        mImageView = (ImageView) findViewById(R.id.image_view);
        mImageView.setImageDrawable(imageDrawable);

        mQuestionView = (TextView) findViewById(R.id.question_view);
        mQuestionView.setText(question);
        mContentView = (LinearLayout) findViewById(R.id.content_view);
    }

    boolean isAnswerCorrect()
    {
        return false;
    }
}
