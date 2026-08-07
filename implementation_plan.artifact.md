# Implementation Plan - Apply Liceo Color Theme

The goal is to apply the "Liceo" color theme (Maroon and Gold) throughout the application by correctly using the custom `MyApplicationTheme` instead of the default `MaterialTheme`.

## User Review Required

> [!IMPORTANT]
> I will be replacing the default `MaterialTheme` with `MyApplicationTheme` in `MainActivity.kt`. This will apply the Maroon and Gold colors defined in your theme files to all components.

## Proposed Changes

### [app]

#### [MODIFY] [MainActivity.kt](file:///C:/Users/biene/StudioProjects/edp-android-caballero/app/src/main/java/com/example/myapplication/MainActivity.kt)
- Import `MyApplicationTheme` from `com.example.myapplication.ui.theme`.
- Replace `MaterialTheme` wrapper with `MyApplicationTheme`.
- Remove hardcoded `color = Color.White` from `Surface` to allow the theme to control the background.
- Remove redundant `LiceoMaroon` and `LiceoGold` definitions (as they are already defined in the theme).

#### [MODIFY] [Screens.kt](file:///C:/Users/biene/StudioProjects/edp-android-caballero/app/src/main/java/com/example/myapplication/Screens.kt)
- Ensure components are styled consistently with the theme (though standard Material 3 components will follow the theme automatically).

## Verification Plan

### Automated Tests
- Run the app and verify the UI colors.

### Manual Verification
- Deploy the app to a device/emulator.
- Observe that the button and other elements now use Maroon (`#800000`) and Gold (`#FFD700`) as primary/accent colors.
- Check both Light and Dark modes if supported.
