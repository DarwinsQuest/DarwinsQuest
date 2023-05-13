package darwinsquest.core;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Interface that represents in-game beings, both
 * playable and non-playable characters.
 */
public interface Entity extends Nameable {

    /**
     * Retrieves the {@link Entity}'s personal inventory.
     * @return a list representing the inventory.
     */
    List<Banion> getInventory();

    /**
     * Appends the banion to the end of the inventory.
     * @param banion the new banion.
     * @return {@code true} on successful operation,
     *         {@code false} if not or if the provided banion is
     *         referencing an already present one.
     */
    boolean addToInventory(Banion banion);

    /**
     * Appends all the banions in the given collection to the end of the inventory,
     * in the order that they are returned by the specified collection's iterator.
     * @param banions collection of banions to append in the inventory.
     * @return {@code true} on successful operation,
     *         {@code false} if not or if the provided banions are referencing
     *         an already present one.
     */
    boolean addToInventory(Collection<Banion> banions);

    /**
     * Updates the player's inventory by switching {@code oldBanion} with {@code newBanion}.
     * @param oldBanion the banion to update.
     * @param newBanion the updated banion.
     * @return an optional of {@code oldBanion},
     *         empty if it was not contained in the inventory.
     */
    Optional<Banion> updateInventory(Banion oldBanion, Banion newBanion);

    /**
     * Retrieves the {@link Entity}'s chosen {@link Banion}
     * to be their active battle companion.
     * @return the chosen Banion.
     */
    Banion deployBanion();

    /**
     * Retrieves the {@link Entity}'s selected {@link Move}
     * to be performed by a banion.
     * @param banion the banion to select the move from.
     * @return the selected move.
     */
    Move selectMove(Banion banion);

    /**
     * Swaps the active battle {@link Banion} with
     * another available one chosen by the {@link Entity}.
     * @return an {@link Optional} containing a {@link Banion} if any is left,
     *         empty otherwise.
     */
    Optional<Banion> swapBanion();

    /**
     * Determines whether the {@link Entity} has any {@link Banion}s left.
     * @return {@code true} if the {@link Entity} has no more available Banions.
     *         {@code false} if there are some left.
     */
    boolean isOutOfBanions();

}
