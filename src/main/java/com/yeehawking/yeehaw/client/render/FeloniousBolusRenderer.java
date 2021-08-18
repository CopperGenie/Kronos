package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.FeloniousBolusModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.FeloniousBolusEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class FeloniousBolusRenderer extends MobRenderer<FeloniousBolusEntity, FeloniousBolusModel<FeloniousBolusEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/felonious_bolus.png");

    public FeloniousBolusRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FeloniousBolusModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(FeloniousBolusEntity entity) {
        return TEXTURE;
    }
}
