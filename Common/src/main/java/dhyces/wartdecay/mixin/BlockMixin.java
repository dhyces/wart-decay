package dhyces.wartdecay.mixin;

import dhyces.wartdecay.MixinUtil;
import dhyces.wartdecay.WartDecay;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "isRandomlyTicking", at = @At("HEAD"), cancellable = true)
    private void wartdecay_hasRandomTick(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(Blocks.NETHER_WART_BLOCK) || state.is(Blocks.WARPED_WART_BLOCK)) {
            cir.setReturnValue(state.getValue(WartDecay.WART_DISTANCE) == WartDecay.WART_MAX_DISTANCE && !state.getValue(BlockStateProperties.PERSISTENT));
        }
    }

    @Inject(method = "getStateForPlacement", at = @At("HEAD"), cancellable = true)
    private void wartdecay_getStateForPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir) {
        var state = context.getLevel().getBlockState(context.getClickedPos());
        if (state.is(Blocks.NETHER_WART_BLOCK) || state.is(Blocks.WARPED_WART_BLOCK)) {
            BlockState blockState = state.setValue(BlockStateProperties.PERSISTENT, true);
            cir.setReturnValue(MixinUtil.updateDistance(blockState, context.getLevel(), context.getClickedPos()));
        }
    }
}
