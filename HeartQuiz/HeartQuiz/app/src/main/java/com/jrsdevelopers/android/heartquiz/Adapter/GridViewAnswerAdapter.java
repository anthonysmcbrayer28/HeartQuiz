package com.jrsdevelopers.android.heartquiz.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class GridViewAnswerAdapter extends BaseAdapter {

    private char[] mAnswerCharacter;
    private Context mContext;

    public GridViewAnswerAdapter(char[] answerCharacter,Context mcontext) {
        this.mAnswerCharacter = answerCharacter;
        this.mContext = mcontext;
    }


    @Override
    public int getCount() {
        return mAnswerCharacter.length;
    }

    @Override
    public Object getItem(int position) {
        return mAnswerCharacter[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
       Button button;
       if (convertView == null)
       {
           button = new Button(mContext);
           button.setLayoutParams(new GridView.LayoutParams(85,85));
           button.setPadding(8,8,8,8);
           button.setBackgroundColor(Color.DKGRAY);
           button.setTextColor(Color.YELLOW);
           button.setText(String.valueOf(mAnswerCharacter[position]));
       }
       else button =(Button)convertView;
       return button;
    }
}
