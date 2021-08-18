package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.SoulTraderModel;
import com.yeehawking.yeehaw.entities.SoulTraderEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class SoulTraderRenderer extends MobRenderer<SoulTraderEntity, SoulTraderModel<SoulTraderEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/soul_trader.png");

    public SoulTraderRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SoulTraderModel<>(), 0.6f);
    }

    @Override
    public ResourceLocation getEntityTexture(SoulTraderEntity entity) {
        return TEXTURE;
    }
}
