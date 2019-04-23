package com.javacore.yushkovartem.profile;

import java.util.HashMap;
import java.util.Map;

public enum ProfileStore {
    INSTANCE;

    Map<Integer, ProfileModel> profiles;

    {
        profiles = new HashMap<>();
    }

    public void loadDate() {
        profiles.clear();
        for (int i = 0; i < 50; i++) {
            ProfileModel model = ProfileModel.randomModel();
            profiles.put(model.getId(), model);
        }
    }

    public ProfileModel getProfile(int id){
        return profiles.get(id);
    }

}
