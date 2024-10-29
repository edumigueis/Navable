import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:navable/src/pages/models/acc_category.dart';
import 'package:navable/src/pages/models/badge.dart';

import '../../util/config/app_config.dart';
import '../models/user.dart';

class ProfileService {
  Future<ThemeMode> themeMode() async => ThemeMode.system;

  /// Persists the user.dart's preferred ThemeMode to local or remote storage.
  Future<void> updateThemeMode(ThemeMode theme) async {
    // Use the shared_preferences package to persist settings locally or the
    // http package to persist settings over the network.
  }

  Future<User> getUser(String userId) async {
    final url = Uri.parse('${AppConfig.baseUrl}/usuarios/$userId');
    try {
      final response = await http.get(url);

      if (response.statusCode == 200) {
        final Map<String, dynamic> data =
            json.decode(utf8.decode(response.bodyBytes));
        return User.fromJson(data); // Parse JSON into User object
      } else {
        throw Exception('Failed to load user data');
      }
    } catch (e) {
      throw Exception('Error fetching user data: $e');
    }
  }

  Future<List<AccessibilityCategory>> getUserCategories(String userId) async {
    final url = Uri.parse('${AppConfig.baseUrl}/categorias-acessibilidade');
    try {
      final response = await http.get(url);

      if (response.statusCode == 200) {
        final List<dynamic> data = json.decode(utf8.decode(response.bodyBytes));
        return data.map((json) => AccessibilityCategory.fromJson(json)).toList();
      } else {
        throw Exception('Failed to load categories');
      }
    } catch (e) {
      throw Exception('Error fetching categories: $e');
    }
  }

  Future<List<AccessibilityBadge>> getUserBadges(String userId) async {
    /*final url = Uri.parse('${AppConfig.baseUrl}/categorias-acessibilidade');
    try {
      final response = await http.get(url);

      if (response.statusCode == 200) {
        final List<dynamic> data = json.decode(utf8.decode(response.bodyBytes));
        return data.map((json) => AccessibilityCategory.fromJson(json)).toList();
      } else {
        throw Exception('Failed to load categories');
      }
    } catch (e) {
      throw Exception('Error fetching categories: $e');
    }*/
    return List.of([AccessibilityBadge("a", "a")]);
  }
}
