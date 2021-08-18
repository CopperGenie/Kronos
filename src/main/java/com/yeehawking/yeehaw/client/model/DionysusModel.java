package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.AlliedKnightEntity;
import com.yeehawking.yeehaw.entities.DionysusEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class DionysusModel<T extends DionysusEntity> extends EntityModel<T> {

    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer armLeft;
    private final ModelRenderer cube_r1;
    private final ModelRenderer armRight;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer legRight;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer legLeft;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;

    public DionysusModel() {
        textureWidth = 256;
        textureHeight = 256;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -35.5F, 0.5F);
        head.setTextureOffset(76, 0).addBox(-7.5F, -15.5F, -7.5F, 15.0F, 15.0F, 15.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -21.9F, 0.2F);
        body.setTextureOffset(73, 54).addBox(-12.5F, 15.9F, -5.2F, 25.0F, 5.0F, 12.0F, 0.0F, false);
        body.setTextureOffset(0, 54).addBox(-13.0F, -0.1F, -11.2F, 26.0F, 16.0F, 21.0F, 0.0F, false);
        body.setTextureOffset(75, 72).addBox(-14.0F, -14.1F, -9.2F, 28.0F, 14.0F, 19.0F, 0.0F, false);
        body.setTextureOffset(42, 105).addBox(11.0F, -16.1F, -7.2F, 14.0F, 13.0F, 14.0F, 0.0F, false);
        body.setTextureOffset(0, 91).addBox(-25.0F, -16.1F, -7.2F, 14.0F, 13.0F, 14.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(19.0F, -29.0F, 0.05F);
        armLeft.setTextureOffset(0, 118).addBox(-5.0F, -5.0F, -5.05F, 10.0F, 22.0F, 10.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, 23.0F, -0.55F);
        armLeft.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.6109F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(129, 128).addBox(-4.5F, -8.0F, -7.9F, 9.0F, 22.0F, 9.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-23.3F, -32.5333F, -0.3F);
        armRight.setTextureOffset(0, 0).addBox(-5.7F, -27.4667F, -16.7F, 22.0F, 22.0F, 32.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-11.7F, -12.4667F, 0.4F);
        armRight.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, 0.1745F);
        cube_r2.setTextureOffset(127, 21).addBox(-4.4F, -17.6F, -4.5F, 9.0F, 22.0F, 9.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-9.7F, 9.5333F, 0.3F);
        armRight.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, -0.7854F);
        cube_r3.setTextureOffset(98, 105).addBox(8.0F, -18.0F, -5.0F, 10.0F, 22.0F, 10.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-7.0F, -2.7F, 0.35F);
        legRight.setTextureOffset(147, 52).addBox(-3.5F, 21.7F, -5.35F, 7.0F, 5.0F, 10.0F, 0.0F, false);
        legRight.setTextureOffset(0, 150).addBox(-3.0F, 14.7F, -1.35F, 6.0F, 7.0F, 6.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(0.0F, 9.7F, 1.65F);
        legRight.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.3927F, 0.0F, 0.0F);
        cube_r4.setTextureOffset(80, 137).addBox(-4.0F, -6.0F, -7.1F, 8.0F, 12.0F, 8.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(0.0F, 2.7F, -0.35F);
        legRight.addChild(cube_r5);
        setRotationAngle(cube_r5, -0.4363F, 0.0F, 0.0F);
        cube_r5.setTextureOffset(136, 0).addBox(-5.0F, -4.3F, -4.5F, 10.0F, 11.0F, 10.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(7.0F, -2.7F, 0.35F);
        legLeft.setTextureOffset(138, 105).addBox(-3.5F, 21.7F, -5.35F, 7.0F, 5.0F, 10.0F, 0.0F, false);
        legLeft.setTextureOffset(42, 91).addBox(-3.0F, 14.7F, -1.35F, 6.0F, 7.0F, 6.0F, 0.0F, false);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(0.0F, 9.7F, 1.65F);
        legLeft.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.3927F, 0.0F, 0.0F);
        cube_r6.setTextureOffset(0, 0).addBox(-4.0F, -6.0F, -7.1F, 8.0F, 12.0F, 8.0F, 0.0F, false);

        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(0.0F, 2.7F, -0.35F);
        legLeft.addChild(cube_r7);
        setRotationAngle(cube_r7, -0.4363F, 0.0F, 0.0F);
        cube_r7.setTextureOffset(40, 132).addBox(-5.0F, -4.3F, -4.5F, 10.0F, 11.0F, 10.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.legRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.armRight.rotateAngleX = MathHelper.cos(limbSwing * 0.1F + (float)Math.PI) * 0.1F * limbSwingAmount;
        this.armLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
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
