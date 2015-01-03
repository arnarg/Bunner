package org.granra.bunner.helpers;

import org.granra.bunner.entities.Player;
import org.granra.bunner.main.Bunner;

/**
 * Created by arnar on 1/3/15.
 */
public class GameWorld {

    private Player player;

    public GameWorld() {

        player = new Player(Bunner.CAMERA_WIDTH / 4, Bunner.CAMERA_HEIGHT - 64,
                32, 32);

    }

    public void update(float delta) {

        handleInput();

        player.update(delta);

    }

    private void handleInput() {}

    public Player getPlayer() { return player; }

}
