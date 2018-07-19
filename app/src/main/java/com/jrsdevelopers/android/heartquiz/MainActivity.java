package com.jrsdevelopers.android.heartquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.jrsdevelopers.android.heartquiz.Adapter.GridViewAnswerAdapter;
import com.jrsdevelopers.android.heartquiz.Adapter.GridViewSuggestAdapter;
import com.jrsdevelopers.android.heartquiz.Common.Common.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public List<String> mSuggestSource = new ArrayList<>();

    public GridViewAnswerAdapter mAnswerAdapter;
    public GridViewSuggestAdapter mSuggestAdapter;

    public Button mBtnSubmit;

    public GridView mGridViewAnswer,mGridViewSuggest;

    public ImageView mImgViewQuestion;

    int[] image_list = {

            R.drawable.aorta,
            R.drawable.aortic_valve,
            R.drawable.chordaetendineae,
            R.drawable.endocardium,
            R.drawable.epicardium,
            R.drawable.fossa_ovalis,
            R.drawable.inferior_vena_cava,
            R.drawable.intervtricular_septum,
            R.drawable.left_atrlum,
            R.drawable.left_pulmonary_artery,
            R.drawable.left_pulmonary_veins,
            R.drawable.left_ventbricle,
            R.drawable.mitral_bicuspld_valve,
            R.drawable.myocardium,
            R.drawable.papillary_ventricle,
            R.drawable.pectinate_muscles,
            R.drawable.pulmonary_trunk,
            R.drawable.pulmonary_valve,
            R.drawable.right_atrium,
            R.drawable.right_pulmonary_artery,
            R.drawable.right_pulmonary_veins,
            R.drawable.right_ventricie,
            R.drawable.superior_vena_cava,
            R.drawable.trabeculae_carneae,
            R.drawable.tricuspid_valve


    };

    public char[] mAnswer;

    public String correct_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mGridViewAnswer = findViewById(R.id.gridViewAnswer);
        mGridViewSuggest = findViewById(R.id.gridViewSuggest);

        mImgViewQuestion = findViewById(R.id.heartImage);
        setupList();

        mBtnSubmit = (Button)findViewById(R.id.btn);
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < Common.user_submit_answer.length;i++)
                    result.append(String.valueOf(Common.user_submit_answer[i]));
                if (result.toString().equals(correct_answer)){
                    Toast.makeText(getApplicationContext(),
                            "Finish ! This is"+ result,Toast.LENGTH_SHORT).show();

                    Common.count = 0;
                    Common.user_submit_answer = new char[correct_answer.length()];

                    GridViewAnswerAdapter mAnswerAdapter = new GridViewAnswerAdapter(setupNullList(),getApplicationContext());
                    mGridViewAnswer.setAdapter(mAnswerAdapter);
                    mAnswerAdapter.notifyDataSetChanged();

                    GridViewSuggestAdapter mSuggestAdapter = new GridViewSuggestAdapter(mSuggestSource,getApplicationContext(),MainActivity.this);
                    mGridViewSuggest.setAdapter(mSuggestAdapter);
                    mSuggestAdapter.notifyDataSetChanged();
                    
                    setupList();

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Incorrect!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupList() {
        Random random = new Random();
        int imageSelected = image_list[random.nextInt(image_list.length)];
        mImgViewQuestion.setImageResource(imageSelected);



        correct_answer = getResources().getResourceName(imageSelected);
        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/")+1);

        mAnswer = correct_answer.toCharArray();

        Common.user_submit_answer = new char[mAnswer.length];

        // add Answer character to list
        mSuggestSource.clear();
        for (char item:mAnswer)
        {
            // add hear to list
            mSuggestSource.add(String.valueOf(item));
        }
        // Random add some characters to list
        for (int i = mAnswer.length;i < mAnswer.length*2;i++)

            mSuggestSource.add(Common.alphabet_character[random.nextInt(Common.alphabet_character.length)]);

            // sort random
            Collections.shuffle(mSuggestSource);

            // set for GridView
        mAnswerAdapter = new GridViewAnswerAdapter(setupNullList(),this);
        mSuggestAdapter = new GridViewSuggestAdapter(mSuggestSource,this,this);

        mAnswerAdapter.notifyDataSetChanged();
        mSuggestAdapter.notifyDataSetChanged();

        mGridViewSuggest.setAdapter(mSuggestAdapter);
        mGridViewAnswer.setAdapter(mAnswerAdapter);




    }

    private char[] setupNullList() {
        char result[] = new char[mAnswer.length];
        for (int i = 0; i< mAnswer.length;i++) {
            result [i] = ' ';
        }
        return result;

    }
}
