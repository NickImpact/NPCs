package me.mrdaniel.npcs.catalogtypes.propertytype.types;

import com.google.common.reflect.TypeToken;
import me.mrdaniel.npcs.catalogtypes.npctype.NPCType;
import me.mrdaniel.npcs.catalogtypes.propertytype.PropertyType;
import me.mrdaniel.npcs.mixin.interfaces.NPCAble;
import org.spongepowered.api.entity.ArmorEquipable;
import org.spongepowered.api.item.inventory.ItemStack;

public class PropertyBoots extends PropertyType<ItemStack> {

	public PropertyBoots() {
		super("Boots", "boots", new Object[]{"equipment", "boots"});
	}

	@Override
	public TypeToken<ItemStack> getTypeToken() {
		return TypeToken.of(ItemStack.class);
	}

	@Override
	public boolean isSupported(final NPCAble npc) {
		return npc instanceof ArmorEquipable;
	}

	@Override
	public boolean isSupported(NPCType type) {
		return type.isArmorEquipable();
	}

	@Override
	public void apply(NPCAble npc, ItemStack value) {
		((ArmorEquipable)npc).setBoots(value);
	}
}
