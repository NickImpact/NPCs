package me.mrdaniel.npcs.mixin.mixin;

import me.mrdaniel.npcs.mixin.interfaces.INPCMixin;
import net.minecraft.entity.monster.EntityMagmaCube;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityMagmaCube.class)
public abstract class MixinEntityMagmaCube implements INPCMixin {

    @Inject(method = "canDamagePlayer", at = @At("RETURN"), cancellable = true)
    public void onCanDamagePlayer(CallbackInfoReturnable<Boolean> cir) {
        if (this.isNPC() && cir.getReturnValue()) {
            cir.setReturnValue(false);
        }
    }
}