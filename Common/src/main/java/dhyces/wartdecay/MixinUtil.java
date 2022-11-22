package dhyces.wartdecay;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class MixinUtil {

    public static BlockState updateDistance(BlockState state, LevelAccessor level, BlockPos pos) {
        int dist = WartDecay.WART_MAX_DISTANCE;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        Direction[] directions = Direction.values();

        for(int index = 0; index < directions.length; ++index) {
            Direction direction = directions[index];
            mutableBlockPos.setWithOffset(pos, direction);
            dist = Math.min(dist, getDistanceAt(level.getBlockState(mutableBlockPos)) + 1);
            if (dist == 1) {
                break;
            }
        }

        return state.setValue(WartDecay.WART_DISTANCE, dist);
    }

    public static int getDistanceAt(BlockState neighbor) {
        if (neighbor.is(BlockTags.WARPED_STEMS) || neighbor.is(BlockTags.CRIMSON_STEMS)) {
            return 0;
        } else {
            return neighbor.is(BlockTags.WART_BLOCKS) ? neighbor.getValue(WartDecay.WART_DISTANCE) : WartDecay.WART_MAX_DISTANCE;
        }
    }
}
