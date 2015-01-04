package org.granra.bunner.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import org.granra.bunner.entities.Player;
import org.granra.bunner.main.Bunner;
import org.granra.bunner.screens.GameScreen;

/**
 * Created by arnar on 1/3/15.
 */
public class GameRenderer {

    private GameScreen screen;

    private GameWorld world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera cam;
    private OrthographicCamera b2dCam;
    private OrthographicCamera hudCam;

    private TiledMap tileMap;
    private OrthogonalTiledMapRenderer tmr;

    private Player player;

    private SpriteBatch batch;

    public GameRenderer(GameScreen screen, GameWorld world) {

        this.screen = screen;
        this.world = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Bunner.CAMERA_WIDTH, Bunner.CAMERA_HEIGHT);

        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, Bunner.CAMERA_WIDTH, Bunner.CAMERA_HEIGHT);

        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false,
                Bunner.CAMERA_WIDTH / B2DVars.PPM, Bunner.CAMERA_HEIGHT / B2DVars.PPM);
        b2dr = new Box2DDebugRenderer();

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        tileMap = new TmxMapLoader().load("maps/level.tmx");
        tmr = new OrthogonalTiledMapRenderer(tileMap);

        world.createEnvironment(tileMap);

        player = world.getPlayer();

    }

    public void render(float runTime) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (screen.getState() != GameState.WON && screen.getState() != GameState.GAME_OVER)
            cam.position.set(
                player.getBody().getPosition().x * B2DVars.PPM + Bunner.CAMERA_WIDTH / 4,
                Bunner.CAMERA_HEIGHT / 2, 0
            );
        cam.update();

        tmr.setView(cam);
        tmr.render();

        batch.setProjectionMatrix(cam.combined);

        batch.begin();
        batch.draw((screen.getState() == GameState.READY ? AssetLoader.bunnySprite[0]
                        : AssetLoader.bunnyAnimation.getKeyFrame(runTime)),
                player.getBody().getPosition().x * B2DVars.PPM - 16,
                player.getBody().getPosition().y * B2DVars.PPM - 16,
                player.getWidth(), player.getHeight());
        batch.end();

        batch.setProjectionMatrix(hudCam.combined);

        if (screen.getState() != GameState.PLAYING) {

            batch.begin();
            switch (screen.getState()) {

                case READY:
                    AssetLoader.largeFont.draw(batch, 3 - (int)runTime + "",
                            Bunner.CAMERA_WIDTH / 2 - 10, Bunner.CAMERA_HEIGHT / 2);
                    break;
                case GAME_OVER:
                    AssetLoader.largeFont.draw(batch, "Try Again?",
                            Bunner.CAMERA_WIDTH / 2 - 100, Bunner.CAMERA_HEIGHT / 2);
                    break;
                case WON:
                    AssetLoader.largeFont.draw(batch, "You Win!",
                            Bunner.CAMERA_WIDTH / 2 - 80, Bunner.CAMERA_HEIGHT / 2);
                    break;
            }
            batch.end();

        }

        // Draw box2d world
        if (Bunner.DEBUG) b2dr.render(world.getWorld(), b2dCam.combined);

    }

    public void dispose() {

        b2dr.dispose();
        tileMap.dispose();
        tmr.dispose();
        batch.dispose();

    }

}
