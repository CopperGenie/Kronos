package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.ToadkinModel;
import com.yeehawking.yeehaw.entities.ToadkinEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ToadkinRenderer extends MobRenderer<ToadkinEntity, ToadkinModel<ToadkinEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/toadkin.png");

    public ToadkinRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ToadkinModel<>(), 0.3f);
    }

    @Override
    public ResourceLocation getEntityTexture(ToadkinEntity entity) {
        return TEXTURE;
    }
}
