package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.AlliedKnightModel;
import com.yeehawking.yeehaw.client.model.NPCTommyTempusModel;
import com.yeehawking.yeehaw.entities.AlliedKnightEntity;
import com.yeehawking.yeehaw.entities.NPCTommyTempusEntity;
import com.yeehawking.yeehaw.entities.ToadMotherEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NPCTommyTempusRenderer extends MobRenderer<NPCTommyTempusEntity, NPCTommyTempusModel<NPCTommyTempusEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/tommy_tempus.png");

    public NPCTommyTempusRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCTommyTempusModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCTommyTempusEntity entity) {
        return TEXTURE;
    }
}
