package dev.kamivt.amammamod.client.renderer;

import dev.kamivt.amammamod.client.model.Modelamamma;
import dev.kamivt.amammamod.entity.AmammaEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class AmammaRenderer extends MobRenderer<AmammaEntity, Modelamamma<AmammaEntity>> {
  public AmammaRenderer(EntityRendererProvider.Context context) {
    super(context, new Modelamamma<>(context.m_174023_(Modelamamma.LAYER_LOCATION)), 0.3F);
  }
  
  public ResourceLocation getTextureLocation(AmammaEntity entity) {
    return new ResourceLocation("amamma:textures/entities/amammamc.png");
  }
}
