package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.NimbusGiantEntity;
import com.yeehawking.yeehaw.entities.ShadowDemonEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NimbusGiantModel extends AnimatedGeoModel<NimbusGiantEntity>
{
    @Override
    public ResourceLocation getModelLocation(NimbusGiantEntity object)
    {
        return new ResourceLocation(GeckoLib.ModID, "geo/entity/nimbus_giant.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NimbusGiantEntity object)
    {
        return new ResourceLocation(GeckoLib.ModID, "textures/entity/nimbus_giant.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NimbusGiantEntity object)
    {
        return new ResourceLocation(GeckoLib.ModID, "animations/entity/nimbus_giant.animation.json");
    }
}

//public class NimbusGiantModel<T extends NimbusGiantEntity> extends EntityModel<T> {
//    private final ModelRenderer yRotator1;
//    private final ModelRenderer yRotator2;
//    private final ModelRenderer head;
//    private final ModelRenderer body;
//    private final ModelRenderer leg1;
//    private final ModelRenderer leg2;
//    private final ModelRenderer leg3;
//
//    public NimbusGiantModel() {
//        textureWidth = 256;
//        textureHeight = 256;
//
//        yRotator1 = new ModelRenderer(this);
//        yRotator1.setRotationPoint(0.0F, -2.0F, 0.0F);
//        yRotator1.setTextureOffset(165, 128).addBox(-35.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//        yRotator1.setTextureOffset(140, 162).addBox(33.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//        yRotator1.setTextureOffset(132, 162).addBox(-1.0F, -6.0F, 33.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//        yRotator1.setTextureOffset(124, 162).addBox(-1.0F, -6.0F, -35.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//        yRotator1.setTextureOffset(16, 158).addBox(-25.0F, -6.0F, -25.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//        yRotator1.setTextureOffset(8, 158).addBox(-25.0F, -6.0F, 23.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//        yRotator1.setTextureOffset(0, 158).addBox(23.0F, -6.0F, 23.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//        yRotator1.setTextureOffset(62, 154).addBox(23.0F, -6.0F, -25.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//
//        yRotator2 = new ModelRenderer(this);
//        yRotator2.setRotationPoint(0.0F, -2.0F, 0.0F);
//        yRotator2.setTextureOffset(78, 131).addBox(-15.0F, -6.0F, 31.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//        yRotator2.setTextureOffset(70, 125).addBox(-15.0F, -6.0F, -33.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//        yRotator2.setTextureOffset(62, 125).addBox(13.0F, -6.0F, -33.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//        yRotator2.setTextureOffset(8, 125).addBox(13.0F, -6.0F, 31.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//        yRotator2.setTextureOffset(0, 125).addBox(-33.0F, -6.0F, 13.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//        yRotator2.setTextureOffset(0, 89).addBox(31.0F, -6.0F, 13.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//        yRotator2.setTextureOffset(73, 45).addBox(31.0F, -6.0F, -15.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//        yRotator2.setTextureOffset(65, 45).addBox(-33.0F, -6.0F, -15.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
//
//        head = new ModelRenderer(this);
//        head.setRotationPoint(-0.0714F, -46.8571F, 3.9286F);
//        head.setTextureOffset(0, 0).addBox(7.0714F, -4.1429F, -3.9286F, 2.0F, 8.0F, 8.0F, 0.0F, false);
//        head.setTextureOffset(0, 45).addBox(-8.9286F, -5.1429F, -5.9286F, 2.0F, 7.0F, 8.0F, 0.0F, false);
//        head.setTextureOffset(122, 64).addBox(-5.9286F, -8.1429F, -6.9286F, 12.0F, 12.0F, 12.0F, 0.0F, false);
//        head.setTextureOffset(96, 162).addBox(-2.9286F, 3.8571F, -4.9286F, 7.0F, 2.0F, 7.0F, 0.0F, false);
//        head.setTextureOffset(0, 16).addBox(-1.9286F, 7.8571F, -2.9286F, 4.0F, 2.0F, 4.0F, 0.0F, false);
//        head.setTextureOffset(96, 131).addBox(-4.9286F, -6.1429F, 5.0714F, 9.0F, 10.0F, 1.0F, 0.0F, false);
//        head.setTextureOffset(135, 100).addBox(-5.9286F, -9.1429F, -5.9286F, 11.0F, 1.0F, 11.0F, 0.0F, false);
//
//        body = new ModelRenderer(this);
//        body.setRotationPoint(-0.875F, -14.4583F, -0.1667F);
//        body.setTextureOffset(0, 125).addBox(-22.125F, -6.5417F, -8.8333F, 2.0F, 16.0F, 17.0F, 0.0F, false);
//        body.setTextureOffset(123, 114).addBox(-24.125F, -9.5417F, -4.8333F, 4.0F, 16.0F, 17.0F, 0.0F, false);
//        body.setTextureOffset(0, 0).addBox(-20.125F, -12.5417F, -8.8333F, 40.0F, 22.0F, 23.0F, 0.0F, false);
//        body.setTextureOffset(65, 45).addBox(-17.125F, 9.4583F, -4.8333F, 35.0F, 3.0F, 16.0F, 0.0F, false);
//        body.setTextureOffset(145, 88).addBox(-4.125F, 15.4583F, -0.8333F, 10.0F, 5.0F, 6.0F, 0.0F, false);
//        body.setTextureOffset(21, 125).addBox(-2.125F, 13.4583F, 2.1667F, 9.0F, 5.0F, 6.0F, 0.0F, false);
//        body.setTextureOffset(76, 131).addBox(19.875F, -12.5417F, -5.8333F, 3.0F, 15.0F, 14.0F, 0.0F, false);
//        body.setTextureOffset(38, 125).addBox(19.875F, -7.5417F, -1.8333F, 5.0F, 15.0F, 14.0F, 0.0F, false);
//        body.setTextureOffset(0, 97).addBox(-11.125F, -16.5417F, -10.8333F, 26.0F, 14.0F, 14.0F, 0.0F, false);
//        body.setTextureOffset(67, 66).addBox(-17.125F, -15.5417F, -6.8333F, 16.0F, 22.0F, 23.0F, 0.0F, false);
//        body.setTextureOffset(0, 45).addBox(-7.125F, -14.5417F, -6.8333F, 20.0F, 19.0F, 25.0F, 0.0F, false);
//        body.setTextureOffset(80, 111).addBox(-7.125F, -10.5417F, -13.8333F, 25.0F, 15.0F, 5.0F, 0.0F, false);
//        body.setTextureOffset(103, 0).addBox(-15.125F, -7.5417F, -16.8333F, 25.0F, 15.0F, 8.0F, 0.0F, false);
//
//        leg1 = new ModelRenderer(this);
//        leg1.setRotationPoint(-13.0F, 9.0F, -11.6667F);
//        leg1.setTextureOffset(26, 154).addBox(-3.0F, -4.0F, -5.3333F, 6.0F, 4.0F, 12.0F, 0.0F, false);
//        leg1.setTextureOffset(151, 40).addBox(-6.0F, -2.0F, -7.3333F, 7.0F, 5.0F, 11.0F, 0.0F, false);
//        leg1.setTextureOffset(160, 14).addBox(-1.0F, -2.0F, -3.3333F, 7.0F, 7.0F, 9.0F, 0.0F, false);
//
//        leg2 = new ModelRenderer(this);
//        leg2.setRotationPoint(18.5F, 13.3333F, 0.8333F);
//        leg2.setTextureOffset(158, 56).addBox(-3.5F, -5.3333F, -1.8333F, 8.0F, 7.0F, 9.0F, 0.0F, false);
//        leg2.setTextureOffset(148, 147).addBox(-1.5F, -3.3333F, -6.8333F, 8.0F, 9.0F, 9.0F, 0.0F, false);
//        leg2.setTextureOffset(126, 23).addBox(-7.5F, -2.3333F, -5.8333F, 9.0F, 6.0F, 11.0F, 0.0F, false);
//
//        leg3 = new ModelRenderer(this);
//        leg3.setRotationPoint(1.0F, 15.6667F, 15.0F);
//        leg3.setTextureOffset(62, 160).addBox(-1.0F, -3.6667F, -4.0F, 9.0F, 7.0F, 8.0F, 0.0F, false);
//        leg3.setTextureOffset(110, 147).addBox(-6.0F, -1.6667F, -2.0F, 11.0F, 7.0F, 8.0F, 0.0F, false);
//        leg3.setTextureOffset(148, 112).addBox(-8.0F, -5.6667F, -6.0F, 10.0F, 8.0F, 8.0F, 0.0F, false);
//    }
//
//    @Override
//    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        this.yRotator1.rotateAngleY = (float)entityIn.ticksExisted * 0.2F;
//        this.yRotator2.rotateAngleY = (float)entityIn.ticksExisted * 0.2F;
//        // translation todo
//
//    }
//
//    @Override
//    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
//        yRotator1.render(matrixStack, buffer, packedLight, packedOverlay);
//        yRotator2.render(matrixStack, buffer, packedLight, packedOverlay);
//        head.render(matrixStack, buffer, packedLight, packedOverlay);
//        body.render(matrixStack, buffer, packedLight, packedOverlay);
//        leg1.render(matrixStack, buffer, packedLight, packedOverlay);
//        leg2.render(matrixStack, buffer, packedLight, packedOverlay);
//        leg3.render(matrixStack, buffer, packedLight, packedOverlay);
//    }
//
//    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
//}
