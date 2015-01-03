package org.granra.bunner.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import org.granra.bunner.entities.Player;
import org.granra.bunner.main.Bunner;

/**
 * Created by arnar on 1/3/15.
 */
public class GameWorld {

    private Player player;

    public GameWorld() {

        player = new Player(Bunner.CAMERA_WIDTH / 4, 32, 32, 32);

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

}
