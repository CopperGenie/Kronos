package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.NPCGasmanModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.NPCAelymoreEntity;
import com.yeehawking.yeehaw.entities.NPCGasmanEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NPCGasmanRenderer extends MobRenderer<NPCGasmanEntity, NPCGasmanModel<NPCGasmanEntity>> {

    protected static final ResourceLocation TEXTURE_0 = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/gasman.png");
    protected static final ResourceLocation TEXTURE_1 = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/gasman_1.png");

    public NPCGasmanRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCGasmanModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCGasmanEntity entity) {

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
