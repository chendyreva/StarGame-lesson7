package ru.geekbrains.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
    import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
    import ru.geekbrains.Star2DGame;

public class DesktopLauncher {
    public DesktopLauncher() {
    }

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        float aspect = 0.75F;
        config.height = 500;
        config.width = (int)((float)config.height * aspect);
        config.resizable = false;
        new LwjglApplication(new Star2DGame(), config);
    }
}


