package com.itproger.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView question_label;
    private Button btn_correct, btn_incorrect, btn_next;
    private int question_index = 0;
    private int count_correct_answers;

    private Question[] questions = new Question[] {
            new Question(true, R.string.question_1),
            new Question(true, R.string.question_2),
            new Question(false, R.string.question_3),
            new Question(true, R.string.question_4),
            new Question(false, R.string.question_5)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question_label = findViewById(R.id.question_label);
        btn_correct = findViewById(R.id.btn_correct);
        btn_incorrect = findViewById(R.id.btn_incorrect);
        btn_next = findViewById(R.id.btn_next);

        question_label.setText(questions[question_index].getQuestion());

        btn_correct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        btn_incorrect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (question_index != questions.length - 1) {
                    question_index++;
                    question_label.setText(questions[question_index].getQuestion());
                    btn_correct.setVisibility(View.VISIBLE);
                    btn_incorrect.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.INVISIBLE);
                } else {
                    question_label.setTextColor(Color.RED);
                    question_label.setText("Викторина пройдена! Ваш результат: " + count_correct_answers +
                            " из " + questions.length + " возможных");
                    btn_next.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void checkAnswer(boolean answer){
            if(answer == questions[question_index].isCorrect()){
                Toast.makeText(this, "Вы молодец! Ответили верно!", Toast.LENGTH_SHORT).show();
                count_correct_answers++;
            }else {
                Toast.makeText(this, "Вы ответили НЕ верно!", Toast.LENGTH_SHORT).show();
            }

            btn_next.setVisibility(View.VISIBLE);
            btn_correct.setVisibility(View.INVISIBLE);
            btn_incorrect.setVisibility(View.INVISIBLE);
    }
}
