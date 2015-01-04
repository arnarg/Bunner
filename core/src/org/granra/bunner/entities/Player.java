package org.granra.bunner.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import org.granra.bunner.helpers.B2DVars;

/**
 * Created by arnar on 1/3/15.
 */
public class Player {

    private Body body;

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

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.position.set(x / B2DVars.PPM, y / B2DVars.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.linearVelocity.set(0f, 0f);
        body = world.createBody(bdef);

        shape.setAsBox(this.width / 2 / B2DVars.PPM, this.height / 2 / B2DVars.PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = 2;
        fdef.filter.maskBits = -1;
        body.createFixture(fdef).setUserData("player");

    }

    public void jump() {

        if (nrOfJumps < 2) {

            nrOfJumps++;
            body.setLinearVelocity(0, 6f);

        }

    }

    public void land() { nrOfJumps = 0; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public Body getBody() { return body; }

}
