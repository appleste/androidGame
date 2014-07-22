package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.helpers.AssetLoader;
import com.mygdx.game.screen.GameScreen;

public class ZBGame extends Game {

    @Override
    public void create() {
        System.out.println("ZBGame Created!");
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

}