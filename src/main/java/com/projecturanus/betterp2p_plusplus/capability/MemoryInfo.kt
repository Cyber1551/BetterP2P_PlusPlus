package com.projecturanus.betterp2p_plusplus.capability

import com.projecturanus.betterp2p_plusplus.item.BetterMemoryCardModes

data class MemoryInfo(var selectedIndex: Int = -1,
                      var frequency: Short = 0,
                      var mode: BetterMemoryCardModes = BetterMemoryCardModes.OUTPUT)
