package darwinsquest.core.decision;

import darwinsquest.core.battle.Turn;

/**
 * Interface that represents a decision made by a {@link darwinsquest.core.gameobject.entity.GameEntity}
 * during a battle.
 */
public interface Decision {

    /**
     * Retrieves the {@link Turn} associated with this {@link Decision}.
     * @param previousTurn the previous battle turn.
     * @return the turn associated with the specific decision.
     */
    Turn getAssociatedTurn(Turn previousTurn);

}
