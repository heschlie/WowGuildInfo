package com.heschlie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by heschlie on 4/23/2015. Copyright under Iridium Flare Games LLC.
 */
public class Assets {

    private static Assets instance;
    public Skin skin;

    private Assets() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
    }

    public static void createInstance() {
        if (instance == null) {
            instance = new Assets();
        }
    }

    public static Assets getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
}
