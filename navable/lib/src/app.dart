import 'package:flutter/material.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:navable/src/pages/controllers/map_controller.dart';
import 'package:navable/src/pages/controllers/pick_accessibilities_controller.dart';
import 'package:navable/src/pages/controllers/profile_controller.dart';
import 'package:navable/src/pages/landing_view.dart';
import 'package:navable/src/pages/pick_accessibilities_view.dart';
import 'package:navable/src/pages/profile_view.dart';
import 'package:navable/src/pages/review_view.dart';
import 'package:navable/src/pages/signin_view.dart';
import 'package:navable/src/pages/signup_view.dart';

import 'pages/controllers/settings_controller.dart';
import 'pages/map_view.dart';
import 'pages/settings_view.dart';

class Navable extends StatelessWidget {
  const Navable(
      {super.key,
      required this.settingsController,
      required this.profileController,
      required this.mapController,
      required this.pickController});

  final SettingsController settingsController;
  final ProfileController profileController;
  final MapViewController mapController;
  final PickAccessibilitiesController pickController;

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
                    return MapView(controller: mapController);
                  case PickAccessibilitiesView.routeName:
                    return PickAccessibilitiesView(
                        controller: pickController);
                  case ReviewView.routeName:
                    return ReviewView(controller: profileController);
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
