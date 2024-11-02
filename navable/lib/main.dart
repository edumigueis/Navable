import 'package:flutter/material.dart';
import 'package:navable/src/components/splash.dart';
import 'package:navable/src/pages/controllers/map_controller.dart';
import 'package:navable/src/pages/controllers/pick_accessibilities_controller.dart';
import 'package:navable/src/pages/controllers/profile_controller.dart';
import 'package:navable/src/pages/controllers/signin_controller.dart';
import 'package:navable/src/pages/controllers/signup_controller.dart';
import 'package:navable/src/pages/services/map_service.dart';
import 'package:navable/src/pages/services/pick_accessibilities_service.dart';
import 'package:navable/src/pages/services/profile_service.dart';
import 'package:navable/src/pages/services/signin_service.dart';
import 'package:navable/src/pages/services/signup_service.dart';

import 'src/app.dart';
import 'src/pages/controllers/settings_controller.dart';
import 'src/pages/services/settings_service.dart';

void main() {
  runApp(const LoadingApp());
}

class LoadingApp extends StatelessWidget {
  const LoadingApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Navable',
      home: FutureBuilder(
        future: _initializeControllers(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const SplashScreen();
          } else if (snapshot.hasError) {
            return Center(child: Text('Erro ao carregar o app: ${snapshot.error}'));
          } else {
            final controllers = snapshot.data as Map<String, dynamic>;
            final isUserSignedIn = controllers['isUserSignedIn'];

            return Navable(
              settingsController: controllers['settingsController'],
              profileController: controllers['profileController'],
              mapController: controllers['mapController'],
              pickController: controllers['pickController'],
              signupController: controllers['signupController'],
              signinController: controllers['signinController'],
              isUserSignedIn: controllers['isUserSignedIn'],
            );
          }
        },
      ),
    );
  }

  Future<Map<String, dynamic>> _initializeControllers() async {
    final profileController = ProfileController(ProfileService());

    final settingsController = SettingsController(SettingsService());
    await settingsController.loadSettings();

    final mapController = MapViewController(MapService());
    final pickController = PickAccessibilitiesController(PickAccessibilitiesService());
    final signupController = SignupController(SignupService());
    final signinController = SigninController(SigninService());
    final isUserSignedIn = await signinController.isUserSignedIn();

    return {
      'settingsController': settingsController,
      'profileController': profileController,
      'mapController': mapController,
      'pickController': pickController,
      'signupController': signupController,
      'signinController': signinController,
      'isUserSignedIn': isUserSignedIn,
    };
  }
}
