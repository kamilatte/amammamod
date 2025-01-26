package dev.kamivt.amammamod.procedures;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class AmammaPlayerCollidesWithThisEntityProcedure {
  public static void execute(LevelAccessor world, Entity sourceentity) {
    if (sourceentity == null)
      return; 
    sourceentity.m_6469_(new DamageSource((Holder)world.m_9598_().m_175515_(Registries.f_268580_).m_246971_(ResourceKey.m_135785_(Registries.f_268580_, new ResourceLocation("amamma:banned")))), 40.0F);
  }
}
