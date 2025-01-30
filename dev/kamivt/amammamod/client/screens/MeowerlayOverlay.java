package dev.kamivt.amammamod.client.screens;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.kamivt.amammamod.procedures.MeowerlayDisplayOverlayIngameProcedure;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber({Dist.CLIENT})
public class MeowerlayOverlay {
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public static void eventHandler(ScreenEvent.Render.Post event) {
    if (event.getScreen() instanceof net.minecraft.client.gui.screens.DeathScreen) {
      int w = (event.getScreen()).f_96543_;
      int h = (event.getScreen()).f_96544_;
      Level world = null;
      double x = 0.0D;
      double y = 0.0D;
      double z = 0.0D;
      LocalPlayer localPlayer = (Minecraft.m_91087_()).f_91074_;
      if (localPlayer != null) {
        world = localPlayer.m_9236_();
        x = localPlayer.m_20185_();
        y = localPlayer.m_20186_();
        z = localPlayer.m_20189_();
      } 
      RenderSystem.disableDepthTest();
      RenderSystem.depthMask(false);
      RenderSystem.enableBlend();
      RenderSystem.setShader(GameRenderer::m_172817_);
      RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      if (MeowerlayDisplayOverlayIngameProcedure.execute((Entity)localPlayer))
        event.getGuiGraphics().m_280163_(new ResourceLocation("amamma:textures/screens/img1.png"), w / 2 + -99, h / 2 + -103, 0.0F, 0.0F, 200, 200, 200, 200); 
      RenderSystem.depthMask(true);
      RenderSystem.defaultBlendFunc();
      RenderSystem.enableDepthTest();
      RenderSystem.disableBlend();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    } 
  }
}
