package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.ToadFatherModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.ToadFatherEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ToadFatherRenderer extends MobRenderer<ToadFatherEntity, ToadFatherModel<ToadFatherEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/toad_father.png");

    public ToadFatherRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ToadFatherModel<>(), 1f);
    }

    @Override
    public ResourceLocation getEntityTexture(ToadFatherEntity entity) {
        return TEXTURE;
    }
}
