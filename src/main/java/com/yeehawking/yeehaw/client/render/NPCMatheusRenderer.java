package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.NPCMatheusModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.NPCMatheusEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NPCMatheusRenderer extends MobRenderer<NPCMatheusEntity, NPCMatheusModel<NPCMatheusEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/matheus.png");

    public NPCMatheusRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCMatheusModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCMatheusEntity entity) {
        return TEXTURE;
    }
}
