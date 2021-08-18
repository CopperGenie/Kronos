package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.GenieModel;
import com.yeehawking.yeehaw.entities.GenieEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GenieRenderer extends MobRenderer<GenieEntity, GenieModel<GenieEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/genie.png");

    public GenieRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GenieModel<>(), 0.6f);
    }

    @Override
    public ResourceLocation getEntityTexture(GenieEntity entity) {
        return TEXTURE;
    }
}
