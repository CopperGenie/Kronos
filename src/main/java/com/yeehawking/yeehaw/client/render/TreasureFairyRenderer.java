package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.TreasureFairyModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.TreasureFairyEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TreasureFairyRenderer extends MobRenderer<TreasureFairyEntity, TreasureFairyModel<TreasureFairyEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/treasure_fairy.png");

    public TreasureFairyRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TreasureFairyModel<>(), 0.4f);
    }

    @Override
    public ResourceLocation getEntityTexture(TreasureFairyEntity entity) {
        return TEXTURE;
    }
}
