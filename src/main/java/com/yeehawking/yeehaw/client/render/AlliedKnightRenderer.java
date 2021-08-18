package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.AlliedKnightModel;
import com.yeehawking.yeehaw.entities.AlliedKnightEntity;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.util.ResourceLocation;

/*
public class AlliedKnightRenderer extends BipedRenderer<AlliedKnightEntity, AlliedKnightModel<AlliedKnightEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/allied_knight.png");

    public AlliedKnightRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new AlliedKnightModel<>(), 1f);
        this.addLayer(new BipedArmorLayer<>(this, new SkeletonModel(0.5F, true), new SkeletonModel(1.0F, true)));
    }

    @Override
    public ResourceLocation getEntityTexture(AlliedKnightEntity entity) {
        return TEXTURE;
    }
}*/

public class AlliedKnightRenderer extends MobRenderer<AlliedKnightEntity, AlliedKnightModel<AlliedKnightEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/allied_knight.png");

    public AlliedKnightRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new AlliedKnightModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(AlliedKnightEntity entity) {
        return TEXTURE;
    }
}
