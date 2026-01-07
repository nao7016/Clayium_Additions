package com.nao7016.ClayiumAdditions.item.gadget;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class GadgetSpeed extends GadgetAddOrdinal {

    Logger log = LogManager.getLogger();

    private AttributeModifier mod;
    private static UUID uuid = UUID.fromString("400d58e3-62dc-7c2d-e4b4-e3d3348af325");

    // リストのインデックスによって効果値を変更させる
    public GadgetSpeed() {
        super(new String[] { "Speed0", "Speed1", "Speed2" });
    }

    @Override
    public void update(int itemIndex, Entity entity, boolean isRemote) {
        // log.info("[debug]: [GadgetSpeed] update called with itemIndex: {}", itemIndex);
        // AttributeModifierがnullの場合、新たに作成
        if (this.mod == null) {
            this.mod = new AttributeModifier(uuid, "GadgetSpeed", 0.2D, 1);
            // log.info("[debug]: [GadgetSpeed] GadgetSpeed modified: {}", this.mod);
        }

        // インデックスに応じて効果を適用または削除
        // itemIndex >= 0 (ガジェットが収納されている) の場合は効果を適用
        // itemIndex = -1 (ガジェットが未収納) の場合は削除
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
                // log.info("[debug]: [GadgetSpeed] GadgetSpeed modified: {}", map);
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
