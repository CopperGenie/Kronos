package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.ShadowDemonModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.ShadowDemonEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ShadowDemonRenderer extends MobRenderer<ShadowDemonEntity, ShadowDemonModel<ShadowDemonEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/shadow_demon.png");

    public ShadowDemonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ShadowDemonModel(), 1f);
    }

    @Override
    public ResourceLocation getEntityTexture(ShadowDemonEntity entity) {
        return TEXTURE;
    }
}
