plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.kirilpetriv.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    // Fix for transitive dependency issue with ByteBuddy
    jvmArgs("-Dnet.bytebuddy.experimental=true")
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":domain"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    implementation(libs.androidx.paging.ktx)
    testImplementation(libs.androidx.paging.testing)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.test.coroutines)
    testImplementation(libs.kotlin.test.junit5)

    implementation(project(":model"))
    implementation(project(":network"))
}