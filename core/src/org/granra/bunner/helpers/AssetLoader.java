package org.granra.bunner.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by arnar on 1/3/15.
 */
public class AssetLoader {

    public static Texture texture = new Texture(Gdx.files.internal("images/bunny.png"));

    public static Animation bunnyAnimation;
    public static TextureRegion[] bunnySprite;

    public static void load() {

        bunnySprite = TextureRegion.split(texture, 32, 32)[0];
        bunnyAnimation = new Animation(1 / 12f, bunnySprite);
        bunnyAnimation.setPlayMode(Animation.PlayMode.LOOP);

    }

    public static void dispose() {

        texture.dispose();

    }

}
