package dev.kamivt.amammamod.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class MeowerlayDisplayOverlayIngameProcedure {
  public static boolean execute(Entity entity) {
    if (entity == null)
      return false; 
    boolean dead = false;
    LivingEntity _livEnt = (LivingEntity)entity;
    if (0.0F == ((entity instanceof LivingEntity) ? _livEnt.m_21223_() : -1.0F)) {
      dead = true;
    } else {
      dead = false;
    } 
    return dead;
  }
}
