package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.NPCTenebrikModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.NPCTenebrikEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NPCTenebrikRenderer extends MobRenderer<NPCTenebrikEntity, NPCTenebrikModel<NPCTenebrikEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/tenebrik.png");

    public NPCTenebrikRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCTenebrikModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCTenebrikEntity entity) {
        return TEXTURE;
    }
}
