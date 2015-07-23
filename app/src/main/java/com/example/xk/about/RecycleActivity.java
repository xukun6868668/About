package com.example.xk.about;

import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class RecycleActivity extends ActionBarActivity implements Myadapt.OnclickListener {

    private RecyclerView recycle;
    private Myadapt myadapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        recycle = ((RecyclerView) findViewById(R.id.rececler));

        /*LinearLayoutManager manager = new LinearLayoutManager(this);
        recycle.setLayoutManager(manager);
        manager.setReverseLayout(true);//设置逆向滑动*/
        //manager.setOrientation(LinearLayoutManager.HORIZONTAL);//设置横向滑动（listview只能是）

        //recycle.setLayoutManager(new GridLayoutManager(this,3));

        StaggeredGridLayoutManager stagg=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager manager1=new GridLayoutManager(this,3);//设置gridview为三列
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycle.setLayoutManager(manager1);

        List<String>list=new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format("Item%03d",i));
        }
        myadapt = new Myadapt(this,list);

         recycle.setAdapter(myadapt);
        myadapt.setOnclickLisener(this);


        //设置每个Item下面有那一条横线（对比Listview的）
        recycle.addItemDecoration(new RecyclerView.ItemDecoration(){
            /**
             *
             * @param outRect 为边框
             * @param view itemView
             * @param parent RecyclerView
             * @param state  状态
             */
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
               outRect.set(1,1,1,1);
            }
        });
        //RecyclerView.ItemAnimator animator1=new DefaultItemAnimator();
        RecyclerView.ItemAnimator animator=new MyItemAnimation();
        animator.setRemoveDuration(1000);
        recycle.setItemAnimator(animator);
    }


    @Override
    public void onChildClick(View view, int position, String data) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
        myadapt.remove(position);
    }
}
