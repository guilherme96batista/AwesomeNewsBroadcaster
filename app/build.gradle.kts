import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "pt.guilhermerodrigues.awesomenewsbrodcaster"
    compileSdk = 36

    defaultConfig {
        applicationId = "pt.guilhermerodrigues.awesomenewsbrodcaster"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val localPropApiKey = providers.provider {
            val props = Properties()
            val file = rootProject.file("local.properties")
            if (file.exists()) file.inputStream().use { props.load(it) }
            props.getProperty("news_api_key") ?: ""
        }

        val apiKey = providers.gradleProperty("news_api_key")
            .orElse(providers.environmentVariable("news_api_key"))
            .orElse(localPropApiKey)
            .orElse("")

        buildConfigField("String", "news_api_key", "\"${apiKey.get()}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        jvmToolchain(11)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    flavorDimensions += listOf("news_from_another_source")
}

dependencies {
    //retrofit
    implementation(libs.retrofit.core)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.kotlinx.serialization)
    //coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    //kotlin serialization
    implementation(libs.serialization.json)
    //koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    //coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}