package com.lukavuksanovic.bomberman;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public void create() {
        setScreen(new FirstScreen());
    }
}
