package com.bonnidee.drawrecyclerviewdemo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> mDatasList;
    private MainAdapter adapter;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatasList = new ArrayList<>();
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        mDatasList.add("sdadad");
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        DefaultTouchItem defaultTouchItem = DefaultTouchItem.initItemTouchHelper(recyclerView);
        defaultTouchItem.setOnItmeMoveListener(new ItemMoveListener() {
            @Override
            public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
                //if (srcHolder.getItemViewType() != targetHolder.getItemViewType()) return false;

                int fromPosition = srcHolder.getAdapterPosition();
                int toPostition = targetHolder.getAdapterPosition();
                Collections.swap(mDatasList, fromPosition, toPostition);
                Log.d(TAG, "fromPosition" + fromPosition + "toPosition" + toPostition);
                adapter.notifyItemMoved(fromPosition, toPostition);
                return true;
            }

            @Override
            public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {

            }
        });
        defaultTouchItem.setOnItemStateChanged(new OnItemStateChangedListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                switch (actionState) {
                    case OnItemStateChangedListener.ACTION_STATE_IDLE:
                        viewHolder.itemView.setBackgroundColor(R.color.colorPrimary);
                        break;
                    case OnItemStateChangedListener.ACTION_STATE_DRAG:
                        ViewCompat.setBackground(viewHolder.itemView, ContextCompat.getDrawable(MainActivity.this, R.drawable.select_white));
                        break;


                }
            }
        });
    }


    class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_image, viewGroup, false);
            return new MainViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int i) {
            mainViewHolder.tv_title.setText("索引:" + i);
        }

        @Override
        public int getItemCount() {
            return mDatasList.size();
        }
    }


    class MainViewHolder extends RecyclerView.ViewHolder {


        private TextView tv_title;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
