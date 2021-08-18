package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.NPCElizabethModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.NPCElizabethEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NPCElizabethRenderer extends MobRenderer<NPCElizabethEntity, NPCElizabethModel<NPCElizabethEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/elizabeth.png");

    public NPCElizabethRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCElizabethModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCElizabethEntity entity) {
        return TEXTURE;
    }
}
