package org.granra.bunner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

import org.granra.bunner.helpers.GameRenderer;
import org.granra.bunner.helpers.GameState;
import org.granra.bunner.helpers.GameWorld;

/**
 * Created by arnar on 1/3/15.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;

    private float runTime;

    public GameState state;

    public GameScreen() {

        runTime = 0;
        state = GameState.READY;

        world = new GameWorld(this);
        renderer = new GameRenderer(this, world);

    }

    @Override
    public void render(float delta) {

        handleInput();

        runTime += delta;
        if (runTime >= 3) state = GameState.PLAYING;
        if (state != GameState.READY) world.update(delta);
        renderer.render(runTime);

    }

    private void handleInput() {

        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

            switch (state) {

                case READY:
                    break;
                case PLAYING:
                    world.getPlayer().jump();
                    break;
                case GAME_OVER:
                    world.reset();
                    state = GameState.READY;
                    runTime = 0;
                    break;
                case WON:
                    world.reset();
                    state = GameState.READY;
                    runTime = 0;
                    break;
            }

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {

            state = GameState.READY;
            world.reset();

        }

    }

    @Override
    public void show() {

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

        renderer.dispose();
        world.dispose();

    }

}
