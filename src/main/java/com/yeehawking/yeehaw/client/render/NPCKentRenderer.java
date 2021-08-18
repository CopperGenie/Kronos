package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.AngelModel;
import com.yeehawking.yeehaw.client.model.NPCKentModel;
import com.yeehawking.yeehaw.entities.AngelEntity;
import com.yeehawking.yeehaw.entities.NPCAelymoreEntity;
import com.yeehawking.yeehaw.entities.NPCKentEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NPCKentRenderer extends MobRenderer<NPCKentEntity, NPCKentModel<NPCKentEntity>> {

    protected static final ResourceLocation TEXTURE_0 = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/kent_0.png");
    protected static final ResourceLocation TEXTURE_1 = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/kent_1.png");

    public NPCKentRenderer(EntityRendererManager renderManagerIn) { super(renderManagerIn, new NPCKentModel<>(), 0.5f); }

    @Override
    public ResourceLocation getEntityTexture(NPCKentEntity entity) {

        switch(entity.getTexture()) {
            case 0:
                return TEXTURE_0;
            case 1:
                return TEXTURE_1;
            default:
                return null;
        }
    }
}
