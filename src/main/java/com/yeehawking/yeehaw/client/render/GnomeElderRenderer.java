package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.GnomeElderModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.GnomeElderEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GnomeElderRenderer extends MobRenderer<GnomeElderEntity, GnomeElderModel<GnomeElderEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/gnome_elder.png");

    public GnomeElderRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GnomeElderModel<>(), 0.3f);
    }

    @Override
    public ResourceLocation getEntityTexture(GnomeElderEntity entity) {
        return TEXTURE;
    }
}
