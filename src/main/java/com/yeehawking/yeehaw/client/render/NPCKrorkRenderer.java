package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.NPCGasmanModel;
import com.yeehawking.yeehaw.client.model.NPCKrorkModel;
import com.yeehawking.yeehaw.entities.NPCGasmanEntity;
import com.yeehawking.yeehaw.entities.NPCKrorkEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NPCKrorkRenderer extends MobRenderer<NPCKrorkEntity, NPCKrorkModel<NPCKrorkEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/krork.png");

    public NPCKrorkRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCKrorkModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCKrorkEntity entity) {
        return TEXTURE;
    }
}
