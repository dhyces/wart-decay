package dhyces.wartdecay.mixin;

import dhyces.wartdecay.MixinUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.class)
public abstract class BlockBehaviorMixin {

    @Shadow @Deprecated public abstract void tick(BlockState $$0, ServerLevel $$1, BlockPos $$2, RandomSource $$3);

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void wartdecay_randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if ((Object)this instanceof Block block) {
            if (block == Blocks.NETHER_WART_BLOCK || block == Blocks.WARPED_WART_BLOCK) {
                tick(state, level, pos, random);
                if (!state.getValue(BlockStateProperties.PERSISTENT) && state.getValue(BlockStateProperties.DISTANCE) == 7) {
                    // Block.dropResources(state, level, pos);  //too much spam, the way these trees generate can drop a lot
                    level.removeBlock(pos, false);
                    ci.cancel();
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void wartdecay_tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if ((Object)this instanceof Block block) {
            if (block == Blocks.NETHER_WART_BLOCK || block == Blocks.WARPED_WART_BLOCK) {
                level.setBlock(pos, MixinUtil.updateDistance(state, level, pos), 3);
            }
        }
    }

    @Inject(method = "updateShape", at = @At("HEAD"), cancellable = true)
    private void wartdecay_updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        var thiz = (Block)(Object)this;
        if (thiz == Blocks.NETHER_WART_BLOCK || thiz == Blocks.WARPED_WART_BLOCK) {
            int i = MixinUtil.getDistanceAt(neighborState) + 1;
            if (i != 1 || state.getValue(BlockStateProperties.DISTANCE) != i) {
                level.scheduleTick(currentPos, thiz, 1);
            }

            cir.setReturnValue(state);
        }
    }
}
