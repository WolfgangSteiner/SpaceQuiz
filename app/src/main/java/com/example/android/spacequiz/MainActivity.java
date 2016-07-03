package com.example.android.spacequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGradeQuiz(View aView)
    {
        Log.i("MainActivity", "Checking answers!");

        LinearLayout quizContainer = (LinearLayout) findViewById(R.id.quiz_container);
        int numCorrectAnswers = 0;
        int numQuestions = quizContainer.getChildCount();

        for (int i = 0; i < numQuestions; i++)
        {
            QuizCard iCard = (QuizCard) quizContainer.getChildAt(i);

            if (iCard.isAnswerCorrect())
            {
                numCorrectAnswers++;
            }
        }

        Toast.makeText(
            this,
            getString(R.string.result_toast) + " " + numCorrectAnswers + " / " + numQuestions,
            Toast.LENGTH_SHORT).show();
    }
}
