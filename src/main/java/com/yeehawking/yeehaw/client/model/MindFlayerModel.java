package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.MindFlayerEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class MindFlayerModel<T extends MindFlayerEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer cloak1;
    private final ModelRenderer cloak2;
    private final ModelRenderer cloak3;
    private final ModelRenderer cloak4;
    private final ModelRenderer collar1;
    private final ModelRenderer collar2;
    private final ModelRenderer collar5;
    private final ModelRenderer collar3;
    private final ModelRenderer collar4;
    private final ModelRenderer armLeft;
    private final ModelRenderer armRight;
    private final ModelRenderer legLeft;
    private final ModelRenderer legRight;
    private final ModelRenderer cape;
    private final ModelRenderer cape1;
    private final ModelRenderer cape2;
    private final ModelRenderer ten1;
    private final ModelRenderer tent1;
    private final ModelRenderer ten2;
    private final ModelRenderer tent2;
    private final ModelRenderer ten3;
    private final ModelRenderer tent3;
    private final ModelRenderer ten4;
    private final ModelRenderer tent4;

    public MindFlayerModel() {
        textureWidth = 128;
        textureHeight = 128;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 2.75F, -0.5F);
        head.setTextureOffset(0, 0).addBox(-3.5F, -7.75F, -3.5F, 7.0F, 8.0F, 7.0F, 0.0F, false);
        head.setTextureOffset(21, 0).addBox(-3.0F, -8.75F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(20, 20).addBox(-4.0F, -21.0F, -2.0F, 8.0F, 10.0F, 4.0F, 0.0F, false);
        body.setTextureOffset(21, 0).addBox(-1.0F, -14.0F, -2.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        cloak1 = new ModelRenderer(this);
        cloak1.setRotationPoint(0.0F, -11.0F, -2.0F);
        body.addChild(cloak1);
        setRotationAngle(cloak1, -0.0873F, 0.0F, 0.0F);
        cloak1.setTextureOffset(48, 48).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 11.0F, 0.0F, 0.0F, false);

        cloak2 = new ModelRenderer(this);
        cloak2.setRotationPoint(0.0F, -11.0F, 2.0F);
        body.addChild(cloak2);
        setRotationAngle(cloak2, 0.0873F, 0.0F, 0.0F);
        cloak2.setTextureOffset(44, 22).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 11.0F, 0.0F, 0.0F, false);

        cloak3 = new ModelRenderer(this);
        cloak3.setRotationPoint(4.0F, -11.0F, 0.0F);
        body.addChild(cloak3);
        setRotationAngle(cloak3, 0.0F, 0.0F, -0.0698F);
        cloak3.setTextureOffset(28, 45).addBox(0.0F, 0.0F, -2.0F, 0.0F, 11.0F, 4.0F, 0.0F, false);

        cloak4 = new ModelRenderer(this);
        cloak4.setRotationPoint(-4.0F, -11.0F, 0.0F);
        body.addChild(cloak4);
        setRotationAngle(cloak4, 0.0F, 0.0F, 0.0698F);
        cloak4.setTextureOffset(28, 3).addBox(0.0F, 0.0F, -2.0F, 0.0F, 11.0F, 4.0F, 0.0F, false);

        collar1 = new ModelRenderer(this);
        collar1.setRotationPoint(0.0F, -21.0F, 3.0F);
        body.addChild(collar1);
        setRotationAngle(collar1, -0.3491F, 0.0F, 0.0F);
        collar1.setTextureOffset(39, 0).addBox(-3.5F, -6.0F, 0.0F, 7.0F, 6.0F, 0.0F, 0.0F, false);

        collar2 = new ModelRenderer(this);
        collar2.setRotationPoint(-4.0F, -21.0F, 0.5F);
        body.addChild(collar2);
        setRotationAngle(collar2, 0.0F, 0.1745F, -0.4363F);
        collar2.setTextureOffset(0, 46).addBox(0.1877F, -6.0217F, -2.1314F, 0.0F, 6.0F, 5.0F, 0.0F, false);

        collar5 = new ModelRenderer(this);
        collar5.setRotationPoint(-2.5F, -21.0F, 3.5F);
        body.addChild(collar5);
        setRotationAngle(collar5, -0.6981F, 0.6981F, -0.8727F);
        collar5.setTextureOffset(48, 38).addBox(1.8187F, -5.6465F, -3.1003F, 0.0F, 6.0F, 3.0F, 0.0F, false);

        collar3 = new ModelRenderer(this);
        collar3.setRotationPoint(-4.0F, -21.0F, 0.5F);
        body.addChild(collar3);
        setRotationAngle(collar3, 0.0F, -0.1745F, 0.4363F);
        collar3.setTextureOffset(36, 44).addBox(7.2931F, -9.2946F, -3.1063F, 0.0F, 6.0F, 5.0F, 0.0F, false);

        collar4 = new ModelRenderer(this);
        collar4.setRotationPoint(3.7687F, -21.9583F, 3.3528F);
        body.addChild(collar4);
        setRotationAngle(collar4, -0.7418F, -0.8727F, 0.9163F);
        collar4.setTextureOffset(36, 52).addBox(-1.189F, -4.6875F, -1.7856F, 0.0F, 6.0F, 3.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(4.3333F, 4.8333F, -0.6667F);
        armLeft.setTextureOffset(32, 34).addBox(-0.3333F, -1.8333F, -1.3333F, 4.0F, 11.0F, 4.0F, 0.0F, false);
        armLeft.setTextureOffset(20, 22).addBox(0.6667F, 9.1667F, -1.3333F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armLeft.setTextureOffset(56, 8).addBox(2.6667F, 9.1667F, -1.3333F, 1.0F, 3.0F, 3.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-4.3333F, 4.8333F, -0.6667F);
        armRight.setTextureOffset(16, 34).addBox(-3.6667F, -1.8333F, -1.3333F, 4.0F, 11.0F, 4.0F, 0.0F, false);
        armRight.setTextureOffset(21, 3).addBox(-1.6667F, 9.1667F, -1.3333F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armRight.setTextureOffset(42, 56).addBox(-3.6667F, 9.1667F, -1.3333F, 1.0F, 3.0F, 3.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(2.0F, 13.0F, 0.0F);
        legLeft.setTextureOffset(0, 36).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-2.0F, 13.0F, 0.0F);
        legRight.setTextureOffset(40, 7).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, 0.0F, false);

        cape = new ModelRenderer(this);
        cape.setRotationPoint(0.0F, 3.0F, 2.0F);
        setRotationAngle(cape, 0.1745F, 0.0F, 0.0F);
        cape.setTextureOffset(0, 15).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 21.0F, 0.0F, 0.0F, false);

        cape1 = new ModelRenderer(this);
        cape1.setRotationPoint(3.5F, 0.0F, 0.0F);
        cape.addChild(cape1);
        setRotationAngle(cape1, 0.0436F, 0.0F, -0.1745F);
        cape1.setTextureOffset(22, 49).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 18.0F, 0.0F, 0.0F, false);

        cape2 = new ModelRenderer(this);
        cape2.setRotationPoint(-3.5F, 0.0F, 0.0F);
        cape.addChild(cape2);
        setRotationAngle(cape2, 0.0436F, 0.0F, 0.1745F);
        cape2.setTextureOffset(16, 49).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 18.0F, 0.0F, 0.0F, false);

        ten1 = new ModelRenderer(this);
        ten1.setRotationPoint(3.0F, 2.0F, -3.5F);
        setRotationAngle(ten1, -0.1407F, 0.3678F, -0.3753F);
        ten1.setTextureOffset(48, 33).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        tent1 = new ModelRenderer(this);
        tent1.setRotationPoint(0.0F, 4.0F, 0.0F);
        ten1.addChild(tent1);
        setRotationAngle(tent1, 0.1745F, -0.3054F, -2.0071F);
        tent1.setTextureOffset(56, 14).addBox(-0.875F, 0.3503F, -0.4184F, 1.0F, 6.0F, 1.0F, 0.0F, false);

        ten2 = new ModelRenderer(this);
        ten2.setRotationPoint(1.1F, 2.0F, -3.5F);
        setRotationAngle(ten2, -0.3215F, 0.4904F, -0.1354F);
        ten2.setTextureOffset(8, 55).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        tent2 = new ModelRenderer(this);
        tent2.setRotationPoint(0.0F, 4.0F, 0.0F);
        ten2.addChild(tent2);
        setRotationAngle(tent2, -0.48F, -0.7418F, 0.0F);
        tent2.setTextureOffset(0, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);

        ten3 = new ModelRenderer(this);
        ten3.setRotationPoint(-1.1F, 2.0F, -3.5F);
        setRotationAngle(ten3, -1.1215F, -0.8657F, 1.1397F);
        ten3.setTextureOffset(54, 39).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        tent3 = new ModelRenderer(this);
        tent3.setRotationPoint(0.0F, 4.0F, 0.0F);
        ten3.addChild(tent3);
        setRotationAngle(tent3, -0.1309F, -2.6616F, -0.8727F);
        tent3.setTextureOffset(20, 15).addBox(-0.2094F, -0.2067F, -0.6455F, 1.0F, 6.0F, 1.0F, 0.0F, false);

        ten4 = new ModelRenderer(this);
        ten4.setRotationPoint(-3.3F, 2.0F, -3.5F);
        setRotationAngle(ten4, -0.1238F, -0.3272F, 0.3695F);
        ten4.setTextureOffset(53, 0).addBox(-0.6114F, -1.0867F, -0.6975F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        tent4 = new ModelRenderer(this);
        tent4.setRotationPoint(0.4F, 4.5F, 0.0F);
        ten4.addChild(tent4);
        setRotationAngle(tent4, -0.3491F, 0.0F, 0.4363F);
        tent4.setTextureOffset(36, 7).addBox(-0.7391F, -0.3673F, -0.5191F, 1.0F, 6.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.ten1.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.08F;
        this.ten1.rotateAngleZ = MathHelper.cos((float)entityIn.ticksExisted * 0.15F) * 0.08F;
        this.ten2.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * -0.08F;
        this.ten2.rotateAngleZ = MathHelper.cos((float)entityIn.ticksExisted * 0.15F) * -0.08F;
        this.ten3.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15F) * 0.08F;
        this.ten3.rotateAngleZ = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.08F;
        this.ten4.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15F) * -0.08F;
        this.ten4.rotateAngleZ = MathHelper.cos((float)entityIn.ticksExisted * 0.15F) * -0.08F;

        this.tent1.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.3F;
        this.tent1.rotateAngleZ = MathHelper.cos((float)entityIn.ticksExisted * 0.15F) * 0.3F;
        this.tent2.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * -0.3F;
        this.tent2.rotateAngleZ = MathHelper.cos((float)entityIn.ticksExisted * 0.15F) * -0.3F;
        this.tent3.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15F) * 0.3F;
        this.tent3.rotateAngleZ = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.3F;
        this.tent4.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15F) * -0.3F;
        this.tent4.rotateAngleZ = MathHelper.cos((float)entityIn.ticksExisted * 0.15F) * -0.3F;

        this.cape.rotateAngleX = 0.18f + MathHelper.cos((float)entityIn.ticksExisted * 0.15F) * 0.15F;

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
        cape.render(matrixStack, buffer, packedLight, packedOverlay);
        ten1.render(matrixStack, buffer, packedLight, packedOverlay);
        ten2.render(matrixStack, buffer, packedLight, packedOverlay);
        ten3.render(matrixStack, buffer, packedLight, packedOverlay);
        ten4.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
