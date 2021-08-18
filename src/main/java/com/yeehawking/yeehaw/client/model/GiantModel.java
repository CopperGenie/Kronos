package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.GiantEntity;
import com.yeehawking.yeehaw.entities.ShadowDemonEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GiantModel<T extends GiantEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer armLeft;
    private final ModelRenderer armRight;
    private final ModelRenderer legLeft;
    private final ModelRenderer legRight;

    public GiantModel() {
        textureWidth = 256;
        textureHeight = 256;

        head = new ModelRenderer(this);
        head.setRotationPoint(-2.0F, -92.3333F, -5.6667F);
        head.setTextureOffset(138, 76).addBox(-3.0F, -3.6667F, 0.6667F, 10.0F, 2.0F, 10.0F, 0.0F, false);
        head.setTextureOffset(104, 92).addBox(-6.0F, -21.6667F, -2.3333F, 16.0F, 18.0F, 16.0F, 0.0F, false);
        head.setTextureOffset(0, 2).addBox(-3.0F, -17.6667F, -3.3333F, 4.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(0, 0).addBox(-3.0F, -14.6667F, -3.3333F, 4.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(4, 4).addBox(0.0F, -16.6667F, -3.3333F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(0, 4).addBox(-3.0F, -16.6667F, -3.3333F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -54.0F, 0.0F);
        body.setTextureOffset(0, 81).addBox(-20.0F, 11.0F, -10.0F, 40.0F, 7.0F, 20.0F, 0.0F, false);
        body.setTextureOffset(0, 0).addBox(-22.0F, -40.0F, -15.0F, 44.0F, 51.0F, 30.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(22.0F, -88.25F, 0.0F);
        armLeft.setTextureOffset(148, 38).addBox(1.0F, 29.25F, -4.0F, 8.0F, 30.0F, 8.0F, 0.0F, false);
        armLeft.setTextureOffset(40, 108).addBox(0.0F, -5.75F, -5.0F, 10.0F, 35.0F, 10.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-22.0F, -88.25F, 0.0F);
        armRight.setTextureOffset(148, 0).addBox(-9.0F, 29.25F, -4.0F, 8.0F, 30.0F, 8.0F, 0.0F, false);
        armRight.setTextureOffset(0, 108).addBox(-10.0F, -5.75F, -5.0F, 10.0F, 35.0F, 10.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(12.0F, -39.25F, 0.0F);
        legLeft.setTextureOffset(0, 153).addBox(-4.0F, 33.25F, -4.0F, 8.0F, 30.0F, 8.0F, 0.0F, false);
        legLeft.setTextureOffset(80, 126).addBox(-5.0F, -1.75F, -5.0F, 10.0F, 35.0F, 10.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-12.0F, -39.25F, 0.0F);
        legRight.setTextureOffset(32, 153).addBox(-4.0F, 33.25F, -4.0F, 8.0F, 30.0F, 8.0F, 0.0F, false);
        legRight.setTextureOffset(120, 126).addBox(-5.0F, -1.75F, -5.0F, 10.0F, 35.0F, 10.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.legRight.rotateAngleX = MathHelper.cos(limbSwing * 0.3662F) * 0.8F * limbSwingAmount;
        this.legLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.3662F + (float)Math.PI) * 0.8F * limbSwingAmount;
        this.armRight.rotateAngleX = MathHelper.cos(limbSwing * 0.3662F + (float)Math.PI) * 0.8F * limbSwingAmount;
        this.armLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.3662F) * 0.8F * limbSwingAmount;

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
