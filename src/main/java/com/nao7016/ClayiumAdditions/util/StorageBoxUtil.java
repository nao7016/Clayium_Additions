package com.nao7016.ClayiumAdditions.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.nao7016.ClayiumAdditions.item.storagebox.itemStorageBox;

public class StorageBoxUtil {

    /**
     * LC + Stack + itemに分ける
     *
     * @param stack         ストレージボックス
     * @param appendItemNum アイテム数を表示する？
     * @return StringBuilder
     */
    public static StringBuilder calcItemNumByUnit(ItemStack stack, boolean appendItemNum) {
        StringBuilder builder = new StringBuilder("Empty");
        ItemStack sItemStack = itemStorageBox.getStoredItemStack(stack);
        if (sItemStack == null) return builder;

        Item sItem = sItemStack.getItem();
        if (sItem == null) return builder;

        builder.setLength(0);
        final int maxStackNum = sItemStack.getMaxStackSize();
        final int LCSlot = 54;
        final int LCNum = LCSlot * maxStackNum;
        int size = sItemStack.stackSize;
        int LC = size / LCNum;
        boolean isHigherUnit = false;
        // LC数の計算
        if (LC >= 1) {
            isHigherUnit = true;
            builder.append(LC)
                .append("LC ");
            size -= LC * LCNum;
        }
        int Stack = size / maxStackNum;
        // stack数の計算
        if (Stack >= 1) {
            isHigherUnit = true;
            if (builder.length() >= 1) {
                builder.append("+ ");
            }
            builder.append(Stack)
                .append("stacks ");
            size -= Stack * maxStackNum;
        }
        // 残りのアイテム数の計算
        if (size >= 1) {
            if (builder.length() >= 1) {
                builder.append("+ ");
            }
            builder.append(size)
                .append("items ");
        }
        if (isHigherUnit && appendItemNum) {
            builder.append("( ")
                .append(sItemStack.stackSize)
                .append(" items)");
        }
        return builder;
    }

    /**
     *
     * @param stack NBT取得元
     * @param key   取得キー
     * @return 取得したデータ
     */
    public static Object getItemNBTData(ItemStack stack, String key) {
        if (stack == null) return null;

        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null || !nbt.hasKey(key)) return null;

        return getItemNBTData(nbt, key);
    }

    /**
     *
     * @param nbt NBT取得元
     * @param key 取得キー
     * @return 取得したデータ
     */
    public static Object getItemNBTData(NBTTagCompound nbt, String key) {
        if (nbt == null || !nbt.hasKey(key)) return null;

        if (nbt.hasKey(key, 1)) return nbt.getByte(key); // 1: Byte
        if (nbt.hasKey(key, 2)) return nbt.getShort(key); // 2: Short
        if (nbt.hasKey(key, 3)) return nbt.getInteger(key); // 3: Int
        if (nbt.hasKey(key, 4)) return nbt.getLong(key); // 4: Long
        if (nbt.hasKey(key, 5)) return nbt.getFloat(key); // 5: Float
        if (nbt.hasKey(key, 6)) return nbt.getDouble(key); // 6: Double
        if (nbt.hasKey(key, 7)) return nbt.getByteArray(key); // 7: ByteArray
        if (nbt.hasKey(key, 8)) return nbt.getString(key); // 8: String
        if (nbt.hasKey(key, 9)) return nbt.getTagList(key, 10);// 9: List (Compound)
        if (nbt.hasKey(key, 10)) {
            // Compound → ItemStack
            return ItemStack.loadItemStackFromNBT(nbt.getCompoundTag(key));
        }
        if (nbt.hasKey(key, 11)) return nbt.getIntArray(key); // 11: IntArray

        return null;

    }

    /**
     * ItemStackのNBTにデータを設定する
     * 
     * @param storageBox ItemStackで渡す
     * @param key        String型
     * @param data       Object型、基本的に何でも対応可能
     */
    public static void setItemNBTData(ItemStack storageBox, String key, Object data) {
        if (storageBox == null || data == null) return;

        NBTTagCompound nbt = storageBox.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            storageBox.setTagCompound(nbt);
        }
        setItemNBTData(nbt, key, data);

    }

    /**
     * NBTにデータを登録する
     * 
     * @param nbt  NBTTagCompound
     * @param key  String型
     * @param data Object型、基本的に何でも対応可能
     */
    public static void setItemNBTData(NBTTagCompound nbt, String key, Object data) {
        if (data == null) return;

        if (data instanceof ItemStack) nbt.setTag(key, ((ItemStack) data).writeToNBT(new NBTTagCompound()));
        else if (data instanceof Integer) nbt.setInteger(key, (Integer) data);
        else if (data instanceof Double) nbt.setDouble(key, (Double) data);
        else if (data instanceof Float) nbt.setFloat(key, (Float) data);
        else if (data instanceof Long) nbt.setLong(key, (Long) data);
        else if (data instanceof Short) nbt.setShort(key, (Short) data);
        else if (data instanceof Byte) nbt.setByte(key, (Byte) data);
        else if (data instanceof Boolean) nbt.setBoolean(key, (Boolean) data);
        else if (data instanceof String) nbt.setString(key, (String) data);
        // その他の型は文字列化して保存
        else nbt.setString(key, data.toString());
    }
}
