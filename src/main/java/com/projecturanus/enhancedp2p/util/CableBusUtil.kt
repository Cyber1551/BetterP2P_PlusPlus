package com.projecturanus.enhancedp2p.util

import appeng.api.config.SecurityPermissions
import appeng.api.networking.IGrid
import appeng.api.networking.security.ISecurityGrid
import appeng.api.parts.IPart
import appeng.api.parts.IPartHost
import appeng.api.parts.SelectedPart
import appeng.parts.AEBasePart
import appeng.parts.ICableBusContainer
import appeng.parts.p2p.PartP2PTunnel
import appeng.tile.networking.TileCableBus
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockAccess

/**
 * @see appeng.block.networking.BlockCableBus.cb
 */
fun getCableBus(w: IBlockAccess, pos: BlockPos): ICableBusContainer? {
    val te = w.getTileEntity(pos)
    var out: ICableBusContainer? = null
    if (te is TileCableBus) {
        out = te.cableBus
    }
    return out
}

fun getPart(w: IBlockAccess, pos: BlockPos, hitX: Float, hitY: Float, hitZ: Float): IPart? {
    val vec = Vec3d(hitX.toDouble(), hitY.toDouble(), hitZ.toDouble())
    val te = w.getTileEntity(pos)
    if (te !is IPartHost) return null
    val p: SelectedPart? = (te as IPartHost).selectPart(vec)
    return p?.part
}

/**
 * Return a list of p2p in the part's target grid
 * @param grid Grid
 * @param player Player to check permission
 * @param clazz P2P class type
 * @return a list of p2p tunnel in the target grid, or an empty list
 */
fun listTargetGridP2P(grid: IGrid?, player: EntityPlayer, clazz: Class<out PartP2PTunnel<*>>): List<PartP2PTunnel<*>> {
    if (grid is ISecurityGrid)
        if (!grid.hasPermission(player, SecurityPermissions.BUILD))
            return emptyList()

    return grid?.getMachines(clazz)?.map { it.machine as PartP2PTunnel<*> }?.toList() ?: emptyList()
}

val AEBasePart.facingPos: BlockPos?
    get() =
        host?.location?.pos?.offset(side?.facing ?: EnumFacing.UP)

val AEBasePart.facingTile: TileEntity?
    get() {
        if (host.isInWorld) {
            val pos = facingPos
            if (pos != null)
                return host?.location?.world?.getTileEntity(pos)
        }
        return null
    }
