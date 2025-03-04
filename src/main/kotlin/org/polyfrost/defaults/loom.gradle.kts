package org.polyfrost.defaults

import dev.architectury.pack200.java.Pack200Adapter
import gradle.kotlin.dsl.accessors._0935894d714bf6b98fac60b9fc45a2f5.loom
import gradle.kotlin.dsl.accessors._0935894d714bf6b98fac60b9fc45a2f5.mappings
import gradle.kotlin.dsl.accessors._0935894d714bf6b98fac60b9fc45a2f5.minecraft
import gradle.kotlin.dsl.accessors._0935894d714bf6b98fac60b9fc45a2f5.modImplementation
import org.polyfrost.gradle.multiversion.Platform
import org.polyfrost.gradle.util.setupLoomPlugin

plugins {
    id("org.polyfrost.loom")
}
val platform = Platform.of(project)

data class Revision(
    val yarn: Map<Int, String>,
    val mcp: Map<Int, String>,
    val fabricLoader: String,
    val forge: Map<Int, String>,
    val neoForge: Map<Int, String>,
    val parchment: Map<Int, String> = emptyMap(), // Parchment support wasn't added until revision 4
) {
    fun update(
        yarn: Map<Int, String> = emptyMap(),
        mcp: Map<Int, String> = emptyMap(),
        fabricLoader: String = this.fabricLoader,
        forge: Map<Int, String> = emptyMap(),
        neoForge: Map<Int, String> = emptyMap(),
        parchment: Map<Int, String> = emptyMap(),
    ) = Revision(
        this.yarn + yarn,
        this.mcp + mcp,
        fabricLoader,
        this.forge + forge,
        this.neoForge + neoForge,
        this.parchment + parchment
    )
}
val revisions = mutableListOf<Revision>()

// Add new versions to the first revision, so they're available to all users.
// To change existing entries, create a new revision extending from the last one, so existing users keep seeing the old
// one until they opt-in to the new one.
revisions.add(Revision(
    yarn = mapOf(
        12100 to "1.21+build.2:v2",
        12006 to "1.20.6+build.1:v2",
        12005 to "1.20.5+build.1:v2",
        12004 to "1.20.4+build.3:v2",
        12003 to "1.20.3+build.1:v2",
        12002 to "1.20.2+build.1:v2",
        12001 to "1.20.1+build.2:v2",
        12000 to "1.20+build.1:v2",
        11904 to "1.19.4+build.2:v2",
        11903 to "1.19.3+build.5:v2",
        11902 to "1.19.2+build.9:v2",
        11901 to "1.19.1+build.5:v2",
        11900 to "1.19+build.1:v2",
        11802 to "1.18.2+build.2:v2",
        11801 to "1.18.1+build.22:v2",
        11701 to "1.17.1+build.39:v2",
        11700 to "1.17+build.13:v2",
        11605 to "1.16.5+build.10:v2",
        11604 to "1.16.4+build.6:v2",
        11602 to "1.16.2+build.1:v2",
        11601 to "1.16.1+build.17:v2",
        11502 to "1.15.2+build.14",
        11404 to "1.14.4+build.16",
    ),
    mcp = mapOf(
        11605 to "snapshot:20210309-1.16.5",
        11602 to "snapshot:20201028-1.16.3",
        11601 to "snapshot:20201028-1.16.3",
        11502 to "snapshot:20200220-1.15.1@zip",
        11404 to "snapshot:20190719-1.14.3@zip",
        11202 to "snapshot:20170615-1.12",
        11201 to "snapshot:20170615-1.12",
        11200 to "snapshot:20170615-1.12",
        11102 to "snapshot:20161220-1.11",
        11100 to "snapshot:20161111-1.10.2",
        11002 to "snapshot:20160518-1.9.4",
        10904 to "snapshot:20160518-1.9.4",
        10809 to "stable:22-1.8.9",
        10800 to "snapshot:20141130-1.8",
        10710 to "stable:12-1.7.10",
    ),
    fabricLoader = "0.13.3",
    forge = mapOf(
        12100 to "1.21-51.0.3",
        12006 to "1.20.6-50.1.3",
        12004 to "1.20.4-49.0.48",
        12003 to "1.20.3-49.0.2",
        12002 to "1.20.2-48.1.0",
        12001 to "1.20.1-47.0.3",
        12000 to "1.20-46.0.14",
        11904 to "1.19.4-45.1.0",
        11903 to "1.19.3-44.1.0",
        11902 to "1.19.2-43.1.16",
        11900 to "1.19-41.0.63",
        11802 to "1.18.2-40.0.46",
        11801 to "1.18.1-39.0.79",
        11701 to "1.17.1-37.0.112",
        11605 to "1.16.5-36.2.39",
        11602 to "1.16.2-33.0.61",
        11601 to "1.16.1-32.0.108",
        11502 to "1.15.2-31.1.18",
        11404 to "1.14.4-28.1.113",
        11202 to "1.12.2-14.23.0.2486",
        11201 to "1.12.1-14.22.0.2444",
        11200 to "1.12-14.21.1.2387",
        11102 to "1.11.2-13.20.0.2216",
        11100 to "1.11-13.19.1.2188",
        11002 to "1.10.2-12.18.2.2099",
        10904 to "1.9.4-12.17.0.1976",
        10809 to "1.8.9-11.15.1.2318-1.8.9",
        10800 to "1.8-11.14.4.1563",
        10710 to "1.7.10-10.13.4.1558-1.7.10",
    ),
    neoForge = mapOf(
        12100 to "21.0.0-beta",
        12006 to "20.6.7-beta",
        12005 to "20.5.21-beta",
        12004 to "20.4.234",
        12003 to "20.3.8-beta",
        12002 to "20.2.88",
    )
))

// Revision 1, adds Legacy Fabric support
revisions.add(revisions.last().update(
    yarn = mapOf(
        11903 to "1.19.3+build.2:v2",
        11605 to "1.16.5+build.10",
        11302 to "1.13.2+build.385:v2",
        11202 to "1.12.2+build.385:v2",
        11102 to "1.11.2+build.385:v2",
        11002 to "1.10.2+build.385:v2",
        10904 to "1.9.4+build.385:v2",
        10809 to "1.8.9+build.385:v2",
        10800 to "1.8+build.385:v2",
        10710 to "1.7.10+build.385:v2"
    ),
    mcp = mapOf(
        11605 to "snapshot:20201028-1.16.3",
        11602 to "snapshot:20201028-1.16.3"
    ),
    fabricLoader = "0.14.10",
    forge = mapOf(
        11802 to "1.18.2-40.1.0",
        11605 to "1.16.5-36.2.34",
    )
))

revisions.add(revisions.last().update(
    yarn = mapOf(
        11903 to "1.19.3+build.5:v2",
        11902 to "1.19.2+build.28:v2",
        11901 to "1.19.1+build.6:v2",
        11900 to "1.19+build.4:v2",
        11802 to "1.18.2+build.4:v2",
        11701 to "1.17.1+build.65:v2",
        11604 to "1.16.4+build.9:v2",
        11602 to "1.16.2+build.47:v2",
        11601 to "1.16.1+build.21:v2",
        11502 to "1.15.2+build.17",
        11404 to "1.14.4+build.18",
    ),
    mcp = mapOf(
        11502 to "stable:60-1.15",
        11404 to "stable:58-1.14.4",
        11202 to "stable:39-1.12",
        11201 to "stable:39-1.12",
        11200 to "stable:39-1.12",
        11102 to "stable:32-1.11",
        11100 to "stable:32-1.11",
        11002 to "stable:29-1.10.2",
        10809 to "stable:22-1.8.9",
        10800 to "stable:18-1.8",
    ),
    fabricLoader = "0.14.21",
    forge = mapOf(
        11903 to "1.19.3-44.1.0",
        11902 to "1.19.2-43.2.0",
        11900 to "1.19-41.1.0",
        11801 to "1.18.1-39.1.0",
        11701 to "1.17.1-37.1.1",
        11502 to "1.15.2-31.2.57",
        11404 to "1.14.4-28.2.26",
        11202 to "1.12.2-14.23.5.2847", // newer versions use a different build system
        11201 to "1.12.1-14.22.1.2478",
        11102 to "1.11.2-13.20.1.2588",
        11100 to "1.11-13.19.1.2189",
        11002 to "1.10.2-12.18.3.2511",
        10904 to "1.9.4-12.17.0.2317",
        10710 to "1.7.10-10.13.4.1614-1.7.10",
    ),
))

revisions.add(revisions.last().update(
    fabricLoader = "0.15.11"
))

revisions.add(revisions.last().update(
    parchment = mapOf(
        12100 to "2024.07.28",
        12006 to "2024.06.16",
        12004 to "2024.04.14",
        12003 to "2023.12.31",
        12002 to "2023.12.10",
        12001 to "2023.09.03",
        11904 to "2023.06.26",
        11903 to "2023.04.25",
        11902 to "2022.11.27",
        11802 to "2022.11.06",
        11801 to "2022.03.06",
        11701 to "2021.12.12",
        11605 to "2022.03.06",
    )
))

val revisionId = findProperty("polyfrost.defaults.loom")?.toString() ?: throw GradleException("""
    No loom defaults version set.
    You need to set `polyfrost.defaults.loom` in the project's `gradle.properties` file to a specific revision,
    so your build does not break when the defaults change.
    The recommended revision is always the most recent one, currently ${revisions.lastIndex}.
""".trimIndent())

val revision = revisions.getOrNull(revisionId.toIntOrNull() ?: -1)
    ?: throw GradleException("Invalid revision `$revisionId` for `polyfrost.defaults.loom`. Latest is ${revisions.lastIndex}.")

fun prop(property: String, default: String?) =
    findProperty("polyfrost.defaults.loom.$property")?.toString()
        ?: default
        ?: throw GradleException("No default $property for ${platform.mcVersionStr} ${platform.loaderStr}. Set `polyfrost.defaults.loom.$property` in the project's `gradle.properties` or PR a new default.")

if (revisions.indexOf(revision) >= 1 && platform.isLegacyFabric) {
    repositories {
        maven("https://repo.polyfrost.org/releases")
    }

    setupLoomPlugin(platform) { platform: Platform ->
        intermediaryUrl.set("https://maven.legacyfabric.net/net/legacyfabric/intermediary/%1\$s/intermediary-%1\$s-v2.jar")
        if (platform.mcVersion < 10800) {
            runs.named("client") {
                programArgs("--userProperties", "{}")
            }
        }
    }
}

dependencies {
    minecraft(prop("minecraft", "com.mojang:minecraft:${platform.mcVersionStr}"))

    val mappingsStr = prop("mappings", when {
        platform.isModernFabric ->
            revision.yarn[platform.mcVersion]?.let { "net.fabricmc:yarn:$it" }
        platform.isLegacyFabric ->
            revision.yarn[platform.mcVersion]?.let { "net.legacyfabric:yarn:$it" }
        platform.isForge && platform.mcVersion < 11700 ->
            revision.mcp[platform.mcVersion]?.let { "de.oceanlabs.mcp:mcp_$it" }
        else -> "official"
    })
    val parchmentStr: String? = if (revisionId.toInt() >= 4 && platform.mcVersion >= 11605) prop("parchment", revision.parchment[platform.mcVersion]?.let { "org.parchmentmc.data:parchment-${platform.mcVersionStr}:$it@zip" }).ifEmpty { null } else null
    if (mappingsStr in listOf("official", "mojang", "mojmap")) {
        mappings(
            if (parchmentStr != null) {
                loom.layered {
                    officialMojangMappings()
                    repositories {
                        maven("https://maven.parchmentmc.org")
                    }
                    parchment(parchmentStr)
                }
            } else {
                loom.officialMojangMappings()
            })
    } else if (mappingsStr == "official-like") {
        if (platform.mcMinor > 12) {
            mappings(
                if (parchmentStr != null) {
                    loom.layered {
                        officialMojangMappings()
                        repositories {
                            maven("https://maven.parchmentmc.org")
                        }
                        parchment(parchmentStr)
                    }
                } else {
                    loom.officialMojangMappings()
                })
        } else {
            if (platform.isFabric) {
                repositories {
                    maven("https://raw.githubusercontent.com/BleachDev/cursed-mappings/main/")
                }
                mappings("net.legacyfabric:yarn:${platform.mcVersionStr}+build.mcp")
            } else {
                revision.mcp[platform.mcVersion]?.let { mappings("de.oceanlabs.mcp:mcp_$it") }
            }
        }
    } else if (mappingsStr.isNotBlank()) {
        mappings(
            if (parchmentStr != null) {
                loom.layered {
                    mappings(mappingsStr)
                    repositories {
                        maven("https://maven.parchmentmc.org")
                    }
                    parchment(parchmentStr)
                }
            } else {
                mappingsStr
            }
        )
    }

    if (platform.isFabric) {
        modImplementation(prop("fabric-loader", "net.fabricmc:fabric-loader:${revision.fabricLoader}"))
    } else if (platform.isForge) {
        "forge"(prop("forge", revision.forge[platform.mcVersion]?.let { "net.minecraftforge:forge:$it" }))

        loom.forge.pack200Provider.set(Pack200Adapter())
    } else if (platform.isNeoForge) {
        "neoForge"(prop("neoForge", revision.neoForge[platform.mcVersion]?.let { "net.neoforged:neoforge:$it" }))
    }

}

// https://github.com/architectury/architectury-loom/pull/10
if (platform.isModLauncher) {
    val forgeRepo = repositories.find { it.name == "Forge" } as? MavenArtifactRepository
    forgeRepo?.metadataSources {
        mavenPom()
        artifact()
        ignoreGradleMetadataRedirection()
    }
}
