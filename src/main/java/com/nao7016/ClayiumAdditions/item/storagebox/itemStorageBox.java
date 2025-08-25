package com.nao7016.ClayiumAdditions.item.storagebox;

import com.nao7016.ClayiumAdditions.CAModMain;
import com.nao7016.ClayiumAdditions.common.CATabs;
import com.nao7016.ClayiumAdditions.util.StorageBoxUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.util.UtilLocale;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class itemStorageBox extends Item {
    public itemStorageBox() {
        super();
        setUnlocalizedName("storage_box");
        setCreativeTab(CATabs.ca_tabs);
        setMaxStackSize(1);
        setHasSubtypes(true);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (getStoredItem(stack) == null) {
                player.openGui(CAModMain.instance, SBGuiHandler.StorageBoxGui, world, 0, 0, 0);
            }
        }
        return stack;
    }

    @Override
    public Item setUnlocalizedName(String name) {
        super.setUnlocalizedName(name);
        return this;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        super.addInformation(stack, player, list, flag);

        list.addAll(UtilLocale.localizeTooltip(this.getUnlocalizedName(stack) + ".tooltip"));


        ItemStack sItemStack = getStoredItem(stack);
        if (sItemStack == null) return;

        Item sItem = sItemStack.getItem();
        if (sItem == null) return;
        String storageItemName = "Name: " + sItem.getItemStackDisplayName(sItemStack);
        list.add(storageItemName);
        StringBuilder builder = StorageBoxUtil.calcItemNumByUnit(stack, false);
        builder.insert(0, "Unit: ");
        list.add(builder.toString());
        list.add("Items: " + getStoredCount(stack));
        list.add("[Information]");
        sItem.addInformation(sItemStack, player, list, flag);
    }

    /**
     *  登録アイテムを取得
     * @param storageBox 取得したいアイテムが入ったストレージボックス
     * @return ItemStack
     */
    public static ItemStack getStoredItem(ItemStack storageBox) {
        if (storageBox.hasTagCompound() && storageBox.getTagCompound().hasKey("Item")) {
            return ItemStack.loadItemStackFromNBT(storageBox.getTagCompound().getCompoundTag("Item"));
        }
        return null;
    }

    /**
     *  登録アイテムの個数を取得
     * @param storageBox 取得したいアイテムが入ったストレージボックス
     * @return long アイテムの個数
     */
    public static int getStoredCount(ItemStack storageBox) {
        if (storageBox.hasTagCompound() && storageBox.getTagCompound().hasKey("Count")) {
            return storageBox.getTagCompound().getInteger("Count");
        }
        return 0;
    }

    /**
     * NBTに登録アイテムと個数をセット
     * @param storageBox ストレージボックス
     * @param item 登録するアイテム
     * @param count アイテムの個数
     */
    public static void setStoredItem(ItemStack storageBox, ItemStack item, int count) {
        NBTTagCompound nbt = storageBox.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            storageBox.setTagCompound(nbt);
        }
        NBTTagCompound itemTag = new NBTTagCompound();
        item.writeToNBT(itemTag);
        nbt.setTag("Item", itemTag);
        nbt.setInteger("Count", count);

        storageBox.setTagCompound(nbt);
    }

    /**
     *
     * @param stack NBT取得元
     * @param Key 取得キー
     * @return 取得したデータ
     */
    public static int getItemNBTData(ItemStack stack, String Key) {
        int ItemData = 0;
        NBTTagCompound nbt = stack.getTagCompound();

        if (nbt != null) {
            ItemData = nbt.getInteger(Key);
        }
        return ItemData;
    }

    public boolean canStore(ItemStack stack) {
        if (stack == null) return false;
        if (stack.getItem() instanceof itemStorageBox) return false;
        if (stack.hasTagCompound()) return false;
        if (Objects.requireNonNull(stack.getItem()).isDamageable()) return false;
        if (stack.isItemEnchanted()) return false;
        return true;
    }
}
