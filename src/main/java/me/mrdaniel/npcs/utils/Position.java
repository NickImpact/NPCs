package me.mrdaniel.npcs.utils;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3f;
import com.flowpowered.math.vector.Vector3i;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.World;

import java.util.Optional;

public class Position {

	private final String world;
	private final double x;
	private final double y;
	private final double z;
	private final float yaw;
	private final float pitch;

	public Position(String world, Vector3d loc, Vector3d head) {
		this(world, loc, head.toFloat());
	}

	public Position(String world, Vector3d loc, Vector3f head) {
		this(world, loc.getX(), loc.getY(), loc.getZ(), head.getY(), head.getX());
	}

	public Position(String world, double x, double y, double z, float yaw, float pitch) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public float getYaw() {
		return yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public String getWorldName() {
		return this.world;
	}

	public Optional<World> getWorld() {
		return Sponge.getServer().getWorld(this.world);
	}

	public Vector3i getChunkPosition() {
		return new Vector3d(this.x, 0, this.z).toInt().div(16);
	}
}
