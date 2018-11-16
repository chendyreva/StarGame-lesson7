package ru.geekbrains;

import com.badlogic.gdx.Game;
import ru.geekbrains.screen.MenuScreen;

public class Star2DGame extends Game {
    public Star2DGame() {
    }

    public void create() {
        this.setScreen(new MenuScreen(this));
    }
}
