package io.github.itamardenkberg.hypertech.core.interfaces;

import java.util.List;

import io.github.itamardenkberg.hypertech.common.blockentities.CopperGeneratorBlockEntity;
import io.github.itamardenkberg.hypertech.core.init.BlockInit;
import io.github.itamardenkberg.hypertech.core.init.MenuTypesInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class CopperGeneratorMenu extends AbstractContainerMenu {
	public final CopperGeneratorBlockEntity entity;
	private final Level world;
	private final ContainerData data;

	public CopperGeneratorMenu(int id, Inventory inv, FriendlyByteBuf extra) {
		this(id, inv, inv.player.level.getBlockEntity(extra.readBlockPos()), new SimpleContainerData(2));
	}

	public CopperGeneratorMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
		super(MenuTypesInit.COPPER_GENERATOR_MENU.get(), id);
		checkContainerSize(inv, 1);
		this.entity = (CopperGeneratorBlockEntity) entity;
		this.world = inv.player.level;
		this.data = data;

		addInventory(inv);
		addHotbar(inv);

		this.entity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
			this.addSlot(new SlotItemHandler(handler, 0, 56, 34));
		});

		addDataSlots(data);
	}

	public boolean isGenerating() {
		return data.get(0) > 0;
	}

	public int getScaledProgress() {
		int progress = this.data.get(0);
		int maxProgress = this.data.get(1);
		int progressArrowSize = 24;

		return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
	}

	public int getScaledEnergy() {
		int energy = this.entity.getEnergyStorage().getEnergyStored();
		int maxEnergy = this.entity.getEnergyStorage().getMaxEnergyStored();
		int energyBarSize = 66;

		System.out.println(this.entity.getEnergyStorage());

		return maxEnergy != 0 && energy != 0 ? energy * energyBarSize / maxEnergy : 0;
	}

	public List<Component> getTooltips() {
		return List.of(Component.literal(
				this.entity.getEnergyLevel() + "/" + this.entity.getEnergyStorage().getMaxEnergyStored() + " FE"));
	}

	private static final int HOTBAR_SLOT_COUNT = 9;
	private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
	private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
	private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
	private static final int VANILLA_FIRST_SLOT_INDEX = 0;
	private static final int BE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

	private static final int BE_INVENTORY_SLOT_COUNT = 1;

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		Slot source = slots.get(index);
		if (source == null || !source.hasItem()) {
			return ItemStack.EMPTY;
		}
		ItemStack stack = source.getItem();
		ItemStack copy = stack.copy();

		if (index < VANILLA_SLOT_COUNT + VANILLA_FIRST_SLOT_INDEX) {
			if (!moveItemStackTo(stack, BE_INVENTORY_FIRST_SLOT_INDEX,
					BE_INVENTORY_FIRST_SLOT_INDEX + BE_INVENTORY_SLOT_COUNT, false)) {
				return ItemStack.EMPTY;
			}
		} else if (index < BE_INVENTORY_FIRST_SLOT_INDEX + BE_INVENTORY_SLOT_COUNT) {
			if (!moveItemStackTo(stack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + BE_INVENTORY_SLOT_COUNT,
					false)) {
				return ItemStack.EMPTY;
			}
		} else {
			System.out.println("Invalid Slot Index: " + index);
			return ItemStack.EMPTY;
		}

		if (stack.getCount() == 0) {
			source.set(ItemStack.EMPTY);
		} else {
			source.setChanged();
		}
		source.onTake(player, stack);
		return copy;
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(ContainerLevelAccess.create(world, entity.getBlockPos()), player,
				BlockInit.COPPER_GENERATOR.get());
	}

	private void addInventory(Inventory inventory) {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
	}

	private void addHotbar(Inventory inventory) {
		for (int i = 0; i < 9; ++i) {
			this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}

	public CopperGeneratorBlockEntity getBlockEntity() {
		return this.entity;
	}
}
