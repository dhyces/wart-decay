package dhyces.wartdecay;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class WartDecay {
    public static final String MODID = "wartdecay";

    public static final int WART_MAX_DISTANCE = 9;
    public static final IntegerProperty WART_DISTANCE = IntegerProperty.create("distance", 1, WART_MAX_DISTANCE);
}
