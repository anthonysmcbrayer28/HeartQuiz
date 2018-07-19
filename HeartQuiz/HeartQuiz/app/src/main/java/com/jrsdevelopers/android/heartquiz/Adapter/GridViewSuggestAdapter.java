package com.jrsdevelopers.android.heartquiz.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.jrsdevelopers.android.heartquiz.Common.Common.Common;
import com.jrsdevelopers.android.heartquiz.MainActivity;

import java.util.List;

public class GridViewSuggestAdapter extends BaseAdapter {

    private List<String> mSuggestSource;
    private Context mContext;
    private MainActivity mMainActivity;



    public GridViewSuggestAdapter(List<String> suggestSource, Context context, MainActivity mainActivity) {
        this.mSuggestSource = suggestSource;
        this.mContext = context;
        this.mMainActivity = mainActivity;
    }


    @Override
    public int getCount() {
        return mSuggestSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mSuggestSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        Button button;
        if (convertView == null)
        {
            if (mSuggestSource.get(position).equals("null"))
            {
                button = new Button(mContext);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);

            }
            else {
                button = new Button(mContext);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
                button.setTextColor(Color.YELLOW);
                button.setText(mSuggestSource.get(position));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // if correct answer contains character user selected

                        if (String.valueOf(mMainActivity.mAnswer).contains(mSuggestSource.get(position)))
                        {
                            char compare = mSuggestSource.get(position).charAt(0);
                            for (int i = 0; i <mMainActivity.mAnswer.length;i++)
                            {
                                if (compare == mMainActivity.mAnswer[i])
                                    Common.user_submit_answer[i] = compare;
                            }
                            // update uI
                            GridViewAnswerAdapter mAnswerAdapter = new GridViewAnswerAdapter(Common.user_submit_answer,mContext);
                            mMainActivity.mGridViewAnswer.setAdapter(mAnswerAdapter);
                            mAnswerAdapter.notifyDataSetChanged();

                            // Remove from suggest source
                            mMainActivity.mSuggestSource.set(position,"null");
                            mMainActivity.mSuggestAdapter = new GridViewSuggestAdapter(mMainActivity.mSuggestSource,mContext,mMainActivity);
                            mMainActivity.mGridViewSuggest.setAdapter(mMainActivity.mSuggestAdapter);
                            mMainActivity.mSuggestAdapter.notifyDataSetChanged();
                        }
                        else // else
                        {
                            // Remove from suggest source
                            mMainActivity.mSuggestSource.set(position,"null");
                            mMainActivity.mSuggestAdapter = new GridViewSuggestAdapter(mMainActivity.mSuggestSource,mContext,mMainActivity);
                            mMainActivity.mGridViewSuggest.setAdapter(mMainActivity.mSuggestAdapter);
                            mMainActivity.mSuggestAdapter.notifyDataSetChanged();

                        }

                    }
                });
            }
        }
        else
            button =(Button)convertView;
        return button;
    }
}
