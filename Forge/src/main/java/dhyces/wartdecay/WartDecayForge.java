package dhyces.wartdecay;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.fml.common.Mod;
import virtuoel.statement.api.StateRefresher;

@Mod(WartDecay.MODID)
public class WartDecayForge {

    public WartDecayForge() {
        StateRefresher.INSTANCE.addBlockProperty(Blocks.NETHER_WART_BLOCK, BlockStateProperties.DISTANCE, 7);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.NETHER_WART_BLOCK, BlockStateProperties.PERSISTENT, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.WARPED_WART_BLOCK, BlockStateProperties.DISTANCE, 7);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.WARPED_WART_BLOCK, BlockStateProperties.PERSISTENT, false);
    }
}
