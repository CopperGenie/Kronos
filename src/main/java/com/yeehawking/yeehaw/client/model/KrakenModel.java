package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.KrakenEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class KrakenModel<T extends KrakenEntity> extends EntityModel<T> {
    private final ModelRenderer tent4a;
    private final ModelRenderer tent4b;
    private final ModelRenderer tent4c;
    private final ModelRenderer tent2a;
    private final ModelRenderer tent2b;
    private final ModelRenderer tent2c;
    private final ModelRenderer tent3a;
    private final ModelRenderer tent3b;
    private final ModelRenderer tent3c;
    private final ModelRenderer tent5a;
    private final ModelRenderer tent5b;
    private final ModelRenderer tent5c;
    private final ModelRenderer tent6a;
    private final ModelRenderer tent6b;
    private final ModelRenderer tent6c;
    private final ModelRenderer tent7a;
    private final ModelRenderer tent7b;
    private final ModelRenderer tent7c;
    private final ModelRenderer tent8a;
    private final ModelRenderer tent8b;
    private final ModelRenderer tent8c;
    private final ModelRenderer tent1a;
    private final ModelRenderer tent1b;
    private final ModelRenderer tent1c;
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer valve;

    public KrakenModel() {
        textureWidth = 256;
        textureHeight = 256;

        tent4a = new ModelRenderer(this);
        tent4a.setRotationPoint(-5.7F, 20.0F, 6.5F);
        setRotationAngle(tent4a, 0.0F, -0.7854F, 0.0F);
        tent4a.setTextureOffset(47, 52).addBox(-3.3536F, -3.0F, 1.1274F, 6.0F, 6.0F, 25.0F, 0.0F, false);

        tent4b = new ModelRenderer(this);
        tent4b.setRotationPoint(0.0F, 0.0F, 23.0F);
        tent4a.addChild(tent4b);
        tent4b.setTextureOffset(35, 114).addBox(-2.8536F, -2.5F, -1.8726F, 5.0F, 5.0F, 25.0F, 0.0F, false);

        tent4c = new ModelRenderer(this);
        tent4c.setRotationPoint(-0.3536F, 0.0F, 21.6274F);
        tent4b.addChild(tent4c);
        tent4c.setTextureOffset(31, 144).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 25.0F, 0.0F, false);

        tent2a = new ModelRenderer(this);
        tent2a.setRotationPoint(-6.1F, 20.0F, -6.6F);
        setRotationAngle(tent2a, 0.0F, 0.7854F, 0.0F);
        tent2a.setTextureOffset(84, 58).addBox(-3.0F, -3.0F, -25.6012F, 6.0F, 6.0F, 25.0F, 0.0F, false);

        tent2b = new ModelRenderer(this);
        tent2b.setRotationPoint(0.0F, 0.0F, -23.1012F);
        tent2a.addChild(tent2b);
        tent2b.setTextureOffset(70, 120).addBox(-2.5F, -2.5F, -24.5F, 5.0F, 5.0F, 25.0F, 0.0F, false);

        tent2c = new ModelRenderer(this);
        tent2c.setRotationPoint(0.0F, 0.0F, -23.0F);
        tent2b.addChild(tent2c);
        tent2c.setTextureOffset(136, 144).addBox(-1.5F, -1.5F, -24.5F, 3.0F, 3.0F, 25.0F, 0.0F, false);

        tent3a = new ModelRenderer(this);
        tent3a.setRotationPoint(-7.5F, 20.0F, 0.0F);
        tent3a.setTextureOffset(131, 12).addBox(-26.5F, -3.0F, -3.0F, 25.0F, 6.0F, 6.0F, 0.0F, false);

        tent3b = new ModelRenderer(this);
        tent3b.setRotationPoint(-24.0F, 0.0F, 0.0F);
        tent3a.addChild(tent3b);
        tent3b.setTextureOffset(146, 120).addBox(-23.5F, -2.5F, -2.5F, 25.0F, 5.0F, 5.0F, 0.0F, false);

        tent3c = new ModelRenderer(this);
        tent3c.setRotationPoint(-21.0F, 0.0F, 0.0F);
        tent3b.addChild(tent3c);
        tent3c.setTextureOffset(62, 6).addBox(-24.5F, -1.5F, -1.5F, 25.0F, 3.0F, 3.0F, 0.0F, false);

        tent5a = new ModelRenderer(this);
        tent5a.setRotationPoint(0.0F, 20.0F, 8.5F);
        tent5a.setTextureOffset(37, 83).addBox(-3.0F, -3.0F, 0.5F, 6.0F, 6.0F, 25.0F, 0.0F, false);

        tent5b = new ModelRenderer(this);
        tent5b.setRotationPoint(0.0F, 0.0F, 22.0F);
        tent5a.addChild(tent5b);
        tent5b.setTextureOffset(111, 111).addBox(-2.5F, -2.5F, -1.5F, 5.0F, 5.0F, 25.0F, 0.0F, false);

        tent5c = new ModelRenderer(this);
        tent5c.setRotationPoint(0.0F, 0.0F, 21.0F);
        tent5b.addChild(tent5c);
        tent5c.setTextureOffset(105, 141).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 25.0F, 0.0F, false);

        tent6a = new ModelRenderer(this);
        tent6a.setRotationPoint(5.7F, 20.0F, 4.5F);
        setRotationAngle(tent6a, 0.0F, 0.7854F, 0.0F);
        tent6a.setTextureOffset(0, 77).addBox(-3.495F, -3.0F, 2.206F, 6.0F, 6.0F, 25.0F, 0.0F, false);

        tent6b = new ModelRenderer(this);
        tent6b.setRotationPoint(-0.495F, 0.0F, 24.706F);
        tent6a.addChild(tent6b);
        tent6b.setTextureOffset(0, 108).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 25.0F, 0.0F, false);

        tent6c = new ModelRenderer(this);
        tent6c.setRotationPoint(0.0F, 0.0F, 20.0F);
        tent6b.addChild(tent6c);
        tent6c.setTextureOffset(0, 138).addBox(-1.5F, -1.5F, 0.5F, 3.0F, 3.0F, 25.0F, 0.0F, false);

        tent7a = new ModelRenderer(this);
        tent7a.setRotationPoint(8.5F, 20.0F, 0.0F);
        tent7a.setTextureOffset(131, 0).addBox(-0.5F, -3.0F, -3.0F, 25.0F, 6.0F, 6.0F, 0.0F, false);

        tent7b = new ModelRenderer(this);
        tent7b.setRotationPoint(22.0F, 0.0F, 0.0F);
        tent7a.addChild(tent7b);
        tent7b.setTextureOffset(111, 92).addBox(-2.5F, -2.5F, -2.5F, 25.0F, 5.0F, 5.0F, 0.0F, false);

        tent7c = new ModelRenderer(this);
        tent7c.setRotationPoint(21.0F, 0.0F, 0.0F);
        tent7b.addChild(tent7c);
        tent7c.setTextureOffset(62, 0).addBox(-0.5F, -1.5F, -1.5F, 25.0F, 3.0F, 3.0F, 0.0F, false);

        tent8a = new ModelRenderer(this);
        tent8a.setRotationPoint(6.9F, 20.0F, -6.6F);
        setRotationAngle(tent8a, 0.0F, -0.7854F, 0.0F);
        tent8a.setTextureOffset(59, 16).addBox(-3.0F, -3.0F, -25.1012F, 6.0F, 6.0F, 25.0F, 0.0F, false);

        tent8b = new ModelRenderer(this);
        tent8b.setRotationPoint(0.0F, 0.0F, -22.6012F);
        tent8a.addChild(tent8b);
        tent8b.setTextureOffset(96, 0).addBox(-2.5F, -2.5F, -22.5F, 5.0F, 5.0F, 25.0F, 0.0F, false);

        tent8c = new ModelRenderer(this);
        tent8c.setRotationPoint(0.0F, 0.0F, -21.0F);
        tent8b.addChild(tent8c);
        tent8c.setTextureOffset(121, 64).addBox(-1.5F, -1.5F, -24.5F, 3.0F, 3.0F, 25.0F, 0.0F, false);

        tent1a = new ModelRenderer(this);
        tent1a.setRotationPoint(0.0F, 20.0F, -8.5F);
        tent1a.setTextureOffset(74, 89).addBox(-3.0F, -3.0F, -25.5F, 6.0F, 6.0F, 25.0F, 0.0F, false);

        tent1b = new ModelRenderer(this);
        tent1b.setRotationPoint(0.0F, 0.0F, -24.0F);
        tent1a.addChild(tent1b);
        tent1b.setTextureOffset(121, 30).addBox(-2.5F, -2.5F, -23.5F, 5.0F, 5.0F, 25.0F, 0.0F, false);

        tent1c = new ModelRenderer(this);
        tent1c.setRotationPoint(-1.0F, -1.0F, -22.0F);
        tent1b.addChild(tent1c);
        tent1c.setTextureOffset(146, 92).addBox(-0.5F, -0.5F, -23.5F, 3.0F, 3.0F, 25.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 57.0F, 0.0F);
        head.setTextureOffset(0, 41).addBox(-9.0F, -53.0F, -9.0F, 18.0F, 18.0F, 18.0F, 0.0F, false);
        head.setTextureOffset(0, 10).addBox(-9.1F, -55.0F, -10.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);
        head.setTextureOffset(0, 0).addBox(4.1F, -55.0F, -10.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -43.0F, -0.5F);
        head.addChild(body);
        setRotationAngle(body, 0.5672F, 0.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-10.0F, -10.3077F, 2.6332F, 20.0F, 19.0F, 22.0F, 0.0F, false);
        body.setTextureOffset(62, 150).addBox(-8.0F, -8.3077F, 24.6332F, 16.0F, 15.0F, 1.0F, 0.0F, false);

        valve = new ModelRenderer(this);
        valve.setRotationPoint(-7.2F, -48.5F, -2.5F);
        head.addChild(valve);
        setRotationAngle(valve, 0.0F, 0.0F, -0.829F);
        valve.setTextureOffset(62, 12).addBox(-5.1273F, -3.556F, -2.5F, 7.0F, 4.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        tent1a.render(matrixStack, buffer, packedLight, packedOverlay);
        tent2a.render(matrixStack, buffer, packedLight, packedOverlay);
        tent3a.render(matrixStack, buffer, packedLight, packedOverlay);
        tent4a.render(matrixStack, buffer, packedLight, packedOverlay);
        tent5a.render(matrixStack, buffer, packedLight, packedOverlay);
        tent6a.render(matrixStack, buffer, packedLight, packedOverlay);
        tent7a.render(matrixStack, buffer, packedLight, packedOverlay);
        tent8a.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.tent1a.rotateAngleX = (-(float)Math.PI / 8f + MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F);
        this.tent1b.rotateAngleX = (-(float)Math.PI / 4f + MathHelper.cos((float)entityIn.ticksExisted * 0.1f * 1.1f) * .35F);
        this.tent1c.rotateAngleX = ((float)Math.PI * 3 / 8f + MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F);
        this.tent2a.rotateAngleX = (-(float)Math.PI / 8f + MathHelper.cos((float)entityIn.ticksExisted * 0.1f * 1.2f) * .35F);
        this.tent2b.rotateAngleX = (-(float)Math.PI / 4f + MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F);
        this.tent2c.rotateAngleX = ((float)Math.PI * 3 / 8f + MathHelper.cos((float)entityIn.ticksExisted * 0.1f * 1.1f) * .35F);
        this.tent3a.rotateAngleZ = (-(float)Math.PI / 8f + MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F);
        this.tent3b.rotateAngleZ = ((float)Math.PI * 0.9f / 2f + MathHelper.cos((float)entityIn.ticksExisted * 0.1f * 1.4f) * .35F);
        this.tent3c.rotateAngleZ = (-(float)Math.PI / 4f + MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F);
        this.tent4a.rotateAngleX = ((float)Math.PI / 8f + MathHelper.cos((float)entityIn.ticksExisted * 0.1f * 1.2f) * .35F);
        this.tent4b.rotateAngleX = ((float)Math.PI / 4f + MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F);
        this.tent4c.rotateAngleX = (-(float)Math.PI * 3 / 8f + MathHelper.cos((float)entityIn.ticksExisted * 0.1f * 1.2f) * .35F);
        this.tent5a.rotateAngleX = (-(float)Math.PI / 8f + MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F);
        this.tent5b.rotateAngleX = ((float)Math.PI * 0.9f / 2f + MathHelper.cos((float)entityIn.ticksExisted * 0.1f * 1.1f) * .35F);
        this.tent5c.rotateAngleX = (-(float)Math.PI / 4f + MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F);
        this.tent6a.rotateAngleX = ((float)Math.PI / 8f + MathHelper.cos((float)entityIn.ticksExisted * 0.1f * 1.4f) * .35F);
        this.tent6b.rotateAngleX = ((float)Math.PI / 4f + MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F);
        this.tent6c.rotateAngleX = (-(float)Math.PI * 3 / 8f + MathHelper.cos((float)entityIn.ticksExisted * 0.1f * 1.3f) * .35F);
        this.tent7a.rotateAngleZ = ((float)Math.PI / 8f + MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F);
        this.tent7b.rotateAngleZ = (-(float)Math.PI * 0.9f / 2f + MathHelper.cos((float)entityIn.ticksExisted * 0.1f * 1.1f) * .35F);
        this.tent7c.rotateAngleZ = ((float)Math.PI / 4f + MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F);
        this.tent8a.rotateAngleX = (-(float)Math.PI / 8f + MathHelper.cos((float)entityIn.ticksExisted * 0.1f * 1.2f) * .35F);
        this.tent8b.rotateAngleX = (-(float)Math.PI / 4f + MathHelper.sin((float)entityIn.ticksExisted * 0.1f) * .35F);
        this.tent8c.rotateAngleX = ((float)Math.PI * 3 / 8f + MathHelper.cos((float)entityIn.ticksExisted * 0.1f * 1.3f) * .35F);

    }
}
