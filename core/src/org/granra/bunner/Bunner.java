package org.granra.bunner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bunner extends ApplicationAdapter {

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
