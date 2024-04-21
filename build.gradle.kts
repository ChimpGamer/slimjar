import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin
import java.net.URI

// Workaround for (https://youtrack.jetbrains.com/issue/KTIJ-19369)
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    java
    alias(libs.plugins.kotlin.jvm)
    id("com.github.hierynomus.license-base") version "0.16.1"
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "com.github.hierynomus.license-base")

    java.sourceCompatibility = JavaVersion.VERSION_11
    java.targetCompatibility = JavaVersion.VERSION_11

    repositories {
        mavenCentral()
    }

    license {
        header = rootProject.file("LICENSE")
        includes(listOf("**/*.java', '**/*.kt"))
        mapping("kt", "DOUBLESLASH_STYLE")
        mapping("java", "DOUBLESLASH_STYLE")
    }

    plugins.withType<KotlinBasePlugin> {
        extensions.configure<KotlinProjectExtension> {
            //jvmToolchain(11)
            explicitApi()
        }
    }

    dependencies {
//        implementation(kotlin("bom:${rootProject.libs.versions.kotlin.get()}")) // Keep kotlin versions in sync.

        testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    }

    //java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))

    afterEvaluate {
        plugins.withType<MavenPublishPlugin> {
            extensions.getByName<PublishingExtension>("publishing").apply {
                repositories.maven {
                    name = "NetworkManagerRepo"
                    url = URI("https://repo.networkmanager.xyz/repository/maven-${if (project.version.toString().endsWith("-SNAPSHOT")) "snapshots" else "releases"}")
                    //credentials(PasswordCredentials::class)
                    credentials {
                        username = project.property("NETWORKMANAGER_NEXUS_USERNAME").toString()
                        password = project.property("NETWORKMANAGER_NEXUS_PASSWORD").toString()
                    }
                }
            }
        }
    }
}
