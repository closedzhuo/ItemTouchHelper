package com.bonnidee.drawrecyclerviewdemo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class DefaultItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private  ItemMoveListener itemMoveListener;
    private OnItemStateChangedListener onItemSateChanageListener;

    // 这个是用来设置用户可以对 viewHolder进行什么操作，
    // 推荐用makeMovementFlags(int dragFlags, int swipeFlags)来处理
    // * 例如 makeMovementFlags(UP | DOWN, LEFT);
    // 就是可以上下拖拽，向左滑动 * @param recyclerView * @param viewHolder
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                int swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                return makeMovementFlags(dragFlags, swipeFlags);
            } else {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                return makeMovementFlags(dragFlags, swipeFlags);
            }
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                int dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                int swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                return makeMovementFlags(dragFlags, swipeFlags);
            } else {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                return makeMovementFlags(dragFlags, swipeFlags);
            }
        }

        return makeMovementFlags(0, 0);
    }
    /*  这个是拖拽的回调
     * @param recyclerView *
     * @param viewHolder 这个是我们拖拽中的ViewHolder *
     * @param target 这个是离我们拖拽ViewHolder最近的ViewHolder，
     * 也就是我们松手后需要替换的ViewHolder *
     * @return 返回true的话 我们已经做好相关操作了，
     * false就我们没做啥操作

     */

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        if (itemMoveListener != null) {
            return itemMoveListener.onItemMove(viewHolder, viewHolder1);
        }
        return false;
    }
    //这个是滑动的回调，这次关注拖拽
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {



    }
    //当选中时候做的事
    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (onItemSateChanageListener != null && actionState != OnItemStateChangedListener.ACTION_STATE_IDLE) {
            onItemSateChanageListener.onSelectedChanged(viewHolder, actionState);
        }
    }
    //是否需要滑动删除
    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }
    //是否可以替换
    @Override
    public boolean canDropOver(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder current, @NonNull RecyclerView.ViewHolder target) {
        return super.canDropOver(recyclerView, current, target);
        //汽车之家最后一个为加号，所以不支持拖拽
        // if (target.getAdapterPosition() == recyclerView.getAdapter().getItemCount() -1){
        // return false; }
        // return super.canDropOver(recyclerView, current, target);



    }

    public void setOnItmeMoveListener(ItemMoveListener listener) {
        this.itemMoveListener = listener;
    }

    public void setOnItemStateChanged(OnItemStateChangedListener listener) {
        this.onItemSateChanageListener = listener;
    }
}
