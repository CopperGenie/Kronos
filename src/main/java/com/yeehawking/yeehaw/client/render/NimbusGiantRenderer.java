package com.yeehawking.yeehaw.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.Yeehaw;
import com.yeehawking.yeehaw.client.model.BeholderModel;
import com.yeehawking.yeehaw.client.model.NimbusGiantModel;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.NimbusGiantEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import software.bernie.example.entity.GeoExampleEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NimbusGiantRenderer extends GeoEntityRenderer<NimbusGiantEntity> {
    public NimbusGiantRenderer(EntityRendererManager renderManager) {
        super(renderManager, new NimbusGiantModel());
    }

    @Override
    public ResourceLocation getEntityTexture(NimbusGiantEntity entity) {
        return null;
    }

    @Override
    public RenderType getRenderType(NimbusGiantEntity animatable, float partialTicks, MatrixStack stack,
                                    IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.getEntityTranslucent(getTextureLocation(animatable));
    }
}

//public class NimbusGiantRenderer extends MobRenderer<NimbusGiantEntity, NimbusGiantModel<NimbusGiantEntity>> {
//
//    protected static final ResourceLocation TEXTURE = new ResourceLocation(Yeehaw.MOD_ID, "textures/entity/nimbus_giant.png");
//
//    public NimbusGiantRenderer(EntityRendererManager renderManagerIn) {
//        super(renderManagerIn, new NimbusGiantModel<>(), 1f);
//    }
//
//    @Override
//    public ResourceLocation getEntityTexture(NimbusGiantEntity entity) {
//        return TEXTURE;
//    }
//}
