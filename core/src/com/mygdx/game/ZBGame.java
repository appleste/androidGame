package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.helpers.AssetLoader;
import com.mygdx.game.screen.SplashScreen;


public class ZBGame extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}