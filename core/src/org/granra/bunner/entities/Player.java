package org.granra.bunner.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by arnar on 1/3/15.
 */
public class Player {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private int width;
    private int height;

    private int nrOfJumps;

    public Player(float x, float y, int width, int height) {

        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);

        nrOfJumps = 0;

    }

    public void update(float delta) {}

}
