package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.CactoidModel;
import com.yeehawking.yeehaw.client.model.ToadkinModel;
import com.yeehawking.yeehaw.entities.CactoidEntity;
import com.yeehawking.yeehaw.entities.ToadkinEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class CactoidRenderer extends MobRenderer<CactoidEntity, CactoidModel<CactoidEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/cactoid.png");

    public CactoidRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CactoidModel<>(), 0.3f);
    }

    @Override
    public ResourceLocation getEntityTexture(CactoidEntity entity) {
        return TEXTURE;
    }
}
