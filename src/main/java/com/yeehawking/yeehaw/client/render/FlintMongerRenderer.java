package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.FlintMongerModel;
import com.yeehawking.yeehaw.entities.FlintMongerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class FlintMongerRenderer extends MobRenderer<FlintMongerEntity, FlintMongerModel<FlintMongerEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/flint_monger.png");

    public FlintMongerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FlintMongerModel<>(), 0.6f);
    }

    @Override
    public ResourceLocation getEntityTexture(FlintMongerEntity entity) {
        return TEXTURE;
    }
}
