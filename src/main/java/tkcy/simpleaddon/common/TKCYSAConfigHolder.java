package tkcy.simpleaddon.common;

import net.minecraftforge.common.config.Config;

import tkcy.simpleaddon.TekCaySimpleAddon;

@Config(modid = TekCaySimpleAddon.MODID)
public class TKCYSAConfigHolder {

    @Config.Comment("Config options applying to all chains")
    @Config.Name("Chains")
    public static Chains chains = new Chains();

    public static class Chains {

        public boolean enableGoldChain = true;
        public boolean enableIronChain = true;
        public boolean enableChromiteChain = true;
        public boolean enableTungstenChain = true;
        public boolean enableRoastingChain = true;
        public boolean enableZincChain = true;
        public boolean enableGermaniumChain = true;
        public boolean enableCopperChain = true;
    }
}
