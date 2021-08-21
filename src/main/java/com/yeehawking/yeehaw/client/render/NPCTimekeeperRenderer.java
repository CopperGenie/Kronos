package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.AlliedKnightModel;
import com.yeehawking.yeehaw.client.model.NPCTimekeeperModel;
import com.yeehawking.yeehaw.entities.AlliedKnightEntity;
import com.yeehawking.yeehaw.entities.NPCTimekeeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NPCTimekeeperRenderer extends MobRenderer<NPCTimekeeperEntity, NPCTimekeeperModel<NPCTimekeeperEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/timekeeper.png");

    public NPCTimekeeperRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCTimekeeperModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCTimekeeperEntity entity) {
        return TEXTURE;
    }
}
