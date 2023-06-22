package com.projecturanus.enhancedp2p.capability

import com.projecturanus.enhancedp2p.item.BetterMemoryCardModes

data class MemoryInfo(var selectedIndex: Int = -1,
                      var frequency: Short = 0,
                      var mode: BetterMemoryCardModes = BetterMemoryCardModes.OUTPUT)
