package fr.maxlego08.essentials.api.commands;

public enum Permission {

    ESSENTIALS_USE,
    ESSENTIALS_RELOAD,
    ESSENTIALS_GAMEMODE,
    ESSENTIALS_GAMEMODE_OTHER,
    ESSENTIALS_GAMEMODE_CREATIVE,
    ESSENTIALS_GAMEMODE_SURVIVAL,
    ESSENTIALS_GAMEMODE_SPECTATOR,
    ESSENTIALS_GAMEMODE_ADVENTURE,
    ESSENTIALS_DAY,
    ESSENTIALS_NIGHT,
    ESSENTIALS_SUN,
    ESSENTIALS_ENDERCHEST,
    ESSENTIALS_ENDERSEE,
    ESSENTIALS_TOP,
    ESSENTIALS_GOD,
    ESSENTIALS_HEAL,
    ESSENTIALS_SPEED,
    ESSENTIALS_TELEPORT_BYPASS,
    ESSENTIALS_TPA,
    ESSENTIALS_TPA_ACCEPT,
    ESSENTIALS_TPA_DENY,
    ESSENTIALS_TPA_CANCEL,
    ESSENTIALS_BYPASS_COOLDOWN,
    ESSENTIALS_MORE,
    ESSENTIALS_TP_WORLD,
    ESSENTIALS_TP_WORLD_OTHER,
    ESSENTIALS_TRASH,
    ESSENTIALS_FEED,
    ESSENTIALS_INVSEE,
    ESSENTIALS_FEED_OTHER,
    ESSENTIALS_HEAL_OTHER,
    ESSENTIALS_CRAFT,
    ESSENTIALS_ENCHANTING, ESSENTIALS_INVSEE_INTERACT, ESSENTIALS_COMPACT, ESSENTIALS_HAT, ESSENTIALS_PLAYER_WEATHER, ESSENTIALS_PLAYER_TIME, ESSENTIALS_TP, ESSENTIALS_ECO_USE, ESSENTIALS_ECO_GIVE, ESSENTIALS_ECO_TAKE, ESSENTIALS_ECO_GIVE_ALL, ESSENTIALS_MONEY;


    public String asPermission() {
        return name().toLowerCase().replace("_", ".");
    }

}
