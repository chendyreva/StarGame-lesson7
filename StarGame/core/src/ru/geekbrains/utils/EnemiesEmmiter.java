package ru.geekbrains.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.EnemyPool;
import ru.geekbrains.sprite.Enemy;

public class EnemiesEmmiter {
    private static final float ENEMY_SMALL_HEIGHT = 0.1F;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01F;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3F;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3.0F;
    private static final int ENEMY_SMALL_HP = 1;
    private static final float ENEMY_MEDIUM_HEIGHT = 0.1F;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.02F;
    private static final float ENEMY_MEDIUM_BULLET_VY = -0.3F;
    private static final int ENEMY_MEDIUM_BULLET_DAMAGE = 5;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 4.0F;
    private static final int ENEMY_MEDIUM_HP = 5;
    private static final float ENEMY_BIG_HEIGHT = 0.2F;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.06F;
    private static final float ENEMY_BIG_BULLET_VY = -0.25F;
    private static final int ENEMY_BIG_BULLET_DAMAGE = 12;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 4.0F;
    private static final int ENEMY_BIG_HP = 20;
    private TextureRegion[] enemySmallRegion;
    private TextureRegion[] enemyMediumRegion;
    private TextureRegion[] enemyBigRegion;
    private Vector2 enemySmallV = new Vector2(0.0F, -0.2F);
    private Vector2 enemyMediumV = new Vector2(0.0F, -0.03F);
    private Vector2 enemyBigV = new Vector2(0.0F, -0.005F);
    private EnemyPool enemyPool;
    private Rect worldBounds;
    private TextureRegion bulletRegion;
    private float generateInterval = 4.0F;
    private float generateTimer;

    public EnemiesEmmiter(EnemyPool enemyPool, Rect worldBounds, TextureAtlas atlas) {
        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;
        TextureRegion textureRegion0 = atlas.findRegion("enemy0");
        this.enemySmallRegion = Regions.split(textureRegion0, 1, 2, 2);
        TextureRegion textureRegion1 = atlas.findRegion("enemy1");
        this.enemyMediumRegion = Regions.split(textureRegion1, 1, 2, 2);
        TextureRegion textureRegion2 = atlas.findRegion("enemy2");
        this.enemyBigRegion = Regions.split(textureRegion2, 1, 2, 2);
        this.bulletRegion = atlas.findRegion("bulletEnemy");
    }

    public void generate(float delta) {
        this.generateTimer += delta;
        if (this.generateTimer >= this.generateInterval) {
            this.generateTimer = 0.0F;
            Enemy enemy = (Enemy)this.enemyPool.obtain();
            float type = (float)Math.random();
            if (type < 0.5F) {
                enemy.set(this.enemySmallRegion, this.enemySmallV, this.bulletRegion, 0.01F, -0.3F, 1, 3.0F, 0.1F, 1);
            } else if ((double)type < 0.8D) {
                enemy.set(this.enemyMediumRegion, this.enemyMediumV, this.bulletRegion, 0.02F, -0.3F, 5, 4.0F, 0.1F, 5);
            } else {
                enemy.set(this.enemyBigRegion, this.enemyBigV, this.bulletRegion, 0.06F, -0.25F, 12, 4.0F, 0.2F, 20);
            }

            enemy.setBottom(this.worldBounds.getTop());
            enemy.pos.x = Rnd.nextFloat(this.worldBounds.getLeft() + enemy.getHalfWidth(), this.worldBounds.getRight() - enemy.getHalfWidth());
        }

    }
}
