# APKShare
APKShare lists all installed apps (system and user) and lets you send the original APK file by e-mail, or share it with Android Beam, Bluetooth, etc.

Root access is <strong>not</strong> required and no permission is used.

## Download

APKShare is available on [Google Play](https://play.google.com/store/apps/details?id=be.brunoparmentier.apkshare).

Signed APK's can also be found on GitHub in the [Releases](https://github.com/bparmentier/APKShare/releases) section.

Build
-----

If you use Android Studio, you can import the project directly from GitHub.

Otherwise you can build it from the command line with [Gradle](https://developer.android.com/sdk/installing/studio-build.html).  
Clone the repo and type:

    ./gradlew build

(You may need to `chmod +x` the `gradlew` script)

The Gradle script will take care of downloading the necessary libraries and will generate the APK's in `app/build/outputs/apk`.