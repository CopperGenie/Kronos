package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.MindFlayerModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.MindFlayerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MindFlayerRenderer extends MobRenderer<MindFlayerEntity, MindFlayerModel<MindFlayerEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/mind_flayer.png");

    public MindFlayerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MindFlayerModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(MindFlayerEntity entity) {
        return TEXTURE;
    }
}
