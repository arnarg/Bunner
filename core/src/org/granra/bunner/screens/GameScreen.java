package org.granra.bunner.screens;

import com.badlogic.gdx.Screen;

import org.granra.bunner.helpers.GameRenderer;
import org.granra.bunner.helpers.GameWorld;

/**
 * Created by arnar on 1/3/15.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;

    private float runTime;

    public GameScreen() {

        runTime = 0;

        world = new GameWorld();
        renderer = new GameRenderer(world);

    }

    @Override
    public void render(float delta) {

        runTime += delta;
        world.update(delta);
        renderer.render(runTime);

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

    }

}
