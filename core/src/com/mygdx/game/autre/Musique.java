package com.mygdx.game.autre;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Valentine on 22/03/2017.
 */

public class Musique {
    private long idM;
    private long idS;
    private boolean isSound = true; // si on active les sons ou non
    private boolean isMusic = true; // si on active la musique ou non
    private Sound sound; //son effet
    private Sound soundMusic; //son musique

    //Pour lancer la musique des le lancement du jeu
    public void lancerMusique(){
        // Si la musique est active dans les options on l'ajoute, sinon on ne fait rien
        if(isMusic) {
            soundMusic = Gdx.audio.newSound(Gdx.files.internal("data/cellos.mp3"));
            idM = soundMusic.play(1.0f);
            soundMusic.loop();
        }
    }

    // Son effet quand on clique sur un bouton
    public void lancerSon(){
        // Si le son est active alors on lance le son, sinon on ne fait rien
        if(isSound) {
            sound = Gdx.audio.newSound(Gdx.files.internal("data/crunch.mp3"));
            idS = sound.play(1.0f);
        }
    }

    // Stopper les effets de son au clic
    public void stopperSon(){
        isSound = false;
    }

    // Stopper la musique
    public void stopperMusique(){
        isMusic = false;
        soundMusic.stop();
    }

    // Relancer la musique
    public void relancerMusique(){
        isMusic = true;
        soundMusic.play();
    }

    public void relancerSon() {
        isSound = true;
    }

    public boolean getIsMusique(){
        return isMusic;
    }

    public boolean getIsSound(){
        return isSound;
    }
}
