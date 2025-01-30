package dev.kamivt.amammamod.procedures;

import dev.kamivt.amammamod.network.AmammaModVariables;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ResetPlayerBanmeterProcedure {
  @SubscribeEvent
  public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
    execute((Event)event, (Entity)event.getEntity());
  }
  
  public static void execute(Entity entity) {
    execute(null, entity);
  }
  
  private static void execute(@Nullable Event event, Entity entity) {
    if (entity == null)
      return; 
    boolean _setval = false;
    entity.getCapability(AmammaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
          capability.wasBannedByAmamma = _setval;
          capability.syncPlayerVariables(entity);
        });
  }
}
