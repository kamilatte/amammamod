package dev.kamivt.amammamod.network;

import dev.kamivt.amammamod.AmammaMod;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AmammaModVariables {
  @SubscribeEvent
  public static void init(FMLCommonSetupEvent event) {
    AmammaMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
  }
  
  @SubscribeEvent
  public static void init(RegisterCapabilitiesEvent event) {
    event.register(PlayerVariables.class);
  }
  
  @EventBusSubscriber
  public static class EventBusVariableHandlers {
    @SubscribeEvent
    public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
      if (!event.getEntity().m_9236_().m_5776_())
        ((AmammaModVariables.PlayerVariables)event.getEntity().getCapability(AmammaModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AmammaModVariables.PlayerVariables())).syncPlayerVariables((Entity)event.getEntity()); 
    }
    
    @SubscribeEvent
    public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
      if (!event.getEntity().m_9236_().m_5776_())
        ((AmammaModVariables.PlayerVariables)event.getEntity().getCapability(AmammaModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AmammaModVariables.PlayerVariables())).syncPlayerVariables((Entity)event.getEntity()); 
    }
    
    @SubscribeEvent
    public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
      if (!event.getEntity().m_9236_().m_5776_())
        ((AmammaModVariables.PlayerVariables)event.getEntity().getCapability(AmammaModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AmammaModVariables.PlayerVariables())).syncPlayerVariables((Entity)event.getEntity()); 
    }
    
    @SubscribeEvent
    public static void clonePlayer(PlayerEvent.Clone event) {
      event.getOriginal().revive();
      AmammaModVariables.PlayerVariables original = (AmammaModVariables.PlayerVariables)event.getOriginal().getCapability(AmammaModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AmammaModVariables.PlayerVariables());
      AmammaModVariables.PlayerVariables clone = (AmammaModVariables.PlayerVariables)event.getEntity().getCapability(AmammaModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AmammaModVariables.PlayerVariables());
      clone.wasBannedByAmamma = original.wasBannedByAmamma;
      if (!event.isWasDeath());
    }
  }
  
  public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
      
      });
  
  @EventBusSubscriber
  private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
      if (event.getObject() instanceof net.minecraft.world.entity.player.Player && !(event.getObject() instanceof net.minecraftforge.common.util.FakePlayer))
        event.addCapability(new ResourceLocation("amamma", "player_variables"), (ICapabilityProvider)new PlayerVariablesProvider()); 
    }
    
    private final AmammaModVariables.PlayerVariables playerVariables = new AmammaModVariables.PlayerVariables();
    
    private final LazyOptional<AmammaModVariables.PlayerVariables> instance = LazyOptional.of(() -> this.playerVariables);
    
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
      return (cap == AmammaModVariables.PLAYER_VARIABLES_CAPABILITY) ? this.instance.cast() : LazyOptional.empty();
    }
    
    public Tag serializeNBT() {
      return this.playerVariables.writeNBT();
    }
    
    public void deserializeNBT(Tag nbt) {
      this.playerVariables.readNBT(nbt);
    }
  }
  
  public static class PlayerVariables {
    public boolean wasBannedByAmamma = false;
    
    public void syncPlayerVariables(Entity entity) {
      if (entity instanceof ServerPlayer) {
        ServerPlayer serverPlayer = (ServerPlayer)entity;
        AmammaMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new AmammaModVariables.PlayerVariablesSyncMessage(this));
      } 
    }
    
    public Tag writeNBT() {
      CompoundTag nbt = new CompoundTag();
      nbt.m_128379_("wasBannedByAmamma", this.wasBannedByAmamma);
      return (Tag)nbt;
    }
    
    public void readNBT(Tag tag) {
      CompoundTag nbt = (CompoundTag)tag;
      this.wasBannedByAmamma = nbt.m_128471_("wasBannedByAmamma");
    }
  }
  
  public static class PlayerVariablesSyncMessage {
    private final AmammaModVariables.PlayerVariables data;
    
    public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
      this.data = new AmammaModVariables.PlayerVariables();
      this.data.readNBT((Tag)buffer.m_130260_());
    }
    
    public PlayerVariablesSyncMessage(AmammaModVariables.PlayerVariables data) {
      this.data = data;
    }
    
    public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
      buffer.m_130079_((CompoundTag)message.data.writeNBT());
    }
    
    public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
      NetworkEvent.Context context = contextSupplier.get();
      context.enqueueWork(() -> {
            if (!context.getDirection().getReceptionSide().isServer()) {
              AmammaModVariables.PlayerVariables variables = (AmammaModVariables.PlayerVariables)(Minecraft.m_91087_()).f_91074_.getCapability(AmammaModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AmammaModVariables.PlayerVariables());
              variables.wasBannedByAmamma = message.data.wasBannedByAmamma;
            } 
          });
      context.setPacketHandled(true);
    }
  }
}
