package com.example.lelandlopez.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String DEBUG_TAG = "GeoQuiz";
    private static final String KEY_INDEX = "index";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true)
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(DEBUG_TAG,"IN SAVE INSTANCE STATE");
        outState.putInt(KEY_INDEX,mCurrentIndex);
    }

    private void assignQuestion() {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }
    private void updateQuestion() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void previousQuestion() {
        if(mCurrentIndex == 0){
            mCurrentIndex = 4;
        } else {
            mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
        }

        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer (boolean userPressesdTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResid = 0;
        if(userPressesdTrue == answerIsTrue) {
            messageResid = R.string.correct_toast;
        } else {
            messageResid = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResid,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null) {
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
        }  // 0 is the default value
        //Log.i(DEBUG_STRING, "IN CREATE METHOD");
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);

            }
        });

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mPreviousButton = (Button)findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                previousQuestion();
            }
        });

        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });
        assignQuestion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(DEBUG_TAG,"OnPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(DEBUG_TAG,"OnStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(DEBUG_TAG,"onDestroy() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(DEBUG_TAG, "onResume() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(DEBUG_TAG, "onStart() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(DEBUG_TAG, "onRestart() called");
    }


}
