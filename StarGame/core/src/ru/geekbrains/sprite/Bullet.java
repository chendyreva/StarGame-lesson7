package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Bullet extends Sprite {
    private Rect worldBounds;
    private Vector2 v = new Vector2();
    private int damage;
    private Object owner;

    public Bullet() {
        this.regions = new TextureRegion[1];
    }

    public void set(Object owner, TextureRegion region, Vector2 pos0, Vector2 v0, float height, Rect worldBounds, int damage) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.v.set(v0);
        this.setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
    }

    public void update(float delta) {
        this.pos.mulAdd(this.v, delta);
        if (this.isOutside(this.worldBounds)) {
            this.destroy();
        }

    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Object getOwner() {
        return this.owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }
}
