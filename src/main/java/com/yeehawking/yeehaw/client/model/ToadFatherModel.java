package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.ShadowDemonEntity;
import com.yeehawking.yeehaw.entities.ToadFatherEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ToadFatherModel<T extends ToadFatherEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer mr1;
    private final ModelRenderer mr2;
    private final ModelRenderer mr3;
    private final ModelRenderer armLeft;
    private final ModelRenderer armRight;
    private final ModelRenderer legLeft;
    private final ModelRenderer legRight;

    public ToadFatherModel() {
        textureWidth = 512;
        textureHeight = 512;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -51.0833F, 0.0F);
        head.setTextureOffset(212, 150).addBox(-9.0F, -18.9167F, -9.0F, 18.0F, 18.0F, 18.0F, 0.0F, false);
        head.setTextureOffset(174, 0).addBox(-13.0F, -20.9167F, -13.0F, 26.0F, 2.0F, 26.0F, 0.0F, false);
        head.setTextureOffset(0, 166).addBox(-17.0F, -22.9167F, -17.0F, 34.0F, 2.0F, 34.0F, 0.0F, false);
        head.setTextureOffset(150, 114).addBox(-17.0F, -34.9167F, -17.0F, 34.0F, 2.0F, 34.0F, 0.0F, false);
        head.setTextureOffset(150, 62).addBox(-21.0F, -24.9167F, -21.0F, 42.0F, 2.0F, 42.0F, 0.0F, false);
        head.setTextureOffset(0, 114).addBox(-25.0F, -26.9167F, -25.0F, 50.0F, 2.0F, 50.0F, 0.0F, false);
        head.setTextureOffset(0, 62).addBox(-25.0F, -32.9167F, -25.0F, 50.0F, 2.0F, 50.0F, 0.0F, false);
        head.setTextureOffset(0, 0).addBox(-29.0F, -30.9167F, -29.0F, 58.0F, 4.0F, 58.0F, 0.0F, false);
        head.setTextureOffset(0, 202).addBox(-23.0F, -30.9167F, -35.0F, 46.0F, 4.0F, 6.0F, 0.0F, false);
        head.setTextureOffset(148, 200).addBox(-23.0F, -30.9167F, 29.0F, 46.0F, 4.0F, 6.0F, 0.0F, false);
        head.setTextureOffset(90, 166).addBox(-35.0F, -30.9167F, -23.0F, 6.0F, 4.0F, 46.0F, 0.0F, false);
        head.setTextureOffset(154, 150).addBox(29.0F, -30.9167F, -23.0F, 6.0F, 4.0F, 46.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -25.9636F, 0.0F);
        body.setTextureOffset(76, 235).addBox(-11.0F, 5.9636F, -7.0F, 22.0F, 8.0F, 14.0F, 0.0F, false);
        body.setTextureOffset(148, 237).addBox(-10.0F, -3.0364F, -6.0F, 20.0F, 9.0F, 12.0F, 0.0F, false);
        body.setTextureOffset(0, 232).addBox(-12.0F, -7.0364F, -7.0F, 24.0F, 4.0F, 14.0F, 0.0F, false);
        body.setTextureOffset(0, 212).addBox(-14.0F, -11.0364F, -8.0F, 28.0F, 4.0F, 16.0F, 0.0F, false);
        body.setTextureOffset(72, 216).addBox(-14.0F, -26.0364F, -8.0F, 28.0F, 3.0F, 16.0F, 0.0F, false);
        body.setTextureOffset(234, 225).addBox(-5.0F, -20.0364F, -10.0F, 10.0F, 3.0F, 20.0F, 0.0F, false);
        body.setTextureOffset(230, 200).addBox(-7.0F, -23.0364F, -11.0F, 14.0F, 3.0F, 22.0F, 0.0F, false);
        body.setTextureOffset(170, 210).addBox(-9.0F, -26.0264F, -12.0F, 18.0F, 3.0F, 24.0F, 0.0F, false);
        body.setTextureOffset(174, 28).addBox(-16.0F, -23.0364F, -9.0F, 32.0F, 12.0F, 18.0F, 0.0F, false);
        body.setTextureOffset(0, 27).addBox(14.0F, -27.0364F, -7.0F, 13.0F, 13.0F, 14.0F, 0.0F, false);
        body.setTextureOffset(0, 0).addBox(-27.0F, -27.0364F, -7.0F, 13.0F, 13.0F, 14.0F, 0.0F, false);

        mr1 = new ModelRenderer(this);
        mr1.setRotationPoint(18.0F, -28.1772F, 5.2242F);
        body.addChild(mr1);
        setRotationAngle(mr1, -0.3927F, 0.0F, 0.0F);
        mr1.setTextureOffset(28, 55).addBox(0.0F, -4.8593F, -3.7242F, 0.0F, 10.0F, 7.0F, 0.0F, false);
        mr1.setTextureOffset(28, 72).addBox(-3.5F, -4.8593F, -0.2242F, 7.0F, 10.0F, 0.0F, 0.0F, false);

        mr2 = new ModelRenderer(this);
        mr2.setRotationPoint(-13.0F, -20.1772F, 9.9242F);
        body.addChild(mr2);
        setRotationAngle(mr2, -0.9163F, -0.2618F, 0.0F);
        mr2.setTextureOffset(40, 20).addBox(0.0F, -4.8593F, -3.7242F, 0.0F, 10.0F, 7.0F, 0.0F, false);
        mr2.setTextureOffset(40, 0).addBox(-3.5F, -4.8593F, -0.2242F, 7.0F, 10.0F, 0.0F, 0.0F, false);

        mr3 = new ModelRenderer(this);
        mr3.setRotationPoint(6.0F, -3.1772F, 7.9242F);
        body.addChild(mr3);
        setRotationAngle(mr3, -0.9163F, 0.0873F, 0.0F);
        mr3.setTextureOffset(0, 20).addBox(0.0F, -4.8593F, -3.7242F, 0.0F, 10.0F, 7.0F, 0.0F, false);
        mr3.setTextureOffset(0, 0).addBox(-3.5F, -4.8593F, -0.2242F, 7.0F, 10.0F, 0.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(21.0F, -46.5F, 0.0F);
        armLeft.setTextureOffset(0, 250).addBox(-4.0F, 1.5F, -4.0F, 8.0F, 28.0F, 8.0F, 0.0F, false);
        armLeft.setTextureOffset(202, 248).addBox(-5.0F, 29.5F, -5.0F, 10.0F, 26.0F, 10.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-21.0F, -46.5F, 0.0F);
        armRight.setTextureOffset(150, 62).addBox(-5.0F, 29.5F, -5.0F, 10.0F, 26.0F, 10.0F, 0.0F, false);
        armRight.setTextureOffset(242, 248).addBox(-4.0F, 1.5F, -4.0F, 8.0F, 28.0F, 8.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(8.0F, -13.0F, 0.0F);
        legLeft.setTextureOffset(0, 166).addBox(-4.0F, 19.0F, -4.0F, 8.0F, 18.0F, 8.0F, 0.0F, false);
        legLeft.setTextureOffset(148, 166).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);
        legLeft.setTextureOffset(0, 100).addBox(0.0F, 1.0F, -7.0F, 0.0F, 36.0F, 14.0F, 0.0F, false);
        legLeft.setTextureOffset(252, 106).addBox(-7.0F, 1.0F, 0.0F, 14.0F, 36.0F, 0.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-8.0F, -13.0F, 0.0F);
        legRight.setTextureOffset(150, 114).addBox(-4.0F, 19.0F, -4.0F, 8.0F, 18.0F, 8.0F, 0.0F, false);
        legRight.setTextureOffset(102, 166).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);
        legRight.setTextureOffset(0, 48).addBox(0.0F, 1.0F, -7.0F, 0.0F, 36.0F, 14.0F, 0.0F, false);
        legRight.setTextureOffset(32, 250).addBox(-7.0F, 1.0F, 0.0F, 14.0F, 36.0F, 0.0F, 0.0F, false);
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
