package com.javacore.yushkovartem.profile;

import com.javacore.yushkovartem.common.BaseView;
import com.javacore.yushkovartem.common.Canvas;
import com.javacore.yushkovartem.common.CompositeView;

public class ProfileView extends CompositeView {
    @Override
    public void draw() {
        System.out.println("Criminal Profile view");

        System.out.println("ID: " + ((ProfileModel)model).getId());
        System.out.println("Name: " + ((ProfileModel)model).getName());
        System.out.println("Active: " + ((ProfileModel)model).getName());
    }

    @Override
    public void draw(Canvas canvas){
        for(BaseView view : children){
            view.draw();
        }
//        canvas.drawText("Criminal Profile view");
//
//        canvas.drawText("ID: " + ((ProfileModel)model).getId());
//        canvas.drawText("Name: " + ((ProfileModel)model).getName());
//        canvas.drawText("Active: " + ((ProfileModel)model).getName());
    }

    public void init() {
        ProfilePhotoView photoView = new ProfilePhotoView(30, 2, 10);
        children.add(photoView);

    }
}
