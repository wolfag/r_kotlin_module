[STEPS TO MAKE KOTLIN MODULE](https://reactnative.dev/docs/native-modules-android?android-language=kotlin)
1. In `android/build.gradle` [add kotlin to existing app](https://developer.android.com/kotlin/add-kotlin)
 - add `kotlinVersion` to `buildscript > ext` block
 - add `classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${project.ext.kotlinVersion}")` to `dependencies` block

 2. In `android/app/build.gradle`
  - add `apply plugin: "kotlin-android"` to head of head of file
  - add `implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.6.0")` to `dependencies` block

  4. Create module file like this `android/app/src/main/java/com/kotlintoast/MyModule.kt`

  5. Create service file like this `android/app/src/main/java/com/kotlintoast/ToastService.kt`

  6. In `android/app/src/main/java/com/kotlintoast/MainApplication.java`, you can add your new module to `getPackages` function like example

  7. You can start using your module in react side like `App.tsx`