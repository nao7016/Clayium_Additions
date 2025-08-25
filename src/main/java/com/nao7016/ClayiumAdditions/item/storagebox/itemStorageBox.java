package com.nao7016.ClayiumAdditions.item.storagebox;

import com.nao7016.ClayiumAdditions.common.CATabs;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

import static com.nao7016.ClayiumAdditions.util.storagebox.ReflectionUtil.findPrivateValue;
import static com.nao7016.ClayiumAdditions.util.storagebox.SBGUIHandler.openInventory;
import static com.nao7016.ClayiumAdditions.util.storagebox.SBNBTUtil.addItemStack;
import static com.nao7016.ClayiumAdditions.util.storagebox.SBNBTUtil.getItem;
import static com.nao7016.ClayiumAdditions.util.storagebox.SBNBTUtil.getItemStack;
import static com.nao7016.ClayiumAdditions.util.storagebox.SBNBTUtil.isAutoCollect;
import static com.nao7016.ClayiumAdditions.util.storagebox.SBNBTUtil.peekItemStackAll;
import static com.nao7016.ClayiumAdditions.util.storagebox.SBNBTUtil.removeItemStack;
import static com.nao7016.ClayiumAdditions.util.storagebox.SBPlayerHandler.dropItem;

public class itemStorageBox extends Item {
    public itemStorageBox() {
        super();
        setUnlocalizedName("storage_box");
        setCreativeTab(CATabs.ca_tabs);
        setMaxStackSize(1);
        setHasSubtypes(true);
    }

    private static final boolean AddName = false;
    private static final boolean AddStack = false;
    private static final int Position = 0;
    Side getSide = FMLCommonHandler.instance().getSide();

    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemStack, int i) {
        return 0xFFFFFF;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack item) {
        ItemStack itemstack = peekItemStackAll(item);

        if (itemstack == null) {
            return false;
        }

        return itemstack.hasEffect(0);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world,
                             int x, int y, int z, int side,
                             float hitX, float hitY, float hitZ) {
        Item sItem = getItem(itemStack);
        if(sItem == null) {
            return super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
        }

        ItemStack sItemStack = getItemStack(itemStack);
        boolean bUse = true;

        if(sItem instanceof ItemBlock) {
            ItemBlock itemBlock = (ItemBlock) sItem;
            if(getSide == Side.CLIENT) {
                bUse = itemBlock.func_150936_a(world, x, y, z, side, player, sItemStack);
            } else {
                bUse = this.canPlaceItemBlockOnSide(world, x, y, z, side, player, sItem, sItemStack);
            }
        }

        if (bUse) {
            bUse = sItem.onItemUse(sItemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
        }

        addItemStack(itemStack, sItemStack);
        return bUse;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        Item sItem = getItem(itemStack);

        if (sItem != null) {
            ItemStack sItemStack = getItemStack(itemStack);
            ItemStack tempStack = sItem.onItemRightClick(sItemStack, world, player);

            if (sItemStack.isItemEqual(tempStack)) {
                ItemStack itemUse = null;
                if (getSide == Side.CLIENT) {
                    itemUse = player.getItemInUse();
                } else {
                    final List<Object> list = findPrivateValue(EntityPlayer.class, player, ItemStack.class);
                    if (1 == list.size()) itemUse = (ItemStack) list.get(0);
                }
                if (itemUse != null && itemUse.getItem() == sItemStack.getItem()) {
                    int itemInUseCount = sItem.getMaxItemUseDuration(sItemStack);
                    if (getSide == Side.CLIENT) {
                        itemInUseCount = player.getItemInUseCount();
                    }
                    player.setItemInUse(itemStack, itemInUseCount);
                }
            } else {
                sItemStack.stackSize -= tempStack.stackSize;
                dropItem(player, tempStack);
            }

            addItemStack(itemStack, sItemStack);
        } else {
            openInventory(itemStack, player, world);
        }

        return super.onItemRightClick(itemStack, world, player);
    }


    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
        Item sItem = getItem(itemStack);
        if (sItem != null) {
            ItemStack sItemStack = getItemStack(itemStack);
            ItemStack tempStack = sItem.onEaten(sItemStack, world, player);

            if (sItemStack != null && !sItemStack.isItemEqual(tempStack)) dropItem(player, tempStack);
            addItemStack(itemStack, sItemStack);
        }

        return super.onEaten(itemStack, world, player);
    }

    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase entity, EntityLivingBase entity2) {
        Item sItem = getItem(itemStack);

        if (sItem != null) {
            ItemStack sItemStack = getItemStack(itemStack);
            sItem.hitEntity(sItemStack, entity, entity2);
            addItemStack(itemStack, sItemStack);
        }

        return super.hitEntity(itemStack, entity, entity2);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
        Item sItem = getItem(itemStack);

        if (sItem != null) {
            ItemStack sitemstack = getItemStack(itemStack);
            sItem.onBlockDestroyed(sitemstack, world, block, x, y, z, entity);
            addItemStack(itemStack, sitemstack);
        }

        return super.onBlockDestroyed(itemStack, world, block, x, y, z, entity);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase entity) {
        boolean result;
        Item sItem = getItem(itemStack);

        if (sItem != null) {
            ItemStack sItemStack = getItemStack(itemStack);
            result = sItem.itemInteractionForEntity(sItemStack, player, entity);
            addItemStack(itemStack, sItemStack);
        } else {
            result = super.itemInteractionForEntity(itemStack, player, entity);
        }

        return result;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        Item sItem = getItem(itemStack);

        if (sItem != null) {
            ItemStack sItemStack = peekItemStackAll(itemStack);
            return sItem.getMaxItemUseDuration(sItemStack);
        }

        return super.getMaxItemUseDuration(itemStack);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack) {
        Item sItem = getItem(itemStack);

        if (sItem != null) {
            ItemStack sItemStack = peekItemStackAll(itemStack);
            return sItem.getItemUseAction(sItemStack);
        }

        return super.getItemUseAction(itemStack);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int i) {
        Item sItem = getItem(itemStack);

        if (sItem == null) {
            return;
        }

        ItemStack sitemstack = getItemStack(itemStack);
        sItem.onPlayerStoppedUsing(sitemstack, world, player, i);
        addItemStack(itemStack, sitemstack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSpriteNumber() {
        return super.getSpriteNumber();
    }

    /**
     * 格納されているのが ItemBlock で、その場所に設置できるなら true。それ以外はすべて false。
     */
    public boolean canPlaceItemBlockOnSide(World world, int x, int y, int z, int side, EntityPlayer player, Item sItem, ItemStack itemStack) {
        if (!(sItem instanceof ItemBlock)) return false;

        Block block = world.getBlock(x, y, z);
        if (block == Blocks.snow) {
            side = 1;
        } else if (block != Blocks.vine
            && block != Blocks.tallgrass
            && block != Blocks.deadbush
            && (block != null || !block.isReplaceable(world, x, y, z))) {
            if (side == 0) --y;
            if (side == 1) ++y;
            if (side == 2) --z;
            if (side == 3) ++z;
            if (side == 4) --x;
            if (side == 5) ++x;
        }
        return world.canPlaceEntityOnSide(Block.getBlockFromItem(sItem), x, y, z, false, side, null, itemStack);
    }

    @SuppressWarnings("rawtypes")
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
        //noinspection StatementWithEmptyBody
        if (!AddName || !AddStack) {
        } else return;

        ItemStack sItemStack = peekItemStackAll(itemStack);
        if (sItemStack == null) return;

        Item sItem = getItem(itemStack);
        if(!AddName) {
            String StorageItemName = "Name : " + sItem.getItemStackDisplayName(sItemStack);
            list.add(StorageItemName);
        }
        if(!AddStack) {
            StringBuilder builder = calcItemNumByUnit(itemStack, false);
            builder.insert(0, "Unit: ");
            list.add(builder.toString());
            list.add("Items: " + sItemStack.stackSize);
        }

        list.add("AutoCollect : " + (isAutoCollect(itemStack) ? "ON" : "OFF"));
        list.add("[Information]");
        sItem.addInformation(sItemStack, player, list, flag);
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        String ItemName = super.getItemStackDisplayName(itemStack);

        if (AddName || AddStack) {
            ItemStack sItemStack = peekItemStackAll(itemStack);

            if (sItemStack != null) {
                if (AddName) {
                    Item sItem = getItem(sItemStack);
                    ItemName += "/Name:" + sItem.getItemStackDisplayName(sItemStack);
                }
                if (AddStack) {
                    ItemName += "/Num:" + calcItemNumByUnit(itemStack, true);
                }
            }
        }

        return ItemName;
    }

    /**
     * アイテム数を 1LC+2stacks+3items(xxxitems) みたいに返す。
     */
    private StringBuilder calcItemNumByUnit(ItemStack itemStack, boolean appendItemNum) {
        StringBuilder builder = new StringBuilder("Empty");
        Item sItem = getItem(itemStack);
        if (sItem == null) return builder;
        ItemStack sItemStack = peekItemStackAll(itemStack);
        if (sItemStack == null) return builder;

        builder.setLength(0);
        final int maxStackNum = sItemStack.getMaxStackSize();
        final int LCNum = 6 * 9 * maxStackNum;
        int stack = sItemStack.stackSize;
        int LC = stack / LCNum;
        boolean isHigherUnit = false;

        if (LC >= 1) {
            isHigherUnit = true;
            builder.append(LC).append(" LC");
            stack -= LC * maxStackNum;
        }
        if (stack >= 1) {
            if (builder.length() >= 1) builder.append(" +");
            builder.append(stack).append(" stacks");
        }
        if (isHigherUnit && appendItemNum) {
            builder.append(" ( ").append(sItemStack.stackSize).append(" items)");
        }
        return builder;
    }

    /**
     * アイテム情報表示
     *
     * @param itemStack
     *            storagebox
     */
    @SideOnly(Side.CLIENT)
    public void displayItemInfo(ItemStack itemStack) {
        StringBuilder builder = calcItemNumByUnit(itemStack, true);
        do {
            Item sItem = getItem(itemStack);
            if (sItem == null) break;
            ItemStack sItemStack = peekItemStackAll(itemStack);
            if(sItemStack == null) break;
            builder.insert(0, '/').insert(0, sItem.getItemStackDisplayName(sItemStack));
        } while(false);

        final String string = builder.toString();
        final Minecraft mc = FMLClientHandler.instance().getClient();
        int width = mc.displayWidth;
        int height = mc.displayHeight;
        ScaledResolution scale = new ScaledResolution(mc, width, height);
        width = scale.getScaledWidth() - mc.fontRenderer.getStringWidth(string) / 2;
        height = scale.getScaledHeight();
        mc.fontRenderer.drawString(string, width, height - 60 - Position, 0xFFFFFF);
    }

    /**
     * チェスト連携機能 チェストから格納処理
     *
     * @param itemStack
     *            プレイヤーが現在手に持っているアイテム
     * @param Slots
     *            GUIの表示スロット一覧
     */
    @SuppressWarnings("rawtypes")
    public static void dropToChest(ItemStack itemStack, List Slots, boolean onlyOneStack) {
        ItemStack sItemStack = peekItemStackAll(itemStack);
        if (sItemStack == null) return;

        int size = sItemStack.stackSize;
        IInventory iInventory = ((Slot) Slots.get(0)).inventory;
        int slotSize = iInventory.getSizeInventory();
        Slot slot = null;

        for (int i = 0; i < slotSize; i++) {
            ItemStack itemStack2 = iInventory.getStackInSlot(i);

            if (i < Slots.size()) slot = (Slot) Slots.get(i);
            if(slot == null) continue;
            if (!slot.isItemValid(itemStack2)) continue;

            boolean canAddItem = false;
            if (itemStack2 == null) {
                ItemStack itemStack3 = sItemStack.copy();

                if (size > sItemStack.getMaxStackSize()) {
                    itemStack3.stackSize = sItemStack.getMaxStackSize();
                    size -= itemStack3.stackSize;
                } else {
                    itemStack3.stackSize = size;
                    size = 0;
                }

                canAddItem = true;
                iInventory.setInventorySlotContents(i, itemStack3.copy());
            } else if (sItemStack.isItemEqual(itemStack2) && itemStack2.stackSize < itemStack2.getMaxStackSize()) {
                canAddItem = true;
                if (size > sItemStack.getMaxStackSize() - itemStack2.stackSize) {
                    size -= sItemStack.getMaxStackSize() - itemStack2.stackSize;
                    itemStack2.stackSize = sItemStack.getMaxStackSize();
                } else {
                    itemStack2.stackSize += size;
                    size = 0;
                }
            }

            if (size == 0) break;
            if (onlyOneStack && canAddItem) break;
        }

        iInventory.markDirty();
        sItemStack.stackSize -= size;
        removeItemStack(itemStack, sItemStack);
    }

    /**
     * チェスト連携機能　チェストへ収納処理
     *
     * @param	itemStack	storagebox
     * @param	Slots		GUIの表示スロット一覧
     */
    @SuppressWarnings("rawtypes")
    public static void storageFromChest(EntityPlayer player, ItemStack itemStack, List Slots) {
        ItemStack sItemStack = peekItemStackAll(itemStack);
        if (sItemStack == null) return;

        int size = 0;//sItemStack.stackSize;
        IInventory iInventory = ((Slot)Slots.get(0)).inventory;
        int slotSize = iInventory.getSizeInventory();
        Slot slot = null;

        for (int i = 0; i < slotSize; i++) {
            ItemStack itemStack2 = iInventory.getStackInSlot(i);
            if (itemStack2 == null) continue;
            if (i < Slots.size()) slot = (Slot)Slots.get(i);
            if (slot == null) continue;
            if (!slot.isItemValid(itemStack2)) continue;

            if (sItemStack.isItemEqual(itemStack2)) {
                slot.onPickupFromSlot(player, itemStack2);
                if (itemStack2.stackSize > 0) {
                    size += itemStack2.stackSize;
                    iInventory.setInventorySlotContents(i, null);
                }
            }
        }

        iInventory.markDirty();
        sItemStack.stackSize = size;
        addItemStack(itemStack, sItemStack);
    }
}
