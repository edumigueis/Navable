import 'package:flutter/material.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:navable/src/pages/controllers/map_controller.dart';
import 'package:navable/src/pages/controllers/pick_accessibilities_controller.dart';
import 'package:navable/src/pages/controllers/profile_controller.dart';
import 'package:navable/src/pages/controllers/signin_controller.dart';
import 'package:navable/src/pages/controllers/signup_controller.dart';
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
  const Navable({
    super.key,
    required this.settingsController,
    required this.profileController,
    required this.mapController,
    required this.pickController,
    required this.signupController,
    required this.signinController,
    required this.isUserSignedIn,
  });

  final SettingsController settingsController;
  final ProfileController profileController;
  final MapViewController mapController;
  final PickAccessibilitiesController pickController;
  final SignupController signupController;
  final SigninController signinController;
  final bool isUserSignedIn;

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
                // Use isUserSignedIn to determine the initial route
                if (routeSettings.name == '/') {
                  return isUserSignedIn
                      ? MapView(controller: mapController)
                      : const Landing();
                }
                switch (routeSettings.name) {
                  case SettingsView.routeName:
                    return SettingsView(controller: settingsController);
                  case ProfileView.routeName:
                    return ProfileView(controller: profileController);
                  case SignUpView.routeName:
                    return SignUpView(controller: signupController);
                  case SignInView.routeName:
                    return SignInView(controller: signinController);
                  case MapView.routeName:
                    return MapView(controller: mapController);
                  case PickAccessibilitiesView.routeName:
                    return PickAccessibilitiesView(controller: pickController);
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
