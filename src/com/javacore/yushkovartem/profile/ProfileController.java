package com.javacore.yushkovartem.profile;

import com.javacore.yushkovartem.common.ConsoleCanvas;

public class ProfileController {

    private ProfileModel profileModel;
    private ProfileView view;
    private ProfileStore store;
    private ConsoleCanvas canvas;

    {
        store.INSTANCE.loadDate();
        view = new ProfileView();
        canvas = new ConsoleCanvas(60, 60);
        view.init();
    }

    public void showProfile(int id){

        ProfileModel model = store.INSTANCE.getProfile(id);
        if (model == null) {
            System.out.println("No record with id: "  + id);
        } else {
            view.setModel(model);
            view.draw(canvas);
            canvas.drawSquare(5);
        }
    }

    public ProfileModel getProfileModel() {
        return profileModel;
    }

    public void setProfileModel(ProfileModel profileModel) {
        this.profileModel = profileModel;
    }

    public ProfileView getView() {
        return view;
    }

    public void setView(ProfileView view) {
        this.view = view;
    }
}
