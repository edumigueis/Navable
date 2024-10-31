import 'dart:convert';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;
import '../../util/config/app_config.dart';
import '../models/user.dart';

class SigninService {
  final _storage = const FlutterSecureStorage();
  static const _userIdKey = 'userId';

  Future<bool> signin(String email, String password) async {
    final url = Uri.parse('${AppConfig.baseUrl}/signin?email=$email&password=$password');

    final response = await http.post(
      url,
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    );

    if (response.statusCode == 200) {
      final data = jsonDecode(response.body);

      final userId = data['userId'];
      if (userId != null) {
        await _storage.write(key: _userIdKey, value: userId.toString());
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  Future<bool> isUserSignedIn() async {
    final userId = await _storage.read(key: _userIdKey);
    return userId != null;
  }
}