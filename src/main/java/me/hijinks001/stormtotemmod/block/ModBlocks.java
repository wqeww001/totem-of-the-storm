package me.hijinks001.stormtotemmod.block;

import me.hijinks001.stormtotemmod.StormTotemMod;
import me.hijinks001.stormtotemmod.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, StormTotemMod.MODID);

    public static final RegistryObject<Block> STORM_BLOCK = registerBlock(
            "storm_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(3f, 6f)
                    .sound(SoundType.METAL)
                    .lightLevel(state -> 3)
            )
    );

    public static final RegistryObject<Block> STORM_ROD = registerBlock(
            "storm_rod",
            () -> new StormRodBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(2f, 4f)
                    .sound(SoundType.METAL)
                    .lightLevel(state -> state.getValue(StormRodBlock.CHARGED) ? 7 : 2)
                    .noOcclusion()
            )
    );

    public static final RegistryObject<Block> STORM_ORE = registerBlock(
            "storm_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(1.5f, 3f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()
            )
    );

    public static final RegistryObject<Block> STORM_PORTAL = BLOCKS.register(
            "storm_portal",
            StormPortalBlock::new
    );

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name,
                () -> new BlockItem(registeredBlock.get(), new Item.Properties()) {
                    @Override
                    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                        tooltip.add(Component.translatable("tooltip.stormtotemmod." + name));
                    }
                }
        );
        return registeredBlock;
    }

    public static final RegistryObject<Block> STORM_CRYSTAL_ORE = registerBlock(
        "storm_crystal_ore",
        () -> new Block(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_CYAN)
                .strength(4f, 6f)
                .sound(SoundType.GLASS)
                .lightLevel(state -> 7)
                .requiresCorrectToolForDrops()
        )
    );
}