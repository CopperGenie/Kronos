package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.AlliedKnightModel;
import com.yeehawking.yeehaw.client.model.DionysusModel;
import com.yeehawking.yeehaw.entities.AlliedKnightEntity;
import com.yeehawking.yeehaw.entities.DionysusEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class DionysusRenderer extends MobRenderer<DionysusEntity, DionysusModel<DionysusEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/dionysus.png");

    public DionysusRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new DionysusModel<>(), 2f);
    }

    @Override
    public ResourceLocation getEntityTexture(DionysusEntity entity) {
        return TEXTURE;
    }
}
