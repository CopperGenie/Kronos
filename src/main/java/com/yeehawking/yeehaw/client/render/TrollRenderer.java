package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.TrollModel;
import com.yeehawking.yeehaw.entities.TrollEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TrollRenderer extends MobRenderer<TrollEntity, TrollModel<TrollEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/troll.png");

    public TrollRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TrollModel<>(), 0.7f);
    }

    @Override
    public ResourceLocation getEntityTexture(TrollEntity entity) {
        return TEXTURE;
    }
}
