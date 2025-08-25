package com.nao7016.ClayiumAdditions.util.storagebox;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SBNBTUtil {
    public static String KeyItemID = "StorageItemID";
    public static String KeyMeta = "StorageMeta";
    public static String KeySize = "StorageSize";
    public static String KeyAuto = "AutoCollect";

    /**
     * NBTからItemインスタンスを生成して返す
     *
     * @param	itemStack  NBT取得元
     * @return	Itemインスタンス
     */
    public static Item getItem(ItemStack itemStack) {
        int ItemID = getItemData(itemStack, KeyItemID);
        if(ItemID == 0) {
            return null;
        }

        int size = getItemData(itemStack, KeySize);
        if (size > 0) {
            return Item.getItemById(ItemID);
        } else {
            return null;
        }
    }

    /**
     * NBTからItemStackインスタンスを生成して返す。 NBTデータの更新を行う。
     *
     * @param itemStack
     *            NBT取得元
     * @return ItemStackインスタンス
     */
    public static ItemStack getItemStack(ItemStack itemStack) {
        return getItemStack(itemStack, 0);
    }

    /**
     * NBTからItemStackインスタンスを生成して返す。 NBTデータの更新を行う。
     *
     * @param itemStack
     *            NBT取得元
     * @param maxNum
     *            取得数
     * @return ItemStackインスタンス
     */
    public static ItemStack getItemStack(ItemStack itemStack, int maxNum) {
        ItemStack result = peekItemStack(itemStack, maxNum);
        if(result == null) {
            return null;
        }

        removeItemStack(itemStack, result);
        return result;
    }

    /**
     * NBTからItemStackインスタンスを生成して返す。
     * NBTデータの更新を行う。
     *
     * @param	itemStack	NBT設定先
     * @param	newStack	設定対象
     */
    public static void setItemStack(ItemStack itemStack, ItemStack newStack) {
        if (newStack == null) {
            setItemData(itemStack, KeyItemID, 0);
            setItemData(itemStack, KeyMeta, 0);
            setItemData(itemStack, KeySize, 0);
        } else {
            setItemData(itemStack, KeyItemID, Item.getIdFromItem(newStack.getItem()));
            setItemData(itemStack, KeyMeta, newStack.getItemDamage());
            setItemData(itemStack, KeySize, newStack.stackSize);
        }
    }

    /**
     * NBTからItemStackインスタンス(スタック上限)を生成して返す。 NBTデータの更新を行わない。
     *
     * @param itemStack
     *            NBT取得元
     * @return ItemStackインスタンス
     */
    public static ItemStack peekItemStack(ItemStack itemStack) {
        return peekItemStack(itemStack, 0);
    }

    /**
     * NBTからItemStackインスタンス(指定個数を上限とする)を生成して返す。 NBTデータの更新を行わない。
     *
     * @param itemStack
     *            NBT取得元
     * @param maxNum 上限個数。0 以下の場合は stackLimit。
     * @return ItemStackインスタンス
     */
    public static ItemStack peekItemStack(ItemStack itemStack, int maxNum) {
        ItemStack result = peekItemStackAll(itemStack);
        if (result == null) {
            return null;
        }
        if (maxNum <= 0) {
            maxNum = result.getMaxStackSize();
        }
        if (maxNum >= 1 && result.stackSize > maxNum) {
            result.stackSize = maxNum;
        }
        return result;
    }

    /**
     * NBTからItemStackインスタンス(全スタック)を生成して返す。
     * NBTデータの更新を行わない。
     *
     * @param	itemStack  NBT取得元
     * @return	ItemStackインスタンス
     */
    public static ItemStack peekItemStackAll(ItemStack itemStack) {
        Item item = getItem(itemStack);
        if (item == null) {
            return null;
        }

        int size = getItemData(itemStack, KeySize);
        ItemStack result = null;
        if (size > 0) {
            int meta = getItemData(itemStack, KeyMeta);
            result = new ItemStack(item, size, meta);
        } else {
            setItemStack(itemStack, null);
        }
        return result;
    }

    /**
     * NBTのスタックサイズに加算する
     *
     * @param	itemStack	NBT取得元
     * @param	addStack	加算する対象
     * @return	ItemStackインスタンス
     */
    public static void addItemStack(ItemStack itemStack, ItemStack addStack) {
        ItemStack itemStackAll = peekItemStackAll(itemStack);
        if (itemStackAll == null) {
            setItemStack(itemStack, addStack);
            addStack = null;
        } else {
            if (itemStackAll.isItemEqual(addStack)) {
                itemStackAll.stackSize += addStack.stackSize;
                addStack = null;
                setItemStack(itemStack, itemStackAll);
            }
        }
    }

    /**
     * NBTのスタックサイズを減算する
     *
     * @param	itemStack		NBT取得元
     * @param	removeStack		減算する対象
     * @return	減算結果
     */
    public static ItemStack removeItemStack(ItemStack itemStack, ItemStack removeStack) {
        ItemStack itemStackAll = peekItemStackAll(itemStack);
        //itemStackの一致確認
        if (itemStackAll != null && itemStackAll.isItemEqual(removeStack)) {
            if (removeStack.stackSize > itemStackAll.stackSize) {
                removeStack.stackSize -= itemStackAll.stackSize;
                itemStackAll = null;
            } else {
                itemStackAll.stackSize -= removeStack.stackSize;
                removeStack = null;
            }

            setItemStack(itemStack, itemStackAll);
        }
        return removeStack;
    }

    /**
     * NBTからデータを取得して返す
     *
     * @param	itemStack	NBT取得元
     * @param	Key			取得キー
     * @return	取得したデータ
     */
    private static int getItemData(ItemStack itemStack , String Key)
    {
        int ItemData = 0;
        NBTTagCompound NBT = itemStack.getTagCompound();

        if (NBT != null)
        {
            ItemData = NBT.getInteger(Key);
        }

        return ItemData;
    }

    /**
     * NBTにデータを設定する
     *
     * @param itemStack
     *            NBT設定先
     * @param Key
     *            設定キー
     * @param ItemData
     *            設定データ
     */
    public static void setItemData(ItemStack itemStack, String Key, int ItemData) {
        NBTTagCompound NBT = itemStack.getTagCompound();
        if (NBT == null) {
            NBT = new NBTTagCompound();
        }

        NBT.setInteger(Key, ItemData);
        itemStack.setTagCompound(NBT);
    }

    /**
     * 自動回収機能が有効であるか
     *
     * @param	itemStack	NBT取得先
     */
    public static boolean isAutoCollect(ItemStack itemStack) {
        return getItemData(itemStack, KeyAuto) == 0;
    }

    /**
     * 自動回収機能をON/OFF切り替え
     *
     * @param	itemStack	NBT設定先
     */
    public static void changeAutoCollect(ItemStack itemStack) {
        int value = isAutoCollect(itemStack) ? 1 : 0;
        setItemData(itemStack, KeyAuto, value);
    }
}
