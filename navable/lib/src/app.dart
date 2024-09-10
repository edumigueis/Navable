import 'package:flutter/material.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:navable/src/pages/controllers/profile_controller.dart';
import 'package:navable/src/pages/landing_view.dart';
import 'package:navable/src/pages/pick_accessibilities_view.dart';
import 'package:navable/src/pages/profile_view.dart';
import 'package:navable/src/pages/signin_view.dart';
import 'package:navable/src/pages/signup_view.dart';

import 'pages/map_view.dart';
import 'pages/controllers/settings_controller.dart';
import 'pages/settings_view.dart';

/// The Widget that configures your application.
class MyApp extends StatelessWidget {
  const MyApp({
    super.key,
    required this.settingsController,
    required this.profileController,
  });

  final SettingsController settingsController;
  final ProfileController profileController;

  @override
  Widget build(BuildContext context) {
    return ListenableBuilder(
      listenable: settingsController,
      builder: (BuildContext context, Widget? child) {
        return MaterialApp(
          restorationScopeId: 'app',
          localizationsDelegates: const [
            AppLocalizations.delegate,
            GlobalMaterialLocalizations.delegate,
            GlobalWidgetsLocalizations.delegate,
            GlobalCupertinoLocalizations.delegate,
          ],
          supportedLocales: const [
            Locale('en', ''), // English, no country code
          ],
          onGenerateTitle: (BuildContext context) =>
              AppLocalizations.of(context)!.appTitle,
          theme: ThemeData(),
          darkTheme: ThemeData.dark(),
          themeMode: settingsController.themeMode,
          onGenerateRoute: (RouteSettings routeSettings) {
            return MaterialPageRoute<void>(
              settings: routeSettings,
              builder: (BuildContext context) {
                switch (routeSettings.name) {
                  case SettingsView.routeName:
                    return SettingsView(controller: settingsController);
                  case ProfileView.routeName:
                    return ProfileView(controller: profileController);
                  case SignUpView.routeName:
                    return SignUpView(controller: settingsController);
                  case SignInView.routeName:
                    return SignInView(controller: settingsController);
                  case MapView.routeName:
                    return const MapView();
                  case PickAccessibilitiesView.routeName:
                    return PickAccessibilitiesView(controller: settingsController);
                  default:
                    return const Landing();
                }
              },
            );
          },
        );
      },
    );
  }
}
