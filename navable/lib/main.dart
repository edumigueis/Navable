import 'package:flutter/material.dart';
import 'package:navable/src/pages/controllers/profile_controller.dart';
import 'package:navable/src/pages/services/profile_service.dart';

import 'src/app.dart';
import 'src/pages/controllers/settings_controller.dart';
import 'src/pages/services/settings_service.dart';

void main() async {
  final settingsController = SettingsController(SettingsService());
  await settingsController.loadSettings();
  final profileController = ProfileController(ProfileService());
  await profileController.loadSettings();

  runApp(MyApp(
      settingsController: settingsController,
      profileController: profileController));
}
