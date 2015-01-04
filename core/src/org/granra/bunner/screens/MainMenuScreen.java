package org.granra.bunner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.granra.bunner.helpers.AssetLoader;
import org.granra.bunner.main.Bunner;

/**
 * Created by arnar on 1/4/15.
 */
public class MainMenuScreen implements Screen {

    final Bunner game;

    private OrthographicCamera cam;

    private SpriteBatch batch;

    private float runTime;

    public MainMenuScreen(final Bunner game) {

        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Bunner.CAMERA_WIDTH, Bunner.CAMERA_HEIGHT);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        runTime = 0;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        handleInput();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        runTime += delta;

        batch.begin();

        // Draw text
        AssetLoader.largeFont.draw(batch, "Tap to begin!",
                Bunner.CAMERA_WIDTH / 2 - 120, Bunner.CAMERA_HEIGHT / 2);

        // Draw instructions
        AssetLoader.smallFont.draw(batch, "Tap = Jump", 10, 15);
        AssetLoader.smallFont.draw(batch, "Double-tap = High-jump", Bunner.CAMERA_WIDTH - 128, 15);

        // Draw bunner
        batch.draw(AssetLoader.bunnyAnimation.getKeyFrame(runTime), Bunner.CAMERA_WIDTH / 2 - 25, 40, 50, 50);

        batch.end();

    }

    public void handleInput() {

        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            game.setScreen(game.gameScreen);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
