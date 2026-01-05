package com.nao7016.ClayiumAdditions.item.gadget;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class GadgetSpeed extends GadgetAddOrdinal {

    private AttributeModifier mod;
    private static UUID uuid = UUID.fromString("400d58e3-62dc-7c2d-e4b4-e3d3348af325");

    public GadgetSpeed() {
        super(new String[] { "Speed0", "Speed1", "Speed2" });
    }

    public void update(int itemIndex, Entity entity, boolean isRemote) {
        if (this.mod == null) {
            this.mod = new AttributeModifier(uuid, "GadgetSpeed", 0.2D, 2);
        }

        if (itemIndex >= 0) {
            if (entity instanceof EntityLivingBase) {
                Multimap map = HashMultimap.create();
                map.put(
                    SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(),
                    new AttributeModifier(
                        uuid,
                        this.mod.getName(),
                        this.mod.getAmount() * (double) (itemIndex + 1),
                        this.mod.getOperation()));
                ((EntityLivingBase) entity).getAttributeMap()
                    .applyAttributeModifiers(map);
            }
        } else if (entity instanceof EntityLivingBase) {
            Multimap map = HashMultimap.create();
            map.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), this.mod);
            ((EntityLivingBase) entity).getAttributeMap()
                .removeAttributeModifiers(map);
            System.out.println("[debug]: AttributeModifiers removed: " + map);
        }

    }
}
