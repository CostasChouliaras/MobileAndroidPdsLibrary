package com.educationperfect.pds_library.buildsrc

object Versions {
    const val ktlint = "0.39.0"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.3.0-alpha03"
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.0.9"
    const val junit = "junit:junit:4.13"

    object Kotlin {
        const val version = "1.6.10"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Google {
        const val gmsClasspath = "com.google.gms:google-services:4.3.10"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
    }

    object Coroutines {
        private const val version = "1.5.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object RxKotlin {
        private const val version = "3.0.1"
        const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:$version"
    }

    object Hilt {
        const val version = "2.38.1"
        const val hiltVersion = "1.0.0"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val hiltKapt = "com.google.dagger:hilt-compiler:$version"
        const val hiltTest = "com.google.dagger:hilt-android-testing:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.0"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"

        object Compose {
            private const val snapshot = ""
            const val version = "1.1.0"
            private const val navVersion = "2.5.0-alpha01"
            private const val hiltVersion = "1.0.0"
            private const val viewmodelVersion = "1.0.0-alpha07"
            private const val navHiltVersion = "1.0.0"

            @get:JvmStatic
            val snapshotUrl: String
                get() = "https://androidx.dev/snapshots/builds/$snapshot/artifacts/repository/"

            const val ui = "androidx.compose.ui:ui:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val material = "androidx.compose.material:material:$version"
            const val animation = "androidx.compose.animation:animation:$version"
            const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val composeNavigation = "androidx.navigation:navigation-compose:$navVersion"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:$viewmodelVersion"
            const val navHilt = "androidx.hilt:hilt-navigation-compose:$navHiltVersion"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val hilt = "androidx.hilt:hilt-*:$hiltVersion"
            const val test = "androidx.compose.test:test-core:$version"
            const val uiTest = "androidx.compose.ui:ui-test:$version"
        }

        object Datastore {
            private const val version = "1.0.0"
            const val datastore = "androidx.datastore:datastore-preferences:$version"
        }

        object Test {
            private const val version = "1.4.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.3"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
        }

        object Material {
            private const val version = "1.4.0"
            const val material = "com.google.android.material:material:$version"
        }

        object Lifecycle {
            private const val version = "2.3.1"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object Networking {
            private const val gsonVersion = "2.8.6"
            private const val okhttpVersion = "4.9.0"
            private const val retrofitVersion = "2.9.0"
            const val gson = "com.google.code.gson:gson:$gsonVersion"
            const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
            const val converterGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
            const val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
            const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
        }

        object Room {
            private const val version = "2.4.0"
            const val roomCompiler = "androidx.room:room-compiler:$version"
            const val roomRuntime = "androidx.room:room-runtime:$version"
            const val roomKtx = "androidx.room:room-ktx:$version"
        }

        object Timber {
            private const val version = "4.7.1"
            const val timber = "com.jakewharton.timber:timber:$version"
        }

        object InAppUpdateCompose {
            private const val version = "0.0.14"
            const val appUpdate = "se.warting.in-app-update:in-app-update-compose:$version"
        }
    }

    object Accompanist {
        const val version = "0.20.3"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
        const val uicontroller = "com.google.accompanist:accompanist-systemuicontroller:$version"
    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:1.3.2"
    }

    object MixPanel {
        const val mixPanel = "com.mixpanel.android:mixpanel-android:5.+"
    }
}