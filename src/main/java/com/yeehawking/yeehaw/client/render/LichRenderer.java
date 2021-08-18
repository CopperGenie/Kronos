package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.LichModel;
import com.yeehawking.yeehaw.client.model.MindFlayerModel;
import com.yeehawking.yeehaw.entities.LichEntity;
import com.yeehawking.yeehaw.entities.MindFlayerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class LichRenderer extends MobRenderer<LichEntity, LichModel<LichEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/lich.png");

    public LichRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new LichModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(LichEntity entity) {
        return TEXTURE;
    }
}
