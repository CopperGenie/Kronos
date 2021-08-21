package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.init.ModEntityType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class NPCSpawner extends Item {
    public NPCSpawner() {
        super(new Properties().group(Yeehaw.SUMMON_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getEntityWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) playerIn.getEntityWorld();

            ModEntityType.NPC_KENT.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_ELIZABETH.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_MATHEUS.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_JAFIR.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_SCHOLAR.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_WYNNBRIM.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_KRORK.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_GASMAN.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_PHANTASMUS.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_OSWIR.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_PERAGON.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_TENEBRIK.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_AELYMORE.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_SLAYER.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_TOMMY_TEMPUS.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);
            ModEntityType.NPC_TIMEKEEPER.get().spawn(serverWorld, null, null, playerIn.getPosition().add(0,2,0), SpawnReason.EVENT, false, true);

        }
        playerIn.playSound(SoundEvents.ENTITY_COW_AMBIENT, 1f, 0.7f);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

    // Item description
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.text0().mergeStyle(TextFormatting.AQUA));
    }
    // References en_us
    @OnlyIn(Dist.CLIENT)
    public IFormattableTextComponent text0() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc"); }
}
