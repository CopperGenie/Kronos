package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.GenieEntity;
import com.yeehawking.yeehaw.entities.MindFlayerEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GenieModel<T extends GenieEntity> extends EntityModel<T> {
    private final ModelRenderer armLeft;
    private final ModelRenderer body;
    private final ModelRenderer armRight;
    private final ModelRenderer head;
    private final ModelRenderer ponytail;
    private final ModelRenderer ear1;
    private final ModelRenderer ear2;

    public GenieModel() {
        textureWidth = 256;
        textureHeight = 256;

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(-12.6F, -8.9F, 0.1F);
        armLeft.setTextureOffset(76, 76).addBox(-8.0F, -5.0F, -5.0F, 10.0F, 18.0F, 10.0F, 0.0F, false);
        armLeft.setTextureOffset(93, 104).addBox(-7.0F, 5.0F, 17.0F, 5.0F, 8.0F, 8.0F, 0.0F, false);
        armLeft.setTextureOffset(0, 30).addBox(-2.0F, 5.0F, 21.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);
        armLeft.setTextureOffset(0, 108).addBox(-2.0F, 4.7F, 17.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);
        armLeft.setTextureOffset(106, 68).addBox(-7.0F, 5.0F, 25.0F, 3.0F, 3.0F, 8.0F, 0.0F, false);
        armLeft.setTextureOffset(59, 15).addBox(-7.0F, 6.0F, 2.0F, 8.0F, 8.0F, 15.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-10.5F, -37.0F, -7.5F, 21.0F, 14.0F, 16.0F, 0.0F, false);
        body.setTextureOffset(89, 38).addBox(-15.4F, -39.0F, -6.0F, 8.0F, 8.0F, 12.0F, 0.0F, false);
        body.setTextureOffset(0, 88).addBox(7.6F, -39.0F, -6.0F, 8.0F, 8.0F, 12.0F, 0.0F, false);
        body.setTextureOffset(89, 58).addBox(-10.5F, -37.0F, 8.5F, 21.0F, 9.0F, 1.0F, 0.0F, false);
        body.setTextureOffset(91, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 3.0F, 8.0F, 0.0F, false);
        body.setTextureOffset(90, 15).addBox(-4.0F, -6.0F, -10.0F, 8.0F, 3.0F, 11.0F, 0.0F, false);
        body.setTextureOffset(105, 29).addBox(-3.0F, -9.0F, -13.0F, 6.0F, 3.0F, 6.0F, 0.0F, false);
        body.setTextureOffset(43, 30).addBox(-2.0F, -12.0F, -19.0F, 4.0F, 3.0F, 8.0F, 0.0F, false);
        body.setTextureOffset(31, 100).addBox(-4.5F, -13.0F, -4.5F, 9.0F, 4.0F, 9.0F, 0.0F, false);
        body.setTextureOffset(58, 0).addBox(-5.5F, -17.0F, -5.5F, 11.0F, 4.0F, 11.0F, 0.0F, false);
        body.setTextureOffset(0, 30).addBox(-7.5F, -23.0F, -6.5F, 15.0F, 6.0F, 13.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(12.6F, -8.9F, 0.1F);
        armRight.setTextureOffset(36, 72).addBox(-2.0F, -5.0F, -5.0F, 10.0F, 18.0F, 10.0F, 0.0F, false);
        armRight.setTextureOffset(67, 104).addBox(2.0F, 5.0F, 17.0F, 5.0F, 8.0F, 8.0F, 0.0F, false);
        armRight.setTextureOffset(0, 0).addBox(0.0F, 5.0F, 21.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);
        armRight.setTextureOffset(66, 72).addBox(-1.0F, 4.7F, 17.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);
        armRight.setTextureOffset(78, 38).addBox(4.0F, 5.0F, 25.0F, 3.0F, 3.0F, 8.0F, 0.0F, false);
        armRight.setTextureOffset(0, 57).addBox(-1.0F, 6.0F, 2.0F, 8.0F, 8.0F, 15.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 24.0F, 0.0F);
        head.setTextureOffset(45, 45).addBox(-5.5F, -51.0F, 0.5F, 11.0F, 16.0F, 11.0F, 0.0F, false);
        head.setTextureOffset(0, 49).addBox(-4.5F, -37.0F, 10.5F, 9.0F, 3.0F, 3.0F, 0.0F, false);
        head.setTextureOffset(29, 16).addBox(-1.0F, -46.0F, 11.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        ponytail = new ModelRenderer(this);
        ponytail.setRotationPoint(0.0F, -47.5F, -0.5F);
        head.addChild(ponytail);
        setRotationAngle(ponytail, -0.4363F, 0.0F, 0.0F);
        ponytail.setTextureOffset(31, 49).addBox(-1.0F, -3.5937F, -0.5774F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        ear1 = new ModelRenderer(this);
        ear1.setRotationPoint(6.2F, -49.0F, 5.2F);
        head.addChild(ear1);
        setRotationAngle(ear1, 0.1745F, 0.0F, 0.3054F);
        ear1.setTextureOffset(58, 0).addBox(-0.3986F, -1.1215F, -2.3312F, 2.0F, 6.0F, 3.0F, 0.0F, false);

        ear2 = new ModelRenderer(this);
        ear2.setRotationPoint(-6.2F, -49.0F, 5.2F);
        head.addChild(ear2);
        setRotationAngle(ear2, 0.1745F, 0.0F, -0.3054F);
        ear2.setTextureOffset(0, 56).addBox(-1.6014F, -1.1215F, -2.3312F, 2.0F, 6.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.armRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.0F * limbSwingAmount;
        this.armLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        armLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        armRight.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
