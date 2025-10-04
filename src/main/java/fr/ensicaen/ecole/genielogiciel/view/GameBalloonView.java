package fr.ensicaen.ecole.genielogiciel.view;

import fr.ensicaen.ecole.genielogiciel.Main;
import fr.ensicaen.ecole.genielogiciel.model.*;
import fr.ensicaen.ecole.genielogiciel.presenter.GameBalloonPresenter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public final class GameBalloonView {

    private GameBalloonPresenter _gameBalloonPresenter;

    private Stage _stage;

    @FXML
    private Pane _board;

    @FXML
    private Pane _crosshair;

    @FXML
    private Pane _arrow;

    @FXML
    private Pane _targetPane1;

    @FXML
    private Pane _targetPane2;

    @FXML
    private Pane _targetPane3;

    @FXML
    private Pane _title;

    @FXML
    private Pane _background;

    @FXML
    private Label _viewScore;

    @FXML
    private Label _nbArrow;

    @FXML
    private Label _viewWind;


    @FXML
    private final Balloon _target1 = new Balloon(100,325,360,25);

    @FXML
    private final Balloon _target2 = new Balloon(100,100,200,25);

    @FXML
    private final Balloon _target3 = new Balloon(100,480,100,25);

    private final ArrayList<Target> _targets = new ArrayList<>();


    private final Bow _bow = new Bow();

    private final Score _score = new Score();

    private final Random randomNumbers = new Random();

    private MediaPlayer _mediaPlayer;
    private MediaPlayer _arrowSoundPlayer;
    private MediaPlayer _balloonSoundPlayer;
    private boolean _isMusicPlaying = false;


    // Touches de l'ordinateur
    private boolean KeySPACEPressed = false;

    private Wind _wind;

    public GameBalloonView(){
        _targets.add(_target1);
        _targets.add(_target2);
        _targets.add(_target3);
    }


    public static GameBalloonView createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBalloonView.class.getResource("BoardBalloon.fxml"), Main.getMessageBundle());
        Parent root = fxmlLoader.load();
        final GameBalloonView view = fxmlLoader.getController();

        fxmlLoader.setController(view);
        Scene scene = new Scene(root, 650.0, 720.0);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);

        // TODO ajouter une image
        //      Image image = new Image(getClass().getResourceAsStream("toto.png"));

        view._stage = stage;

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> view.onKeyPressed(event.getCode()));
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, view::onMouseClicked);
        // Pour supprimer l'abonnement à l'événement
        // scene.removeEventFilter(MouseEvent.MOUSE_PRESSED, view::onMouseClicked);
        scene.addEventFilter(MouseEvent.MOUSE_RELEASED, view::onMouseRelased);
        scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, view::onMouseDragged);
        scene.addEventFilter(MouseEvent.MOUSE_MOVED, view::onMouseMoved);
        scene.setCursor(Cursor.NONE);

        return view;
    }

    public void setPresenter( GameBalloonPresenter gameBalloonPresenter) {
        _gameBalloonPresenter = gameBalloonPresenter;
    }

    public void show() {
        _wind = new Wind(); // Initialisation de l'objet Wind
        _wind.getWindConditions(); // Récupérer les conditions de vent
        updateWindLabel(); // Mettre à jour le label du vent
        _stage.show();
    }

    private void updateWindLabel(){
        double windSpeed = _wind.getSpeed();
        String windDirection = _wind.getDirection();
        String windInfo = String.format("Vitesse: %.2f km/h, Direction: %s", windSpeed, windDirection);
        _viewWind.setText(windInfo);
    }

    private void updateViewTarget(){
        if (_target1.get_isTouched()) {
            _targetPane1.setVisible(false);
            playBalloonSound();
        }
        if (_target2.get_isTouched()) {
            _targetPane2.setVisible(false);
            playBalloonSound();
        }
        if (_target3.get_isTouched()) {
            _targetPane3.setVisible(false);
            playBalloonSound();
        }
    }

    private void playArrowSound() {
        String arrowSoundFile = "src/main/resources/fr/ensicaen/ecole/genielogiciel/view/arrow_shot.mp3";
        Media arrowSound = new Media(new File(arrowSoundFile).toURI().toString());
        _arrowSoundPlayer = new MediaPlayer(arrowSound);
        _arrowSoundPlayer.setOnEndOfMedia(() -> _arrowSoundPlayer.stop()); // Optionnel : Arrêter le son à la fin
        _arrowSoundPlayer.play();
    }

    private void playBalloonSound() {
        String arrowSoundFile = "src/main/resources/fr/ensicaen/ecole/genielogiciel/view/exploding_balloon.mp3";
        Media arrowSound = new Media(new File(arrowSoundFile).toURI().toString());
        _balloonSoundPlayer = new MediaPlayer(arrowSound);
        _balloonSoundPlayer.setOnEndOfMedia(() -> _arrowSoundPlayer.stop()); // Optionnel : Arrêter le son à la fin
        _balloonSoundPlayer.play();
    }



    private void onKeyPressed( KeyCode code ) {
        if ((code == KeyCode.SPACE) && (!KeySPACEPressed)) {
            KeySPACEPressed = true;

            if (!_isMusicPlaying) {
                String musicFile = "src/main/resources/fr/ensicaen/ecole/genielogiciel/view/music.mp3";
                Media sound = new Media(new File(musicFile).toURI().toString());
                _mediaPlayer = new MediaPlayer(sound);
                _mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                _mediaPlayer.play();
                _isMusicPlaying = true;
            }

            _targetPane1.setScaleX(1);
            _targetPane1.setScaleY(1);
            _targetPane1.setLayoutX(_target1.get_xTarget());
            _targetPane1.setLayoutY(_target1.get_yTarget());
            _targetPane1.setVisible(true);

            _targetPane2.setScaleX(1);
            _targetPane2.setScaleY(1);
            _targetPane2.setLayoutX(_target2.get_xTarget());
            _targetPane2.setLayoutY(_target2.get_yTarget());
            _targetPane2.setVisible(true);

            _targetPane3.setScaleX(1);
            _targetPane3.setScaleY(1);
            _targetPane3.setLayoutX(_target3.get_xTarget());
            _targetPane3.setLayoutY(_target3.get_yTarget());
            _targetPane3.setVisible(true);

            _crosshair.setVisible(true);
            _viewScore.setVisible(true);
            _nbArrow.setText(String.valueOf(_bow.getNbArrow()));
            _nbArrow.setVisible(true);
            _viewWind.setVisible(true);
            _background.setVisible(true);
            _title.setVisible(false);
            _gameBalloonPresenter.runGameLoop();
        }
    }

    private void onMouseClicked( MouseEvent mouseEvent ) {
        if ((KeySPACEPressed) && (_bow.getNbArrow() > 0)) {
            _bow.addForce(100);
            _bow.move(randomNumbers.nextInt(10) - 5, randomNumbers.nextInt(10) - 5);
            _crosshair.setLayoutX(_bow.getAngleX());
            _crosshair.setLayoutY(_bow.getAngleY());
        }
    }


    private void onMouseRelased( MouseEvent mouseEvent ) {
        if ((KeySPACEPressed) && (_bow.getNbArrow() > 0)) {

            _score.addScore(_bow.shootArrow(_targets,_wind.getVelocity()));
            _viewScore.setText(String.valueOf(_score.getScore()));

            _arrow.setLayoutX(_bow.getArrowX());
            _arrow.setLayoutY(_bow.getArrowY());
            _arrow.setVisible(true);

            System.out.println(_bow.toString());
            System.out.println();

            _nbArrow.setText(String.valueOf(_bow.getNbArrow()));

            playArrowSound();
            updateViewTarget();
        }
    }

    private void onMouseDragged( MouseEvent mouseEvent ) {
        if ((KeySPACEPressed) && (_bow.getNbArrow() > 0)) {
            _bow.chooseDirection(mouseEvent.getX(), mouseEvent.getY());

            if ((randomNumbers.nextInt(3) - 1) == 0) {
                _bow.move(randomNumbers.nextInt(14) - 7, randomNumbers.nextInt(14) - 7);
            }

            _crosshair.setLayoutX(_bow.getAngleX());
            _crosshair.setLayoutY(_bow.getAngleY());
        }
    }

    private void onMouseMoved( MouseEvent mouseEvent ) {
        if (KeySPACEPressed) {
            _bow.chooseDirection(mouseEvent.getX(), mouseEvent.getY());
            _crosshair.setLayoutX(_bow.getAngleX());
            _crosshair.setLayoutY(_bow.getAngleY());
        }
    }
}
