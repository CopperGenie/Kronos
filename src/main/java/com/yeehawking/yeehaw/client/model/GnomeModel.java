package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.GnomeEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GnomeModel<T extends GnomeEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;
    private final ModelRenderer cube_r9;
    private final ModelRenderer cube_r10;
    private final ModelRenderer body;
    private final ModelRenderer armLeft;
    private final ModelRenderer armRight;
    private final ModelRenderer legLeft;
    private final ModelRenderer legRight;

    public GnomeModel() {
        textureWidth = 32;
        textureHeight = 32;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 15.1364F, 0.0F);
        head.setTextureOffset(0, 8).addBox(-1.5F, -2.1364F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, 8.8636F, 0.0F);
        head.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.3927F, -0.5236F);
        cube_r1.setTextureOffset(0, 23).addBox(2.8F, -10.6F, 1.3F, 1.0F, 2.0F, 0.0F, 0.0F, true);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.0F, 8.8636F, 0.0F);
        head.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, -0.3927F, 0.5236F);
        cube_r2.setTextureOffset(0, 23).addBox(-3.8F, -10.6F, 1.3F, 1.0F, 2.0F, 0.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(0.0F, -1.6364F, 1.0F);
        head.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.1309F, 0.0F, 0.0F);
        cube_r3.setTextureOffset(18, 13).addBox(-0.5F, -8.4F, -0.4F, 1.0F, 8.0F, 1.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(1.0F, -1.6364F, 0.0F);
        head.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.1309F, 0.7854F, 0.0F);
        cube_r4.setTextureOffset(14, 0).addBox(-1.2F, -4.4F, -0.1F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(-1.0F, -1.6364F, 0.0F);
        head.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.1309F, -0.7854F, 0.0F);
        cube_r5.setTextureOffset(21, 9).addBox(0.2F, -4.4F, -0.1F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(-1.0F, -1.6364F, 0.0F);
        head.addChild(cube_r6);
        setRotationAngle(cube_r6, -0.1309F, 0.7854F, 0.0F);
        cube_r6.setTextureOffset(22, 14).addBox(0.2F, -4.4F, -0.9F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(1.0F, -1.6364F, 0.0F);
        head.addChild(cube_r7);
        setRotationAngle(cube_r7, -0.1309F, -0.7854F, 0.0F);
        cube_r7.setTextureOffset(16, 22).addBox(-1.2F, -4.4F, -0.9F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(1.0F, -1.6364F, 0.0F);
        head.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 0.0F, -0.1309F);
        cube_r8.setTextureOffset(0, 14).addBox(-0.4F, -8.4F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, false);

        cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(-1.0F, -1.6364F, 0.0F);
        head.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.0F, 0.0F, 0.1309F);
        cube_r9.setTextureOffset(4, 14).addBox(-0.6F, -8.4F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, false);

        cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(0.0F, -1.6364F, -1.0F);
        head.addChild(cube_r10);
        setRotationAngle(cube_r10, -0.1309F, 0.0F, 0.0F);
        cube_r10.setTextureOffset(20, 0).addBox(-0.5F, -8.4F, -0.6F, 1.0F, 8.0F, 1.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-2.0F, -8.0F, -1.5F, 4.0F, 5.0F, 3.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(2.0F, 17.0F, 0.0F);
        armLeft.setTextureOffset(10, 13).addBox(0.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-2.0F, 17.0F, 0.0F);
        armRight.setTextureOffset(12, 6).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(1.0F, 21.0F, 0.0F);
        legLeft.setTextureOffset(8, 20).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-1.0F, 21.0F, 0.0F);
        legRight.setTextureOffset(20, 20).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
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
