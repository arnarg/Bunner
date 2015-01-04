package org.granra.bunner.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import org.granra.bunner.helpers.B2DVars;

/**
 * Created by arnar on 1/3/15.
 */
public class Player {

    private Body body;
    private BodyDef bdef;
    private FixtureDef fdef;
    private PolygonShape pShape;
    private CircleShape cShape;

    private int width;
    private int height;

    private int nrOfJumps;

    public Player(float x, float y, int width, int height, World world) {

        this.width = width;
        this.height = height;

        createBody(x, y, world);

        nrOfJumps = 0;

    }

    private void createBody(float x, float y, World world) {

        bdef = new BodyDef();
        fdef = new FixtureDef();
        pShape = new PolygonShape();
        cShape = new CircleShape();

        bdef.position.set(x / B2DVars.PPM, y / B2DVars.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.linearVelocity.set(2.5f, 0f);
        body = world.createBody(bdef);

        // Create body
        pShape.setAsBox(8f / B2DVars.PPM, 6f / B2DVars.PPM,
                new Vector2(-5f / B2DVars.PPM, -9f / B2DVars.PPM), 0);
        fdef.shape = pShape;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = -1;
        body.createFixture(fdef).setUserData("player");

        // Create head
        cShape.setRadius(9f / B2DVars.PPM);
        cShape.setPosition(new Vector2(4f / B2DVars.PPM, -2f / B2DVars.PPM));
        fdef.shape = cShape;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = -1;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("head");

        // Create foot sensor
        pShape.setAsBox(6f / B2DVars.PPM, 2f / B2DVars.PPM,
                new Vector2(-5f / B2DVars.PPM, -15f / B2DVars.PPM), 0);
        fdef.shape = pShape;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = -1;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("foot");

    }

    public void jump() {

        if (nrOfJumps < 2) {

            nrOfJumps++;
            body.setLinearVelocity(2.5f, 6f);

        }

    }

    public void land() { nrOfJumps = 0; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public Body getBody() { return body; }

    public void dispose() {

        pShape.dispose();
        cShape.dispose();

    }

}
