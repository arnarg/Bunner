package org.granra.bunner.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by arnar on 1/4/15.
 */
public class MyContactListener implements ContactListener {

    private GameWorld world;

    public MyContactListener(GameWorld world) {

        super();
        this.world = world;

    }

    @Override
    public void beginContact(Contact contact) {

        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa.getUserData() != null && fa.getUserData().equals("foot"))
            world.getPlayer().land();
        if (fb.getUserData() != null && fb.getUserData().equals("foot"))
            world.getPlayer().land();

        if (fa.getUserData() != null && fa.getUserData().equals("head"))
            world.kill();
        if (fb.getUserData() != null && fb.getUserData().equals("head"))
            world.kill();

    }

    @Override
    public void endContact(Contact contact) {

        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
