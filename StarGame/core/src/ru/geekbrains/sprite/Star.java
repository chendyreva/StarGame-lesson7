package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;

public class Star extends Sprite {
    private Vector2 v = new Vector2();
    private Rect worldBounds;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        this.setHeightProportion(0.01F);
        this.v.set(Rnd.nextFloat(-0.005F, 0.005F), Rnd.nextFloat(-0.5F, -0.1F));
    }

    public void update(float delta) {
        this.pos.mulAdd(this.v, delta);
        this.checkAndHandleBounds();
    }

    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        this.pos.set(posX, posY);
    }

    private void checkAndHandleBounds() {
        if (this.getRight() < this.worldBounds.getLeft()) {
            this.setLeft(this.worldBounds.getRight());
        }

        if (this.getLeft() > this.worldBounds.getRight()) {
            this.setRight(this.worldBounds.getLeft());
        }

        if (this.getTop() < this.worldBounds.getBottom()) {
            this.setBottom(this.worldBounds.getTop());
        }

        if (this.getBottom() > this.worldBounds.getTop()) {
            this.setTop(this.worldBounds.getBottom());
        }

    }
}
