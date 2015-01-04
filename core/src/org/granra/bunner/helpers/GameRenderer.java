package org.granra.bunner.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import org.granra.bunner.entities.Player;
import org.granra.bunner.main.Bunner;

/**
 * Created by arnar on 1/3/15.
 */
public class GameRenderer {

    private static final boolean debug = true;

    private GameWorld world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera cam;
    private OrthographicCamera b2dCam;

    private Player player;

    private SpriteBatch batch;

    public GameRenderer(GameWorld world) {

        this.world = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Bunner.CAMERA_WIDTH, Bunner.CAMERA_HEIGHT);

        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, Bunner.CAMERA_WIDTH, Bunner.CAMERA_HEIGHT);
        b2dr = new Box2DDebugRenderer();

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        player = world.getPlayer();

    }

    public void render(float runTime) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();

        batch.begin();
        batch.draw(AssetLoader.bunnyAnimation.getKeyFrame(runTime),
                player.getX(), player.getY(), player.getWidth(), player.getHeight());
        batch.end();

        // Draw box2d world
        if (debug) b2dr.render(world.getWorld(), b2dCam.combined);

    }

}
