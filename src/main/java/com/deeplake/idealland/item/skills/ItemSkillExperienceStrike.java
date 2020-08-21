package com.deeplake.idealland.item.skills;

import com.deeplake.idealland.entity.creatures.misc.Entity33Elk;
import com.deeplake.idealland.util.AchvDef;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import static com.deeplake.idealland.util.CommonFunctions.TryGrantAchv;

public class ItemSkillExperienceStrike extends ItemSkillBase {
    public ItemSkillExperienceStrike(String name) {
        super(name);
        setCD(2f,0.5f);
        maxLevel = 3;
        showDamageDesc = false;
        showCDDesc = true;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand handIn) {
        World world = playerIn.world;
        if (!world.isRemote)
        {
            DamageSource damageSource = DamageSource.causePlayerDamage(playerIn).setMagicDamage();
            if (target.getHealth() < playerIn.experienceLevel)
            {
                damageSource.setDamageBypassesArmor();
            }
            target.attackEntityFrom(damageSource, playerIn.experienceLevel);
        }
        playerIn.swingArm(handIn);
        activateCoolDown(playerIn, stack);
        target.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1f, 1f);
        return true;
    }
}