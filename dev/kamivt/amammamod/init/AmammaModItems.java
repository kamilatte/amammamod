package dev.kamivt.amammamod.init;

import java.util.function.Supplier;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AmammaModItems {
  public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, "amamma");
  
  public static final RegistryObject<Item> AMAMMA_SPAWN_EGG = REGISTRY.register("amamma_spawn_egg", () -> new ForgeSpawnEggItem((Supplier)AmammaModEntities.AMAMMA, -1, -1, new Item.Properties()));
}
