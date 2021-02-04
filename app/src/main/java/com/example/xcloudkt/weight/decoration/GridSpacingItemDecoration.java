package com.example.xcloudkt.weight.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int spanCount;//列数
    private int horizontalSpacing;//横向间隔距离
    private int verticalSpacing;//垂直方向的间隔距离
    private boolean includeEdge;//是否包含边缘

    public GridSpacingItemDecoration(int spanCount, int horizontalSpace, int verticalSpace, boolean includeEdge) {
        this.spanCount = spanCount;
        this.horizontalSpacing = horizontalSpace;
        this.verticalSpacing = verticalSpace;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = horizontalSpacing - column * horizontalSpacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * horizontalSpacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = verticalSpacing;
            }
            outRect.bottom = verticalSpacing; // item bottom
        } else {
            outRect.left = column * horizontalSpacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = horizontalSpacing - (column + 1) * horizontalSpacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = verticalSpacing; // item top
            }
        }

    }
}
