package pl.edu.pb.wi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String QUIZ_TAG ="MainActivity";
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button promptButton;
    private TextView questionTextView;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private boolean answerWasShown=false;
    private Question[] questions =new Question[]{
            new Question(R.string.q_countries, false),
            new Question(R.string.q_lake, false),
            new Question(R.string.q_union, true),
            new Question(R.string.q_usa, false),
            new Question(R.string.q_civilwar, true)
    };
    private int currentIndex=0;
    private int questionCounter=0;
    private int questionCountTotal=5;
    private int score=0;
    public static final String KEY_EXTRA_ANSWER="pl.edu.pb.wi.quiz.correctAnswer";
    public static final int REQUEST_CODE_PROMPT=0; //numer aktywnosći z poziomu danej aktywności
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onCreate");
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null)
        {
            currentIndex=savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }

        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        nextButton=findViewById(R.id.next_button);
        promptButton=findViewById(R.id.prompt_button);
        questionTextView=findViewById(R.id.question_text_view);
        textViewScore=findViewById(R.id.score_text_view);
        textViewQuestionCount=findViewById(R.id.question_count_text_view);

        trueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View w)
            {
                checkAnswerCorrectioness(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View w)
            {
                checkAnswerCorrectioness(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentIndex=(currentIndex+1)%questions.length;
                answerWasShown=false;
                setNextQuestion();
            }
        });
        promptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, PromptyActivity.class); //wywołanie aktywności Prompty
                boolean correctAnswer=questions[currentIndex].isTrueAnswer();
                intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);//przekazanie poprawnej odpwiedzi
//                startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_PROMPT);
            }
        });
        setNextQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(QUIZ_TAG, "Wywołana została metoda: onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }

    private void checkAnswerCorrectioness(boolean userAnswer)
    {
        boolean correntAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId=0;
        if(answerWasShown){
            resultMessageId=R.string.answer_was_shown;
        }else {
            if (userAnswer == correntAnswer) {
                score++;
                resultMessageId = R.string.correct_answer;

            } else {
                resultMessageId = R.string.incorrect_answer;
            }

        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

    private void setNextQuestion()
    {
        if(questionCounter<questionCountTotal){
            questionTextView.setText(questions[currentIndex].getQuestionId());
            questionCounter++;
            textViewScore.setText("Punkty: " +score);
            textViewQuestionCount.setText("Pytanie: "+ questionCounter+"/"+questionCountTotal);
        }
        else
        {
            finishQuiz();
        }

    }
    private void finishQuiz(){
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode!=RESULT_OK){return;}
        if(requestCode==REQUEST_CODE_PROMPT){
            if(data==null){return;}
            answerWasShown=data.getBooleanExtra(PromptyActivity.KEY_EXTRA_ANSWER_SHOWN,false);
        }
    }

}
