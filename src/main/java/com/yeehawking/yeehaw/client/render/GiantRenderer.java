package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.CactoidModel;
import com.yeehawking.yeehaw.client.model.GiantModel;
import com.yeehawking.yeehaw.entities.CactoidEntity;
import com.yeehawking.yeehaw.entities.GiantEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GiantRenderer extends MobRenderer<GiantEntity, GiantModel<GiantEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/giant.png");

    public GiantRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GiantModel<>(), 3f);
    }

    @Override
    public ResourceLocation getEntityTexture(GiantEntity entity) {
        return TEXTURE;
    }
}
