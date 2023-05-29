package darwinsquest.view;

import darwinsquest.Controller;
import darwinsquest.view.sound.GameSoundSystem;
import darwinsquest.util.JavaFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class that represents the fxml view controller of the user login.
 */
public class LoginController extends ControllerStageInteractive implements Initializable {

    @FXML
    private VBox vBox;
    @FXML
    private Button btEnter;
    @FXML
    private TextField userName;

    /**
     * Default constructor.
     * @param manager the stage manager related to this javafx controller.
     * @param controller the MVC controller.
     */
    public LoginController(final StageManager manager, final Controller controller) {
        super(manager, controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        JavaFXUtils.initializeBackground(vBox, "img/Blue.png");
    }

    /**
     * Enter event.
     * @param event the event.
     */
    @FXML
    protected void onEnterAction(final ActionEvent event) {
        GameSoundSystem.playSfx("IntroJingle.wav", MediaPlayer::pause, MediaPlayer::play);
        getController().login(userName.getText());
        getManager().setUsername(userName.getText());
        getManager().showDifficulties();
    }

    /**
     * Key changed event.
     * @param event the event.
     */
    @FXML
    protected void onUserNameTextChanged(final KeyEvent event) {
        btEnter.setDisable(!getController().isValidUsername(((TextField) event.getSource()).getText()));
        if (event.getEventType().equals(KeyEvent.KEY_RELEASED)
                && event.getCode().isLetterKey()
                || event.getCode().isDigitKey()
                || event.getCode().equals(KeyCode.BACK_SPACE)
                || event.getCode().equals(KeyCode.DELETE)) {
            GameSoundSystem.playSfx("LowThud.mp3");
        }
    }
}

