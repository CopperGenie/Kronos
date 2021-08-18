package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.ToadMotherModel;
import com.yeehawking.yeehaw.client.model.ToadkinModel;
import com.yeehawking.yeehaw.entities.ToadMotherEntity;
import com.yeehawking.yeehaw.entities.ToadkinEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ToadMotherRenderer extends MobRenderer<ToadMotherEntity, ToadMotherModel<ToadMotherEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/toad_mother.png");

    public ToadMotherRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ToadMotherModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(ToadMotherEntity entity) {
        return TEXTURE;
    }
}
