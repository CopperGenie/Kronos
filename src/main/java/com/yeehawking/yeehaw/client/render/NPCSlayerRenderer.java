package com.yeehawking.yeehaw.client.render;

import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.NPCSlayerModel;
import com.yeehawking.yeehaw.entities.NPCSlayerEntity;
import com.yeehawking.yeehaw.init.ModItems;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class NPCSlayerRenderer extends MobRenderer<NPCSlayerEntity, NPCSlayerModel<NPCSlayerEntity>> {

    protected static final ResourceLocation TEXTURE_1 = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/slayer_1.png");
    protected static final ResourceLocation TEXTURE_2 = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/slayer_2.png");
    protected static final ResourceLocation TEXTURE_3 = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/slayer_3.png");
    protected static final ResourceLocation TEXTURE_4 = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/slayer_4.png");

    public NPCSlayerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCSlayerModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCSlayerEntity entity) {

        switch(entity.getTexture()) {
            case 0:
                return TEXTURE_1;
            case 1:
                return TEXTURE_2;
            case 2:
                return TEXTURE_3;
            case 3:
                return TEXTURE_4;
            default:
                return null;
        }
    }
}
