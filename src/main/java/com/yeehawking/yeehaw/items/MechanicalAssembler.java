package com.yeehawking.yeehaw.items;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MechanicalAssembler extends Item {
    public MechanicalAssembler() {
        super(new Properties().group(Yeehaw.TOOL_TAB));
    }

    public boolean hasContainerItem() // Item persists in crafting
    {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
        if (!hasContainerItem(itemStack))
        {
            return ItemStack.EMPTY;
        }
        return new ItemStack(this);
    } // End
}
