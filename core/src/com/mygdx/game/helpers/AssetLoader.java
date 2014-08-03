package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.io.File;

public class AssetLoader {

	public static Texture texture, logoTexture;
	public static TextureRegion logo, zbLogo, bg, grass, bird, birdDown,
			birdUp, skullUp, skullDown, bar, playButtonUp, playButtonDown,
			ready, gameOver, highScore, scoreboard, star, noStar, retry,
    deadBird1, deadBird2, deadBird3;
	public static Animation birdAnimation, deadBirdAnimation;
	public static Sound dead, flap, coin, fall;
	public static BitmapFont font, shadow, whiteFont;
	private static Preferences prefs;
    private static String windowsDataPath = "android\\assets\\data\\";

	public static void load() {

        String dataPath = getDataPath();

        logoTexture = new Texture(Gdx.files.internal(dataPath + "logo.png"));
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

		texture = new Texture(Gdx.files.internal(dataPath + "texture2.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
		playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
		playButtonUp.flip(false, true);
		playButtonDown.flip(false, true);

		ready = new TextureRegion(texture, 59, 83, 34, 7);
		ready.flip(false, true);

		retry = new TextureRegion(texture, 59, 110, 33, 7);
		retry.flip(false, true);
		
		gameOver = new TextureRegion(texture, 59, 92, 46, 7);
		gameOver.flip(false, true);

		scoreboard = new TextureRegion(texture, 111, 83, 97, 37);
		scoreboard.flip(false, true);

		star = new TextureRegion(texture, 152, 70, 10, 10);
        star.flip(false, true);

		noStar = new TextureRegion(texture, 165, 70, 10, 10);
		noStar.flip(false, true);

		highScore = new TextureRegion(texture, 59, 101, 48, 7);
		highScore.flip(false, true);

		zbLogo = new TextureRegion(texture, 0, 55, 135, 24);
		zbLogo.flip(false, true);

		bg = new TextureRegion(texture, 0, 0, 136, 43);
		bg.flip(false, true);

		grass = new TextureRegion(texture, 0, 43, 143, 11);
		grass.flip(false, true);

		birdDown = new TextureRegion(texture, 136, 0, 17, 12);
		birdDown.flip(false, true);

		bird = new TextureRegion(texture, 153, 0, 17, 12);
		bird.flip(false, true);

		birdUp = new TextureRegion(texture, 170, 0, 17, 12);
		birdUp.flip(false, true);

        deadBird1 = new TextureRegion(texture, 136, 27, 17, 14);
        deadBird1.flip(false, true);

        deadBird2 = new TextureRegion(texture, 153, 26, 17, 15);
        deadBird2.flip(false, true);

        deadBird3 = new TextureRegion(texture, 171, 26, 22, 15);
		deadBird3.flip(false, true);


		TextureRegion[] birdFrames = { birdDown, bird, birdUp };
		birdAnimation = new Animation(0.06f, birdFrames);
		birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        TextureRegion[] deadBirdFrames = { deadBird1, deadBird2, deadBird3 };
        deadBirdAnimation = new Animation(0.24f, deadBirdFrames);
        deadBirdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);


        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
		// Create by flipping existing skullUp
		skullDown = new TextureRegion(skullUp);
		skullDown.flip(false, true);

		bar = new TextureRegion(texture, 136, 16, 22, 3);
		bar.flip(false, true);

		dead = Gdx.audio.newSound(Gdx.files.internal(dataPath + "dead.wav"));
		flap = Gdx.audio.newSound(Gdx.files.internal(dataPath + "flap.wav"));
		coin = Gdx.audio.newSound(Gdx.files.internal(dataPath + "coin.wav"));
		fall = Gdx.audio.newSound(Gdx.files.internal(dataPath + "fall.wav"));

		font = new BitmapFont(Gdx.files.internal(dataPath + "text.fnt"));
		font.setScale(.25f, -.25f);

		whiteFont = new BitmapFont(Gdx.files.internal(dataPath + "whitetext.fnt"));
		whiteFont.setScale(.1f, -.1f);

		shadow = new BitmapFont(Gdx.files.internal(dataPath + "shadow.fnt"));
		shadow.setScale(.25f, -.25f);

		// Create (or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("ZombieBird");

		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
	}

	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}

	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		texture.dispose();

		// Dispose sounds
		dead.dispose();
		flap.dispose();
		coin.dispose();

		font.dispose();
		shadow.dispose();
	}

    public static String getDataPath() {
        if (System.getProperty("os.name").startsWith("Windows"))
            return windowsDataPath + File.separator ;
        else
            return "data" + File.separator;
    }
}