package com.mjr.extraplanets.moons.Titan.worldgen.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.blocks.fluid.ExtraPlanets_Fluids;
import com.mjr.extraplanets.moons.Titan.worldgen.TitanBiomes;

public class BiomeGenTitanMethaneSea extends Biome {
	public BiomeGenTitanMethaneSea(Biome.BiomeProperties properties) {
		super(properties);
		this.spawnableCreatureList.clear();
		Biome.registerBiome(Config.TITAN_SEA_BIOME_ID, TitanBiomes.titanMethaneSea.getBiomeName(), TitanBiomes.titanMethaneSea);
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.COLD, BiomeDictionary.Type.OCEAN);
		this.topBlock = ExtraPlanets_Fluids.METHANE.getDefaultState();
		this.fillerBlock = ExtraPlanets_Fluids.METHANE.getDefaultState();
	}
}