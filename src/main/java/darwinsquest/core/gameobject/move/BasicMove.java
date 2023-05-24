package darwinsquest.core.gameobject.move;

import java.util.Objects;

import darwinsquest.core.gameobject.banion.Banion;
import darwinsquest.core.gameobject.element.Element;

/**
 * A basic implementation of {@link Move}.
 */
public class BasicMove implements DamageMove {

    private final int baseDamage;
    private final String name;
    private final Element element;

    private BasicMove(final BasicMove move) {
        baseDamage = move.baseDamage;
        name = move.name;
        element = move.element;
    }

    /**
     * This constructor creates a new {@link BasicMove} with the provided name, damage and element.
     * @param name The name of the {@link BasicMove}.
     * @param baseDamage The damage inflicted by the {@link BasicMove}.
     * @param element The {@link Element} with which the {@link BasicMove} is associated.
     */
    public BasicMove(final int baseDamage, final String name, final Element element) {
        if (baseDamage <= 0) {
            throw new IllegalArgumentException("baseDamage can't be less or equal to zero.");
        }
        this.baseDamage = baseDamage;
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("name can't be null or blank.");
        }
        this.name = name;
        this.element = Objects.requireNonNull(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform(final Banion opponentBanion) {
        opponentBanion.decreaseHp(getDamage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStackable() {
        throw new UnsupportedOperationException("Stackability not yet supported.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCooldown() {
        throw new UnsupportedOperationException("Cooldown not yet supported.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDuration() {
        throw new UnsupportedOperationException("Duration not yet supported.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        throw new UnsupportedOperationException("Type not yet supported.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return this.baseDamage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Element getElement() {
        return this.element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BasicMove copy() {
        return new BasicMove(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " [name = " + this.getName() + ", element = " + this.getElement()
                + ", damage = " + this.getDamage() + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(baseDamage, name, element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final BasicMove move = (BasicMove) obj;
        return baseDamage == move.baseDamage && name.equals(move.name) && element.equals(move.element);
    }

}