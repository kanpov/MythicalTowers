// Made with Blockbench 3.9.2
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports

	package com.example.mod;

	public class bullet_entity extends EntityModel<Entity> {
private final ModelPart main_group;
	private final ModelPart side_part_6_r1;
	private final ModelPart side_part_4_r1;
public bullet_entity() {
		textureWidth = 16;
		textureHeight = 16;
		main_group = new ModelPart(this);
		main_group.setPivot(0.0F, 24.0F, 0.0F);
		main_group.setTextureOffset(0, 0).addCuboid(-1.0F, -11.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		main_group.setTextureOffset(0, 10).addCuboid(-1.0F, -9.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		main_group.setTextureOffset(0, 0).addCuboid(-1.0F, -12.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		side_part_6_r1 = new ModelPart(this);
		side_part_6_r1.setPivot(0.0F, -8.0F, 0.0F);
		main_group.addChild(side_part_6_r1);
		setRotationAngle(side_part_6_r1, -1.5708F, 1.5708F, 0.0F);
		side_part_6_r1.setTextureOffset(0, 4).addCuboid(-1.0F, 1.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		side_part_6_r1.setTextureOffset(6, 2).addCuboid(-1.0F, -2.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		side_part_4_r1 = new ModelPart(this);
		side_part_4_r1.setPivot(0.0F, -8.0F, 0.0F);
		main_group.addChild(side_part_4_r1);
		setRotationAngle(side_part_4_r1, -1.5708F, 0.0F, 0.0F);
		side_part_4_r1.setTextureOffset(6, 5).addCuboid(-1.0F, 1.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		side_part_4_r1.setTextureOffset(0, 7).addCuboid(-1.0F, -2.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
}
@Override
public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
}
@Override
public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		
		main_group.render(matrixStack, buffer, packedLight, packedOverlay);
}
public void setRotationAngle(ModelPart bone, float x, float y, float z) {
		bone.pitch = x;
		bone.yaw = y;
		bone.roll = z;
}

	}