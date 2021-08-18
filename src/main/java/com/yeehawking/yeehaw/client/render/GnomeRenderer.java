package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.GnomeModel;
import com.yeehawking.yeehaw.client.model.ToadkinModel;
import com.yeehawking.yeehaw.entities.GnomeEntity;
import com.yeehawking.yeehaw.entities.ToadkinEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GnomeRenderer extends MobRenderer<GnomeEntity, GnomeModel<GnomeEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/gnome.png");

    public GnomeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GnomeModel<>(), 0.3f);
    }

    @Override
    public ResourceLocation getEntityTexture(GnomeEntity entity) {
        return TEXTURE;
    }
}
