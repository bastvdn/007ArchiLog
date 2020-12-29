package game.armor;

/*
 * Each Entity is equiped with onje armor which has:
 * damageReduced: reduce the incoming damages according to the formula : damages = damagesBeforeReduction *(100/(100+damageReduced))
 * pdV: increase the max Hp
 * regen: regenates health at the end of every fight
 * */
public interface Armor {
    int damageReduced();
    int pdV();
    int regen();
}
