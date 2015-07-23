package com.example.xk.about;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView card = (CardView) findViewById(R.id.card);
        card.setOnTouchListener(this);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"来来乐乐乐乐而来",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,RecycleActivity.class));
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        CardView cardView = (CardView) v;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                cardView.setCardElevation(getResources().getDimension(R.dimen.card_max_evelation));
                break;
            case MotionEvent.ACTION_UP:
                cardView.setCardElevation(getResources().getDimension(R.dimen.card_evelation));
        }
        return !v.isClickable();
        /*!v.isClickable()这样写是因为点击事件和这个触摸事件要求这个返回值不一样
        点击事件要求“false”,而响应触摸事件要求“true”，所以这样写*/
    }
}
