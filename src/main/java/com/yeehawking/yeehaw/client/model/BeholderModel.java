package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.BeholderEntity;
import com.yeehawking.yeehaw.entities.ToadkinEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class BeholderModel<T extends BeholderEntity> extends EntityModel<T> {

    private final ModelRenderer body;
    private final ModelRenderer armLeftTop;
    private final ModelRenderer upper;
    private final ModelRenderer lower;
    private final ModelRenderer armLeftMid;
    private final ModelRenderer upper2;
    private final ModelRenderer lower2;
    private final ModelRenderer armLeftBot;
    private final ModelRenderer upper3;
    private final ModelRenderer lower3;
    private final ModelRenderer armRightTop;
    private final ModelRenderer upper4;
    private final ModelRenderer lower4;
    private final ModelRenderer armRightMid;
    private final ModelRenderer upper5;
    private final ModelRenderer lower5;
    private final ModelRenderer armRightBot;
    private final ModelRenderer upper6;
    private final ModelRenderer lower6;

    public BeholderModel() {
        textureWidth = 128;
        textureHeight = 128;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-9.5F, -19.0F, -9.5F, 19.0F, 19.0F, 19.0F, 0.0F, false);
        body.setTextureOffset(20, 38).addBox(-6.5F, -17.0F, -10.5F, 13.0F, 10.0F, 1.0F, 0.0F, false);
        body.setTextureOffset(8, 16).addBox(-4.5F, -20.0F, -7.5F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        body.setTextureOffset(54, 46).addBox(-7.5F, -20.0F, -0.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
        body.setTextureOffset(0, 46).addBox(4.5F, -20.0F, -0.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
        body.setTextureOffset(0, 42).addBox(-8.5F, -21.0F, -0.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
        body.setTextureOffset(57, 0).addBox(-8.5F, -22.0F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        body.setTextureOffset(54, 55).addBox(7.5F, -22.0F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        body.setTextureOffset(0, 16).addBox(2.5F, -20.0F, -7.5F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        body.setTextureOffset(0, 38).addBox(5.5F, -21.0F, -0.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
        body.setTextureOffset(32, 55).addBox(-5.5F, -12.0F, 9.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
        body.setTextureOffset(0, 55).addBox(3.5F, -5.0F, 9.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
        body.setTextureOffset(12, 0).addBox(4.5F, -17.0F, 9.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        body.setTextureOffset(32, 49).addBox(1.5F, -10.0F, 9.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        body.setTextureOffset(26, 49).addBox(-5.5F, -17.0F, 9.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        body.setTextureOffset(20, 49).addBox(-7.5F, -6.0F, 9.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        body.setTextureOffset(12, 8).addBox(-3.5F, -5.0F, 9.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        armLeftTop = new ModelRenderer(this);
        armLeftTop.setRotationPoint(9.7F, 7.4F, 2.0F);
        setRotationAngle(armLeftTop, 0.0F, 0.48F, -0.7854F);


        upper = new ModelRenderer(this);
        upper.setRotationPoint(2.0F, -1.0F, -2.0F);
        armLeftTop.addChild(upper);
        upper.setTextureOffset(76, 17).addBox(-2.9937F, -0.6464F, -1.1672F, 16.0F, 4.0F, 4.0F, 0.0F, false);

        lower = new ModelRenderer(this);
        lower.setRotationPoint(14.5F, 0.0F, 0.0F);
        armLeftTop.addChild(lower);
        setRotationAngle(lower, 0.0F, -0.5672F, 0.0F);
        lower.setTextureOffset(57, 0).addBox(-2.1256F, -1.1F, -13.5696F, 3.0F, 3.0F, 14.0F, 0.0F, false);
        lower.setTextureOffset(76, 25).addBox(-2.6256F, -1.6F, -15.5696F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        armLeftMid = new ModelRenderer(this);
        armLeftMid.setRotationPoint(9.9F, 12.0F, -4.0F);
        setRotationAngle(armLeftMid, 0.0F, 0.48F, 0.0F);


        upper2 = new ModelRenderer(this);
        upper2.setRotationPoint(14.0F, 1.0F, -7.0F);
        armLeftMid.addChild(upper2);
        upper2.setTextureOffset(74, 54).addBox(-14.8638F, -3.0F, 3.9004F, 16.0F, 4.0F, 4.0F, 0.0F, false);

        lower2 = new ModelRenderer(this);
        lower2.setRotationPoint(0.0F, 0.0F, 0.0F);
        armLeftMid.addChild(lower2);
        setRotationAngle(lower2, 0.0F, -0.2618F, 0.0F);
        lower2.setTextureOffset(34, 57).addBox(11.7854F, -1.5F, -17.5655F, 3.0F, 3.0F, 14.0F, 0.0F, false);
        lower2.setTextureOffset(40, 74).addBox(11.2854F, -2.0F, -19.5655F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        armLeftBot = new ModelRenderer(this);
        armLeftBot.setRotationPoint(11.0F, 19.0F, 3.0F);
        setRotationAngle(armLeftBot, 0.0F, 0.3491F, 0.1745F);


        upper3 = new ModelRenderer(this);
        upper3.setRotationPoint(0.0F, 0.0F, 0.0F);
        armLeftBot.addChild(upper3);
        upper3.setTextureOffset(0, 74).addBox(-1.0242F, -2.0868F, -3.2411F, 16.0F, 4.0F, 4.0F, 0.0F, false);

        lower3 = new ModelRenderer(this);
        lower3.setRotationPoint(14.0895F, -0.5F, 1.1276F);
        armLeftBot.addChild(lower3);
        setRotationAngle(lower3, 0.48F, -0.6981F, 0.0F);
        lower3.setTextureOffset(0, 55).addBox(-3.5354F, -2.009F, -15.2772F, 3.0F, 3.0F, 14.0F, 0.0F, false);
        lower3.setTextureOffset(32, 59).addBox(-4.0354F, -2.509F, -17.2772F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        armRightTop = new ModelRenderer(this);
        armRightTop.setRotationPoint(-8.3F, 10.0F, 2.0F);
        setRotationAngle(armRightTop, 0.0F, 0.48F, -2.618F);


        upper4 = new ModelRenderer(this);
        upper4.setRotationPoint(9.955F, -3.4086F, 1.9621F);
        armRightTop.addChild(upper4);
        upper4.setTextureOffset(64, 71).addBox(-10.0664F, 1.3535F, -2.7669F, 16.0F, 4.0F, 4.0F, 0.0F, false);

        lower4 = new ModelRenderer(this);
        lower4.setRotationPoint(22.455F, -2.4086F, 3.9621F);
        armRightTop.addChild(lower4);
        setRotationAngle(lower4, 0.0F, -0.5672F, 0.0F);
        lower4.setTextureOffset(54, 54).addBox(-8.9502F, 0.9086F, -11.1189F, 3.0F, 3.0F, 14.0F, 0.0F, false);
        lower4.setTextureOffset(20, 55).addBox(-9.4502F, 0.4086F, -13.1189F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        armRightMid = new ModelRenderer(this);
        armRightMid.setRotationPoint(-8.3F, 15.0F, -6.0F);
        setRotationAngle(armRightMid, 0.0F, 0.48F, -3.098F);


        upper5 = new ModelRenderer(this);
        upper5.setRotationPoint(9.955F, -3.4086F, 1.9621F);
        armRightMid.addChild(upper5);
        upper5.setTextureOffset(68, 46).addBox(-10.0664F, 1.3535F, -2.7669F, 16.0F, 4.0F, 4.0F, 0.0F, false);

        lower5 = new ModelRenderer(this);
        lower5.setRotationPoint(22.4357F, -2.9081F, 3.952F);
        armRightMid.addChild(lower5);
        setRotationAngle(lower5, 0.0F, -0.3927F, 0.0F);
        lower5.setTextureOffset(34, 38).addBox(-9.3601F, 1.3535F, -12.1959F, 3.0F, 3.0F, 14.0F, 0.0F, false);
        lower5.setTextureOffset(0, 8).addBox(-9.8601F, 0.8535F, -14.1959F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        armRightBot = new ModelRenderer(this);
        armRightBot.setRotationPoint(-8.3F, 20.0F, 2.0F);
        setRotationAngle(armRightBot, 0.0F, 0.48F, 2.9671F);


        upper6 = new ModelRenderer(this);
        upper6.setRotationPoint(9.955F, -3.4086F, 1.9621F);
        armRightBot.addChild(upper6);
        upper6.setTextureOffset(54, 38).addBox(-10.0664F, 1.3535F, -2.7669F, 16.0F, 4.0F, 4.0F, 0.0F, false);

        lower6 = new ModelRenderer(this);
        lower6.setRotationPoint(21.1216F, -2.995F, 5.4099F);
        armRightBot.addChild(lower6);
        setRotationAngle(lower6, -0.3927F, -0.5672F, 0.0F);
        lower6.setTextureOffset(0, 38).addBox(-9.3601F, 1.3535F, -12.1959F, 3.0F, 3.0F, 14.0F, 0.0F, false);
        lower6.setTextureOffset(0, 0).addBox(-9.8601F, 0.8535F, -14.1959F, 4.0F, 4.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        this.armLeftTop.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.2F;
        this.armLeftMid.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * -0.2F;
        this.armLeftBot.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.2F;
        this.armRightTop.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * -0.2F;
        this.armRightMid.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.2F;
        this.armRightBot.rotateAngleX = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * -0.2F;

        this.armLeftTop.rotateAngleY = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.2F;
        this.armLeftMid.rotateAngleY = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * -0.2F;
        this.armLeftBot.rotateAngleY = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.2F;
        this.armRightTop.rotateAngleY = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * -0.2F;
        this.armRightMid.rotateAngleY = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * 0.2F;
        this.armRightBot.rotateAngleY = MathHelper.cos((float)entityIn.ticksExisted * 0.15f) * -0.2F;
    }


    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        armLeftTop.render(matrixStack, buffer, packedLight, packedOverlay);
        armLeftMid.render(matrixStack, buffer, packedLight, packedOverlay);
        armLeftBot.render(matrixStack, buffer, packedLight, packedOverlay);
        armRightTop.render(matrixStack, buffer, packedLight, packedOverlay);
        armRightMid.render(matrixStack, buffer, packedLight, packedOverlay);
        armRightBot.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
