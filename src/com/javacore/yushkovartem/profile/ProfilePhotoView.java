package com.javacore.yushkovartem.profile;

import com.javacore.yushkovartem.common.BaseView;
import com.javacore.yushkovartem.common.Canvas;

public class ProfilePhotoView extends BaseView {

    private int x;
    private int y;
    private int size;

    public ProfilePhotoView(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawSquareAt(x,y,size);
    }
}
