package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.TheRestlessModel;
import com.yeehawking.yeehaw.entities.TheRestlessEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TheRestlessRenderer extends MobRenderer<TheRestlessEntity, TheRestlessModel<TheRestlessEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/the_restless.png");

    public TheRestlessRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TheRestlessModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(TheRestlessEntity entity) {
        return TEXTURE;
    }
}
