package fr.ensicaen.ecole.genielogiciel.presenter;

import fr.ensicaen.ecole.genielogiciel.model.Player;
import fr.ensicaen.ecole.genielogiciel.view.GameBalloonView;

public final class GameBalloonPresenter {
    private final Player _player;
    private GameBalloonView _view;
    private final boolean _end = false;

    public GameBalloonPresenter(String nickName ) {
        _player = new Player();
        _player.setNickname(nickName);
    }

    public void setView( GameBalloonView view ) {
        _view = view;
    }

    public void runGameLoop() {
        System.out.println("Mode Ballon lancé");
    }

    private void update() {
        // Update the model
    }

    private void render() {
        // Display the result on the view
        //_view.toto();
    }
}