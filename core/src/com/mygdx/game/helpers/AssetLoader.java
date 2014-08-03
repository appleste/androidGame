package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
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
    private static String windowsDataPath =
            "android" + File.separator + "assets" + File.separator + "data" + File.separator;

    public static void load() {

        String dataPath = getDataPath();

        logoTexture = new Texture(Gdx.files.internal(dataPath + "logo.png"));
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

        texture = new Texture(Gdx.files.internal(dataPath + "texture2.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        playButtonUp = textureRegion(texture, 0, 83, 29, 16);
        playButtonDown = textureRegion(texture, 29, 83, 29, 16);
        ready = textureRegion(texture, 59, 83, 34, 7);
        retry = textureRegion(texture, 59, 110, 33, 7);
        gameOver = textureRegion(texture, 59, 92, 46, 7);
        scoreboard = textureRegion(texture, 111, 83, 97, 37);
        star = textureRegion(texture, 152, 70, 10, 10);
        noStar = textureRegion(texture, 165, 70, 10, 10);
        highScore = textureRegion(texture, 59, 101, 48, 7);
        zbLogo = textureRegion(texture, 0, 55, 135, 24);
        bg = textureRegion(texture, 0, 0, 136, 43);
        grass = textureRegion(texture, 0, 43, 143, 11);
        birdDown = textureRegion(texture, 136, 0, 17, 12);
        bird = textureRegion(texture, 153, 0, 17, 12);
        birdUp = textureRegion(texture, 170, 0, 17, 12);
        deadBird1 = textureRegion(texture, 136, 27, 17, 14);
        deadBird2 = textureRegion(texture, 153, 26, 17, 15);
        deadBird3 = textureRegion(texture, 171, 26, 22, 15);

        skullUp = textureRegion(texture, 192, 0, 24, 14);
        skullUp.flip(false, true);
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = textureRegion(texture, 136, 16, 22, 3);

        TextureRegion[] birdFrames = {birdDown, bird, birdUp};
        birdAnimation = new Animation(0.06f, birdFrames);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        TextureRegion[] deadBirdFrames = {deadBird1, deadBird2, deadBird3};
        deadBirdAnimation = new Animation(0.24f, deadBirdFrames);
        deadBirdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

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

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
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

    private static String getDataPath() {
        if (System.getProperty("os.name").startsWith("Windows"))
            return windowsDataPath + File.separator;
        else
            return "data" + File.separator;
    }

    private static TextureRegion textureRegion(Texture texture, int x, int y, int width, int height) {
        TextureRegion region = new TextureRegion(texture, x, y, width, height);
        region.flip(false, true);
        return region;
    }
}