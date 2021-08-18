package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.SoulTraderEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class SoulTraderModel<T extends SoulTraderEntity> extends EntityModel<T> {

    private final ModelRenderer head;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer body;
    private final ModelRenderer cube_r4;
    private final ModelRenderer armLeft;
    private final ModelRenderer armRight;
    private final ModelRenderer legLeft;
    private final ModelRenderer legRight;

    public SoulTraderModel() {
        textureWidth = 128;
        textureHeight = 128;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 5.4333F, -2.6333F);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(3.5F, -4.3333F, 2.3333F);
        head.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.3491F, 0.0F, 0.3927F);
        cube_r1.setTextureOffset(0, 17).addBox(0.0F, -4.5F, -4.5F, 0.0F, 7.0F, 3.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-3.5F, -4.3333F, 2.3333F);
        head.addChild(cube_r2);
        setRotationAngle(cube_r2, -0.3491F, 0.0F, -0.3927F);
        cube_r2.setTextureOffset(24, 17).addBox(0.0F, -4.5F, -4.5F, 0.0F, 7.0F, 3.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(0.0F, 4.5667F, 2.6333F);
        head.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.2618F, 0.0F, 0.0F);
        cube_r3.setTextureOffset(32, 20).addBox(-3.5F, -12.4F, -4.8F, 7.0F, 7.0F, 7.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 10.5857F, 6.0286F);
        body.setTextureOffset(32, 0).addBox(-5.5F, 5.4143F, -9.0286F, 11.0F, 1.0F, 6.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(0.0F, -0.5857F, -6.0286F);
        body.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.2618F, 0.0F, 0.0F);
        cube_r4.setTextureOffset(24, 34).addBox(-6.0F, -7.4F, 4.7F, 2.0F, 13.0F, 8.0F, 0.0F, false);
        cube_r4.setTextureOffset(0, 40).addBox(4.0F, -7.4F, 4.7F, 2.0F, 13.0F, 8.0F, 0.0F, false);
        cube_r4.setTextureOffset(44, 34).addBox(-4.0F, -7.4F, 2.7F, 8.0F, 13.0F, 2.0F, 0.0F, false);
        cube_r4.setTextureOffset(44, 49).addBox(-4.0F, -7.4F, 12.7F, 8.0F, 13.0F, 2.0F, 0.0F, false);
        cube_r4.setTextureOffset(0, 20).addBox(-4.0F, -5.4F, 4.7F, 8.0F, 12.0F, 8.0F, 0.0F, false);
        cube_r4.setTextureOffset(0, 0).addBox(-6.0F, -5.4F, -5.3F, 12.0F, 12.0F, 8.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(6.0F, 8.0F, -2.0F);
        armLeft.setTextureOffset(20, 55).addBox(0.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-6.0F, 8.0F, -2.0F);
        armRight.setTextureOffset(53, 7).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(3.0F, 15.5F, 0.0F);
        legLeft.setTextureOffset(60, 60).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-3.0F, 15.5F, 0.0F);
        legRight.setTextureOffset(60, 21).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
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
