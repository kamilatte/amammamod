package dev.kamivt.amammamod.procedures;

import dev.kamivt.amammamod.network.AmammaModVariables;
import net.minecraft.world.entity.Entity;

public class MeowerlayDisplayOverlayIngameProcedure {
  public static boolean execute(Entity entity) {
    if (entity == null)
      return false; 
    boolean dead = false;
    return ((AmammaModVariables.PlayerVariables)entity.getCapability(AmammaModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AmammaModVariables.PlayerVariables())).wasBannedByAmamma;
  }
}
