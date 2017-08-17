package com.mjr.extraplanets.client.render.entities.landers;

import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.entities.landers.EntityJupiterLander;

@SideOnly(Side.CLIENT)
public class RenderJupiterLander extends Render<EntityJupiterLander> {
	private OBJModel.OBJBakedModel landerModel;

	public RenderJupiterLander(RenderManager manager) {
		super(manager);
		this.shadowSize = 2F;
	}

	@SuppressWarnings("deprecation")
	private void updateModels() {
		if (landerModel == null) {
			OBJModel model;
			try {
				model = (OBJModel) ModelLoaderRegistry.getModel(new ResourceLocation(Constants.ASSET_PREFIX, "jupiter_lander.obj"));
				model = (OBJModel) model.process(ImmutableMap.of("flip-v", "true"));
				Function<ResourceLocation, TextureAtlasSprite> spriteFunction = location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());

				landerModel = (OBJModel.OBJBakedModel) model.bake(
						new OBJModel.OBJState(ImmutableList.of("Body", "FirstLeg", "FirstLegPart1", "FirstLegPart2", "TwoLeg", "TwoLegPart1", "TwoLegPart2", "ThirdLeg", "ThirdLegPart1", "ThirdLegPart2", "FourLeg", "FourLegPart1", "FourLegPart2"),
								false), DefaultVertexFormats.ITEM, spriteFunction);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityJupiterLander par1Entity) {
		return new ResourceLocation("missing");
	}

	@Override
	public void doRender(EntityJupiterLander lander, double par2, double par4, double par6, float par8, float par9) {
		GL11.glPushMatrix();
		final float var24 = lander.prevRotationPitch + (lander.rotationPitch - lander.prevRotationPitch) * par9;
		GL11.glTranslatef((float) par2, (float) par4 + 0.8F, (float) par6);
		GL11.glRotatef(par8, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-var24, 0.0F, 0.0F, 1.0F);

		float f6 = lander.timeSinceHit - par9;
		float f7 = lander.currentDamage - par9;

		if (f7 < 0.0F) {
			f7 = 0.0F;
		}

		if (f6 > 0.0F) {
			GL11.glRotatef((float) Math.sin(f6) * 0.2F * f6 * f7 / 25.0F, 1.0F, 0.0F, 0.0F);
		}

		this.updateModels();
		this.bindTexture(TextureMap.locationBlocksTexture);
		if (Minecraft.isAmbientOcclusionEnabled()) {
			GlStateManager.shadeModel(GL11.GL_SMOOTH);
		} else {
			GlStateManager.shadeModel(GL11.GL_FLAT);
		}
		GL11.glScalef(0.2F, 0.2F, 0.2F);
		ClientUtil.drawBakedModel(landerModel);
		GL11.glPopMatrix();
		RenderHelper.enableStandardItemLighting();
	}
}