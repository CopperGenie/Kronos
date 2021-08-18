package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.NPCWynnbrimModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.NPCWynnbrimEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NPCWynnbrimRenderer extends MobRenderer<NPCWynnbrimEntity, NPCWynnbrimModel<NPCWynnbrimEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/wynnbrim.png");

    public NPCWynnbrimRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCWynnbrimModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCWynnbrimEntity entity) {
        return TEXTURE;
    }
}
