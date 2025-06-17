import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import '../../util/config/app_config.dart';
import '../models/acc_category.dart';

class PickAccessibilitiesService {
  final FlutterSecureStorage _storage = FlutterSecureStorage();

  Future<List<AccessibilityCategory>> getCategories() async {
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

  Future<void> registerCategories(List<int> categoryIds) async {
    final userId = await _storage.read(key: AppConfig.userIdKey);

    if (userId == null) {
      throw Exception('User ID not found in secure storage');
    }

    final url = Uri.parse('${AppConfig.baseUrl}/usuarios/categoria/$userId');
    final headers = {'Content-Type': 'application/json'};
    final body = json.encode(categoryIds);

    try {
      final response = await http.post(url, headers: headers, body: body);

      if (response.statusCode != 200) {
        throw Exception('Failed to register categories: ${response.body}');
      }
    } catch (e) {
      throw Exception('Error registering categories: $e');
    }
  }
}