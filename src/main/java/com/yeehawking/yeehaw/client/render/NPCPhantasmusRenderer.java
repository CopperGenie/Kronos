package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.NPCAelymoreModel;
import com.yeehawking.yeehaw.client.model.NPCPhantasmusModel;
import com.yeehawking.yeehaw.entities.NPCAelymoreEntity;
import com.yeehawking.yeehaw.entities.NPCPhantasmusEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NPCPhantasmusRenderer extends MobRenderer<NPCPhantasmusEntity, NPCPhantasmusModel<NPCPhantasmusEntity>> {

    protected static final ResourceLocation TEXTURE_0 = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/phantasmus.png");
    protected static final ResourceLocation TEXTURE_1 = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/aelymore_1.png");

    public NPCPhantasmusRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCPhantasmusModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCPhantasmusEntity entity) {

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
