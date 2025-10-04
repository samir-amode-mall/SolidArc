package fr.ensicaen.ecole.genielogiciel.presenter;

import fr.ensicaen.ecole.genielogiciel.Main;
import fr.ensicaen.ecole.genielogiciel.view.LoginView;
import fr.ensicaen.ecole.genielogiciel.view.GameClassicView;
import fr.ensicaen.ecole.genielogiciel.view.GameBalloonView;


import java.io.IOException;

public final class LoginPresenter {
    private LoginView _view;

    public void setView( LoginView view ) {
        _view = view;
    }

    public void launchGameClassic( String nickName ) {
        if (nickName.isEmpty()) {
            _view.displayError(Main.getMessageBundle().getString("error.nickname"));
        } else {
            try {
                createAndDisplayGameClassicView(nickName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            _view.close();
        }
    }

    public void launchGameBalloon( String nickName ) {
        if (nickName.isEmpty()) {
            _view.displayError(Main.getMessageBundle().getString("error.nickname"));
        } else {
            try {
                createAndDisplayGameBalloonView(nickName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            _view.close();
        }
    }

    private void createAndDisplayGameClassicView( String nickName ) throws IOException {
        GameClassicView view = GameClassicView.createView();
        GameClassicPresenter gameClassicPresenter = new GameClassicPresenter(nickName);
        view.setPresenter(gameClassicPresenter);
        gameClassicPresenter.setView(view);
        view.show();
    }

    private void createAndDisplayGameBalloonView( String nickName ) throws IOException {
        GameBalloonView view = GameBalloonView.createView();
        GameBalloonPresenter gameBalloonPresenter = new GameBalloonPresenter(nickName);
        view.setPresenter(gameBalloonPresenter);
        gameBalloonPresenter.setView(view);
        view.show();
    }
}
