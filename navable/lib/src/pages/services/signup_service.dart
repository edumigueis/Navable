import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:navable/src/pages/services/signin_service.dart';
import '../../util/config/app_config.dart';
import '../models/user.dart';

class SignupService {
  Future<void> registerUser(String name, String email, String password) async {
  const String apiUrl = '${AppConfig.baseUrl}/usuarios';

  final Map<String, dynamic> userData = {
    'nome': name,
    'email': email,
    'senha': password,
    'pontos': 0,
  };

  final String jsonBody = json.encode(userData);

  try {
    final response = await http.post(
      Uri.parse(apiUrl),
      headers: {
        'Content-Type': 'application/json',
      },
      body: jsonBody,
    );

    if (response.statusCode == 200 || response.statusCode == 201) {
      print('User registered successfully: ${response.body}');
      final signin = SigninService();
      signin.signin(email, password);
    } else {
      print('Failed to register user: ${response.statusCode} ${response.body}');
    }
  } catch (e) {
    throw Exception();
  }
  }
}
