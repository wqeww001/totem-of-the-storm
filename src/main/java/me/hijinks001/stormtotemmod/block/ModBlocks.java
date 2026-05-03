package me.hijinks001.stormtotemmod.block;

import me.hijinks001.stormtotemmod.StormTotemMod;
import me.hijinks001.stormtotemmod.item.ModItems;
import me.hijinks001.stormtotemmod.block.StormRodBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, StormTotemMod.MODID);

    // Грозовой блок
    public static final RegistryObject<Block> STORM_BLOCK = registerBlock(
            "storm_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(3f, 6f)
                    .sound(SoundType.METAL)
                    .lightLevel(state -> 3) // слабое свечение
            )
    );

    // Грозовой стержень
// Стало:
public static final RegistryObject<Block> STORM_ROD = registerBlock(
        "storm_rod",
        () -> new StormRodBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL)
                .strength(2f, 4f)
                .sound(SoundType.METAL)
                .lightLevel(state -> state.getValue(StormRodBlock.CHARGED) ? 7 : 2)
                .noOcclusion()
                .randomTicks() // нужно для таймера кулдауна
        )
);

    // Вспомогательный метод — регистрирует блок + BlockItem сразу
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name,
                () -> new BlockItem(registeredBlock.get(), new Item.Properties()));
        return registeredBlock;
    }
}