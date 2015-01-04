package org.granra.bunner.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import org.granra.bunner.entities.Player;
import org.granra.bunner.main.Bunner;
import org.granra.bunner.screens.GameScreen;

/**
 * Created by arnar on 1/3/15.
 */
public class GameWorld {

    private GameScreen screen;

    private Player player;
    private World world;
    private MyContactListener cl;

    private float levelEnd;

    public GameWorld(GameScreen screen) {

        this.screen = screen;
        world = new World(new Vector2(0f, -20f), false);
        cl = new MyContactListener(this);
        world.setContactListener(cl);
        player = new Player(Bunner.CAMERA_WIDTH / 4, 50, 32, 32, world);

    }

    public void update(float delta) {

        world.step(delta, 2, 2);

        if (screen.getState() == GameState.PLAYING && player.getBody().getPosition().y < 0f)
            kill();
        if (screen.getState() == GameState.PLAYING &&
                player.getBody().getPosition().x * B2DVars.PPM >= levelEnd)
            screen.setState(GameState.WON);

    }

    public void kill() {

        AssetLoader.death.play(.5f);
        player.setMaskBits((short)0);
        screen.setState(GameState.GAME_OVER);
        player.getBody().setLinearVelocity(new Vector2(0f, -2f));

    }

    public void reset() {

        player.setMaskBits((short)-1);
        player.getBody().setTransform(Bunner.CAMERA_WIDTH / 4 / B2DVars.PPM,
                50f / B2DVars.PPM, player.getBody().getAngle());
        player.getBody().setLinearVelocity(new Vector2(2.5f, 0f));

    }

    public void createEnvironment(TiledMap tileMap) {

        levelEnd = tileMap.getProperties().get("width", Integer.class)
                * B2DVars.tileSize - (12 * B2DVars.tileSize);

        MapLayer layer;
        layer = tileMap.getLayers().get("ground");
        createLayer(layer, B2DVars.BIT_GROUND);
        layer = tileMap.getLayers().get("walls");
        createLayer(layer, B2DVars.BIT_WALL);

    }

    private void createLayer(MapLayer layer, short bits) {

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();

        for (MapObject mo : layer.getObjects()) {

            bdef.type = BodyDef.BodyType.StaticBody;
            Rectangle p = ((RectangleMapObject) mo).getRectangle();

            float x = (p.getX() + (p.getWidth() / 2)) / B2DVars.PPM;
            float y = (p.getY() + (p.getHeight() / 2)) / B2DVars.PPM;

            bdef.position.set(x, y);

            PolygonShape pShape = new PolygonShape();
            pShape.setAsBox(p.getWidth() / 2 / B2DVars.PPM, p.getHeight() / 2 / B2DVars.PPM);

            fdef.friction = 0;
            fdef.shape = pShape;
            fdef.filter.categoryBits = bits;
            fdef.filter.maskBits = B2DVars.BIT_PLAYER;
            fdef.isSensor = false;
            world.createBody(bdef).createFixture(fdef);

        }

    }

    public Player getPlayer() { return player; }
    public World getWorld() { return world; }
    public GameScreen getScreen() {return screen; }

    public void dispose() {

        player.dispose();
        world.dispose();

    }

}
