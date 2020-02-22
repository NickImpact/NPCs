package me.mrdaniel.npcs.catalogtypes.propertytype.types;

import com.google.common.reflect.TypeToken;
import me.mrdaniel.npcs.catalogtypes.propertytype.PropertyType;
import me.mrdaniel.npcs.catalogtypes.rabbittype.RabbitType;
import me.mrdaniel.npcs.interfaces.mixin.NPCAble;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.Human;

import java.util.UUID;

public class PropertySkinUUID extends PropertyType<UUID> {

	public PropertySkinUUID() {
		super("SkinUUID", "skin-uuid");
	}

	@Override
	public TypeToken<UUID> getTypeToken() {
		return TypeToken.of(UUID.class);
	}

	@Override
	public boolean isSupported(final NPCAble npc) {
		return npc instanceof Human;
	}

	@Override
	public void apply(NPCAble npc, UUID value) {
		((Human) npc).offer(Keys.SKIN_UNIQUE_ID, value);
	}
}