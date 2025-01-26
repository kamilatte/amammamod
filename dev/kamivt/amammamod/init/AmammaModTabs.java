package dev.kamivt.amammamod.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AmammaModTabs {
  public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.f_279569_, "amamma");
  
  @SubscribeEvent
  public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
    if (tabData.getTabKey() == CreativeModeTabs.f_256731_)
      tabData.m_246326_((ItemLike)AmammaModItems.AMAMMA_SPAWN_EGG.get()); 
  }
}
