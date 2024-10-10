pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "hh_test_task"
include(":app")
include(":core:uikit")
include(":core:utils")
include(":feature_search:api")
include(":feature_search:impl")
include(":feature_sign_in:api")
include(":feature_sign_in:impl")
include(":feature_sign_in:impl")
include(":feature_sign_in:impl")
include(":core:navigation")
