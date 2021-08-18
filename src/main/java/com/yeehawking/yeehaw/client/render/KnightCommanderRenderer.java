package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.KnightCommanderModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.KnightCommanderEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class KnightCommanderRenderer extends MobRenderer<KnightCommanderEntity, KnightCommanderModel<KnightCommanderEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/knight_commander.png");

    public KnightCommanderRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KnightCommanderModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(KnightCommanderEntity entity) {
        return TEXTURE;
    }
}
