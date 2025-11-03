
plugins {
    kotlin("multiplatform") version "2.1.10"
    id("maven-publish")
}

group = "com.blackstone"
version = "0.3.5"


repositories {
    mavenCentral()
    google()
    mavenLocal()
}

kotlin {
    jvm()

    js(IR) {
        browser { binaries.executable() }
        nodejs { binaries.executable() }
    }
    watchosX64()
    watchosArm64()
    watchosDeviceArm64()
    watchosSimulatorArm64()
    watchosArm32()
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3" )

            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
    }
}



publishing {
    // this fetches our credentials from ~/.gradle/gradle.properties
    val mavenUser: String? by project
    val mavenPassword: String? by project

    repositories {
        maven {
            name = "reposiliteRepositoryReleases"
            setUrl("https://repos.awhb.dev/releases")
            authentication {
                create("basic", BasicAuthentication::class.java)
            }
            credentials {
                username = mavenUser
                password = mavenPassword
            }
        }
    }
}

