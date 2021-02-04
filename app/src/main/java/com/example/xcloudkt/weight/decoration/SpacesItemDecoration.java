package com.example.xcloudkt.weight.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private Decoration orientation;

    public SpacesItemDecoration(int space, Decoration orientation) {
        this.space = space;
        this.orientation = orientation;
    }

    public int getSpace() {
        return space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (orientation == Decoration.HORIZONTAL) {
            outRect.left = space;
        } else {
            outRect.top = space;
        }
    }
}
