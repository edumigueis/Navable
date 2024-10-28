import 'dart:convert';
import 'package:http/http.dart' as http;
import '../../util/config/app_config.dart';
import '../models/acc_category.dart';

class PickAccessibilitiesService {
  Future<List<AccessibilityCategory>> getCategories() async {
    final url = Uri.parse('${AppConfig.baseUrl}/categorias-acessibilidade');
    try {
      final response = await http.get(url);

      if (response.statusCode == 200) {
        final List<dynamic> data = json.decode(response.body);
        return data.map((json) => AccessibilityCategory.fromJson(json)).toList();
      } else {
        throw Exception('Failed to load categories');
      }
    } catch (e) {
      throw Exception('Error fetching categories: $e');
    }
  }
}
