package dependencies

const val COMPILE_SDK = 29
const val MIN_SDK = 21
const val TARGET_SDK = 29

private const val VKOTLIN = "1.4.10"
private const val VNAVIGATION = "2.2.0"

const val ANDROID_PLUGIN = "com.android.tools.build:gradle:4.1.0"
const val KOTLIN_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VKOTLIN"
const val SAFEARGS_PLUGIN =
  "androidx.navigation:navigation-safe-args-gradle-plugin:$VNAVIGATION"
const val VERSIONS_PLUGIN = "com.github.ben-manes:gradle-versions-plugin:0.20.0"

private const val VCOUROUTINE = "1.4.0"
const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:$VKOTLIN"
const val COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VCOUROUTINE"
const val UI_COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VCOUROUTINE"

const val KTX = "androidx.core:core-ktx:1.1.0"
const val ACTIVITYX = "androidx.activity:activity-ktx:1.1.0"
const val FRAGMENTX = "androidx.fragment:fragment-ktx:1.2.0"

const val APPCOMPAT = "androidx.appcompat:appcompat:1.1.0"
const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:1.0.0"
const val CONSTRAINTLAYOUT = "androidx.constraintlayout:constraintlayout:2.0.0-beta3"
const val CARDVIEW = "androidx.cardview:cardview:1.0.0"

const val INSETTER = "dev.chrisbanes:insetter-ktx:0.2.0"

const val STORE = "com.dropbox.mobile.store:store4:4.0.0-beta01"
const val STORE_FILE_SYSTEM = "com.dropbox.mobile.store:filesystem4:4.0.0-beta01"

const val MATERIAL = "com.google.android.material:material:1.1.0-beta02"
const val PAGING = "androidx.paging:paging-runtime:2.1.0"
const val BROWSER = "androidx.browser:browser:1.0.0"

const val NAVIGATION = "androidx.navigation:navigation-common-ktx:$VNAVIGATION"
const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$VNAVIGATION"
const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:$VNAVIGATION"

private const val VROOM = "2.1.0-alpha04"
const val ROOM_COMMON = "androidx.room:room-common:$VROOM"
const val ROOM_RUNTIME = "androidx.room:room-runtime:$VROOM"
const val ROOM_COMPILER = "androidx.room:room-compiler:$VROOM"

const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0-rc01"
const val LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0-rc01"
const val LIFECYCLE_COMPILER = "androidx.lifecycle:lifecycle-compiler:2.0.0"
const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:2.2.0-rc01"
const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:2.2.0-rc01"

const val BIOMETRIC = "androidx.biometric:biometric:1.0.0-alpha03"

const val EMOJI = "androidx.emoji:emoji:1.0.0"
const val EMOJI_COMPAT = "androidx.emoji:emoji-appcompat:1.0.0"
const val EMOJI_BUNDLED = "androidx.emoji:emoji-bundled:1.0.0"

const val SAVEDSTATE = "androidx.savedstate:savedstate:1.0.0-alpha02"
const val SAVEDSTATE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-savedstate:1.0.0-alpha01"

const val VIEWPAGER2 = "androidx.viewpager2:viewpager2:1.0.0-alpha01"

const val RECOMMENDATION = "androidx.recommendation:recommendation:1.0.0"

const val PREFERENCE = "androidx.preference:preference-ktx:1.1.0-alpha03"

const val MULTIDEX = "androidx.multidex:multidex:2.0.0"

const val RXWEBVIEW = "com.github.satoshun.RxWebView:rxwebview-kotlin:2.3.0"

const val RXJAVA = "io.reactivex.rxjava2:rxjava:2.2.0"
const val RXANDROID = "io.reactivex.rxjava2:rxandroid:2.1.0"

const val GROUPIE = "com.xwray:groupie:2.3.0"
const val GROUPIE_DATABINDING = "com.xwray:groupie-databinding:2.3.0"

const val WORK_MANAGER = "androidx.work:work-runtime-ktx:2.0.0-rc01"

const val ANDROID_ANNOTATION = "androidx.annotation:annotation:1.0.0"

private const val VDAGGER = "2.28"
const val DAGGER = "com.google.dagger:dagger:$VDAGGER"
const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:$VDAGGER"

private const val VDAGGER_HILT = "2.28-alpha"
const val DAGGER_HILT_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:$VDAGGER_HILT"
const val DAGGER_HILT = "com.google.dagger:hilt-android:$VDAGGER_HILT"
const val DAGGER_HILT_COMPILER = "com.google.dagger:hilt-android-compiler:$VDAGGER_HILT"

const val OKIO = "com.squareup.okio:okio:2.6.0"
const val MOSHI = "com.squareup.moshi:moshi-kotlin:1.9.3"
const val MOSHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:1.9.3"

const val GLIDE = "com.github.bumptech.glide:glide:4.9.0"

const val JUNIT = "junit:junit:4.13-beta-1"
const val TRUTH = "com.google.truth:truth:0.42"
const val MOCKITO_KOTLIN = "com.nhaarman:mockito-kotlin-kt1.1:1.5.0"
const val TEST_RUNNER = "androidx.test:runner:1.1.1"
const val TEST_RULE = "androidx.test:rules:1.1.0"
const val ESPRESSO = "androidx.test.espresso:espresso-core:3.1.1"
