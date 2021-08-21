package com.yeehawking.yeehaw.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yeehawking.yeehaw.entities.ThePoliceEntity;
import com.yeehawking.yeehaw.entities.x0026gEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class PoliceCarModel<T extends ThePoliceEntity> extends EntityModel<T> {

    private final ModelRenderer nyoom;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer bb_main;

    public PoliceCarModel() {
        textureWidth = 256;
        textureHeight = 256;

        nyoom = new ModelRenderer(this);
        nyoom.setRotationPoint(0.0F, 24.0F, 0.0F);
        nyoom.setTextureOffset(0, 57).addBox(-11.5F, -10.0F, -24.0F, 23.0F, 3.0F, 48.0F, 0.0F, false);
        nyoom.setTextureOffset(0, 0).addBox(-12.0F, -13.0F, -27.0F, 24.0F, 3.0F, 54.0F, 0.0F, false);
        nyoom.setTextureOffset(94, 57).addBox(-12.0F, -16.0F, -15.0F, 24.0F, 3.0F, 42.0F, 0.0F, false);
        nyoom.setTextureOffset(0, 14).addBox(-12.0F, -18.0F, 25.0F, 24.0F, 2.0F, 2.0F, 0.0F, false);
        nyoom.setTextureOffset(0, 108).addBox(-12.0F, -23.0F, -7.0F, 24.0F, 7.0F, 16.0F, 0.0F, false);
        nyoom.setTextureOffset(0, 24).addBox(-10.5F, -16.0F, -27.0F, 21.0F, 3.0F, 2.0F, 0.0F, false);
        nyoom.setTextureOffset(102, 0).addBox(-9.0F, -7.0F, -7.0F, 18.0F, 1.0F, 29.0F, 0.0F, false);
        nyoom.setTextureOffset(130, 102).addBox(-9.0F, -7.0F, -22.0F, 18.0F, 1.0F, 12.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, -8.5F, -26.5F);
        nyoom.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.7854F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(0, 0).addBox(-11.0F, 33.8F, 34.7F, 22.0F, 3.0F, 4.0F, 0.0F, false);
        cube_r1.setTextureOffset(0, 7).addBox(-11.0F, -1.2F, 0.7F, 22.0F, 4.0F, 3.0F, 0.0F, false);

        cube_r2_r1 = new ModelRenderer(this);
        cube_r2_r1.setRotationPoint(0.0F, 30.3F, 41.7F);
        cube_r1.addChild(cube_r2_r1);
        setRotationAngle(cube_r2_r1, -0.1745F, 0.0F, 0.0F);
        cube_r2_r1.setTextureOffset(0, 131).addBox(-11.5F, -0.8F, -4.7F, 23.0F, 3.0F, 6.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.0F, -16.0F, -16.0F);
        nyoom.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.1222F, 0.0F, 0.0F);
        cube_r2.setTextureOffset(102, 30).addBox(-11.0F, -1.4F, -11.0F, 22.0F, 5.0F, 18.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(0.0F, -42.9859F, 0.7553F);
        nyoom.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.9599F, 0.0F, 0.0F);
        cube_r3.setTextureOffset(80, 124).addBox(-11.5F, 18.4F, -17.8F, 23.0F, 12.0F, 6.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(0.0F, -24.2977F, -16.9151F);
        nyoom.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.7854F, 0.0F, 0.0F);
        cube_r4.setTextureOffset(64, 108).addBox(-11.5F, 8.2F, -4.9F, 23.0F, 5.0F, 11.0F, 0.0F, false);

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 0.0F, 0.0F);
        nyoom.addChild(bb_main);
        bb_main.setTextureOffset(0, 18).addBox(-10.0F, -25.0F, -1.0F, 20.0F, 2.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        float ageInSeconds = (float)Math.floor(ageInTicks / 20);
//        this.bb_main.rotateAngleY = ageInSeconds * (float)Math.PI ;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        nyoom.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
