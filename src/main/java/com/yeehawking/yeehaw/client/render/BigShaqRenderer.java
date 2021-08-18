package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.AlliedKnightModel;
import com.yeehawking.yeehaw.client.model.BigShaqModel;
import com.yeehawking.yeehaw.entities.AlliedKnightEntity;
import com.yeehawking.yeehaw.entities.BigShaqEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BigShaqRenderer extends EntityRenderer<BigShaqEntity> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/big_shaq.png");

    public BigShaqRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
        this.shadowSize = 1F;
    }

    @Override
    public ResourceLocation getEntityTexture(BigShaqEntity entity) {
        return TEXTURE;
    }
}
