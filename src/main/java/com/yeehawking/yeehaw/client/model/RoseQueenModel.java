package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.AlliedKnightEntity;
import com.yeehawking.yeehaw.entities.RoseQueenEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class RoseQueenModel<T extends RoseQueenEntity> extends EntityModel<T> {

    private final ModelRenderer head;
    private final ModelRenderer cube_r1;
    private final ModelRenderer armLeft;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer armRight;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer legLeft;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;
    private final ModelRenderer body1;
    private final ModelRenderer cube_r8;
    private final ModelRenderer cube_r9;
    private final ModelRenderer cube_r10;
    private final ModelRenderer cube_r11;
    private final ModelRenderer body3;
    private final ModelRenderer cube_r12;
    private final ModelRenderer cube_r13;
    private final ModelRenderer cube_r14;
    private final ModelRenderer cube_r15;
    private final ModelRenderer legRight;
    private final ModelRenderer cube_r16;
    private final ModelRenderer cube_r17;
    private final ModelRenderer body2;
    private final ModelRenderer cube_r18;
    private final ModelRenderer cube_r19;
    private final ModelRenderer cube_r20;
    private final ModelRenderer cube_r21;
    private final ModelRenderer body4;
    private final ModelRenderer cube_r22;
    private final ModelRenderer cube_r23;
    private final ModelRenderer cube_r24;
    private final ModelRenderer cube_r25;

    public RoseQueenModel() {
        textureWidth = 128;
        textureHeight = 128;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.4F, -23.6F, 0.4F);
        head.setTextureOffset(0, 0).addBox(-4.4F, -8.4F, -4.4F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(16, 46).addBox(-4.4F, -10.4F, -0.4F, 4.0F, 2.0F, 4.0F, 0.0F, false);
        head.setTextureOffset(46, 27).addBox(-0.4F, -9.4F, -4.4F, 4.0F, 1.0F, 4.0F, 0.0F, false);
        head.setTextureOffset(0, 46).addBox(-0.4F, -11.4F, -0.4F, 4.0F, 3.0F, 4.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(4.1F, 28.6F, -4.4F);
        head.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.0F, 0.7854F);
        cube_r1.setTextureOffset(32, 0).addBox(-27.0F, -21.0F, 2.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(4.0F, -22.0F, 0.0F);


        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(1.5F, 11.8441F, 2.0107F);
        armLeft.addChild(cube_r2);
        setRotationAngle(cube_r2, -0.1047F, 0.0F, -0.2618F);
        cube_r2.setTextureOffset(16, 16).addBox(-0.1F, -13.2F, -0.9F, 2.0F, 28.0F, 2.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(1.5F, 11.8441F, -2.0107F);
        armLeft.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.1047F, 0.0F, -0.2618F);
        cube_r3.setTextureOffset(24, 16).addBox(-0.1F, -13.2F, -0.5F, 2.0F, 28.0F, 2.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-3.4F, -22.0F, 0.0F);


        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(-2.1F, 11.8441F, 2.0107F);
        armRight.addChild(cube_r4);
        setRotationAngle(cube_r4, -0.1047F, 0.0F, 0.2618F);
        cube_r4.setTextureOffset(0, 16).addBox(-1.9F, -13.2F, -0.9F, 2.0F, 28.0F, 2.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(-2.1F, 11.8441F, -2.0107F);
        armRight.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.1047F, 0.0F, 0.2618F);
        cube_r5.setTextureOffset(8, 16).addBox(-1.9F, -13.2F, -0.5F, 2.0F, 28.0F, 2.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(0.0F, -16.0F, 0.0F);


        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(3.4F, 34.5F, -1.0F);
        legLeft.addChild(cube_r6);
        setRotationAngle(cube_r6, -0.1396F, 0.0F, 0.0F);
        cube_r6.setTextureOffset(40, 10).addBox(-1.0F, -14.1F, 1.9F, 2.0F, 19.0F, 2.0F, 0.0F, false);

        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(3.4F, 34.5F, 1.0F);
        legLeft.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.1396F, 0.0F, 0.0F);
        cube_r7.setTextureOffset(40, 31).addBox(-1.0F, -14.1F, -3.0F, 2.0F, 19.0F, 2.0F, 0.0F, false);

        body1 = new ModelRenderer(this);
        body1.setRotationPoint(4.5F, 21.0F, -4.0F);
        legLeft.addChild(body1);


        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(0.0F, 0.0F, 0.0F);
        body1.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 0.0F, 0.7854F);
        cube_r8.setTextureOffset(46, 56).addBox(-21.3F, -18.6F, -0.1F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(0.0F, 0.0F, 0.0F);
        body1.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.7854F, 0.0F, 0.0F);
        cube_r9.setTextureOffset(60, 44).addBox(-9.0F, -13.6F, 14.3F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(0.0F, 0.0F, 0.0F);
        body1.addChild(cube_r10);
        setRotationAngle(cube_r10, 0.0F, 0.0F, -0.7854F);
        cube_r10.setTextureOffset(62, 0).addBox(3.0F, -15.0F, 7.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        cube_r11 = new ModelRenderer(this);
        cube_r11.setRotationPoint(0.0F, 0.0F, 0.0F);
        body1.addChild(cube_r11);
        setRotationAngle(cube_r11, -0.7854F, 0.0F, 0.0F);
        cube_r11.setTextureOffset(8, 62).addBox(-2.0F, -10.0F, 0.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        body3 = new ModelRenderer(this);
        body3.setRotationPoint(4.0F, 21.0F, 4.9F);
        legLeft.addChild(body3);
        setRotationAngle(body3, 0.0F, -1.5708F, 0.0F);


        cube_r12 = new ModelRenderer(this);
        cube_r12.setRotationPoint(0.0F, 0.0F, 0.0F);
        body3.addChild(cube_r12);
        setRotationAngle(cube_r12, 0.0F, 0.0F, 0.7854F);
        cube_r12.setTextureOffset(54, 0).addBox(-21.3F, -18.6F, -0.1F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        cube_r13 = new ModelRenderer(this);
        cube_r13.setRotationPoint(0.0F, 0.0F, 0.0F);
        body3.addChild(cube_r13);
        setRotationAngle(cube_r13, 0.7854F, 0.0F, 0.0F);
        cube_r13.setTextureOffset(54, 54).addBox(-9.0F, -13.6F, 14.3F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        cube_r14 = new ModelRenderer(this);
        cube_r14.setRotationPoint(0.0F, 0.0F, 0.0F);
        body3.addChild(cube_r14);
        setRotationAngle(cube_r14, 0.0F, 0.0F, -0.7854F);
        cube_r14.setTextureOffset(56, 12).addBox(3.0F, -15.0F, 7.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        cube_r15 = new ModelRenderer(this);
        cube_r15.setRotationPoint(0.0F, 0.0F, 0.0F);
        body3.addChild(cube_r15);
        setRotationAngle(cube_r15, -0.7854F, 0.0F, 0.0F);
        cube_r15.setTextureOffset(56, 32).addBox(-2.0F, -10.0F, 0.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(0.0F, -16.0F, 0.0F);


        cube_r16 = new ModelRenderer(this);
        cube_r16.setRotationPoint(-3.4F, 34.5F, -1.0F);
        legRight.addChild(cube_r16);
        setRotationAngle(cube_r16, -0.1396F, 0.0F, 0.0F);
        cube_r16.setTextureOffset(32, 10).addBox(-1.0F, -14.1F, 1.9F, 2.0F, 19.0F, 2.0F, 0.0F, false);

        cube_r17 = new ModelRenderer(this);
        cube_r17.setRotationPoint(-3.4F, 34.5F, 1.0F);
        legRight.addChild(cube_r17);
        setRotationAngle(cube_r17, 0.1396F, 0.0F, 0.0F);
        cube_r17.setTextureOffset(32, 31).addBox(-1.0F, -14.1F, -3.0F, 2.0F, 19.0F, 2.0F, 0.0F, false);

        body2 = new ModelRenderer(this);
        body2.setRotationPoint(-4.5F, 21.0F, 4.9F);
        legRight.addChild(body2);
        setRotationAngle(body2, 0.0F, 3.1416F, 0.0F);


        cube_r18 = new ModelRenderer(this);
        cube_r18.setRotationPoint(0.0F, 0.0F, 0.0F);
        body2.addChild(cube_r18);
        setRotationAngle(cube_r18, 0.0F, 0.0F, 0.7854F);
        cube_r18.setTextureOffset(22, 52).addBox(-21.3F, -18.6F, -0.1F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        cube_r19 = new ModelRenderer(this);
        cube_r19.setRotationPoint(0.0F, 0.0F, 0.0F);
        body2.addChild(cube_r19);
        setRotationAngle(cube_r19, 0.7854F, 0.0F, 0.0F);
        cube_r19.setTextureOffset(30, 52).addBox(-9.0F, -13.6F, 14.3F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        cube_r20 = new ModelRenderer(this);
        cube_r20.setRotationPoint(0.0F, 0.0F, 0.0F);
        body2.addChild(cube_r20);
        setRotationAngle(cube_r20, 0.0F, 0.0F, -0.7854F);
        cube_r20.setTextureOffset(38, 52).addBox(3.0F, -15.0F, 7.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        cube_r21 = new ModelRenderer(this);
        cube_r21.setRotationPoint(0.0F, 0.0F, 0.0F);
        body2.addChild(cube_r21);
        setRotationAngle(cube_r21, -0.7854F, 0.0F, 0.0F);
        cube_r21.setTextureOffset(0, 53).addBox(-2.0F, -10.0F, 0.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        body4 = new ModelRenderer(this);
        body4.setRotationPoint(-4.0F, 21.0F, -4.1F);
        legRight.addChild(body4);
        setRotationAngle(body4, 0.0F, 1.5708F, 0.0F);


        cube_r22 = new ModelRenderer(this);
        cube_r22.setRotationPoint(0.0F, 0.0F, 0.0F);
        body4.addChild(cube_r22);
        setRotationAngle(cube_r22, 0.0F, 0.0F, 0.7854F);
        cube_r22.setTextureOffset(48, 10).addBox(-21.3F, -18.6F, -0.1F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        cube_r23 = new ModelRenderer(this);
        cube_r23.setRotationPoint(0.0F, 0.0F, 0.0F);
        body4.addChild(cube_r23);
        setRotationAngle(cube_r23, 0.7854F, 0.0F, 0.0F);
        cube_r23.setTextureOffset(48, 32).addBox(-9.0F, -13.6F, 14.3F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        cube_r24 = new ModelRenderer(this);
        cube_r24.setRotationPoint(0.0F, 0.0F, 0.0F);
        body4.addChild(cube_r24);
        setRotationAngle(cube_r24, 0.0F, 0.0F, -0.7854F);
        cube_r24.setTextureOffset(48, 44).addBox(3.0F, -15.0F, 7.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        cube_r25 = new ModelRenderer(this);
        cube_r25.setRotationPoint(0.0F, 0.0F, 0.0F);
        body4.addChild(cube_r25);
        setRotationAngle(cube_r25, -0.7854F, 0.0F, 0.0F);
        cube_r25.setTextureOffset(14, 52).addBox(-2.0F, -10.0F, 0.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.legRight.rotateAngleX = MathHelper.cos(limbSwing * 0.5F) * 0.5F * limbSwingAmount;
        this.legLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 0.5F * limbSwingAmount;
        this.armRight.rotateAngleX = MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.2F * limbSwingAmount;
        this.armLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.5F) * 1.2F * limbSwingAmount;
    }


    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
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
