package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.ToadMotherModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.ToadMotherEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BeholderRenderer extends MobRenderer<BeholderEntity, BeholderModel<BeholderEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/beholder.png");

    public BeholderRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BeholderModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(BeholderEntity entity) {
        return TEXTURE;
    }
}
