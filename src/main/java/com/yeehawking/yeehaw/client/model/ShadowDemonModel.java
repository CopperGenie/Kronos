package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.GnomeEntity;
import com.yeehawking.yeehaw.entities.MindFlayerEntity;
import com.yeehawking.yeehaw.entities.ShadowDemonEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ShadowDemonModel<T extends ShadowDemonEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer body;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;
    private final ModelRenderer cube_r9;
    private final ModelRenderer armLeft;
    private final ModelRenderer cube_r10;
    private final ModelRenderer cube_r11;
    private final ModelRenderer cube_r12;
    private final ModelRenderer cube_r13;
    private final ModelRenderer cube_r14;
    private final ModelRenderer cube_r15;
    private final ModelRenderer cube_r16;
    private final ModelRenderer cube_r17;
    private final ModelRenderer cube_r18;
    private final ModelRenderer cube_r19;
    private final ModelRenderer cube_r20;
    private final ModelRenderer armRight;
    private final ModelRenderer cube_r21;
    private final ModelRenderer cube_r22;
    private final ModelRenderer cube_r23;
    private final ModelRenderer cube_r24;
    private final ModelRenderer cube_r25;
    private final ModelRenderer cube_r26;
    private final ModelRenderer cube_r27;
    private final ModelRenderer cube_r28;
    private final ModelRenderer cube_r29;
    private final ModelRenderer cube_r30;
    private final ModelRenderer cube_r31;
    private final ModelRenderer legLeft;
    private final ModelRenderer cube_r32;
    private final ModelRenderer cube_r33;
    private final ModelRenderer cube_r34;
    private final ModelRenderer cube_r35;
    private final ModelRenderer cube_r36;
    private final ModelRenderer cube_r37;
    private final ModelRenderer legRight;
    private final ModelRenderer cube_r38;
    private final ModelRenderer cube_r39;
    private final ModelRenderer cube_r40;
    private final ModelRenderer cube_r41;
    private final ModelRenderer cube_r42;
    private final ModelRenderer cube_r43;
    private final ModelRenderer tailTop;
    private final ModelRenderer cube_r44;
    private final ModelRenderer tailMid;
    private final ModelRenderer cube_r45;
    private final ModelRenderer tailBot;
    private final ModelRenderer cube_r46;

    public ShadowDemonModel() {
        textureWidth = 256;
        textureHeight = 256;

        head = new ModelRenderer(this);
        head.setRotationPoint(-0.6617F, -38.1619F, -4.0286F);
        head.setTextureOffset(0, 67).addBox(-5.3383F, -9.8381F, -5.9714F, 12.0F, 12.0F, 12.0F, 0.0F, false);
        head.setTextureOffset(58, 142).addBox(-2.3383F, 1.1619F, -5.6714F, 6.0F, 4.0F, 4.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-6.4723F, -12.6323F, -0.4714F);
        head.addChild(cube_r1);
        setRotationAngle(cube_r1, -1.309F, 0.1745F, -0.2618F);
        cube_r1.setTextureOffset(0, 11).addBox(-3.9321F, -14.1641F, -6.1F, 1.0F, 5.0F, 1.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-6.4723F, -12.6323F, -0.4714F);
        head.addChild(cube_r2);
        setRotationAngle(cube_r2, -1.0908F, 0.0F, -0.3927F);
        cube_r2.setTextureOffset(60, 74).addBox(-1.9321F, -11.3641F, -4.2F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-6.4723F, -12.6323F, -0.4714F);
        head.addChild(cube_r3);
        setRotationAngle(cube_r3, -0.6545F, 0.0F, -0.5236F);
        cube_r3.setTextureOffset(0, 30).addBox(-1.6321F, -5.9641F, -2.2F, 3.0F, 7.0F, 3.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(6.1617F, -8.3381F, -2.9714F);
        head.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, 0.5236F);
        cube_r4.setTextureOffset(0, 0).addBox(-2.5F, -5.5F, 0.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(7.7957F, -12.6323F, -0.4714F);
        head.addChild(cube_r5);
        setRotationAngle(cube_r5, -0.6545F, 0.0F, 0.5236F);
        cube_r5.setTextureOffset(0, 67).addBox(-1.3679F, -2.9641F, -2.2F, 3.0F, 4.0F, 3.0F, 0.0F, false);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(-4.8383F, -8.3381F, -2.9714F);
        head.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, 0.0F, -0.5236F);
        cube_r6.setTextureOffset(77, 74).addBox(-1.5F, -5.5F, 0.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -23.3643F, 2.4643F);
        body.setTextureOffset(0, 30).addBox(-9.5F, -1.6357F, -5.4643F, 19.0F, 23.0F, 14.0F, 0.0F, false);
        body.setTextureOffset(0, 0).addBox(-11.0F, -14.6357F, -7.4643F, 22.0F, 13.0F, 17.0F, 0.0F, false);
        body.setTextureOffset(63, 96).addBox(-11.0F, -0.6357F, -7.4643F, 10.0F, 1.0F, 14.0F, 0.0F, false);
        body.setTextureOffset(78, 12).addBox(1.0F, -0.6357F, -7.4643F, 10.0F, 1.0F, 14.0F, 0.0F, false);
        body.setTextureOffset(113, 14).addBox(1.0F, 2.3643F, -6.4643F, 9.0F, 1.0F, 13.0F, 0.0F, false);
        body.setTextureOffset(112, 0).addBox(-10.0F, 2.3643F, -6.4643F, 9.0F, 1.0F, 13.0F, 0.0F, false);
        body.setTextureOffset(56, 115).addBox(-10.0F, 5.3643F, -6.4643F, 7.0F, 1.0F, 13.0F, 0.0F, false);
        body.setTextureOffset(0, 115).addBox(3.0F, 5.3643F, -6.4643F, 7.0F, 1.0F, 13.0F, 0.0F, false);

        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(0.0F, -14.6357F, -1.4643F);
        body.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.5672F, 0.0F, 0.0F);
        cube_r7.setTextureOffset(94, 96).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 4.0F, 17.0F, 0.0F, false);
        cube_r7.setTextureOffset(114, 143).addBox(-0.5F, 25.0F, -11.0F, 1.0F, 3.0F, 7.0F, 0.0F, false);
        cube_r7.setTextureOffset(83, 111).addBox(-1.0F, 18.0F, -6.0F, 2.0F, 4.0F, 7.0F, 0.0F, false);
        cube_r7.setTextureOffset(0, 142).addBox(-1.0F, 12.0F, -1.0F, 2.0F, 4.0F, 7.0F, 0.0F, false);

        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(0.0F, -14.6357F, -1.4643F);
        body.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.4363F, 0.0F, 0.0F);
        cube_r8.setTextureOffset(109, 39).addBox(-1.0F, 4.4F, -5.0F, 2.0F, 4.0F, 17.0F, 0.0F, false);

        cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(0.0F, -14.6357F, -1.4643F);
        body.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.9163F, 0.0F, 0.0F);
        cube_r9.setTextureOffset(60, 74).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 13.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(11.0F, -30.2386F, 2.2272F);


        cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(5.3864F, 21.9256F, -33.8651F);
        armLeft.addChild(cube_r10);
        setRotationAngle(cube_r10, 2.2253F, 1.1345F, 2.2253F);
        cube_r10.setTextureOffset(61, 12).addBox(-5.5F, -2.6F, 2.0F, 9.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r11 = new ModelRenderer(this);
        cube_r11.setRotationPoint(18.0164F, 15.4346F, -38.7536F);
        armLeft.addChild(cube_r11);
        setRotationAngle(cube_r11, 2.3562F, 1.0908F, 2.2253F);
        cube_r11.setTextureOffset(66, 56).addBox(1.1F, 5.5F, 1.2F, 9.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r12 = new ModelRenderer(this);
        cube_r12.setRotationPoint(19.6036F, 30.9409F, -37.5849F);
        armLeft.addChild(cube_r12);
        setRotationAngle(cube_r12, 1.9199F, 0.5236F, 2.0508F);
        cube_r12.setTextureOffset(36, 67).addBox(-5.3F, -1.4F, -2.5F, 9.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r13 = new ModelRenderer(this);
        cube_r13.setRotationPoint(21.1575F, 22.7153F, -34.0616F);
        armLeft.addChild(cube_r13);
        setRotationAngle(cube_r13, 1.789F, 0.5236F, 1.7017F);
        cube_r13.setTextureOffset(25, 97).addBox(-4.5F, -0.5F, -2.3F, 9.0F, 3.0F, 3.0F, 0.0F, false);

        cube_r14 = new ModelRenderer(this);
        cube_r14.setRotationPoint(18.0164F, 15.4346F, -38.7536F);
        armLeft.addChild(cube_r14);
        setRotationAngle(cube_r14, 1.789F, 1.0908F, 1.5708F);
        cube_r14.setTextureOffset(49, 97).addBox(-5.3F, 4.6F, -0.3F, 9.0F, 3.0F, 3.0F, 0.0F, false);

        cube_r15 = new ModelRenderer(this);
        cube_r15.setRotationPoint(18.0164F, 15.4346F, -38.7536F);
        armLeft.addChild(cube_r15);
        setRotationAngle(cube_r15, 2.2689F, 1.2654F, 2.4871F);
        cube_r15.setTextureOffset(130, 50).addBox(-4.4F, -0.1F, -0.1F, 9.0F, 3.0F, 3.0F, 0.0F, false);

        cube_r16 = new ModelRenderer(this);
        cube_r16.setRotationPoint(9.3864F, 21.9256F, -44.8651F);
        armLeft.addChild(cube_r16);
        setRotationAngle(cube_r16, 2.8798F, 1.0908F, 2.9234F);
        cube_r16.setTextureOffset(112, 60).addBox(-7.3F, -5.1F, 5.4F, 9.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r17 = new ModelRenderer(this);
        cube_r17.setRotationPoint(14.0164F, 15.4346F, -27.7536F);
        armLeft.addChild(cube_r17);
        setRotationAngle(cube_r17, 2.2689F, 0.48F, 2.4871F);
        cube_r17.setTextureOffset(135, 90).addBox(-2.1F, -1.5F, -1.5F, 9.0F, 3.0F, 3.0F, 0.0F, false);

        cube_r18 = new ModelRenderer(this);
        cube_r18.setRotationPoint(20.0F, -2.2614F, -4.7272F);
        armLeft.addChild(cube_r18);
        setRotationAngle(cube_r18, 1.4835F, 1.0472F, 1.2654F);
        cube_r18.setTextureOffset(137, 60).addBox(23.6F, 0.1F, -8.1F, 6.0F, 6.0F, 4.0F, 0.0F, false);
        cube_r18.setTextureOffset(0, 129).addBox(28.6F, -0.9F, -8.5F, 7.0F, 8.0F, 5.0F, 0.0F, false);

        cube_r19 = new ModelRenderer(this);
        cube_r19.setRotationPoint(20.0F, -2.2614F, -4.7272F);
        armLeft.addChild(cube_r19);
        setRotationAngle(cube_r19, 0.9599F, 1.0472F, 1.2654F);
        cube_r19.setTextureOffset(52, 30).addBox(2.6F, 4.1F, -8.5F, 26.0F, 6.0F, 6.0F, 0.0F, false);

        cube_r20 = new ModelRenderer(this);
        cube_r20.setRotationPoint(20.0F, -2.2614F, -4.7272F);
        armLeft.addChild(cube_r20);
        setRotationAngle(cube_r20, 0.1745F, 0.4363F, 0.3491F);
        cube_r20.setTextureOffset(59, 60).addBox(-23.8F, 4.1F, -8.5F, 23.0F, 7.0F, 7.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-11.0F, -30.2F, 2.22F);


        cube_r21 = new ModelRenderer(this);
        cube_r21.setRotationPoint(-5.3864F, 21.887F, -33.858F);
        armRight.addChild(cube_r21);
        setRotationAngle(cube_r21, 2.2253F, -1.1345F, -2.2253F);
        cube_r21.setTextureOffset(144, 14).addBox(-3.5F, -2.6F, 2.0F, 9.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r22 = new ModelRenderer(this);
        cube_r22.setRotationPoint(-18.0164F, 15.396F, -38.7465F);
        armRight.addChild(cube_r22);
        setRotationAngle(cube_r22, 2.3562F, -1.0908F, -2.2253F);
        cube_r22.setTextureOffset(138, 34).addBox(-10.1F, 5.5F, 1.2F, 9.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r23 = new ModelRenderer(this);
        cube_r23.setRotationPoint(-19.6036F, 30.9023F, -37.5778F);
        armRight.addChild(cube_r23);
        setRotationAngle(cube_r23, 1.9199F, -0.5236F, -2.0508F);
        cube_r23.setTextureOffset(137, 70).addBox(-3.7F, -1.4F, -2.5F, 9.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r24 = new ModelRenderer(this);
        cube_r24.setRotationPoint(-21.1575F, 22.6767F, -34.0545F);
        armRight.addChild(cube_r24);
        setRotationAngle(cube_r24, 1.789F, -0.5236F, -1.7017F);
        cube_r24.setTextureOffset(138, 28).addBox(-4.5F, -0.5F, -2.3F, 9.0F, 3.0F, 3.0F, 0.0F, false);

        cube_r25 = new ModelRenderer(this);
        cube_r25.setRotationPoint(-18.0164F, 15.396F, -38.7465F);
        armRight.addChild(cube_r25);
        setRotationAngle(cube_r25, 1.789F, -1.0908F, -1.5708F);
        cube_r25.setTextureOffset(138, 109).addBox(-3.7F, 4.6F, -0.3F, 9.0F, 3.0F, 3.0F, 0.0F, false);

        cube_r26 = new ModelRenderer(this);
        cube_r26.setRotationPoint(-18.0164F, 15.396F, -38.7465F);
        armRight.addChild(cube_r26);
        setRotationAngle(cube_r26, 2.2689F, -1.2654F, -2.4871F);
        cube_r26.setTextureOffset(141, 96).addBox(-4.6F, -0.1F, -0.1F, 9.0F, 3.0F, 3.0F, 0.0F, false);

        cube_r27 = new ModelRenderer(this);
        cube_r27.setRotationPoint(-9.3864F, 21.887F, -44.858F);
        armRight.addChild(cube_r27);
        setRotationAngle(cube_r27, 2.8798F, -1.0908F, -2.9234F);
        cube_r27.setTextureOffset(123, 143).addBox(-1.7F, -5.1F, 5.4F, 9.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r28 = new ModelRenderer(this);
        cube_r28.setRotationPoint(-14.0164F, 15.396F, -27.7465F);
        armRight.addChild(cube_r28);
        setRotationAngle(cube_r28, 2.2689F, -0.48F, -2.4871F);
        cube_r28.setTextureOffset(141, 102).addBox(-6.9F, -1.5F, -1.5F, 9.0F, 3.0F, 3.0F, 0.0F, false);

        cube_r29 = new ModelRenderer(this);
        cube_r29.setRotationPoint(-20.0F, -2.3F, -4.72F);
        armRight.addChild(cube_r29);
        setRotationAngle(cube_r29, 1.4835F, -1.0472F, -1.2654F);
        cube_r29.setTextureOffset(78, 139).addBox(-29.6F, 0.1F, -8.1F, 6.0F, 6.0F, 4.0F, 0.0F, false);
        cube_r29.setTextureOffset(58, 129).addBox(-35.6F, -0.9F, -8.5F, 7.0F, 8.0F, 5.0F, 0.0F, false);

        cube_r30 = new ModelRenderer(this);
        cube_r30.setRotationPoint(-20.0F, -2.3F, -4.72F);
        armRight.addChild(cube_r30);
        setRotationAngle(cube_r30, 0.9599F, -1.0472F, -1.2654F);
        cube_r30.setTextureOffset(61, 0).addBox(-28.6F, 4.1F, -8.5F, 26.0F, 6.0F, 6.0F, 0.0F, false);

        cube_r31 = new ModelRenderer(this);
        cube_r31.setRotationPoint(-20.0F, -2.3F, -4.72F);
        armRight.addChild(cube_r31);
        setRotationAngle(cube_r31, 0.1745F, -0.4363F, -0.3491F);
        cube_r31.setTextureOffset(66, 42).addBox(0.8F, 4.1F, -8.5F, 23.0F, 7.0F, 7.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(7.0F, -9.1826F, 5.2541F);
        setRotationAngle(legLeft, 0.0F, -0.2182F, 0.0F);


        cube_r32 = new ModelRenderer(this);
        cube_r32.setRotationPoint(-0.3223F, 9.6826F, -8.8283F);
        legLeft.addChild(cube_r32);
        setRotationAngle(cube_r32, -0.6981F, 0.0F, 0.0F);
        cube_r32.setTextureOffset(33, 103).addBox(-4.5F, -14.2F, -4.5F, 9.0F, 16.0F, 9.0F, 0.0F, false);

        cube_r33 = new ModelRenderer(this);
        cube_r33.setRotationPoint(-0.3223F, 19.6826F, 0.1717F);
        legLeft.addChild(cube_r33);
        setRotationAngle(cube_r33, 0.6545F, 0.0F, 0.0F);
        cube_r33.setTextureOffset(96, 117).addBox(-3.5F, -16.5F, -3.5F, 7.0F, 19.0F, 7.0F, 0.0F, false);

        cube_r34 = new ModelRenderer(this);
        cube_r34.setRotationPoint(-9.8223F, 30.9087F, -13.6972F);
        legLeft.addChild(cube_r34);
        setRotationAngle(cube_r34, -1.5708F, -0.2182F, 0.0F);
        cube_r34.setTextureOffset(0, 91).addBox(10.0F, -4.8F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        cube_r35 = new ModelRenderer(this);
        cube_r35.setRotationPoint(-2.8223F, 30.9087F, -13.6972F);
        legLeft.addChild(cube_r35);
        setRotationAngle(cube_r35, -1.5708F, 0.2182F, 0.0F);
        cube_r35.setTextureOffset(18, 142).addBox(-2.0F, -7.5F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        cube_r36 = new ModelRenderer(this);
        cube_r36.setRotationPoint(-0.3223F, 24.9087F, -3.1972F);
        legLeft.addChild(cube_r36);
        setRotationAngle(cube_r36, -1.5708F, 0.0F, 0.0F);
        cube_r36.setTextureOffset(116, 28).addBox(-3.5F, 0.0F, 4.0F, 7.0F, 7.0F, 4.0F, 0.0F, false);

        cube_r37 = new ModelRenderer(this);
        cube_r37.setRotationPoint(-0.3223F, 24.9087F, -3.1972F);
        legLeft.addChild(cube_r37);
        setRotationAngle(cube_r37, -0.6109F, 0.0F, 0.0F);
        cube_r37.setTextureOffset(117, 90).addBox(-3.0F, -8.0F, -3.0F, 6.0F, 16.0F, 6.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-7.0F, -9.1826F, 5.2541F);
        setRotationAngle(legRight, 0.0F, 0.2182F, 0.0F);


        cube_r38 = new ModelRenderer(this);
        cube_r38.setRotationPoint(0.3223F, 9.6826F, -8.8283F);
        legRight.addChild(cube_r38);
        setRotationAngle(cube_r38, -0.6981F, 0.0F, 0.0F);
        cube_r38.setTextureOffset(110, 65).addBox(-4.5F, -14.2F, -4.5F, 9.0F, 16.0F, 9.0F, 0.0F, false);

        cube_r39 = new ModelRenderer(this);
        cube_r39.setRotationPoint(0.3223F, 19.6826F, 0.1717F);
        legRight.addChild(cube_r39);
        setRotationAngle(cube_r39, 0.6545F, 0.0F, 0.0F);
        cube_r39.setTextureOffset(124, 117).addBox(-3.5F, -16.5F, -3.5F, 7.0F, 19.0F, 7.0F, 0.0F, false);

        cube_r40 = new ModelRenderer(this);
        cube_r40.setRotationPoint(9.8223F, 30.9087F, -13.6972F);
        legRight.addChild(cube_r40);
        setRotationAngle(cube_r40, -1.5708F, 0.2182F, 0.0F);
        cube_r40.setTextureOffset(143, 0).addBox(-14.0F, -4.8F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        cube_r41 = new ModelRenderer(this);
        cube_r41.setRotationPoint(2.8223F, 30.9087F, -13.6972F);
        legRight.addChild(cube_r41);
        setRotationAngle(cube_r41, -1.5708F, -0.2182F, 0.0F);
        cube_r41.setTextureOffset(98, 143).addBox(-2.0F, -7.5F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        cube_r42 = new ModelRenderer(this);
        cube_r42.setRotationPoint(0.3223F, 24.9087F, -3.1972F);
        legRight.addChild(cube_r42);
        setRotationAngle(cube_r42, -1.5708F, 0.0F, 0.0F);
        cube_r42.setTextureOffset(130, 39).addBox(-3.5F, 0.0F, 4.0F, 7.0F, 7.0F, 4.0F, 0.0F, false);

        cube_r43 = new ModelRenderer(this);
        cube_r43.setRotationPoint(0.3223F, 24.9087F, -3.1972F);
        legRight.addChild(cube_r43);
        setRotationAngle(cube_r43, -0.6109F, 0.0F, 0.0F);
        cube_r43.setTextureOffset(34, 128).addBox(-3.0F, -8.0F, -3.0F, 6.0F, 16.0F, 6.0F, 0.0F, false);

        tailTop = new ModelRenderer(this);
        tailTop.setRotationPoint(0.0F, -4.9684F, 8.3604F);
        setRotationAngle(tailTop, -0.2356F, 0.0F, 0.0F);


        cube_r44 = new ModelRenderer(this);
        cube_r44.setRotationPoint(0.0F, 2.9684F, 12.1396F);
        tailTop.addChild(cube_r44);
        setRotationAngle(cube_r44, -0.7418F, 0.0F, 0.0F);
        cube_r44.setTextureOffset(31, 74).addBox(-3.0F, 3.0F, -12.5F, 6.0F, 6.0F, 17.0F, 0.0F, false);

        tailMid = new ModelRenderer(this);
        tailMid.setRotationPoint(0.0F, -4.9684F, 8.3604F);
        setRotationAngle(tailMid, -0.2356F, 0.0F, 0.0F);


        cube_r45 = new ModelRenderer(this);
        cube_r45.setRotationPoint(0.0F, 9.8235F, 10.1961F);
        tailMid.addChild(cube_r45);
        setRotationAngle(cube_r45, -0.4363F, 0.0F, 0.0F);
        cube_r45.setTextureOffset(77, 74).addBox(-2.5F, -2.8672F, -0.6984F, 5.0F, 5.0F, 17.0F, 0.0F, false);

        tailBot = new ModelRenderer(this);
        tailBot.setRotationPoint(0.0F, -4.9684F, 8.3604F);
        setRotationAngle(tailBot, -0.2356F, 0.0F, 0.0F);


        cube_r46 = new ModelRenderer(this);
        cube_r46.setRotationPoint(0.0F, 16.2142F, 23.7057F);
        tailBot.addChild(cube_r46);
        setRotationAngle(cube_r46, -0.1309F, 0.0F, 0.0F);
        cube_r46.setTextureOffset(0, 91).addBox(-2.0F, -2.439F, -1.6431F, 4.0F, 4.0F, 17.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.legRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.armRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.armLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.tailBot.rotateAngleY = 0F + MathHelper.cos((float)entityIn.ticksExisted * 0.3F) * 0.5F;
        this.tailMid.rotateAngleY = 0F + MathHelper.cos((float)entityIn.ticksExisted * 0.3F) * 0.5F;
        this.tailTop.rotateAngleY = 0F + MathHelper.cos((float)entityIn.ticksExisted * 0.3F) * 0.5F;


    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        armLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        armRight.render(matrixStack, buffer, packedLight, packedOverlay);
        legLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        legRight.render(matrixStack, buffer, packedLight, packedOverlay);
        tailTop.render(matrixStack, buffer, packedLight, packedOverlay);
        tailMid.render(matrixStack, buffer, packedLight, packedOverlay);
        tailBot.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
