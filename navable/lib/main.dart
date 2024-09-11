import 'package:flutter/material.dart';
import 'package:navable/src/components/splash.dart';
import 'package:navable/src/pages/controllers/map_controller.dart';
import 'package:navable/src/pages/controllers/profile_controller.dart';
import 'package:navable/src/pages/services/map_service.dart';
import 'package:navable/src/pages/services/profile_service.dart';

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
        future: _initializeControllers(), // Carrega os controladores
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const SplashScreen();
          } else if (snapshot.hasError) {
            return Center(
                child: Text('Erro ao carregar o app: ${snapshot.error}'));
          } else {
            final controllers = snapshot.data as Map<String, dynamic>;
            return Navable(
              settingsController: controllers['settingsController'],
              profileController: controllers['profileController'],
              mapController: controllers['mapController'],
            );
          }
        },
      ),
    );
  }

  Future<Map<String, dynamic>> _initializeControllers() async {
    final settingsController = SettingsController(SettingsService());
    await settingsController.loadSettings();

    final profileController = ProfileController(ProfileService());
    await profileController.loadSettings();

    final mapController = MapViewController(MapService());
    await Future.delayed(Duration(seconds: 3));
    return {
      'settingsController': settingsController,
      'profileController': profileController,
      'mapController': mapController,
    };
  }
}
