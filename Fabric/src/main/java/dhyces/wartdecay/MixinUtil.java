package dhyces.wartdecay;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class MixinUtil {

    public static BlockState updateDistance(BlockState state, LevelAccessor level, BlockPos pos) {
        int dist = 7;

        for (BlockPos testPos : BlockPos.withinManhattan(pos, 1, 1, 1)) {
            dist = Math.min(dist, getDistanceAt(level.getBlockState(testPos)) + 1);
            if (dist == 1) {
                break;
            }
        }

        return state.setValue(BlockStateProperties.DISTANCE, dist);
    }

    public static int getDistanceAt(BlockState neighbor) {
        if (neighbor.is(BlockTags.WARPED_STEMS) || neighbor.is(BlockTags.CRIMSON_STEMS)) {
            return 0;
        } else {
            return neighbor.is(BlockTags.WART_BLOCKS) ? neighbor.getValue(BlockStateProperties.DISTANCE) : 7;
        }
    }
}
