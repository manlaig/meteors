package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by manlai on 10/12/2017.
 */

public class Player extends InputAdapter
{

    Vector2 position;
    Vector2 velocity;
    ExtendViewport viewport;

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    public Player(ExtendViewport viewport)
    {
        this.viewport = viewport;
        init();
    }

    public void init()
    {
        position = new Vector2(this.viewport.getWorldWidth() / 2, 2.0F);
        velocity = new Vector2();
    }

    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Color.BLACK);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.circle(this.position.x, this.position.y, 0.5F, 20);
        Vector2 torsoTop = new Vector2(this.position.x, this.position.y - 0.5F);
        Vector2 torsoBottom = new Vector2(torsoTop.x, torsoTop.y - 1.0F);
        renderer.rectLine(torsoTop, torsoBottom, 0.1F);
        renderer.rectLine(torsoTop.x, torsoTop.y, torsoTop.x + 0.5F, torsoTop.y - 0.5F, 0.1F);
        renderer.rectLine(torsoTop.x, torsoTop.y, torsoTop.x - 0.5F, torsoTop.y - 0.5F, 0.1F);
        renderer.rectLine(torsoBottom.x, torsoBottom.y, torsoBottom.x + 0.5F, torsoBottom.y - 0.5F, 0.1F);
        renderer.rectLine(torsoBottom.x, torsoBottom.y, torsoBottom.x - 0.5F, torsoBottom.y - 0.5F, 0.1F);
    }

    public void update(float delta)
    {

    }
}
