package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.geekbrains.base.ActionListener;
import ru.geekbrains.base.ScaledTouchUpButton;
import ru.geekbrains.math.Rect;

public class ButtonExit extends ScaledTouchUpButton {
    public ButtonExit(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("btExit"), actionListener);
    }

    public void resize(Rect worldBounds) {
        this.setBottom(worldBounds.getBottom());
        this.setRight(worldBounds.getRight());
    }
}
