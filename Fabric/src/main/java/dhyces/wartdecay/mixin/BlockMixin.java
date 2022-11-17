package dhyces.wartdecay.mixin;

import dhyces.wartdecay.MixinUtil;
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
        var thiz = (Block)(Object)this;
        if (thiz == Blocks.NETHER_WART_BLOCK || thiz == Blocks.WARPED_WART_BLOCK) {
            cir.setReturnValue(state.getValue(BlockStateProperties.DISTANCE) == 7 && !state.getValue(BlockStateProperties.PERSISTENT));
        }
    }

    @Inject(method = "getStateForPlacement", at = @At("HEAD"), cancellable = true)
    private void wartdecay_getStateForPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir) {
        var thiz = (Block)(Object)this;
        if (thiz == Blocks.NETHER_WART_BLOCK || thiz == Blocks.WARPED_WART_BLOCK) {
            BlockState blockState = thiz.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true);
            cir.setReturnValue(MixinUtil.updateDistance(blockState, context.getLevel(), context.getClickedPos()));
        }
    }
}
