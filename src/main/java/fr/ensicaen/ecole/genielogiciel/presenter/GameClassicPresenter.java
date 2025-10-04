package fr.ensicaen.ecole.genielogiciel.presenter;

import fr.ensicaen.ecole.genielogiciel.model.Player;
import fr.ensicaen.ecole.genielogiciel.view.GameClassicView;

public final class GameClassicPresenter {
    private final Player _player;
    private GameClassicView _view;
    private final boolean _end = false;

    public GameClassicPresenter(String nickName ) {
        _player = new Player();
        _player.setNickname(nickName);
    }

    public void setView( GameClassicView view ) {
        _view = view;
    }

    public void runGameLoop() {
        System.out.println("Mode Classic lancé");
    }

    private void update() {
        // Update the model
    }

    private void render() {
        // Display the result on the view
        //_view.toto();
    }
}