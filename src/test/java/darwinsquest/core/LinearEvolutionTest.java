package darwinsquest.core;

import darwinsquest.core.element.Element;
import darwinsquest.core.element.Neutral;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinearEvolutionTest {

    record BanionStats(int level, int hp, int maxHp) {
    }

    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;
    public static final int LEVEL_3 = 3;
    public static final int LEVEL_10 = 10;
    public static final int LEVEL_15 = 15;
    public static final int LEVEL_20 = 20;
    public static final int LEVEL_5 = 5;
    private static final String NAME = "Bob";
    private static final int MOVE_DAMAGE = LEVEL_10;
    private static final int DEFAULT_HP = 100;
    private static final double HP_MULTIPLIER = 15;
    private static final BanionStats DEFAULT_RECORD = new BanionStats(LEVEL_1, DEFAULT_HP, DEFAULT_HP);
    private BanionStats lastRecord;
    private final Element neutral = new Neutral();
    private final Set<Move> moves = Set.of(new BasicMove(MOVE_DAMAGE, "1", neutral),
            new BasicMove(MOVE_DAMAGE, "2", neutral),
            new BasicMove(MOVE_DAMAGE, "3", neutral),
            new BasicMove(MOVE_DAMAGE, "4", neutral));

    @Test
    void evolveTest() {
        final Banion b = new BanionImpl(neutral, NAME, DEFAULT_HP, moves);
        assertFalse(b.evolve(banion -> banion.getLevel() > LEVEL_10));
        assertRecordEquals(DEFAULT_RECORD, b);
        lastRecord = new BanionStats(LEVEL_2, addPercentage(DEFAULT_RECORD.hp), addPercentage(DEFAULT_RECORD.maxHp));
        assertTrue(b.evolve(banion -> banion.getLevel() == 1));
        assertRecordEquals(lastRecord, b);
        assertFalse(b.evolve(banion -> false));
        assertRecordEquals(lastRecord, b);
        lastRecord = new BanionStats(LEVEL_3, addPercentage(lastRecord.hp), addPercentage(lastRecord.maxHp));
        assertTrue(b.evolve(banion -> banion.getLevel() % 2 == 0));
        assertRecordEquals(lastRecord, b);
        assertFalse(b.evolve(banion -> banion.getLevel() % 2 == 0));
        assertRecordEquals(lastRecord, b);
    }

    @Test
    void evolveToLevelTest() {
        final Banion b = new BanionImpl(neutral, NAME, DEFAULT_HP, moves);
        lastRecord = new BanionStats(LEVEL_10,
                addPercentage(DEFAULT_RECORD.hp, LEVEL_10 - 1),
                addPercentage(DEFAULT_RECORD.maxHp, LEVEL_10 - 1));
        assertTrue(b.evolveToLevel(LEVEL_10, banion -> banion.getLevel() <= LEVEL_10));
        assertRecordEquals(lastRecord, b);
        lastRecord = new BanionStats(LEVEL_15,
                addPercentage(lastRecord.hp, LEVEL_15 - LEVEL_10),
                addPercentage(lastRecord.maxHp, LEVEL_15 - LEVEL_10));
        assertTrue(b.evolveToLevel(LEVEL_15, banion -> banion.getLevel() < LEVEL_15));
        assertRecordEquals(lastRecord, b);
        assertThrows(IllegalArgumentException.class, () -> b.evolveToLevel(LEVEL_5, banion -> true));
        assertFalse(b.evolveToLevel(LEVEL_20, banion -> false));
        assertRollback(lastRecord, b);
    }

    @Test
    void rollbackTest() {
        final Banion b = new BanionImpl(neutral, NAME, DEFAULT_HP, moves);
        assertFalse(b.evolveToLevel(LEVEL_10, banion -> banion.getLevel() % LEVEL_2 == 0));
        assertRollback(DEFAULT_RECORD, b);
    }

    private void assertRollback(final BanionStats record, final Banion banion) {
        assertEquals(record.level, banion.getLevel());
        assertEquals(record.hp, banion.getHp());
        assertEquals(record.maxHp, banion.getMaxHp());
    }

    private void assertRecordEquals(final BanionStats record, final Banion banion) {
        final var currentRecord = new BanionStats(banion.getLevel(), banion.getHp(), banion.getMaxHp());
        assertEquals(record, currentRecord);
    }

    private int addPercentage(final int stat) {
        final double increase = 1 + (HP_MULTIPLIER / 100);
        return (int) Math.round(stat * increase);
    }

    private int addPercentage(final int stat, final int times) {
        int result = stat;
        for (int i = 0; i < times; i++) {
            result = addPercentage(result);
        }
        return result;
    }

}