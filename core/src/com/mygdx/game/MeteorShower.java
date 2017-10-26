package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Random;

/**
 * Created by manlai on 10/14/2017.
 */

public class MeteorShower
{

    Array<Meteor> meteors;
    private ExtendViewport viewport;
    private int score = 0;
    private int topScore = 0;
    private float difficulty;

    public MeteorShower(ExtendViewport viewport, float difficulty)
    {
        meteors = new Array<Meteor>();
        this.viewport = viewport;
        this.difficulty = difficulty;
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

        if(random.nextFloat() < delta * difficulty)
            meteors.add(new Meteor(this.viewport));


        for(int i = 0; i < meteors.size; i++)
        {
            Meteor meteor = meteors.get(i);
            meteor.update(delta);

            if(meteor.isBelowScreen())
            {
                meteors.removeIndex(i);
                score++;
                if(score >= topScore)
                    topScore = score;
            }
        }

    }

    // if player is hit, we call this method to reset the meteors
    public void reset()
    {
        for(int i = 0; i < meteors.size; i++)
        {
            meteors.removeRange(0, meteors.size - 1);
        }
    }

    public void setScore(int newScore)
    {
        this.score = newScore;
    }

    public int getScore()
    {
        return this.score;
    }

    public int getTopScore()
    {
        return this.topScore;
    }

}


class Runner extends Thread         //I am saving this class for future features
{
    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
