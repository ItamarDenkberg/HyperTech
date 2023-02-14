package io.github.itamardenkberg.hypertech.core.networking.packet;

import java.util.function.Supplier;

import io.github.itamardenkberg.hypertech.common.blockentities.CopperGeneratorBlockEntity;
import io.github.itamardenkberg.hypertech.core.interfaces.CopperGeneratorMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class EnergySyncS2CPacket {
	private final int energy;
	private final BlockPos pos;

	public EnergySyncS2CPacket(int energy, BlockPos pos) {
		this.energy = energy;
		this.pos = pos;
	}

	public EnergySyncS2CPacket(FriendlyByteBuf buf) {
		this.energy = buf.readInt();
		this.pos = buf.readBlockPos();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(energy);
		buf.writeBlockPos(pos);
	}

	@SuppressWarnings("resource")
	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof CopperGeneratorBlockEntity blockEntity) {
				blockEntity.setEnergyLevel(energy);

				if (Minecraft.getInstance().player.containerMenu instanceof CopperGeneratorMenu menu
						&& menu.getBlockEntity().getBlockPos().equals(pos)) {
					blockEntity.setEnergyLevel(energy);
				}
			}
		});

		return true;
	}
}
