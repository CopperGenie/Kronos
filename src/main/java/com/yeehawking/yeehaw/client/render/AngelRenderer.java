package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.AngelModel;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.entities.AngelEntity;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class AngelRenderer extends MobRenderer<AngelEntity, AngelModel<AngelEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/angel.png");

    public AngelRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new AngelModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(AngelEntity entity) {
        return TEXTURE;
    }
}
