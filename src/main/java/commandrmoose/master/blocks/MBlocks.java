package commandrmoose.master.blocks;

import commandrmoose.master.Master;
import commandrmoose.master.blocks.exteriors.BoothExteriorBlock;
import commandrmoose.master.items.MItemProperties;
import commandrmoose.master.other.IMakeItem;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import net.tardis.mod.blocks.ConsoleBlock;
import net.tardis.mod.blocks.ExteriorBlock;
import net.tardis.mod.blocks.RoundelBlock;
import net.tardis.mod.itemgroups.TItemGroups;

import java.util.ArrayList;
import java.util.List;

@ObjectHolder(Master.MODID)
@Mod.EventBusSubscriber(modid = Master.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MBlocks {

    public static List<Item> ITEMS = new ArrayList<>();
    public static List<Block> BLOCKS = new ArrayList<>();


    // Consoles
    public static MConsoleBlock console_copper = register(new MConsoleBlock(), "console_copper", null, false);

    // Tiles
    public static TemporalSiphonBlock temporal_siphon = register(new TemporalSiphonBlock(Block.Properties.create(Material.ANVIL), SoundType.ANVIL, 1.25F, 4.2F), "temporal_siphon");
    public static HalliganKitBlock halligan_kit = register(new HalliganKitBlock(Block.Properties.create(Material.IRON), SoundType.METAL, 1.25f, 6f), "halligan_kit");
    public static TileBlock broken_engine = register(new TileBlock(Block.Properties.create(Material.ANVIL).hardnessAndResistance(100f, 100f)), "broken_engine");

    // Exteriors
    public static BoothExteriorBlock exterior_booth = register(new BoothExteriorBlock(), "exterior_booth");

    // Electricity
    public static ElectricBarBlock electric_bars = register(new ElectricBarBlock(Block.Properties.create(Material.IRON)), "electric_bars");

    // Blocks
    public static ToasterBlock toaster = register(new ToasterBlock(Block.Properties.create(Material.IRON), SoundType.METAL, 0.5f, 0.5f), "toaster");

    public static RoundelBlock roundel_copper = register(new RoundelBlock(Block.Properties.create(Material.ROCK), SoundType.STONE, 1.25f, 4.2f), "roundel_copper", TItemGroups.ROUNDELS);
    public static RoundelBlock roundel_copper_large = register(new RoundelBlock(Block.Properties.create(Material.ROCK), SoundType.STONE, 1.25f, 4.2f), "roundel_copper_large", TItemGroups.ROUNDELS);
    public static RoundelBlock roundel_copper_small = register(new RoundelBlock(Block.Properties.create(Material.ROCK), SoundType.STONE, 1.25f, 4.2f), "roundel_copper_small", TItemGroups.ROUNDELS);

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Block> event){
        for(Block block : BLOCKS){
            event.getRegistry().registerAll(block);
        }
    }

    public static <T extends Block> T register(T block, String name, ItemGroup group, boolean hasItem) {
        // Establish the resource location
        ResourceLocation loc = new ResourceLocation(Master.MODID, name);
        // Set the block to it.
        block.setRegistryName(loc);

        if(hasItem) {
            if(block instanceof IMakeItem) {
                ITEMS.add(((IMakeItem)block).getItem().setRegistryName(loc));
            }
            else ITEMS.add(new BlockItem(block, MItemProperties.BLOCK.group(group)).setRegistryName(loc));
        }

        BLOCKS.add(block);

        return block;
    }

    public static <T extends Block> T register(T block, String name, ItemGroup group){
        return register(block, name, group, true);
    }

    public static <T extends Block> T register(T block, String name) {
        return register(block, name, null);
    }

}
