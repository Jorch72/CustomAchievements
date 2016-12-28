package com.davqvist.customachievements.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityTrophy extends TileEntity {

    private ItemStack is;
    private String player;
    private EnumFacing facing;
    private int timestamp;

    public void setItemStack( ItemStack itemstack ){
        this.is = itemstack;
    }
    public ItemStack getItemStack(){ return this.is; }

    public void setPlayer( String player ){
        this.player = player;
    }
    public String getPlayer(){ return this.player; }

    @Override
    public NBTTagCompound writeToNBT( NBTTagCompound compound ){
        super.writeToNBT( compound );
        compound.setTag( "item", is.serializeNBT() );
        if( !player.isEmpty() ){ compound.setString( "player", player ); }
        if( facing != null ){ compound.setInteger( "facing", facing.ordinal() ); }
        return compound;
    }

    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT( new NBTTagCompound() );
    }

    public EnumFacing getFacing(){ return facing; }
    public void setFacing( EnumFacing facing ){ this.facing = facing; }

    @Override
    public void readFromNBT( NBTTagCompound compound ){
        super.readFromNBT( compound );
        if( compound.hasKey( "item" ) ){
            is = ItemStack.loadItemStackFromNBT( (NBTTagCompound) compound.getTag( "item" ) );
        }
        if( compound.hasKey( "player" ) ){
            player = compound.getString( "player" );
        }
        if( compound.hasKey( "facing" ) ){
            facing = EnumFacing.getHorizontal( compound.getInteger( "facing" ) );
        }
    }
}
