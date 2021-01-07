package game.test;

import game.entity.mob.MobsBoard;
import game.entity.mob.MobsBuilder;
import game.entity.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GameTest {

    @Nested
    @DisplayName("Test player generation")
    class TestPlayer{

        @Test
        @DisplayName("Test singleton")
        void testSingleton(){
            Assertions.assertSame(Player.getInstance(), Player.getInstance());
        }
    }

    @Nested
    @DisplayName("Test mob generation")
    class TestBuildMobs{

        private final MobsBuilder mobsBuilder = new MobsBuilder();

        @Nested
        @DisplayName("Should allow")
        class ShouldAllow{

            @ParameterizedTest
            @DisplayName("Test number of mobs")
            @ValueSource(ints = {1, 2, 3})
            void TestNumberOfMobs(int level){
                MobsBoard mobs = mobsBuilder.buildMobs(level);
                Assertions.assertEquals(level, mobs.getMob(level-1).getLevel());
                Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
                    mobs.getMob(3);
                });
            }
        }
        @Nested
        @DisplayName("Should not allow")
        class shouldNotAllow{

            MobsBoard mobs = mobsBuilder.buildMobs(1);

            @Test
            @DisplayName("Test if mobs are differents")
            void RandomMob(){
                MobsBoard mobs2 = mobsBuilder.buildMobs(1);
                Assertions.assertNotEquals(mobs.getMob(1).getInfoMob(), mobs2.getMob(1).getInfoMob());
                Assertions.assertNotEquals(mobs, mobs2);
            }
        }
    }

}