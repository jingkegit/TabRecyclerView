package com.jke.tabrecyclerview;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by 荆柯 on 2016/10/21.
 */

public interface ItemTouchHelperViewHolder {

    /**
     * Called when the {@link ItemTouchHelper} first registers an item as being moved or swiped.
     * Implementations should update the item view to indicate it's active state.
     */
    void onItemSelected();

    /**
     * Called when the {@link ItemTouchHelper} has completed the move or swipe, and the active item
     * state should be cleared.
     */
    void onItemClear();
}
