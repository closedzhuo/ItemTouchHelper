package com.bonnidee.drawrecyclerviewdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class DefaultTouchItem extends ItemTouchHelper {

    private DefaultItemTouchHelperCallback mDefaultItemTouchHelperCallback;
    private static DefaultTouchItem defaultTouchItem;
    private ItemMoveListener itemMoveListener;

    public DefaultTouchItem(@NonNull Callback callback) {
        super(callback);
        mDefaultItemTouchHelperCallback = (DefaultItemTouchHelperCallback) callback;
    }

    public DefaultTouchItem() {
        this(new DefaultItemTouchHelperCallback());

    }


    public static DefaultTouchItem initItemTouchHelper(RecyclerView recyclerView) {
        if (defaultTouchItem == null) {
            defaultTouchItem = new DefaultTouchItem();
            defaultTouchItem.attachToRecyclerView(recyclerView);
        }
        return defaultTouchItem;
    }


    public void setOnItmeMoveListener(ItemMoveListener listener) {
       mDefaultItemTouchHelperCallback.setOnItmeMoveListener(listener);
    }
    public void setOnItemStateChanged(OnItemStateChangedListener listener) {
        mDefaultItemTouchHelperCallback.setOnItemStateChanged(listener);
    }

}
