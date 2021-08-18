package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.HerobrineModel;
import com.yeehawking.yeehaw.entities.HerobrineEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class HerobrineRenderer extends MobRenderer<HerobrineEntity, HerobrineModel<HerobrineEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/herobrine.png");

    public HerobrineRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HerobrineModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(HerobrineEntity entity) {
        return TEXTURE;
    }
}
