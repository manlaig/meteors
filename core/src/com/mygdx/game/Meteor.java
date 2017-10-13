package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by manla on 10/12/2017.
 */

public class Meteor
{
    Vector2 position;
    Vector2 velocity;
    ExtendViewport viewport;

    float baseRadius;
    float radiusMultiplier;

    public Meteor(ExtendViewport viewport)
    {
        this.viewport = viewport;
        init();
    }

    public void update(float delta)
    {

    }

    public void init()
    {
        position = new Vector2(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2);
        velocity = new Vector2();
        baseRadius = Constants.RADIUS_FACTOR * Math.min(viewport.getWorldWidth(), viewport.getWorldHeight());
        radiusMultiplier = 1;
    }

    protected void render(ShapeRenderer renderer, ExtendViewport viewport)
    {
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLACK);
        renderer.rect(Constants.WORLD_SIZE, Constants.WORLD_SIZE, viewport.getWorldWidth(), viewport.getWorldHeight());
    }

}
