import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as _storage;
import 'package:http/http.dart' as http;

import '../../util/config/app_config.dart';

class SettingsService {
  final _storage = const FlutterSecureStorage();

  // Default theme mode
  ThemeMode _cachedThemeMode = ThemeMode.system;

  // Theme settings
  Future<ThemeMode> themeMode() async {
    try {
      final themeModeString = await _storage.read(key: 'themeMode');
      if (themeModeString == 'light') {
        _cachedThemeMode = ThemeMode.light;
      } else if (themeModeString == 'dark') {
        _cachedThemeMode = ThemeMode.dark;
      } else {
        _cachedThemeMode = ThemeMode.system;
      }
    } catch (e) {
      // If there's an error, return the cached or default value
    }
    return _cachedThemeMode;
  }

  Future<void> updateThemeMode(ThemeMode theme) async {
    String themeModeString;
    switch (theme) {
      case ThemeMode.light:
        themeModeString = 'light';
        break;
      case ThemeMode.dark:
        themeModeString = 'dark';
        break;
      default:
        themeModeString = 'system';
    }

    await _storage.write(key: 'themeMode', value: themeModeString);
    _cachedThemeMode = theme;
  }

  // Methods for other settings can similarly use FlutterSecureStorage
  Future<bool> useSystemFontSize() async {
    final value = await _storage.read(key: 'useSystemFontSize');
    return value != 'false'; // Default to true if not set
  }

  Future<void> updateUseSystemFontSize(bool value) async {
    await _storage.write(key: 'useSystemFontSize', value: value.toString());
  }

  Future<void> logout() async {
    await _storage.delete(key: AppConfig.userIdKey);
  await _storage.delete(key: AppConfig.tokenKey);
  }

  Future<void> deleteAccount() async {
    try {
      // Get user credentials from storage
      final token = await _storage.read(key: AppConfig.tokenKey);
      final userId = await _storage.read(key: AppConfig.userIdKey);

      if (token != null && userId != null) {
        // Call your Delete API endpoint
        final response = await http.delete(
          Uri.parse('${AppConfig.baseUrl}/usuarios/$userId'),
          headers: {
            'Authorization': 'Bearer $token',
            'Content-Type': 'application/json',
          },
        );

        // Check response status
        if (response.statusCode != 200 && response.statusCode != 204) {
          throw Exception('Failed to delete account: ${response.statusCode}');
        }
      }

      // Clean up local storage after successful deletion
      await _storage.delete(key: AppConfig.userIdKey);
      await _storage.delete(key: AppConfig.tokenKey);
      // You may want to delete all other user settings too
      await _storage.deleteAll();

    } catch (e) {
      // Handle error (you might want to show an error message to the user)
      print('Error deleting account: $e');
      rethrow;
    }
  }
}