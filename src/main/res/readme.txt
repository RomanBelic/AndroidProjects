# AndroidProjects
Important : 
-Mettez � jour votre Android Studio
-Soyez s�r d'avoir l'API 25 install�, sinon t�l�chargez-le depuis le SDK
-Installez les derniers SDK build-tools et SDK platform-tools 

1. Importez ce projet et buildez-le avec Gradle.
-Vous allez avoir un message d'erreur "Plugin with id 'android-library' not found"

2. Ajouter ce bout du code au fichier "build.gradle" apr�s la ligne "apply plugin: 'com.android.application'"
------------------------------------------------------------------------------
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:0.12.+'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
    }
}
------------------------------------------------------------------------------
3. Clickez "try again" et rebuilder le projet