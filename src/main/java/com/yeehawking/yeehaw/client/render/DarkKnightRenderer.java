package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.DarkKnightModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.DarkKnightEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class DarkKnightRenderer extends MobRenderer<DarkKnightEntity, DarkKnightModel<DarkKnightEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/dark_knight.png");

    public DarkKnightRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new DarkKnightModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(DarkKnightEntity entity) {
        return TEXTURE;
    }
}
