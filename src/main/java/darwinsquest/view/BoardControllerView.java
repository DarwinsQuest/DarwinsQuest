package darwinsquest.view;

import darwinsquest.BoardController;
import darwinsquest.annotation.Description;
import darwinsquest.util.JavaFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Class that represents the fxml view controller of the start menu.
 */
@Description("board")
public final class BoardControllerView implements Initializable, BoardView {

    private static final String PREFIX = "Level: ";
    @FXML
    private VBox vBox;
    @FXML
    private Label lbLevel;
    @FXML
    private Button btMove;
    @FXML
    private Button btFight;
    @FXML
    private ProgressBar progress;

    private final BoardController controller;
    private String suffix;
    private int maxStep;
    private int pos;
    private boolean canMove;
    private boolean canFight;

    /**
     * Default constructor.
     * @param controller the MVC controller.
     */
    public BoardControllerView(final BoardController controller) {
        this.controller = Objects.requireNonNull(controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final int pos, final int levels, final int maxStep, final boolean canMove, final boolean canFight) {
        suffix = " (max step = " + maxStep + ")";
        this.maxStep = maxStep;
        this.pos = pos;
        this.canMove = canMove;
        this.canFight = canFight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        JavaFXUtils.initializeBackground(vBox, "img/Blue.png");
        setPos(pos);
        toggleMove(canMove);
        toggleFight(canFight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPos(final int pos) {
        this.pos = pos;
        setLabelText(Integer.toString(pos));
        progress.setProgress((double) pos / maxStep);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleMove(final boolean canMove) {
        this.canMove = canMove;
        btMove.setDisable(!canMove);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleFight(final boolean canFight) {
        this.canFight = canFight;
        btFight.setDisable(!canFight);
    }

    private void setLabelText(final String text) {
        lbLevel.setText(PREFIX + text + suffix);
    }

    @FXML
    private void onMoveAction() { // NOPMD, events are indirectly used
        this.controller.move();
    }

    @FXML
    private void onFightAction() { // NOPMD, events are indirectly used
//        controller.fight();
    }
}
