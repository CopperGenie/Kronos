package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.AngelEntity;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class AngelModel<T extends AngelEntity> extends EntityModel<T> {

    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer armLeft;
    private final ModelRenderer armRight;
    private final ModelRenderer legLeft;
    private final ModelRenderer legRight;
    private final ModelRenderer wingLeft;
    private final ModelRenderer cube_r1;
    private final ModelRenderer wingRight;
    private final ModelRenderer cube_r2;

    public AngelModel() {
        textureWidth = 128;
        textureHeight = 128;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -0.25F, -0.5F);
        head.setTextureOffset(0, 64).addBox(-4.0F, -15.75F, -3.5F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(32, 64).addBox(-4.0F, -32.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(4.01F, -5.0F, 0.0F);
        armLeft.setTextureOffset(80, 0).addBox(0.0F, -3.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-4.01F, -5.0F, 0.0F);
        armRight.setTextureOffset(0, 80).addBox(-4.0F, -3.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(2.0F, 4.0F, 0.0F);
        legLeft.setTextureOffset(72, 64).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-2.0F, 4.0F, 0.0F);
        legRight.setTextureOffset(56, 64).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        wingLeft = new ModelRenderer(this);
        wingLeft.setRotationPoint(0.0F, 0.0F, 0.0F);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, 24.0F, -0.8F);
        wingLeft.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, -0.7854F, 0.0F);
        cube_r1.setTextureOffset(0, 32).addBox(2.0F, -40.0F, 2.0F, 40.0F, 32.0F, 0.0F, 0.0F, false);

        wingRight = new ModelRenderer(this);
        wingRight.setRotationPoint(0.0F, 0.0F, 0.0F);


        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.0F, 24.0F, -0.8F);
        wingRight.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.7854F, 0.0F);
        cube_r2.setTextureOffset(0, 0).addBox(-42.0F, -40.0F, 2.0F, 40.0F, 32.0F, 0.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        this.armLeft.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * -0.2F;
        this.armRight.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.2F;
        this.legLeft.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.3F;
        this.legRight.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * -0.3F;

        this.wingLeft.rotateAngleY = MathHelper.cos((float)entityIn.ticksExisted * 0.4f) * 0.5F;
        this.wingRight.rotateAngleY = MathHelper.cos((float)entityIn.ticksExisted * 0.4f) * -0.5F;
    }


    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        armLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        armRight.render(matrixStack, buffer, packedLight, packedOverlay);
        legLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        legRight.render(matrixStack, buffer, packedLight, packedOverlay);
        wingLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        wingRight.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
