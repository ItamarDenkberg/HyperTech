package io.github.itamardenkberg.hypertech.common.blockentities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.itamardenkberg.hypertech.HyperTech;
import io.github.itamardenkberg.hypertech.core.init.BlockEntitiesInit;
import io.github.itamardenkberg.hypertech.core.init.ItemInit;
import io.github.itamardenkberg.hypertech.core.interfaces.CopperGeneratorMenu;
import io.github.itamardenkberg.hypertech.core.util.enums.EnergyStorageUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CopperGeneratorBlockEntity extends BlockEntity implements MenuProvider {
	private int progress = 0;
	private int maxProgress = 60;
	protected final ContainerData data;

	private final EnergyStorageUtil ENERGY_STORAGE = new EnergyStorageUtil(20000, 256) {

		@Override
		public void onEnergyChanged() {
			setChanged();
		}
	};

	private final ItemStackHandler itemHandler = new ItemStackHandler(1) {

		@Override
		protected void onContentsChanged(int slot) {
			setChanged();
		}
	};

	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
	private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

	public CopperGeneratorBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntitiesInit.COPPER_GENERATOR.get(), pos, state);

		this.data = new ContainerData() {

			@Override
			public void set(int index, int value) {
				switch (index) {
				case 0 -> CopperGeneratorBlockEntity.this.progress = value;
				case 1 -> CopperGeneratorBlockEntity.this.maxProgress = value;
				}
			}

			@Override
			public int getCount() {
				return 2;
			}

			@Override
			public int get(int index) {
				return switch (index) {
				case 0 -> CopperGeneratorBlockEntity.this.progress;
				case 1 -> CopperGeneratorBlockEntity.this.maxProgress;
				default -> 0;
				};
			}
		};
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
		return new CopperGeneratorMenu(id, inventory, this, this.data);
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("blockentity." + HyperTech.MOD_ID + ".copper_generator");
	}

	@Override
	public void onLoad() {
		super.onLoad();
		lazyItemHandler = LazyOptional.of(() -> itemHandler);
		lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		lazyItemHandler.invalidate();
		lazyEnergyHandler.invalidate();
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		nbt.put("inventory", itemHandler.serializeNBT());
		nbt.putInt("copper_generator.progress", progress);
		nbt.putInt("copper_generator.energy", ENERGY_STORAGE.getEnergyStored());
		super.saveAdditional(nbt);
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		itemHandler.deserializeNBT(nbt.getCompound("inventory"));
		ENERGY_STORAGE.setEnergy(nbt.getInt("copper_generator.energy"));
		progress = nbt.getInt("copper_generator.progress");
	}

	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
		if (capability == ForgeCapabilities.ENERGY) {
			return lazyEnergyHandler.cast();
		}

		if (capability == ForgeCapabilities.ITEM_HANDLER) {
			return lazyItemHandler.cast();
		}

		return super.getCapability(capability, side);
	}

	public void onBreak() {
		SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
		for (int i = 0; i < itemHandler.getSlots(); i++) {
			inventory.setItem(i, itemHandler.getStackInSlot(i));
		}

		Containers.dropContents(this.level, this.worldPosition, inventory);
	}

	public void setCustomName(Component hoverName) {

	}

	public static void tick(Level world, BlockPos pos, BlockState state, CopperGeneratorBlockEntity entity) {
		if (world.isClientSide()) {
			return;
		}

		if (hasRecipe(entity)) {
			entity.progress++;
			setChanged(world, pos, state);

			if (entity.progress >= entity.maxProgress) {
				generateFE(entity);
			}
		} else {
			entity.resetProgress();
			setChanged(world, pos, state);
		}

		if (hasFuel(entity)) {
			entity.ENERGY_STORAGE.receiveEnergy(64, false);
		}
	}

	private static boolean hasFuel(CopperGeneratorBlockEntity entity) {
		return false;
	}

	private void resetProgress() {
		this.progress = 0;
	}

	private static void generateFE(CopperGeneratorBlockEntity entity) {
		entity.ENERGY_STORAGE.receiveEnergy(100, false);
		entity.resetProgress();
	}

	private static boolean hasRecipe(CopperGeneratorBlockEntity entity) {
		SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
		for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
			inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
		}

		boolean hasFuel = entity.itemHandler.getStackInSlot(0).getItem() == ItemInit.ALUMINIUM_DUST.get();

		return hasFuel && canInsertAmount(inventory)
				&& canInsertItem(inventory, new ItemStack(ItemInit.ALUMINIUM_DUST.get(), 1));
	}

	private static boolean canInsertItem(SimpleContainer inventory, ItemStack stack) {
		return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
	}

	private static boolean canInsertAmount(SimpleContainer inventory) {
		return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
	}
}
