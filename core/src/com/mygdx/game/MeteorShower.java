package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Random;

/**
 * Created by manla on 10/14/2017.
 */

public class MeteorShower {

    Array<Meteor> meteors;
    ExtendViewport viewport;

    public MeteorShower(ExtendViewport viewport)
    {
        meteors = new Array<Meteor>();
        this.viewport = viewport;
    }

    public void render(ShapeRenderer renderer)
    {
        for(Meteor meteor : meteors)
        {
            meteor.render(renderer);
        }
    }

    public void update(float delta)
    {
        Random random = new Random();

        if(random.nextFloat() < delta * 20)      //  THIS NUMBER 20 REPRESENTS THE DIFFICULTY
        {
            meteors.add(new Meteor(this.viewport));
        }

        for(int i = 0; i < meteors.size; i++)
        {
            Meteor meteor = meteors.get(i);
            meteor.update(delta);

            if(meteor.isBelowScreen())
            {
                meteors.removeIndex(i);
            }
        }

    }

    public void reset()
    {
        meteors.removeAll(meteors, true);
    }

}
