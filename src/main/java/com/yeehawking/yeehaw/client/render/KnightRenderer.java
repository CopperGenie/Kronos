package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.KnightModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.KnightEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class KnightRenderer extends MobRenderer<KnightEntity, KnightModel<KnightEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/knight.png");

    public KnightRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KnightModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(KnightEntity entity) {
        return TEXTURE;
    }
}
