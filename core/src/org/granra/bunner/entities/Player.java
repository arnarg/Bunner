package org.granra.bunner.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by arnar on 1/3/15.
 */
public class Player {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private Body body;

    private int width;
    private int height;

    private int nrOfJumps;

    public Player(float x, float y, int width, int height, World world) {

        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);

        createBody(x, y, world);

        nrOfJumps = 0;

    }

    private void createBody(float x, float y, World world) {

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.position.set(x + (this.width / 2), y + (this.height / 2));
        bdef.type = BodyDef.BodyType.KinematicBody;
        bdef.linearVelocity.set(0f, 0f);
        body = world.createBody(bdef);

        shape.setAsBox(this.width / 2, this.height / 2);
        fdef.shape = shape;
        body.createFixture(fdef).setUserData("player");

    }

    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        position.add(velocity.cpy().scl(delta));

        body.setTransform(position.x + (width / 2), position.y + (height / 2), body.getAngle());

        if (onGround()) {

            position.y = 32;
            acceleration.y = 0;
            velocity.y = 0;
            nrOfJumps = 0;

        }

    }

    public void jump() {

        if (nrOfJumps < 2) {

            nrOfJumps++;
            acceleration.y = -1500;
            velocity.y = 530;

        }

    }

    public float getY() { return position.y; }
    public float getX() { return position.x; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public Body getBody() { return body; }
    public boolean onGround() { return position.y <= 32; }

}
