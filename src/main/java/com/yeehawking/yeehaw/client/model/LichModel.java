package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.LichEntity;
import com.yeehawking.yeehaw.entities.MindFlayerEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class LichModel<T extends LichEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer collar;
    private final ModelRenderer collar1;
    private final ModelRenderer collar2;
    private final ModelRenderer collar3;
    private final ModelRenderer chest;
    private final ModelRenderer armLeft;
    private final ModelRenderer armRight;
    private final ModelRenderer legLeft;
    private final ModelRenderer legRight;
    private final ModelRenderer cape;
    private final ModelRenderer cape1;
    private final ModelRenderer cape2;

    public LichModel() {
        textureWidth = 128;
        textureHeight = 128;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 2.75F, -0.5F);
        head.setTextureOffset(0, 0).addBox(-3.5F, -7.75F, -3.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);


        collar = new ModelRenderer(this);
        collar.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(collar);
        collar.setTextureOffset(36, 20).addBox(4.0F, -25.0F, -3.0F, 0.0F, 3.0F, 7.0F, 0.0F, false);
        collar.setTextureOffset(20, 9).addBox(-4.0F, -25.0F, -3.0F, 0.0F, 3.0F, 7.0F, 0.0F, false);
        collar.setTextureOffset(44, 33).addBox(-4.0F, -25.0F, 4.0F, 8.0F, 3.0F, 0.0F, 0.0F, false);

        collar1 = new ModelRenderer(this);
        collar1.setRotationPoint(0.0F, -22.0F, 4.0F);
        collar.addChild(collar1);
        setRotationAngle(collar1, -1.5272F, 0.0F, 0.0F);
        collar1.setTextureOffset(44, 30).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 3.0F, 0.0F, 0.0F, false);

        collar2 = new ModelRenderer(this);
        collar2.setRotationPoint(4.0F, -22.0F, 0.0F);
        collar.addChild(collar2);
        setRotationAngle(collar2, 0.0F, 0.0F, -0.1745F);
        collar2.setTextureOffset(40, 11).addBox(0.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, 0.0F, false);

        collar3 = new ModelRenderer(this);
        collar3.setRotationPoint(-4.0F, -22.0F, 0.0F);
        collar.addChild(collar3);
        setRotationAngle(collar3, 0.0F, 0.0F, -2.9671F);
        collar3.setTextureOffset(17, 0).addBox(0.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, 0.0F, false);

        chest = new ModelRenderer(this);
        chest.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(chest);
        chest.setTextureOffset(44, 0).addBox(-4.0F, -22.0F, 2.0F, 8.0F, 11.0F, 0.0F, 0.0F, false);
        chest.setTextureOffset(44, 47).addBox(-1.0F, -22.0F, 0.98F, 2.0F, 11.0F, 1.0F, 0.0F, false);
        chest.setTextureOffset(16, 36).addBox(-4.0F, -22.0F, -1.0F, 8.0F, 11.0F, 0.0F, 0.0F, false);
        chest.setTextureOffset(36, 16).addBox(-4.0F, -22.0F, -2.0F, 8.0F, 11.0F, 0.0F, 0.0F, false);
        chest.setTextureOffset(36, 43).addBox(4.0F, -22.0F, -2.0F, 0.0F, 11.0F, 4.0F, 0.0F, false);
        chest.setTextureOffset(28, 43).addBox(-4.0F, -22.0F, -2.0F, 0.0F, 11.0F, 4.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(4.3333F, 4.8333F, -0.6667F);
        armLeft.setTextureOffset(28, 0).addBox(-0.3233F, -2.8333F, -1.3333F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-4.3333F, 4.8333F, -0.6667F);
        armRight.setTextureOffset(20, 20).addBox(-3.6767F, -2.8333F, -1.3333F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(2.0F, 13.0F, 0.0F);
        legLeft.setTextureOffset(0, 35).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-2.0F, 13.0F, 0.0F);
        legRight.setTextureOffset(32, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, 0.0F, false);

        cape = new ModelRenderer(this);
        cape.setRotationPoint(0.0F, 3.0F, 2.0F);
        setRotationAngle(cape, 0.1745F, 0.0F, 0.0F);
        cape.setTextureOffset(0, 14).addBox(-5.0F, -0.9848F, 0.1736F, 10.0F, 21.0F, 0.0F, 0.0F, false);

        cape1 = new ModelRenderer(this);
        cape1.setRotationPoint(3.5F, 0.0F, 0.0F);
        cape.addChild(cape1);
        setRotationAngle(cape1, 0.0436F, 0.0F, -0.1745F);
        cape1.setTextureOffset(22, 47).addBox(-1.329F, -0.9614F, 0.2157F, 3.0F, 18.0F, 0.0F, 0.0F, false);

        cape2 = new ModelRenderer(this);
        cape2.setRotationPoint(-3.5F, 0.0F, 0.0F);
        cape.addChild(cape2);
        setRotationAngle(cape2, 0.0436F, 0.0F, 0.1745F);
        cape2.setTextureOffset(16, 47).addBox(-1.671F, -0.9614F, 0.2157F, 3.0F, 18.0F, 0.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.cape.rotateAngleX = 0.18f + MathHelper.cos((float)entityIn.ticksExisted * 0.1f) * 0.15F;

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
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
