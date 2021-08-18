package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.NPCOswirModel;
import com.yeehawking.yeehaw.client.model.NPCPeragonModel;
import com.yeehawking.yeehaw.entities.NPCOswirEntity;
import com.yeehawking.yeehaw.entities.NPCPeragonEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NPCPeragonRenderer extends MobRenderer<NPCPeragonEntity, NPCPeragonModel<NPCPeragonEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/peragon.png");

    public NPCPeragonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCPeragonModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCPeragonEntity entity) {
        return TEXTURE;
    }
}
