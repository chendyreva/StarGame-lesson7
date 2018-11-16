package ru.geekbrains.sprite;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Background extends Sprite {
    public Background(TextureRegion region) {
        super(region);
    }

    public void resize(Rect worldBounds) {
        this.setHeightProportion(worldBounds.getHeight());
        this.pos.set(worldBounds.pos);
    }
}
