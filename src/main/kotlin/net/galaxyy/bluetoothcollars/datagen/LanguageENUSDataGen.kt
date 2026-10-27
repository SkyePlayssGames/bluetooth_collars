package net.galaxyy.bluetoothcollars.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.galaxyy.bluetoothcollars.BluetoothCollars.MOD_ID
import net.galaxyy.bluetoothcollars.BluetoothCollars.TRINKETS_MOD_ID
import net.galaxyy.bluetoothcollars.items.ModBlocks
import net.galaxyy.bluetoothcollars.items.ModItems
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class LanguageENUSDataGen(output: FabricPackOutput, lookup: CompletableFuture<HolderLookup.Provider>) : FabricLanguageProvider(output, "en_us", lookup) {
    override fun generateTranslations(
        registryLookup: HolderLookup.Provider,
        translationBuilder: TranslationBuilder
    ) {
        translationBuilder.add(ModItems.TIED_DOWN_COLLAR, "Tied-Down Collar")
        translationBuilder.add(ModItems.TIED_DOWN_REMOTE, "Tied-Down Remote")
        translationBuilder.add(ModItems.CLUMSY_COLLAR, "Clumsy Collar")
        translationBuilder.add(ModItems.CLUMSY_REMOTE, "Clumsy Remote")
        translationBuilder.add(ModItems.CRAWL_COLLAR, "Crawl Collar")
        translationBuilder.add(ModItems.CRAWL_REMOTE, "Crawl Remote")
        translationBuilder.add(ModItems.PAW_COLLAR, "Paw Collar")
        translationBuilder.add(ModItems.PAW_REMOTE, "Paw Remote")
        translationBuilder.add(ModItems.TINY_COLLAR, "Tiny Collar")
        translationBuilder.add(ModItems.TINY_REMOTE, "Tiny Remote")
        translationBuilder.add(ModItems.BLINDING_COLLAR, "Blinding Collar")
        translationBuilder.add(ModItems.BLINDING_REMOTE, "Blinding Remote")
        translationBuilder.add(ModItems.FROZEN_COLLAR, "Frozen Collar")
        translationBuilder.add(ModItems.FROZEN_REMOTE, "Frozen Remote")
        translationBuilder.add(ModItems.LOCK, "Collar Lock")

        translationBuilder.add(ModBlocks.COLLAR_CRAFTER, "Collar Enhancer")

        translationBuilder.add("$MOD_ID.overlay.click", "You hear your collar click!")
        translationBuilder.add("$MOD_ID.tooltip.owners_plural", "Owners:")
        translationBuilder.add("$MOD_ID.tooltip.owners_singular", "Owner:")
        translationBuilder.add("$MOD_ID.tooltip.owners_none", "No Owners")
        translationBuilder.add("$MOD_ID.tooltip.owners_all", "Owned by Everyone")
        translationBuilder.add("$MOD_ID.tooltip.owners_offline_singular", "* 1 Offline Owner")
        translationBuilder.add("$MOD_ID.tooltip.owners_offline_plural", "* %s Offline Owners")
        translationBuilder.add("$MOD_ID.tooltip.owner", "- %s")

        translationBuilder.add("$MOD_ID.tooltip.bell", "Bell")

        translationBuilder.add("collar_tab.$MOD_ID", "Bluetooth Collars")

        translationBuilder.add("tag.item.$MOD_ID.collars", "Collars")
        translationBuilder.add("tag.item.$MOD_ID.remotes", "Remotes")

        translationBuilder.add("advancement.$MOD_ID.get_collared.name", "Get Collared!")
        translationBuilder.add("advancement.$MOD_ID.get_collared.description", "Time to be a little pet!")
        translationBuilder.add("advancement.$MOD_ID.collar_someone.name", "Collar Someone")
        translationBuilder.add("advancement.$MOD_ID.collar_someone.description", "Time to show your control!")
        translationBuilder.add("advancement.$MOD_ID.experimental.name", "Experimental")
        translationBuilder.add("advancement.$MOD_ID.experimental.description", "Experiment with all the effects!")
        translationBuilder.add("advancement.$MOD_ID.universal_toy.name", "Universal Toy")
        translationBuilder.add("advancement.$MOD_ID.universal_toy.description", "Wear a cursed universal collar!")

        translationBuilder.add("sound.$MOD_ID.click", "Collar clicks")
        translationBuilder.add("sound.$MOD_ID.bell", "Collar rings")

        translationBuilder.add("stat.$MOD_ID.collar_crafter_crafts", "Crafts with Collar Enhancer")

        translationBuilder.add("$MOD_ID.remote_range", "Remote Range")
        translationBuilder.add("gamerule.$MOD_ID.remote_range", "Range of a remote clicking")

        translationBuilder.add("modmenu.nameTranslation.$MOD_ID", "Bluetooth Collars")
        translationBuilder.add("modmenu.descriptionTranslation.$MOD_ID", "Collar all your friends and give them restrictions, or be the tail of the joke yourself!")
        translationBuilder.add("modmenu.summaryTranslation.$MOD_ID", "Collar your friends!")
        translationBuilder.add("$MOD_ID.modmenu_link.source", "Source")
    }
}