package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by manla on 10/12/2017.
 */

public class Meteor
{
    Vector2 position;
    Vector2 velocity;
    ExtendViewport viewport;


    public Meteor(ExtendViewport viewport)
    {
        this.viewport = viewport;
        init();
    }

    public void update(float delta)
    {
        velocity.y += Constants.METEOR_ACCELERATION * delta;

        position.y += velocity.y * delta;
    }

    public void init()
    {
        position = new Vector2(random.nextFloat() * (viewport.getWorldWidth() - 2 * 0.2f) + 0.2f,
                                viewport.getWorldHeight() + 0.3F);
        velocity = new Vector2();
    }

    protected void render(ShapeRenderer renderer)
    {
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.RED);
        renderer.circle(position.x, position.y, Constants.METEOR_RADIUS, 16);
    }

    public boolean isBelowScreen()
    {
        boolean returnValue = false;

        if(this.position.y < -Constants.METEOR_RADIUS)
        {
            returnValue = true;
        }
        return returnValue;
    }

}
