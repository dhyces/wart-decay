package dhyces.wartdecay.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;
import net.minecraft.world.level.levelgen.feature.HugeFungusFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(HugeFungusFeature.class)
public class HugeFungusFeatureMixin {

    @Inject(method = "place", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void wartdecay_place(FeaturePlaceContext<HugeFungusConfiguration> context, CallbackInfoReturnable<Boolean> cir,
                                 WorldGenLevel worldGenLevel, BlockPos blockPos, RandomSource randomSource,
                                 ChunkGenerator chunkGenerator, HugeFungusConfiguration  hugeFungusConfiguration,
                                 Block block, BlockPos  blockPos1, BlockState blockState, int height) {
//        blockPos1 = blockPos1.above(height/2);
//        for (BlockPos pos : BlockPos.withinManhattan(blockPos1, 4, 4, height+1)) {
//            var state = worldGenLevel.getBlockState(pos);
//            if (state.is(BlockTags.WART_BLOCKS) && state.hasProperty(BlockStateProperties.PERSISTENT) && !state.getValue(BlockStateProperties.PERSISTENT)) {
//                for (BlockPos innerPos : BlockPos.withinManhattan(blockPos1, 3, 3, 3)) {
//                    var wartState = worldGenLevel.getBlockState(innerPos);
//                    var dist = (int)pos.distToCenterSqr(innerPos.getX(), innerPos.getY(), innerPos.getZ());
//                    if (state.hasProperty(BlockStateProperties.DISTANCE) && state.getValue(BlockStateProperties.DISTANCE) > dist && (wartState.is(BlockTags.CRIMSON_STEMS) || wartState.is(BlockTags.WARPED_STEMS))) {
//                        worldGenLevel.setBlock(pos, state.setValue(BlockStateProperties.DISTANCE, Math.abs(dist-1)), Block.UPDATE_ALL);
//                    }
//                }
//            }
//        }
    }
}
