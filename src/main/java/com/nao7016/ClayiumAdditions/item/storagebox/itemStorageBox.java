package com.nao7016.ClayiumAdditions.item.storagebox;

import static com.nao7016.ClayiumAdditions.util.StorageBoxUtil.setItemNBTData;

import java.util.List;
import java.util.Objects;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nao7016.ClayiumAdditions.CAModMain;
import com.nao7016.ClayiumAdditions.common.CATabs;
import com.nao7016.ClayiumAdditions.util.StorageBoxUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.clayium.util.UtilLocale;

public class itemStorageBox extends Item {

    private static final Logger log = LogManager.getLogger(itemStorageBox.class);
    private IIcon defaultIcon;

    public itemStorageBox() {
        super();
        setUnlocalizedName("storage_box");
        setCreativeTab(CATabs.ca_tabs);
        setMaxStackSize(1);
        setHasSubtypes(true);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        // アイテムを登録していない時GUIを開く
        if (!world.isRemote) {
            if (getStoredItemStack(stack) == null) {
                player.openGui(CAModMain.instance, SBGuiHandler.StorageBoxGui, world, 0, 0, 0);
            } else {
                ItemStack storedItemStack = Objects.requireNonNull(getStoredItemStack(stack));
                log.info(
                    "Item: {}, Size: {}, Damage: {}",
                    storedItemStack.getItem(),
                    storedItemStack.stackSize,
                    storedItemStack.getItemDamage());
                log.info(
                    "NBT Item: {}, Count: {}, Meta: {}",
                    getStoredItem(stack),
                    getStoredCount(stack),
                    getItemNBTData(storedItemStack, "Meta"));
            }
        }
        return stack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        defaultIcon = iconRegister.registerIcon("clayiumadditions:storage_box");
        this.itemIcon = defaultIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return defaultIcon;
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

        ItemStack sItemStack = getStoredItemStack(stack);
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

    public static void keyboardEvent(boolean isShift, EntityPlayer player) {
        ItemStack heldItemStack = player.getHeldItem();
        if (heldItemStack != null) {
            Item heldItem = heldItemStack.getItem();

            if (heldItem instanceof itemStorageBox) {
                if (isShift) {
                    dropStoredItemStack(heldItemStack, player);
                } else {
                    storageItemStack(heldItemStack, player);
                }
                return;
            }
        }

        if (!isShift) {
            ItemStack[] itemStacks = player.inventory.mainInventory;
            for (ItemStack itemStack : itemStacks) {
                if (itemStack != null && itemStack.getItem() instanceof itemStorageBox) {
                    Item sItem = getStoredItem(itemStack);
                    if (sItem != null) {
                        storageItemStack(itemStack, player);
                    }
                }
            }
        }
    }

    /**
     * アイテム生成 & ドロップ処理。スタック上限個生成する。
     * NBTが変化する。
     *
     * @param storageBox ItemStack ストレージボックス
     * @param player     EntityPlayer プレイヤー
     */
    public static void dropStoredItemStack(ItemStack storageBox, EntityPlayer player) {
        dropStoredItemStack(storageBox, player, 0);
    }

    /**
     * アイテム生成 & ドロップ処理。amountで最大数を調整できる。
     * NBTが変化する。
     *
     * @param storageBox ItemStack ストレージボックス
     * @param player     EntityPlayer プレイヤー
     * @param amount     int 最大数
     */
    public static void dropStoredItemStack(ItemStack storageBox, EntityPlayer player, int amount) {
        ItemStack sItemStack = generateStoredItemStack(storageBox, amount);
        if (sItemStack == null) return;

        if (amount >= 1) {
            int slot = player.inventory.getFirstEmptyStack();
            if (slot >= 0) {
                player.inventory.setInventorySlotContents(slot, sItemStack);
                sItemStack = null;
                return;
            }
        }
        if (player.inventory.addItemStackToInventory(sItemStack)) {
            player.inventory.markDirty();
            return;
        }

        if (sItemStack.stackSize > 0) {
            player.dropPlayerItemWithRandomChoice(sItemStack.copy(), false);
        }

    }

    /**
     * アイテム収納処理。
     * NBTが変化する。
     *
     * @param storageBox ItemStack ストレージボックス
     * @param player     EntityPlayer プレイヤー
     */
    public static void storageItemStack(ItemStack storageBox, EntityPlayer player) {
        World world = player.worldObj;

        ItemStack sItemStack = getStoredItemStackAll(storageBox);
        if (sItemStack == null) {
            if (!world.isRemote) {
                player.openGui(CAModMain.instance, SBGuiHandler.StorageBoxGui, world, 0, 0, 0);
            }
        } else {
            int amount = 0;
            InventoryPlayer inventory = player.inventory;
            ItemStack[] itemStacks = inventory.mainInventory;

            for (int i = 0; i < itemStacks.length; i++) {
                if (itemStacks[i] != null && sItemStack.isItemEqual(itemStacks[i])) {
                    amount += itemStacks[i].stackSize;
                    itemStacks[i] = null;
                }
            }

            addStoredCount(storageBox, amount);
        }
    }

    /**
     * 登録アイテムからItemStackを生成する。最大スタック上限個。
     * 生成した分NBTが変化する。
     *
     * @param storageBox ItemStack ストレージボックス
     * @return ItemStack
     */
    public static ItemStack generateStoredItemStack(ItemStack storageBox) {
        return generateStoredItemStack(storageBox, 0);
    }

    /**
     * 登録アイテムからItemStackを生成する。最大スタック上限個。amountで最大個数を設定できる。0以下の場合はスタック上限個。
     * 生成した分NBTが変化する。
     *
     * @param storageBox ItemStack ストレージボックス
     * @param amount     int 取得したい個数
     * @return ItemStack
     */
    public static ItemStack generateStoredItemStack(ItemStack storageBox, int amount) {
        ItemStack result = getStoredItemStack(storageBox, amount);
        if (result == null) return null;
        removeStoredItemStack(storageBox, result);
        return result;
    }

    /**
     * 登録アイテムを最大スタック上限個で返す。
     * NBTは変化しない。
     *
     * @param storageBox ItemStack 取得したいアイテムが入ったストレージボックス。
     * @return ItemStack 最大スタック上限個。
     */
    public static ItemStack getStoredItemStack(ItemStack storageBox) {
        return getStoredItemStack(storageBox, 0);
    }

    /**
     * 登録アイテムを個数を指定して返す。
     * 最大個数を指定できる。0以下の時はスタック上限個。
     * NBTは変化しない。
     *
     * @param storageBox ItemStack 取得したいアイテムが入ったストレージボックス。
     * @param amount     int 個数。0以下の場合、スタック上限で返す。
     * @return ItemStack
     */
    public static ItemStack getStoredItemStack(ItemStack storageBox, int amount) {
        ItemStack sItemStack = getStoredItemStackAll(storageBox);
        if (sItemStack == null) return null;

        if (amount <= 0) amount = sItemStack.getMaxStackSize();
        if (amount >= 1 && sItemStack.stackSize > amount) sItemStack.stackSize = amount;

        return sItemStack;
    }

    /**
     * 登録アイテムを上限なしで返す。
     * NBTは変化しない。
     *
     * @param storageBox ItemStack 取得したいアイテムが入ったストレージボックス。
     * @return ItemStack 上限の個数は無く、NBTの"Count"の個数分返る。
     */
    public static ItemStack getStoredItemStackAll(ItemStack storageBox) {
        Item sItem = getStoredItem(storageBox);
        if (sItem == null) return null;

        int count = getStoredCount(storageBox);

        ItemStack storedAll = null;
        if (count > 0) {
            int meta = getItemNBTData(storageBox, "meta");
            storedAll = new ItemStack(sItem, count, meta);
        } else {
            setStoredItemToNBT(storageBox, null, 0);
        }
        return storedAll;
    }

    /**
     * NBTの"ItemID"からItemを返す
     *
     * @param storageBox ItemStack 取得したいアイテムが入ったストレージボックス
     * @return Item
     */
    public static Item getStoredItem(ItemStack storageBox) {
        if (storageBox.hasTagCompound() && storageBox.getTagCompound()
            .hasKey("ItemID")) {
            int itemID = getItemNBTData(storageBox, "ItemID");
            if (itemID == 0) return null;

            int count = getItemNBTData(storageBox, "Count");
            if (count > 1) return getItemById(itemID);
        }
        return null;
    }

    /**
     * 登録アイテムの個数を取得
     *
     * @param storageBox ItemStack 取得したいアイテムが入ったストレージボックス
     * @return int アイテムの個数。
     */
    public static int getStoredCount(ItemStack storageBox) {
        if (storageBox.hasTagCompound()) {
            return getItemNBTData(storageBox, "Count");
        }
        return 0;
    }

    /**
     * NBTに登録アイテムと個数をセット
     * NBT ["ItemID": int, "Count": int, "Meta": int]
     *
     * @param storageBox ItemStack ストレージボックス
     * @param item       ItemStack 登録するアイテム
     * @param count      int アイテムの個数
     */
    public static void setStoredItemToNBT(ItemStack storageBox, ItemStack item, int count) {
        NBTTagCompound nbt = storageBox.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            storageBox.setTagCompound(nbt);
        }

        if (item == null) {
            setItemNBTData(nbt, "ItemID", 0);
            setItemNBTData(nbt, "Count", 0);
            setItemNBTData(nbt, "Meta", 0);
        } else {
            int itemID = Item.getIdFromItem(item.getItem());
            int meta = item.getItemDamage();
            setItemNBTData(nbt, "ItemID", itemID);
            setItemNBTData(nbt, "Count", count);
            setItemNBTData(nbt, "Meta", meta);
        }

        storageBox.setTagCompound(nbt);
    }

    /**
     * NBTに登録アイテムと個数をセット
     * NBT ["ItemID": int, "Count": int, "Meta": int]
     *
     * @param storageBox ItemStack ストレージボックス
     * @param item       ItemStack 登録するアイテム
     */
    public static void setStoredItemToNBT(ItemStack storageBox, ItemStack item) {
        if (item == null) {
            setItemNBTData(storageBox, "ItemID", 0);
            setItemNBTData(storageBox, "Count", 0);
            setItemNBTData(storageBox, "Meta", 0);
        } else {
            setItemNBTData(storageBox, "ItemID", Item.getIdFromItem(item.getItem()));
            setItemNBTData(storageBox, "Count", item.stackSize);
            setItemNBTData(storageBox, "Meta", item.getItemDamage());
        }
    }

    // public void dropItem(EntityPlayer player, ItemStack item) {
    // if (FMLClientHandler.instance().getClient().thePlayer ==player) {
    // if (player.inventory.addItemStackToInventory(item)) {
    // player.inventory.markDirty();
    // }
    // }
    // }

    /**
     * ストレージボックスのNBTの"Count"を増加させる。
     *
     * @param storageBox ItemStack ストレージボックス
     * @param amount     int 個数
     */
    public static void addStoredCount(ItemStack storageBox, int amount) {
        int current = getStoredCount(storageBox);
        setItemNBTData(storageBox, "Count", current + amount);
    }

    /**
     * ストレージボックスの登録アイテムを減少させる。0個以下になった場合は0になる。
     *
     * @param storageBox ItemStack ストレージボックス
     * @param remove     ItemStack 減少させるアイテムスタック
     */
    public static void removeStoredItemStack(ItemStack storageBox, ItemStack remove) {
        ItemStack All = getStoredItemStackAll(storageBox);
        // ItemStackの一致確認
        if (All != null && All.isItemEqual(remove)) {
            if (remove.stackSize > All.stackSize) {
                remove.stackSize -= All.stackSize;
                All = null;
            } else {
                All.stackSize -= remove.stackSize;
                remove = null;
            }

            setStoredItemToNBT(storageBox, All);
        }
    }

    /**
     *
     * @param storageBox ItemStack 取得したいNBTがあるストレージボックス。
     * @param key        String 取得したいNBTキー。
     * @return int値
     */
    public static int getItemNBTData(ItemStack storageBox, String key) {
        int data = 0;
        NBTTagCompound nbt = storageBox.getTagCompound();
        if (nbt == null) return data;
        data = getItemNBTData(nbt, key);
        return data;
    }

    /**
     *
     * @param nbt NBTTagCompound 取得したいストレージボックスのNBT。
     * @param key String 取得したいNBTキー。
     * @return int値
     */
    public static int getItemNBTData(NBTTagCompound nbt, String key) {
        int data = 0;
        if (nbt == null || !nbt.hasKey(key)) return data;
        data = nbt.getInteger(key);
        return data;
    }

    /**
     * ストレージボックスの収納できるアイテムをbooleanで提示する。
     *
     * @param stack ItemStack ストレージボックス
     * @return boolean
     */
    public boolean canStore(ItemStack stack) {
        if (stack == null) return false;
        if (stack.getItem() instanceof itemStorageBox) return false;
        if (stack.hasTagCompound()) return false;
        if (Objects.requireNonNull(stack.getItem())
            .isDamageable()) return false;
        if (stack.isItemEnchanted()) return false;
        return true;
    }
}
