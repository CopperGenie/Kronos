package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.CactoidEntity;
import com.yeehawking.yeehaw.entities.ToadMotherEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class CactoidModel<T extends CactoidEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer legLeft;
    private final ModelRenderer legRight;
    private final ModelRenderer armLeft;
    private final ModelRenderer armRight;

    public CactoidModel() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 24.0F, 0.0F);
        head.setTextureOffset(32, 32).addBox(-4.0F, -31.0F, 0.0F, 8.0F, 7.0F, 0.0F, 0.0F, false);
        head.setTextureOffset(32, 32).addBox(0.0F, -31.0F, -4.0F, 0.0F, 7.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(0, 0).addBox(-7.0F, -24.0F, -6.0F, 14.0F, 20.0F, 12.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(6.5F, 20.0F, 0.0F);
        legLeft.setTextureOffset(4, 4).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-6.5F, 20.0F, 0.0F);
        legRight.setTextureOffset(0, 0).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(7.0F, 8.0F, 0.0F);
        armLeft.setTextureOffset(16, 32).addBox(0.0F, -2.0F, -3.0F, 2.0F, 14.0F, 6.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-7.0F, 8.0F, 0.0F);
        armRight.setTextureOffset(0, 32).addBox(-2.0F, -2.0F, -3.0F, 2.0F, 14.0F, 6.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.legRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.armRight.rotateAngleX = -MathHelper.sin((float)entityIn.ticksExisted * 0.1f+ (float)Math.PI) * .35F;
        this.armLeft.rotateAngleX = MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F;
        this.armRight.rotateAngleZ = 0.7f + -MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F;
        this.armLeft.rotateAngleZ = -0.7f + MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F;
    }


    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        legLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        legRight.render(matrixStack, buffer, packedLight, packedOverlay);
        armLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        armRight.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
