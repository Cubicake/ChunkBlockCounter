plugins {
    id("java-library")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.21"
    id("xyz.jpenilla.run-paper") version "3.0.2"
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.codemc.io/repository/maven-releases/") }
    maven { url = uri("https://eldonexus.de/repository/maven-public/") }
}

dependencies {
    paperweight.paperDevBundle("1.20.6-R0.1-SNAPSHOT")
    compileOnly("com.github.retrooper:packetevents-spigot:2.12.0")
    compileOnly("it.unimi.dsi:fastutil:8.5.18")
    compileOnly("net.strokkur.commands:annotations-paper:2.1.2")
    annotationProcessor("net.strokkur.commands:processor-paper:2.1.2")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

val javaToolchainService = project.extensions.getByType(JavaToolchainService::class.java)

tasks {
    runServer {
        javaLauncher = javaToolchainService.launcherFor {
            languageVersion.set(JavaLanguageVersion.of(25))
        }
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("26.1.2")
        jvmArgs("-Xms2G", "-Xmx2G", "-Dcom.mojang.eula.agree=true")
    }

    processResources {
        val props = mapOf("version" to version)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}
