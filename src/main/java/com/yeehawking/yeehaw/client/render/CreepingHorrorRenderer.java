package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.CreepingHorrorModel;
import com.yeehawking.yeehaw.entities.CreepingHorrorEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class CreepingHorrorRenderer extends MobRenderer<CreepingHorrorEntity, CreepingHorrorModel<CreepingHorrorEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/creeping_horror.png");

    public CreepingHorrorRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CreepingHorrorModel<>(), 2f);
    }

    @Override
    public ResourceLocation getEntityTexture(CreepingHorrorEntity entity) {
        return TEXTURE;
    }
}
