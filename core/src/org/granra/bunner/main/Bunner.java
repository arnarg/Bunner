package org.granra.bunner.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import org.granra.bunner.helpers.AssetLoader;
import org.granra.bunner.screens.GameScreen;
import org.granra.bunner.screens.MainMenuScreen;

public class Bunner extends Game {

    public static final boolean DEBUG = false;

    public static final String TITLE = "Bunner";
    public static final float WIDTH = 800, HEIGHT = 480;

    public static float CAMERA_HEIGHT;
    public static float CAMERA_WIDTH;

    public GameScreen gameScreen;
    public MainMenuScreen menuScreen;

	@Override
	public void create () {

	// The map is 8 tiles high and each tile is 32x32px
        CAMERA_HEIGHT = 8 * 32;
        // Calculating width from the height relative to the screens aspect ratio
        CAMERA_WIDTH = CAMERA_HEIGHT * ((float)Gdx.graphics.getWidth() / Gdx.graphics.getHeight());

        AssetLoader.load();
        gameScreen = new GameScreen(this);
        menuScreen = new MainMenuScreen(this);
        setScreen(menuScreen);

	}

    @Override
    public void dispose() {

        super.dispose();
        AssetLoader.dispose();
        gameScreen.dispose();
        menuScreen.dispose();

    }

}
