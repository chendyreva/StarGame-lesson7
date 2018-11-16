package ru.geekbrains.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.base.Ship;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.ExplosionPool;

public class Enemy extends Ship {
    private Vector2 v0 = new Vector2();
    private Enemy.State state;
    private Vector2 descentV = new Vector2(0.0F, -0.15F);

    public Enemy(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound shootSound) {
        super(shootSound);
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
        this.v.set(this.v0);
    }

    public void update(float delta) {
        super.update(delta);
        this.pos.mulAdd(this.v, delta);
        switch(this.state) {
            case DESCENT:
                if (this.getTop() <= this.worldBounds.getTop()) {
                    this.v.set(this.v0);
                    this.state = Enemy.State.FIGHT;
                }
                break;
            case FIGHT:
                this.reloadTimer += delta;
                if (this.reloadTimer >= this.reloadInterval) {
                    this.shoot();
                    this.reloadTimer = 0.0F;
                }

                if (this.getBottom() < this.worldBounds.getBottom()) {
                    this.boom();
                    this.destroy();
                }
        }

    }

    public void set(TextureRegion[] regions, Vector2 v0, TextureRegion bulletRegion, float bulletHeight, float bulletVY, int bulletDamage, float reloadInterval, float height, int hp) {
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0.0F, bulletVY);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        this.hp = hp;
        this.reloadTimer = reloadInterval;
        this.setHeightProportion(height);
        this.v.set(this.descentV);
        this.state = Enemy.State.DESCENT;
    }

    public boolean isBulletCollision(Rect bullet) {
        return bullet.getRight() >= this.getLeft() && bullet.getLeft() <= this.getRight() && bullet.getBottom() <= this.getTop() && bullet.getTop() >= this.pos.y;
    }

    public void destroy() {
        this.boom();
        this.hp = 0;
        super.destroy();
    }

    private static enum State {
        DESCENT,
        FIGHT;

        private State() {
        }
    }
}
