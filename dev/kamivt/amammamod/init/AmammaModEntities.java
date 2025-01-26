package dev.kamivt.amammamod.init;

import dev.kamivt.amammamod.entity.AmammaEntity;
import java.util.function.BiFunction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AmammaModEntities {
  public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "amamma");
  
  public static final RegistryObject<EntityType<AmammaEntity>> AMAMMA = register("amamma", 
      EntityType.Builder.m_20704_(AmammaEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(80).setUpdateInterval(3).setCustomClientFactory(AmammaEntity::new).m_20719_().m_20699_(0.7F, 0.8F));
  
  private static <T extends net.minecraft.world.entity.Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
    return REGISTRY.register(registryname, () -> entityTypeBuilder.m_20712_(registryname));
  }
  
  @SubscribeEvent
  public static void init(FMLCommonSetupEvent event) {
    event.enqueueWork(() -> AmammaEntity.init());
  }
  
  @SubscribeEvent
  public static void registerAttributes(EntityAttributeCreationEvent event) {
    event.put((EntityType)AMAMMA.get(), AmammaEntity.createAttributes().m_22265_());
  }
}
