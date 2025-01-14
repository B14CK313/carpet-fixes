package carpetfixes.mixins.entityFixes;

import carpetfixes.CFSettings;
import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Fixes the hopper minecart being slower at [0,0,0] due to some stupid check
 * TODO: Fixed in the next snapshot xD
 */

@Mixin(HopperMinecartEntity.class)
public class HopperMinecartEntity_slowMixin {


    @Redirect(
            method = "tick()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/math/BlockPos;equals(Ljava/lang/Object;)Z"
            )
    )
    private boolean notEqual(BlockPos blockPos, Object o) {
        return !CFSettings.hopperMinecartSlowerAtOriginFix && blockPos.equals(o);
    }
}
