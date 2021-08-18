package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.FlyingCarModel;
import com.yeehawking.yeehaw.entities.x0026gEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class x0026gRenderer extends MobRenderer<x0026gEntity, FlyingCarModel<x0026gEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/x0026g.png");

    public x0026gRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FlyingCarModel<>(), 1f);
    }

    @Override
    public ResourceLocation getEntityTexture(x0026gEntity entity) {
        return TEXTURE;
    }
}
