package dhyces.wartdecay;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import virtuoel.statement.api.StateRefresher;

public class WartDecayFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        StateRefresher.INSTANCE.addBlockProperty(Blocks.NETHER_WART_BLOCK, BlockStateProperties.DISTANCE, 7);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.NETHER_WART_BLOCK, BlockStateProperties.PERSISTENT, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.WARPED_WART_BLOCK, BlockStateProperties.DISTANCE, 7);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.WARPED_WART_BLOCK, BlockStateProperties.PERSISTENT, false);
    }
}
