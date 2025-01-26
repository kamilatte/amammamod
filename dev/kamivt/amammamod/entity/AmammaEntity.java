package dev.kamivt.amammamod.entity;

import dev.kamivt.amammamod.init.AmammaModEntities;
import dev.kamivt.amammamod.procedures.AmammaPlayerCollidesWithThisEntityProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

public class AmammaEntity extends PathfinderMob {
  public AmammaEntity(PlayMessages.SpawnEntity packet, Level world) {
    this((EntityType<AmammaEntity>)AmammaModEntities.AMAMMA.get(), world);
  }
  
  public AmammaEntity(EntityType<AmammaEntity> type, Level world) {
    super(type, world);
    m_274367_(0.6F);
    this.f_21364_ = 0;
    m_21557_(false);
  }
  
  public Packet<ClientGamePacketListener> m_5654_() {
    return NetworkHooks.getEntitySpawningPacket((Entity)this);
  }
  
  protected void m_8099_() {
    super.m_8099_();
    this.f_21345_.m_25352_(1, (Goal)new MeleeAttackGoal(this, 0.6D, true) {
          protected double m_6639_(LivingEntity entity) {
            return (this.f_25540_.m_20205_() * this.f_25540_.m_20205_() + entity.m_20205_());
          }
        });
    this.f_21346_.m_25352_(2, (Goal)new HurtByTargetGoal(this, new Class[0]));
    this.f_21345_.m_25352_(3, (Goal)new RandomStrollGoal(this, 0.3D));
    this.f_21345_.m_25352_(4, (Goal)new RandomLookAroundGoal((Mob)this));
    this.f_21346_.m_25352_(5, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, false, false));
  }
  
  public MobType m_6336_() {
    return MobType.f_21640_;
  }
  
  public SoundEvent m_7975_(DamageSource ds) {
    return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
  }
  
  public SoundEvent m_5592_() {
    return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
  }
  
  public boolean m_6469_(DamageSource damagesource, float amount) {
    if (damagesource.m_276093_(DamageTypes.f_268631_))
      return false; 
    if (damagesource.m_7640_() instanceof net.minecraft.world.entity.projectile.AbstractArrow)
      return false; 
    if (damagesource.m_7640_() instanceof Player)
      return false; 
    if (damagesource.m_7640_() instanceof net.minecraft.world.entity.projectile.ThrownPotion || damagesource.m_7640_() instanceof net.minecraft.world.entity.AreaEffectCloud)
      return false; 
    if (damagesource.m_276093_(DamageTypes.f_268671_))
      return false; 
    if (damagesource.m_276093_(DamageTypes.f_268585_))
      return false; 
    if (damagesource.m_276093_(DamageTypes.f_268722_))
      return false; 
    if (damagesource.m_276093_(DamageTypes.f_268450_))
      return false; 
    if (damagesource.m_276093_(DamageTypes.f_268565_) || damagesource.m_276093_(DamageTypes.f_268448_))
      return false; 
    if (damagesource.m_276093_(DamageTypes.f_268714_))
      return false; 
    if (damagesource.m_276093_(DamageTypes.f_268526_))
      return false; 
    if (damagesource.m_276093_(DamageTypes.f_268482_))
      return false; 
    if (damagesource.m_276093_(DamageTypes.f_268493_) || damagesource.m_276093_(DamageTypes.f_268641_))
      return false; 
    return super.m_6469_(damagesource, amount);
  }
  
  public boolean m_6128_() {
    return true;
  }
  
  public boolean m_5825_() {
    return true;
  }
  
  public void m_6123_(Player sourceentity) {
    super.m_6123_(sourceentity);
    AmammaPlayerCollidesWithThisEntityProcedure.execute((LevelAccessor)m_9236_(), (Entity)sourceentity);
  }
  
  public static void init() {
    SpawnPlacements.m_21754_((EntityType)AmammaModEntities.AMAMMA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) -> 
        (world.m_8055_(pos.m_7495_()).m_204336_(BlockTags.f_184228_) && world.m_45524_(pos, 0) > 8));
    DungeonHooks.addDungeonMob((EntityType)AmammaModEntities.AMAMMA.get(), 180);
  }
  
  public static AttributeSupplier.Builder createAttributes() {
    AttributeSupplier.Builder builder = Mob.m_21552_();
    builder = builder.m_22268_(Attributes.f_22279_, 0.5D);
    builder = builder.m_22268_(Attributes.f_22276_, 11.0D);
    builder = builder.m_22268_(Attributes.f_22284_, 0.0D);
    builder = builder.m_22268_(Attributes.f_22281_, 42.0D);
    builder = builder.m_22268_(Attributes.f_22277_, 80.0D);
    builder = builder.m_22268_(Attributes.f_22282_, 0.3D);
    return builder;
  }
}
