package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.GnomeElderEntity;
import com.yeehawking.yeehaw.entities.GnomeEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GnomeElderModel<T extends GnomeElderEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer h8_r1;
    private final ModelRenderer h5_r1;
    private final ModelRenderer h4_r1;
    private final ModelRenderer body;
    private final ModelRenderer armLeft;
    private final ModelRenderer armRight;
    private final ModelRenderer legLeft;
    private final ModelRenderer legRight;

    public GnomeElderModel() {
        textureWidth = 32;
        textureHeight = 32;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 15.1364F, 0.0F);
        head.setTextureOffset(11, 10).addBox(-1.5F, -2.1364F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
        head.setTextureOffset(10, 4).addBox(-1.5F, -3.1364F, -2.5F, 3.0F, 1.0F, 4.0F, 0.0F, false);
        head.setTextureOffset(20, 0).addBox(-1.0F, 0.5F, -2.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(10, 9).addBox(-0.5F, 4.5F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        h8_r1 = new ModelRenderer(this);
        h8_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(h8_r1);
        setRotationAngle(h8_r1, -0.9599F, 0.0F, 0.0F);
        h8_r1.setTextureOffset(0, 8).addBox(-0.5F, 0.5F, -1.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        h5_r1 = new ModelRenderer(this);
        h5_r1.setRotationPoint(0.0F, 8.8636F, 0.0F);
        head.addChild(h5_r1);
        setRotationAngle(h5_r1, 0.0F, -0.3927F, 0.5236F);
        h5_r1.setTextureOffset(0, 0).addBox(-3.8F, -10.6F, 1.3F, 1.0F, 2.0F, 0.0F, 0.0F, false);

        h4_r1 = new ModelRenderer(this);
        h4_r1.setRotationPoint(0.0F, 8.8636F, 0.0F);
        head.addChild(h4_r1);
        setRotationAngle(h4_r1, 0.0F, 0.3927F, -0.5236F);
        h4_r1.setTextureOffset(11, 0).addBox(2.8F, -10.6F, 1.3F, 1.0F, 2.0F, 0.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-2.0F, -8.0F, -1.5F, 4.0F, 5.0F, 3.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(2.0F, 17.0F, 0.0F);
        armLeft.setTextureOffset(8, 16).addBox(0.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-2.0F, 17.0F, 0.0F);
        armRight.setTextureOffset(0, 13).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(1.0F, 21.0F, 0.0F);
        legLeft.setTextureOffset(0, 20).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-1.0F, 21.0F, 0.0F);
        legRight.setTextureOffset(16, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
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
