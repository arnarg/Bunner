package org.granra.bunner.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.granra.bunner.main.Bunner;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = Bunner.TITLE;
        config.height = (int)Bunner.HEIGHT;
        config.width = (int)Bunner.WIDTH;
		new LwjglApplication(new Bunner(), config);
	}
}
