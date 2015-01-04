package org.granra.bunner.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
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

        world = new World(new Vector2(0f, 0f), false);
        cl = new MyContactListener();
        world.setContactListener(cl);
        player = new Player(Bunner.CAMERA_WIDTH / 4, 32, 32, 32, world);

    }

    public void update(float delta) {

        handleInput();

        player.update(delta);

    }

    private void handleInput() {

        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            player.jump();

    }

    public Player getPlayer() { return player; }
    public World getWorld() { return world; }

}
