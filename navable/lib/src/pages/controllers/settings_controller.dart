import 'package:flutter/material.dart';
import '../services/settings_service.dart';

class SettingsController with ChangeNotifier {
  SettingsController(this._settingsService);

  final SettingsService _settingsService;
  late ThemeMode _themeMode;

  // New settings properties
  bool _useSystemFontSize = true;
  bool _pushNotificationsEnabled = true;
  bool _emailNotificationsEnabled = false;
  bool _analyticsEnabled = true;
  final String _language = 'English (US)';

  // Getters
  ThemeMode get themeMode => _themeMode;
  bool get useSystemFontSize => _useSystemFontSize;
  bool get pushNotificationsEnabled => _pushNotificationsEnabled;
  bool get emailNotificationsEnabled => _emailNotificationsEnabled;
  bool get analyticsEnabled => _analyticsEnabled;
  String get language => _language;

  Future<void> loadSettings() async {
    _themeMode = await _settingsService.themeMode();
    // Load other settings from service
    notifyListeners();
  }

  Future<void> updateThemeMode(ThemeMode? newThemeMode) async {
    if (newThemeMode == null) return;
    if (newThemeMode == _themeMode) return;
    _themeMode = newThemeMode;
    notifyListeners();
    await _settingsService.updateThemeMode(newThemeMode);
  }

  // Methods for updating other settings
  Future<void> updateUseSystemFontSize(bool value) async {
    if (_useSystemFontSize == value) return;
    _useSystemFontSize = value;
    notifyListeners();
    // Persist the setting
  }

  Future<void> updatePushNotifications(bool value) async {
    if (_pushNotificationsEnabled == value) return;
    _pushNotificationsEnabled = value;
    notifyListeners();
    // Persist the setting
  }

  Future<void> updateEmailNotifications(bool value) async {
    if (_emailNotificationsEnabled == value) return;
    _emailNotificationsEnabled = value;
    notifyListeners();
    // Persist the setting
  }

  Future<void> updateAnalytics(bool value) async {
    if (_analyticsEnabled == value) return;
    _analyticsEnabled = value;
    notifyListeners();
    // Persist the setting
  }

  // Methods for account management
  Future<void> logout() async {
    await _settingsService.logout();
  }

  Future<void> deleteAccount() async {
    await _settingsService.deleteAccount();
  }
}