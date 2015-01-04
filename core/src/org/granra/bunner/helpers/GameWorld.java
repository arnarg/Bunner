package org.granra.bunner.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import org.granra.bunner.entities.Player;
import org.granra.bunner.main.Bunner;

/**
 * Created by arnar on 1/3/15.
 */
public class GameWorld {

    private Player player;
    private World world;
    private MyContactListener cl;

    public GameWorld() {

        world = new World(new Vector2(0f, -20f), false);
        cl = new MyContactListener(this);
        world.setContactListener(cl);
        player = new Player(Bunner.CAMERA_WIDTH / 4, 32, 32, 32, world);

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.position.set(Bunner.CAMERA_WIDTH / 4 / B2DVars.PPM, 16 / B2DVars.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bdef);

        shape.setAsBox(50 / B2DVars.PPM, 16 / B2DVars.PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = 4;
        fdef.filter.maskBits = -1;
        body.createFixture(fdef);

    }

    public void update(float delta) {

        handleInput();

        world.step(delta, 4, 2);

    }

    private void handleInput() {

        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            player.jump();

    }

    public Player getPlayer() { return player; }
    public World getWorld() { return world; }

}
