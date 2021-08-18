package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.ToadMotherEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ToadMotherModel<T extends ToadMotherEntity> extends EntityModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer legRight;
    private final ModelRenderer legLeft;
    private final ModelRenderer armLeft;
    private final ModelRenderer armRight;

    public ToadMotherModel() {
        textureWidth = 128;
        textureHeight = 128;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(66, 0).addBox(-6.0F, -5.0F, -4.0F, 12.0F, 1.0F, 8.0F, 0.0F, false);
        body.setTextureOffset(0, 64).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 1.0F, 10.0F, 0.0F, false);
        body.setTextureOffset(60, 60).addBox(-6.0F, -20.0F, -6.0F, 12.0F, 14.0F, 12.0F, 0.0F, false);
        body.setTextureOffset(54, 24).addBox(-6.5F, -22.0F, -6.5F, 13.0F, 2.0F, 13.0F, 0.0F, false);
        body.setTextureOffset(0, 0).addBox(-11.0F, -24.0F, -11.0F, 22.0F, 2.0F, 22.0F, 0.0F, false);
        body.setTextureOffset(54, 44).addBox(-6.0F, -28.0F, -6.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);
        body.setTextureOffset(0, 44).addBox(-9.0F, -26.0F, -9.0F, 18.0F, 2.0F, 18.0F, 0.0F, false);
        body.setTextureOffset(0, 24).addBox(-9.0F, -23.5F, -9.0F, 18.0F, 2.0F, 18.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-6.0F, 20.0F, 0.0F);
        legRight.setTextureOffset(0, 10).addBox(0.0F, 0.0F, -3.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(1.5F, 20.0F, 0.0F);
        legLeft.setTextureOffset(0, 0).addBox(-0.5F, 0.0F, -3.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(3.8333F, 9.0F, -0.8333F);
        armLeft.setTextureOffset(0, 44).addBox(2.1667F, -2.0F, -1.1667F, 4.0F, 11.0F, 4.0F, 0.0F, false);
        armLeft.setTextureOffset(48, 64).addBox(3.1667F, 9.0F, -1.1667F, 3.0F, 1.0F, 4.0F, 0.0F, false);
        armLeft.setTextureOffset(0, 3).addBox(2.1667F, 7.0F, -2.1667F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-7.8333F, 9.0F, -0.8333F);
        armRight.setTextureOffset(0, 24).addBox(-2.1667F, -2.0F, -1.1667F, 4.0F, 11.0F, 4.0F, 0.0F, false);
        armRight.setTextureOffset(34, 64).addBox(-2.1667F, 9.0F, -1.1667F, 3.0F, 1.0F, 4.0F, 0.0F, false);
        armRight.setTextureOffset(0, 0).addBox(-0.1667F, 7.0F, -2.1667F, 2.0F, 2.0F, 1.0F, 0.0F, false);
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
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        legRight.render(matrixStack, buffer, packedLight, packedOverlay);
        legLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        armLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        armRight.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
