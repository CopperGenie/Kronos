package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.NPCJafirModel;
import com.yeehawking.yeehaw.entities.NPCJafirEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NPCJafirRenderer extends MobRenderer<NPCJafirEntity, NPCJafirModel<NPCJafirEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/jafir.png");

    public NPCJafirRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCJafirModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCJafirEntity entity) {
        return TEXTURE;
    }
}
