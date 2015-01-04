package org.granra.bunner.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by arnar on 1/3/15.
 */
public class AssetLoader {

    public static Texture texture = new Texture(Gdx.files.internal("images/bunny.png"));

    public static Animation bunnyAnimation;
    public static TextureRegion[] bunnySprite;
    public static BitmapFont largeFont;
    public static BitmapFont smallFont;
    public static Sound jump;
    public static Sound death;

    public static void load() {

        bunnySprite = TextureRegion.split(texture, 32, 32)[0];
        bunnyAnimation = new Animation(1 / 12f, bunnySprite);
        bunnyAnimation.setPlayMode(Animation.PlayMode.LOOP);

        largeFont = new BitmapFont(Gdx.files.internal("fonts/largeFont.fnt"));
        largeFont.setColor(0, 0, 0, 1);
        smallFont = new BitmapFont(Gdx.files.internal("fonts/smallFont.fnt"));
        smallFont.setColor(0, 0, 0, 1);
        smallFont.setScale(.5f);

        jump = Gdx.audio.newSound(Gdx.files.internal("sounds/jump.wav"));
        death = Gdx.audio.newSound(Gdx.files.internal("sounds/death.wav"));

    }

    public static void dispose() {

        texture.dispose();
        largeFont.dispose();
        smallFont.dispose();

    }

}
