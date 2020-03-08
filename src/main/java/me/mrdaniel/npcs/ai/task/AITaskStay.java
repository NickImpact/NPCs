package me.mrdaniel.npcs.ai.task;

import me.mrdaniel.npcs.NPCs;
import me.mrdaniel.npcs.catalogtypes.propertytype.PropertyTypes;
import me.mrdaniel.npcs.mixin.interfaces.NPCAble;
import me.mrdaniel.npcs.utils.Position;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.ai.task.AITask;
import org.spongepowered.api.entity.ai.task.AITaskType;
import org.spongepowered.api.entity.ai.task.AITaskTypes;
import org.spongepowered.api.entity.living.Agent;

import javax.annotation.Nullable;

public class AITaskStay extends AbstractNPCAITask {

    private static final double START_DISTANCE_SQUARED = 1.1 * 1.1;

    @Nullable private Position target;

    /**
     * Creates a new {@link AITaskStay} with the provided
     * {@link AITaskType}.
     */
    public AITaskStay(double speed, int chance) {
        super(AITaskTypes.WANDER, speed, chance);

        this.target = null;

        ((EntityAIBase) (Object) this).setMutexBits(MUTEX_FLAG_MOVE);
    }

    @Override
    public boolean shouldUpdate() {
        Agent owner = this.getOwner().get();
        Position pos = ((NPCAble)owner).getData().getProperty(PropertyTypes.POSITION).get();

        if (owner.getLocation().getPosition().distanceSquared(pos.getX(), pos.getY(), pos.getZ()) < START_DISTANCE_SQUARED) {
            return false;
        }

        this.target = pos;
        return true;
    }

    @Override
    public void start() {
        this.moveTo(this.getOwner().get(), this.target);
    }

    @Override
    public void update() {
        this.moveTo(this.getOwner().get(), this.target);
    }

    @Override
    public boolean continueUpdating() {
        if (this.target == null) {
            return false;
        }
        return this.distanceSquared(this.getOwner().get(), this.target) > ACCEPTABLE_DISTANCE_SQUARED;
    }

    @Override
    public void reset() {
        this.target = null;
        ((EntityLiving)this.getOwner().get()).getNavigator().clearPathEntity();
    }

    @Override
    public boolean canRunConcurrentWith(AITask<Agent> other) {
        return (((EntityAIBase) (Object) this).getMutexBits() & ((EntityAIBase)other).getMutexBits()) == 0;
    }

    @Override
    public boolean canBeInterrupted() {
        return true;
    }

    public static void register() {
        Sponge.getRegistry().registerAITaskType(NPCs.getInstance(), "stay", "Stay", AITaskStay.class);
    }
}
