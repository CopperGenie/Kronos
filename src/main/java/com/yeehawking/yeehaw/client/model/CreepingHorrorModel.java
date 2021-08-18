package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.CreepingHorrorEntity;
import com.yeehawking.yeehaw.entities.TrollEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class CreepingHorrorModel<T extends CreepingHorrorEntity> extends EntityModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer tail;
    private final ModelRenderer t1;
    private final ModelRenderer t2;
    private final ModelRenderer t3;
    private final ModelRenderer t4;
    private final ModelRenderer armRight;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer armLeft;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;
    private final ModelRenderer cube_r9;
    private final ModelRenderer cube_r10;

    public CreepingHorrorModel() {
        textureWidth = 512;
        textureHeight = 512;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -11.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-35.0F, -35.0F, -35.0F, 70.0F, 70.0F, 70.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -5.49F, -25.5F);
        head.setTextureOffset(177, 204).addBox(-30.0F, -29.5F, -30.5F, 60.0F, 59.0F, 47.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(0.0F, 9.255F, 26.0F);
        tail.setTextureOffset(0, 140).addBox(-30.0F, -44.245F, 0.0F, 60.0F, 59.0F, 52.0F, 0.0F, false);

        t1 = new ModelRenderer(this);
        t1.setRotationPoint(0.0F, 3.755F, 51.0F);
        tail.addChild(t1);
        t1.setTextureOffset(228, 88).addBox(-22.0F, -32.99F, -13.0F, 44.0F, 44.0F, 52.0F, 0.0F, false);

        t2 = new ModelRenderer(this);
        t2.setRotationPoint(0.0F, 3.495F, 34.0F);
        t1.addChild(t2);
        t2.setTextureOffset(0, 251).addBox(-15.0F, -22.495F, -13.0F, 30.0F, 30.0F, 52.0F, 0.0F, false);

        t3 = new ModelRenderer(this);
        t3.setRotationPoint(0.0F, 2.505F, 33.5F);
        t2.addChild(t3);
        t3.setTextureOffset(280, 0).addBox(-10.0F, -14.99F, -15.5F, 20.0F, 20.0F, 52.0F, 0.0F, false);

        t4 = new ModelRenderer(this);
        t4.setRotationPoint(0.0F, -0.01F, 34.5F);
        t3.addChild(t4);
        t4.setTextureOffset(102, 353).addBox(-5.0F, -5.0F, -20.0F, 10.0F, 10.0F, 52.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-27.0F, 2.0F, -4.5F);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-41.2557F, -3.6801F, -84.4347F);
        armRight.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.6109F, -1.8762F, -0.3927F);
        cube_r1.setTextureOffset(0, 42).addBox(-4.5F, 0.5F, -8.5F, 25.0F, 7.0F, 7.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-54.4829F, 6.753F, -78.6736F);
        armRight.addChild(cube_r2);
        setRotationAngle(cube_r2, -0.3054F, -0.9599F, -0.7418F);
        cube_r2.setTextureOffset(0, 14).addBox(-8.5F, 0.5F, -1.9F, 27.0F, 7.0F, 7.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-75.1417F, -1.6F, -63.071F);
        armRight.addChild(cube_r3);
        setRotationAngle(cube_r3, -0.1309F, -0.9599F, 0.6545F);
        cube_r3.setTextureOffset(172, 177).addBox(-9.5F, -15.5F, -26.5F, 23.0F, 7.0F, 7.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(27.0F, 22.0F, 4.5F);
        armRight.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, -1.309F, 0.0F);
        cube_r4.setTextureOffset(344, 184).addBox(-97.6F, -28.1F, 41.9F, 59.0F, 16.0F, 18.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(27.0F, 22.0F, 4.5F);
        armRight.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, -0.6545F, 0.0F);
        cube_r5.setTextureOffset(301, 310).addBox(-67.0F, -30.0F, 1.0F, 57.0F, 20.0F, 23.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(27.0F, 2.0F, -4.5F);


        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(41.2557F, -3.6801F, -84.4347F);
        armLeft.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.6109F, 1.8762F, 0.3927F);
        cube_r6.setTextureOffset(0, 28).addBox(-20.5F, 0.5F, -8.5F, 25.0F, 7.0F, 7.0F, 0.0F, false);

        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(54.4829F, 6.753F, -78.6736F);
        armLeft.addChild(cube_r7);
        setRotationAngle(cube_r7, -0.3054F, 0.9599F, 0.7418F);
        cube_r7.setTextureOffset(0, 0).addBox(-18.5F, 0.5F, -1.9F, 27.0F, 7.0F, 7.0F, 0.0F, false);

        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(75.1417F, -1.6F, -63.071F);
        armLeft.addChild(cube_r8);
        setRotationAngle(cube_r8, -0.1309F, 0.9599F, -0.6545F);
        cube_r8.setTextureOffset(0, 56).addBox(-13.5F, -15.5F, -26.5F, 23.0F, 7.0F, 7.0F, 0.0F, false);

        cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(-27.0F, 22.0F, 4.5F);
        armLeft.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.0F, 1.309F, 0.0F);
        cube_r9.setTextureOffset(0, 335).addBox(38.6F, -28.1F, 41.9F, 59.0F, 16.0F, 18.0F, 0.0F, false);

        cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(-27.0F, 22.0F, 4.5F);
        armLeft.addChild(cube_r10);
        setRotationAngle(cube_r10, 0.0F, 0.6545F, 0.0F);
        cube_r10.setTextureOffset(141, 310).addBox(10.0F, -30.0F, 1.0F, 57.0F, 20.0F, 23.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.armRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.0F * limbSwingAmount;
        this.armLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.head.rotateAngleY = MathHelper.cos(ageInTicks * 0.1F) * 0.2F;

        this.tail.rotateAngleY = MathHelper.cos(ageInTicks * 0.1F) * .5F;
        this.t1.rotateAngleY = MathHelper.cos(ageInTicks * 0.1F) * .5F;
        this.t2.rotateAngleY = MathHelper.cos(ageInTicks * 0.1F) * .5F;
        this.t3.rotateAngleY = MathHelper.cos(ageInTicks * 0.1F) * .5F;
        this.t4.rotateAngleY = MathHelper.cos(ageInTicks * 0.1F) * .5F;
    }


    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        tail.render(matrixStack, buffer, packedLight, packedOverlay);
        armRight.render(matrixStack, buffer, packedLight, packedOverlay);
        armLeft.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
