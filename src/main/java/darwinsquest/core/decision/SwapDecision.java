package darwinsquest.core.decision;

/**
 * Class that represents the {@link darwinsquest.core.gameobject.entity.GameEntity}'s choice of swapping the currently
 * deployed {@link darwinsquest.core.gameobject.banion.Banion} during a battle.
 */
public final class SwapDecision extends MiscDecision {

    /**
     * This constructor creates a new {@link SwapDecision}.
     */
    public SwapDecision() {
        super("Swap");
    }

}
