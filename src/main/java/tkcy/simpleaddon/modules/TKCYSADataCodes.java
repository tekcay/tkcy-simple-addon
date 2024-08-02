package tkcy.simpleaddon.modules;

import static gregtech.api.capability.GregtechDataCodes.assignId;

import java.util.function.Consumer;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class TKCYSADataCodes {

    private static int nextId = 100;

    public static final int UPDATE_ITEM_STACK = assignId();
    public static final int UPDATE_ITEM_COUNT = assignId();

    public static Consumer<PacketBuffer> getItemStackWriter(ItemStack itemStack) {
        return packetBuffer -> packetBuffer.writeItemStack(itemStack);
    }

    public static Consumer<PacketBuffer> getInt(int value) {
        return packetBuffer -> packetBuffer.writeInt(value);
    }
}