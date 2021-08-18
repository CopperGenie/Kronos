package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.ToadkinEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ToadkinModel<T extends ToadkinEntity> extends EntityModel<T> {

    private final ModelRenderer body;
    private final ModelRenderer legRight;
    private final ModelRenderer legLeft;
    private final ModelRenderer armLeft;
    private final ModelRenderer armRight;

    public ToadkinModel() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(24, 21).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);
        body.setTextureOffset(26, 26).addBox(-3.0F, -10.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);
        body.setTextureOffset(0, 0).addBox(-5.0F, -11.25F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
        body.setTextureOffset(27, 11).addBox(-3.0F, -13.25F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
        body.setTextureOffset(0, 21).addBox(-4.0F, -12.25F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
        body.setTextureOffset(0, 11).addBox(-4.5F, -11.0F, -4.5F, 9.0F, 1.0F, 9.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-3.0F, 22.0F, 0.0F);
        legRight.setTextureOffset(0, 0).addBox(0.0F, 0.0F, -1.5F, 2.0F, 2.0F, 3.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(1.0F, 22.0F, 0.0F);
        legLeft.setTextureOffset(0, 5).addBox(0.0F, 0.0F, -1.5F, 2.0F, 2.0F, 3.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(2.0F, 16.5F, 0.0F);
        armLeft.setTextureOffset(0, 21).addBox(1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-4.0F, 16.5F, 0.0F);
        armRight.setTextureOffset(0, 11).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
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
