package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.NPCOswirModel;
import com.yeehawking.yeehaw.client.model.NPCTenebrikModel;
import com.yeehawking.yeehaw.entities.NPCOswirEntity;
import com.yeehawking.yeehaw.entities.NPCTenebrikEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NPCOswirRenderer extends MobRenderer<NPCOswirEntity, NPCOswirModel<NPCOswirEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/oswir.png");

    public NPCOswirRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCOswirModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCOswirEntity entity) {
        return TEXTURE;
    }
}
