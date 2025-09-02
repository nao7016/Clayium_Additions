package com.nao7016.ClayiumAdditions.item.storagebox;

import static com.nao7016.ClayiumAdditions.util.StorageBoxUtil.findPrivateValue;
import static com.nao7016.ClayiumAdditions.util.StorageBoxUtil.setItemNBTData;

import java.util.List;
import java.util.Objects;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nao7016.ClayiumAdditions.CAModMain;
import com.nao7016.ClayiumAdditions.common.CATabs;
import com.nao7016.ClayiumAdditions.util.StorageBoxUtil;

import cpw.mods.fml.common.registry.GameData;
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
    public boolean onItemUse(ItemStack storageBox, EntityPlayer player, World world, int x, int y, int z, int side,
        float hitX, float hitY, float hitZ) {
        Item sItem = getStoredItem(storageBox);
        if (sItem == null) return super.onItemUse(storageBox, player, world, x, y, z, side, hitX, hitY, hitZ);

        ItemStack sItemStack = generateStoredItemStack(storageBox);
        if (sItemStack == null || sItemStack.getItem() == null) {
            return super.onItemUse(storageBox, player, world, x, y, z, side, hitX, hitY, hitZ);
        }

        boolean use = true;

        if (sItem instanceof ItemBlock) {
            ItemBlock block = (ItemBlock) sItem;
            if (world.isRemote) {
                use = block.func_150936_a(world, x, y, z, side, player, sItemStack);
            } else {
                use = canPlaceItemBlockOnSide(world, x, y, z, side, player, sItem, sItemStack);
            }
        }

        if (use) {
            use = sItem.onItemUse(sItemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
        }

        addItemStack(storageBox, sItemStack);
        return use;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack storageBox, World world, EntityPlayer player) {
        Item sItem = getStoredItem(storageBox);
        ItemStack sItemStack = generateStoredItemStack(storageBox);

        if (sItem == null || sItemStack == null) {// アイテムを登録していない時GUIを開く
            player.openGui(CAModMain.instance, SBGuiHandler.StorageBoxGui, world, 0, 0, 0);
        } else {
            ItemStack tempStack = sItem.onItemRightClick(sItemStack, world, player);
            if (tempStack == null) tempStack = sItemStack;
            if (sItemStack.isItemEqual(tempStack)) {
                // 通常のアイテムなど
                ItemStack using = null;
                if (world.isRemote) {
                    using = player.getItemInUse();
                } else {
                    final List<Object> l = findPrivateValue(EntityPlayer.class, player, ItemStack.class);
                    if (l.size() == 1) using = (ItemStack) l.get(0);
                }

                if (using != null && using.getItem() == sItemStack.getItem()) {
                    int itemInUseCount = sItem.getMaxItemUseDuration(sItemStack);
                    if (world.isRemote) itemInUseCount = player.getItemInUseCount();
                    player.setItemInUse(storageBox, itemInUseCount);
                }
            } else {
                // バケツなど、副産物がある系の操作
                sItemStack.stackSize -= tempStack.stackSize;
                dropItem(player, tempStack);
            }
            addItemStack(storageBox, sItemStack);
        }
        return storageBox;
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

    public static boolean canPlaceItemBlockOnSide(World world, int x, int y, int z, int side, EntityPlayer player,
        Item sItem, ItemStack sItemStack) {
        if (!(sItem instanceof ItemBlock)) return false;

        Block block = world.getBlock(x, y, z);

        if (block == Blocks.snow) side = 1;
        else if (block != Blocks.vine && block != Blocks.tallgrass
            && block != Blocks.deadbush
            && !block.isReplaceable(world, x, y, z)) {
                switch (side) {
                    case 0 -> y--;
                    case 1 -> y++;
                    case 2 -> z--;
                    case 3 -> z++;
                    case 4 -> x--;
                    case 5 -> x++;
                }
            }

        return world.canPlaceEntityOnSide(Block.getBlockFromItem(sItem), x, y, z, false, side, player, sItemStack);
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

    public void dropToChest(ItemStack storageBox, List<Slot> slots, boolean onlyOneStack) {
        ItemStack sItemStack = getStoredItemStackAll(storageBox);
        if (sItemStack == null) return;

        int count = sItemStack.stackSize;
        int removed = 0;
        IInventory inventory = slots.get(0).inventory;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            Slot slot = i < slots.size() ? slots.get(i) : null;
            if (slot == null) continue;

            if (!slot.isItemValid(sItemStack)) continue;

            ItemStack target = inventory.getStackInSlot(i);
            // 空スロットは新規に配置
            if (target == null) {
                ItemStack newStack = sItemStack.copy();
                int moving = Math.min(count, sItemStack.getMaxStackSize());

                newStack.stackSize = moving;
                inventory.setInventorySlotContents(i, newStack);
                inventory.markDirty();

                removed += moving;
                count -= moving;
                // 既存のスロットに追加
            } else if (sItemStack.isItemEqual(target) && target.stackSize < target.getMaxStackSize()) {
                int space = target.getMaxStackSize() - target.stackSize;
                int moving = Math.min(count, space);
                target.stackSize += moving;
                inventory.markDirty();

                removed += moving;
                count -= moving;
            }

            if (count <= 0) break;
            if (onlyOneStack && removed > 0) break;
        }

        if (removed > 0) {
            sItemStack.stackSize -= removed;
            removeStoredItemStack(storageBox, sItemStack);
        }
    }

    public void storageFromChest(ItemStack storageBox, List slots, EntityPlayer player) {
        ItemStack sItemStack = getStoredItemStackAll(storageBox);
        if (sItemStack == null) return;

        int added = 0;
        IInventory inventory = null;

        for (int i = 0; i < slots.size(); i++) {
            Slot slot = (Slot) slots.get(i);
            if (slot == null) continue;

            inventory = slot.inventory;
            int slotIndex = slot.getSlotIndex();

            ItemStack containerStack = inventory.getStackInSlot(slotIndex);
            if (containerStack == null) continue;

            if (!sItemStack.isItemEqual(containerStack)) continue;

            slot.onPickupFromSlot(player, containerStack);
            added += containerStack.stackSize;
            inventory.setInventorySlotContents(slotIndex, null);
        }
        if (inventory != null) inventory.markDirty();

        if (added > 0) {
            ItemStack add = sItemStack.copy();
            add.stackSize = added;
            addItemStack(storageBox, add);
        }
    }

    /**
     * 副産物をプレイヤーに還す
     * 
     * @param player    EntityPlayer プレイヤー
     * @param itemStack ItemStack 渡すアイテム(副産物)
     */
    public static void dropItem(EntityPlayer player, ItemStack itemStack) {
        if (itemStack == null || itemStack.stackSize == 0) return;

        if (player.inventory.addItemStackToInventory(itemStack)) {
            player.inventory.markDirty();
        } else {
            player.dropPlayerItemWithRandomChoice(itemStack.copy(), false);
        }

        itemStack.stackSize = 0;
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
        if (count <= 0) return null;

        int meta = getItemNBTData(storageBox, "Meta");
        return new ItemStack(sItem, count, meta);
    }

    /**
     * NBTの"ItemName"からItemを返す
     *
     * @param storageBox ItemStack 取得したいアイテムが入ったストレージボックス
     * @return Item
     */
    public static Item getStoredItem(ItemStack storageBox) {
        NBTTagCompound nbt = storageBox.getTagCompound();
        if (nbt != null && nbt.hasKey("ItemName")
            && !nbt.getString("ItemName")
                .isEmpty()) {
            Object obj = Item.itemRegistry.getObject(nbt.getString("ItemName"));
            if (obj instanceof Item) return (Item) obj;
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
     * NBT ["ItemName": String, "Count": int, "Meta": int]
     *
     * @param storageBox ItemStack ストレージボックス
     * @param item       ItemStack 登録するアイテム
     */
    public static void setStoredItemToNBT(ItemStack storageBox, ItemStack item) {
        if (item == null) {
            setStoredItemToNBT(storageBox, null, 0);
        } else {
            setStoredItemToNBT(storageBox, item, item.stackSize);
        }
    }

    /**
     * NBTに登録アイテムと個数をセット
     * NBT ["ItemName": String, "Count": int, "Meta": int]
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
            setItemNBTData(nbt, "ItemName", "");
            setItemNBTData(nbt, "Count", 0);
            setItemNBTData(nbt, "Meta", 0);
        } else {
            String name = GameData.getItemRegistry()
                .getNameForObject(item.getItem());
            System.out.println("setStoredItemToNBT: " + name);
            setItemNBTData(nbt, "ItemName", name);
            setItemNBTData(nbt, "Count", count);
            setItemNBTData(nbt, "Meta", item.getItemDamage());
        }

        // storageBox.setTagCompound(nbt);
    }

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
     * ストレージボックスの登録アイテムを増加させる。
     * NBTが変化する。
     *
     * @param storageBox ストレージボックス
     * @param add        加算するアイテム
     * @return ItemStackインスタンス
     */
    public static void addItemStack(ItemStack storageBox, ItemStack add) {
        ItemStack All = getStoredItemStackAll(storageBox);

        if (All == null) {
            setStoredItemToNBT(storageBox, add);
            add = null;
        } else {
            // itemstackの一致確認
            if (All.isItemEqual(add)) {
                All.stackSize += add.stackSize;
                add = null;
                setStoredItemToNBT(storageBox, All);
            }
        }
    }

    /**
     * ストレージボックスの登録アイテムを減少させる。0個以下になった場合は0になる。
     *
     * @param storageBox ItemStack ストレージボックス
     * @param remove     ItemStack 減少させるアイテムスタック
     * @return ItemStack 減少させた結果の残り、nullで残りなし
     */
    public static ItemStack removeStoredItemStack(ItemStack storageBox, ItemStack remove) {
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
        return remove;
    }

    /**
     *
     * @param storageBox ItemStack 取得したいNBTがあるストレージボックス。
     * @param key        String 取得したいNBTキー。
     * @return int値
     */
    public static int getItemNBTData(ItemStack storageBox, String key) {
        NBTTagCompound nbt = storageBox.getTagCompound();

        if (nbt == null) return 0;
        return getItemNBTData(nbt, key);
    }

    /**
     *
     * @param nbt NBTTagCompound 取得したいストレージボックスのNBT。
     * @param key String 取得したいNBTキー。
     * @return int値
     */
    public static int getItemNBTData(NBTTagCompound nbt, String key) {
        if (nbt == null || !nbt.hasKey(key)) return 0;
        return nbt.getInteger(key);
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
