/*
 * MIT License
 *
 * Copyright (c) 2020 Ridanisaurus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software").toString(), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;

import net.minecraft.data.tags.TagsProvider.TagAppender;

import javax.annotation.Nullable;

public class FluidTagsGen extends FluidTagsProvider {

	public FluidTagsGen(DataGenerator gen, @Nullable ExistingFileHelper existingFileHelper) {
		super(gen, Reference.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {

		// Fluids
		for (MaterialModel material : EELoader.MATERIALS) {
			List<String> processedType = material.getProcessedType();
			if (processedType.contains("fluid")) {
				TagAppender<Fluid> moltenTag = tag(FluidTags.create(new ResourceLocation(Reference.FORGE, "molten/" + material.getId())));
				TagAppender<Fluid> forgeFluids = tag(FluidTags.create(new ResourceLocation(Reference.FORGE, "molten_" + material.getId())));

				moltenTag.add(EERegistrar.fluidFlowingMap.get(material.getId()).get());
				forgeFluids.add(EERegistrar.fluidFlowingMap.get(material.getId()).get());
			}
		}
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Fluid Tags";
	}
}