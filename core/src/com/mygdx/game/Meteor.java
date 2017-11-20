package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by manlai on 10/12/2017.
 */

public class Meteor
{
    private Vector2 position, velocity;
    private ExtendViewport viewport;

    public Meteor(ExtendViewport viewport)
    {
        this.viewport = viewport;
        init();
    }

    public void updateMeteorPosition(float delta)
    {
        velocity.y += Constants.METEOR_ACCELERATION * delta;
        position.y += velocity.y * delta;
    }

    private void init()
    {
        position = new Vector2(random.nextFloat() * (viewport.getWorldWidth() - 2 * 0.2f) + 0.2f,
                                viewport.getWorldHeight() + 0.3F);
        velocity = new Vector2();
    }

    protected void renderMeteors(ShapeRenderer renderer)
    {
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.RED);
        renderer.circle(position.x, position.y, Constants.METEOR_RADIUS, 16);
    }

    public boolean isBelowScreen()
    {
        return this.position.y < -Constants.METEOR_RADIUS ? true : false;
    }

    public Vector2 getPosition()
    {
        return position;
    }
}
