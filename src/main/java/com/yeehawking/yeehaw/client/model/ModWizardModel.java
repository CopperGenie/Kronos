package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.NPCAelymoreEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ModWizardModel<T extends MobEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer body;
    private final ModelRenderer armLeft;
    private final ModelRenderer armRight;
    private final ModelRenderer legLeft;
    private final ModelRenderer legRight;

    public ModWizardModel() {
        textureWidth = 128;
        textureHeight = 128;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -0.25F, -0.5F);
        head.setTextureOffset(32, 33).addBox(-4.0F, -7.75F, -3.5F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, -8.5F, 1.0F);
        head.addChild(cube_r1);
        setRotationAngle(cube_r1, -1.5708F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(0, 0).addBox(-1.0F, -11.4F, -7.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.0F, -8.5F, 1.0F);
        head.addChild(cube_r2);
        setRotationAngle(cube_r2, -1.0472F, 0.0F, 0.0F);
        cube_r2.setTextureOffset(56, 27).addBox(-2.0F, -9.1F, -4.3F, 4.0F, 5.0F, 4.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(0.0F, -8.5F, 1.0F);
        head.addChild(cube_r3);
        setRotationAngle(cube_r3, -0.6545F, 0.0F, 0.0F);
        cube_r3.setTextureOffset(42, 0).addBox(-3.0F, -6.1F, -3.4F, 6.0F, 4.0F, 6.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(0.0F, -8.5F, 1.0F);
        head.addChild(cube_r4);
        setRotationAngle(cube_r4, -0.4363F, 0.0F, 0.0F);
        cube_r4.setTextureOffset(36, 15).addBox(-4.0F, -3.7F, -4.0F, 8.0F, 4.0F, 8.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(0.0F, -8.5F, 1.0F);
        head.addChild(cube_r5);
        setRotationAngle(cube_r5, -0.2182F, 0.0F, 0.0F);
        cube_r5.setTextureOffset(0, 28).addBox(-5.0F, -1.5F, -5.0F, 10.0F, 3.0F, 10.0F, 0.0F, false);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(0.0F, -8.5F, 1.0F);
        head.addChild(cube_r6);
        setRotationAngle(cube_r6, -0.1745F, 0.0F, 0.0F);
        cube_r6.setTextureOffset(0, 15).addBox(-6.0F, 0.5F, -6.0F, 12.0F, 1.0F, 12.0F, 0.0F, false);
        cube_r6.setTextureOffset(0, 0).addBox(-7.0F, 1.5F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 41).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(4.3333F, 1.8333F, -0.6667F);
        armLeft.setTextureOffset(0, 57).addBox(-0.3233F, -1.8333F, -1.3333F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-4.3333F, 1.8333F, -0.6667F);
        armRight.setTextureOffset(56, 49).addBox(-3.6767F, -1.8333F, -1.3333F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(2.0F, 12.0F, 0.0F);
        legLeft.setTextureOffset(40, 49).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-2.0F, 12.0F, 0.0F);
        legRight.setTextureOffset(24, 49).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.legRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.armRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.armLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }


    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        armLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        armRight.render(matrixStack, buffer, packedLight, packedOverlay);
        legLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        legRight.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

