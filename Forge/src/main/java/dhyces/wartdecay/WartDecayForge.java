package dhyces.wartdecay;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import virtuoel.statement.api.StateRefresher;

@Mod(WartDecay.MODID)
public class WartDecayForge {

    public WartDecayForge() {
        StateRefresher.INSTANCE.addBlockProperty(Blocks.NETHER_WART_BLOCK, WartDecay.WART_DISTANCE, WartDecay.WART_MAX_DISTANCE);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.NETHER_WART_BLOCK, BlockStateProperties.PERSISTENT, false);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.WARPED_WART_BLOCK, WartDecay.WART_DISTANCE, WartDecay.WART_MAX_DISTANCE);
        StateRefresher.INSTANCE.addBlockProperty(Blocks.WARPED_WART_BLOCK, BlockStateProperties.PERSISTENT, false);

        var spec = new ForgeConfigSpec.Builder();
        spec.push("Common");
        Config.doWartDrops = spec.comment("Determines whether wart blocks will drop loot when they decay")
                .define("doWartDrops", false);
        spec.pop();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, spec.build());
    }

    public class Config {

        public static ForgeConfigSpec.BooleanValue doWartDrops;

    }
}
