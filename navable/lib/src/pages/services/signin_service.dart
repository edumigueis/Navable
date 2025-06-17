import 'dart:convert';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;
import '../../util/config/app_config.dart';
import '../models/user.dart';

class SigninService {
  final _storage = const FlutterSecureStorage();

  Future<bool> signin(String email, String password) async {
    final url = Uri.parse('${AppConfig.baseUrl}/auth/login');

    try {
      final response = await http.post(
        url,
        headers: {
          'Content-Type': 'application/json', // Changed to JSON
        },
        body: jsonEncode({
          'email': email,
          'password': password,
        }),
      );

      if (response.statusCode == 200) {
        final data = jsonDecode(response.body);

        // Store both token and user ID
        final token = data['token'];
        final usuario = data['usuario'];
        if (usuario != null && token != null) {
          final userId = usuario['idUsuario'];
          await _storage.write(key: AppConfig.userIdKey, value: userId.toString());
          await _storage.write(key: AppConfig.tokenKey, value: token);
          return true;
        } else {
          return false;
        }
      } else {
        print('Login failed with status code: ${response.statusCode}');
        print('Response body: ${response.body}');
        return false;
      }
    } catch (e) {
      print('Exception during login: $e');
      return false;
    }
  }

  Future<bool> isUserSignedIn() async {
    final userId = await _storage.read(key: AppConfig.userIdKey);
    final token = await _storage.read(key: AppConfig.tokenKey);
    return userId != null && token != null;
  }

  Future<String?> getAuthToken() async {
    return await _storage.read(key: AppConfig.tokenKey);
  }
}