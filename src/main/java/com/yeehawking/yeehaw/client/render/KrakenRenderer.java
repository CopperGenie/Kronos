package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.KrakenModel;
import com.yeehawking.yeehaw.client.model.MindFlayerModel;
import com.yeehawking.yeehaw.entities.KrakenEntity;
import com.yeehawking.yeehaw.entities.MindFlayerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class KrakenRenderer extends MobRenderer<KrakenEntity, KrakenModel<KrakenEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/kraken.png");

    public KrakenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KrakenModel<>(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(KrakenEntity entity) {
        return TEXTURE;
    }
}
