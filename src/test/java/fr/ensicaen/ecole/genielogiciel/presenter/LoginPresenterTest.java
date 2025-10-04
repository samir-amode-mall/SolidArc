package fr.ensicaen.ecole.genielogiciel.presenter;

import fr.ensicaen.ecole.genielogiciel.Main;
import fr.ensicaen.ecole.genielogiciel.view.LoginView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class LoginPresenterTest {

    @Mock
    private LoginView _view;
    private AutoCloseable closeable;

    @BeforeEach
    public void initMocks() { closeable = MockitoAnnotations.openMocks(this); }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void should_activate_view_error_display_when_nickName_is_empty_classic_game() {
        // given
        LoginPresenter presenter = new LoginPresenter();
        presenter.setView(_view);

        // when
        presenter.launchGameClassic("");

        // then
        verify(_view, times(1)).displayError(Main.getMessageBundle().getString("error.nickname"));
    }

    @Test
    void should_activate_view_error_display_when_nickName_is_empty_balloon_game() {
        // given
        LoginPresenter presenter = new LoginPresenter();
        presenter.setView(_view);

        // when
        presenter.launchGameBalloon("");

        // then
        verify(_view, times(1)).displayError(Main.getMessageBundle().getString("error.nickname"));
    }
}