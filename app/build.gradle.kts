import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("idea")
}

android {

    buildFeatures.dataBinding = true

    compileSdkVersion(29)
    buildToolsVersion = "30.0.1"

    defaultConfig {
        applicationId = "jp.pm_coffee.bugchecksample"
        minSdkVersion(23)
        targetSdkVersion(29)
        multiDexEnabled = true
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        named("debug") {
            isDebuggable = true
            isRenderscriptDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "(debug)"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true
    }
    tasks.withType <KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    dexOptions {
        javaMaxHeapSize = "2g"
//        incremental true
    }
    
    useLibrary("android.test.runner")
    useLibrary("android.test.base")
    useLibrary("android.test.mock")
    
    
    testOptions {
        unitTests.apply {
            isIncludeAndroidResources = true
        }
    }
}

val kotlinVersion : String by project

dependencies {
    coreLibraryDesugaring ("com.android.tools:desugar_jdk_libs:1.0.10")

    implementation ( kotlin(module = "stdlib-jdk8", version = kotlinVersion))
    implementation ("androidx.core:core-ktx:1.3.1")
    implementation ("androidx.appcompat:appcompat:1.2.0")
    implementation ("androidx.fragment:fragment-ktx:1.2.5")
    implementation ("com.google.android.material:material:1.2.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.0.1")
    implementation ("com.google.code.gson:gson:2.8.6")



    testImplementation ("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.2")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.3.0")
}

kapt {
    correctErrorTypes = true
}

