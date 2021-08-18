package com.yeehawking.yeehaw.client.model;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ModBipedModel<T extends MobEntity> extends EntityModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer armLeft;
	private final ModelRenderer armRight;
	private final ModelRenderer legLeft;
	private final ModelRenderer legRight;

	public ModBipedModel() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -0.25F, -0.5F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -7.75F, -3.5F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		armLeft = new ModelRenderer(this);
		armLeft.setRotationPoint(4.3333F, 1.8333F, -0.6667F);
		armLeft.setTextureOffset(16, 32).addBox(-0.3233F, -1.8333F, -1.3333F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		armRight = new ModelRenderer(this);
		armRight.setRotationPoint(-4.3333F, 1.8333F, -0.6667F);
		armRight.setTextureOffset(32, 0).addBox(-3.6767F, -1.8333F, -1.3333F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		legLeft = new ModelRenderer(this);
		legLeft.setRotationPoint(2.0F, 12.0F, 0.0F);
		legLeft.setTextureOffset(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		legRight = new ModelRenderer(this);
		legRight.setRotationPoint(-2.0F, 12.0F, 0.0F);
		legRight.setTextureOffset(24, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}