// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    //kapt --> ksp
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false

    id ("androidx.navigation.safeargs") version "2.5.0" apply false
}


