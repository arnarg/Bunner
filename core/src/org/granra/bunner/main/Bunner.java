package org.granra.bunner.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Bunner extends Game {

    public static final String TITLE = "Bunner";
    public static final float WIDTH = 800, HEIGHT = 480;

    public static float CAMERA_HEIGHT;
    public static float CAMERA_WIDTH;

	@Override
	public void create () {

        CAMERA_HEIGHT = 8 * 32;
        CAMERA_WIDTH = CAMERA_HEIGHT * ((float)Gdx.graphics.getWidth() / Gdx.graphics.getHeight());

	}

    @Override
    public void dispose() {

        super.dispose();

    }

}
