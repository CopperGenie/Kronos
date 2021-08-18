package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.TreasureFairyEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class TreasureFairyModel<T extends TreasureFairyEntity> extends EntityModel<T> {

    private final ModelRenderer body;
    private final ModelRenderer armLeft;
    private final ModelRenderer armRight;
    private final ModelRenderer cube_r1;
    private final ModelRenderer legLeft;
    private final ModelRenderer cube_r2;
    private final ModelRenderer legRight;
    private final ModelRenderer cube_r3;
    private final ModelRenderer wingLeft;
    private final ModelRenderer cube_r4;
    private final ModelRenderer wingRight;
    private final ModelRenderer cube_r5;

    public TreasureFairyModel() {
        textureWidth = 32;
        textureHeight = 32;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -1.25F, 0.0F);
        body.setTextureOffset(12, 0).addBox(-2.0F, -0.75F, -1.0F, 4.0F, 6.0F, 2.0F, 0.0F, false);
        body.setTextureOffset(0, 0).addBox(-1.5F, -3.75F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(2.0F, -1.0F, 0.0F);
        armLeft.setTextureOffset(12, 15).addBox(0.0F, -1.0F, -4.0F, 1.0F, 2.0F, 5.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-2.0F, -1.0F, 0.0F);
        armRight.setTextureOffset(12, 8).addBox(-1.0F, -1.0F, -4.0F, 1.0F, 2.0F, 5.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-0.5F, -2.6F, -3.2F);
        armRight.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.3927F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(8, 21).addBox(0.0F, -3.3F, -1.6F, 0.0F, 6.0F, 1.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(1.0F, 3.0F, 0.0F);


        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.0F, 0.3F, 0.0F);
        legLeft.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, -0.2182F);
        cube_r2.setTextureOffset(22, 6).addBox(-1.0F, -0.8F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-1.0F, 3.0F, 0.0F);


        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(0.0F, 0.3F, 0.0F);
        legRight.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, 0.2182F);
        cube_r3.setTextureOffset(0, 22).addBox(-1.0F, -0.8F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        wingLeft = new ModelRenderer(this);
        wingLeft.setRotationPoint(1.0F, 0.0F, 1.0F);


        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
        wingLeft.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.5236F, 0.0F);
        cube_r4.setTextureOffset(0, 8).addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 6.0F, 0.0F, false);

        wingRight = new ModelRenderer(this);
        wingRight.setRotationPoint(-1.0F, 0.0F, 1.0F);


        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
        wingRight.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, -0.5236F, 0.0F);
        cube_r5.setTextureOffset(0, 0).addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 6.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        this.armLeft.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * -0.1F;
        this.armRight.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.1F;
        this.legLeft.rotateAngleZ = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.3F;
        this.legRight.rotateAngleZ = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.3F;

        this.wingLeft.rotateAngleY = MathHelper.cos((float)entityIn.ticksExisted * 0.7f) * 0.5F;
        this.wingRight.rotateAngleY = MathHelper.cos((float)entityIn.ticksExisted * 0.7f) * -0.5F;
    }


    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
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
