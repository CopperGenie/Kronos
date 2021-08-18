package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.PoliceCarModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.ThePoliceEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ThePoliceRenderer extends MobRenderer<ThePoliceEntity, PoliceCarModel<ThePoliceEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/the_police.png");

    public ThePoliceRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PoliceCarModel<>(), 1f);
    }

    @Override
    public ResourceLocation getEntityTexture(ThePoliceEntity entity) {
        return TEXTURE;
    }
}
