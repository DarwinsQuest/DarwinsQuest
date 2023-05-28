package darwinsquest.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.random.RandomGenerator;

import com.github.javafaker.Faker;
import darwinsquest.BanionFactory;
import darwinsquest.core.gameobject.entity.PlayerImpl;
import org.junit.jupiter.api.Test;

/**
 * Test Class for {@link darwinsquest.core.EngineImpl}.
 */
class TestEngine {

    @Test
    void difficulties() {
        final RandomGenerator generator = new Random();
        final var player = new PlayerImpl(new Faker().name().firstName());
        final var engine = new EngineImpl(player);

        player.addToInventory(new BanionFactory().createElements().stream().findAny().orElseThrow());

        engine.getDifficulties().forEach(difficulty -> assertTrue(engine.startGame(difficulty)));

        var stringLength = 0;
        var generatedDifficulty = "";

        while (engine.getDifficulties().contains(generatedDifficulty)) {
            final var array = new byte[++stringLength];
            generator.nextBytes(array);
            generatedDifficulty = new String(array, StandardCharsets.UTF_8);
        }

        final var nonExistingDifficulty = generatedDifficulty;
        assertFalse(engine.startGame(nonExistingDifficulty));
    }
}
