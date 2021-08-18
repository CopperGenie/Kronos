package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.LichEntity;
import com.yeehawking.yeehaw.entities.TrollEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class TrollModel<T extends TrollEntity> extends EntityModel<T> {
    private final ModelRenderer legRight;
    private final ModelRenderer legLeft;
    private final ModelRenderer body;
    private final ModelRenderer armLeft;
    private final ModelRenderer armRight;
    private final ModelRenderer head;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;

    public TrollModel() {
        textureWidth = 128;
        textureHeight = 128;

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-3.0F, 13.0F, 0.0F);
        legRight.setTextureOffset(44, 18).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(3.0F, 14.0F, 0.0F);
        legLeft.setTextureOffset(44, 0).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 8.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 12.0F, 10.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(6.0F, 5.0F, 0.0F);
        armLeft.setTextureOffset(0, 37).addBox(0.0F, -3.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-6.0F, 5.0F, 0.0F);
        armRight.setTextureOffset(26, 31).addBox(-6.0F, -3.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -3.1667F, 0.15F);
        head.setTextureOffset(0, 22).addBox(-4.0F, -1.8333F, -4.15F, 8.0F, 7.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(0, 0).addBox(-1.0F, 0.6667F, -6.75F, 2.0F, 2.0F, 3.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(4.0F, -1.8333F, 3.85F);
        head.addChild(cube_r1);
        setRotationAngle(cube_r1, -1.0036F, -0.6981F, 1.4835F);
        cube_r1.setTextureOffset(24, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-4.0F, -1.8333F, 3.85F);
        head.addChild(cube_r2);
        setRotationAngle(cube_r2, -1.0036F, 0.6981F, -1.4835F);
        cube_r2.setTextureOffset(32, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(4.0F, -1.8333F, -0.15F);
        head.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, 0.4363F);
        cube_r3.setTextureOffset(0, 22).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(-4.0F, -1.8333F, -0.15F);
        head.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, -0.4363F);
        cube_r4.setTextureOffset(34, 0).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
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
        legRight.render(matrixStack, buffer, packedLight, packedOverlay);
        legLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        armLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        armRight.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
