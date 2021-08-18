package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.AlliedKnightModel;
import com.yeehawking.yeehaw.client.model.RoseQueenModel;
import com.yeehawking.yeehaw.entities.AlliedKnightEntity;
import com.yeehawking.yeehaw.entities.RoseQueenEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RoseQueenRenderer extends MobRenderer<RoseQueenEntity, RoseQueenModel<RoseQueenEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/rose_queen.png");

    public RoseQueenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RoseQueenModel<>(), 1f);
    }

    @Override
    public ResourceLocation getEntityTexture(RoseQueenEntity entity) {
        return TEXTURE;
    }
}
