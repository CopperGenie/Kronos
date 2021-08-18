package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.NPCScholarModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.NPCScholarEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NPCScholarRenderer extends MobRenderer<NPCScholarEntity, NPCScholarModel<NPCScholarEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/scholar.png");

    public NPCScholarRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCScholarModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCScholarEntity entity) {
        return TEXTURE;
    }
}
