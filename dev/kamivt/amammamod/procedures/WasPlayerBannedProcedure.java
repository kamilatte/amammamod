package dev.kamivt.amammamod.procedures;

import dev.kamivt.amammamod.network.AmammaModVariables;
import javax.annotation.Nullable;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class WasPlayerBannedProcedure {
  @SubscribeEvent
  public static void onEntityAttacked(LivingAttackEvent event) {
    if (event != null && event.getEntity() != null)
      execute((Event)event, event.getSource(), (Entity)event.getEntity()); 
  }
  
  public static void execute(DamageSource damagesource, Entity entity) {
    execute(null, damagesource, entity);
  }
  
  private static void execute(@Nullable Event event, DamageSource damagesource, Entity entity) {
    if (damagesource == null || entity == null)
      return; 
    if (damagesource.m_276093_(ResourceKey.m_135785_(Registries.f_268580_, new ResourceLocation("amamma:banned")))) {
      boolean _setval = true;
      entity.getCapability(AmammaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.wasBannedByAmamma = _setval;
            capability.syncPlayerVariables(entity);
          });
    } 
  }
}
