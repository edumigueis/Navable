import 'package:flutter/material.dart';
import '../services/signup_service.dart';

class SignupController with ChangeNotifier {
  final SignupService _service;

  SignupController(this._service);

  String? _errorMsg;
  String? get errorMsg => _errorMsg;

  Future<void> register({
    required String name,
    required String email,
    required String password,
    required String confirmPassword,
  }) async {
    _errorMsg = null;

    if (name.isEmpty) {
      _errorMsg = "O nome é obrigatório.";
    } else if (email.isEmpty || !email.contains('@')) {
      _errorMsg = "Por favor, insira um e-mail válido.";
    } else if (password.isEmpty || password.length < 6) {
      _errorMsg = "A senha deve ter pelo menos 6 caracteres.";
    } else if (password != confirmPassword) {
      _errorMsg = "As senhas não coincidem.";
    }

    if (_errorMsg != null) {
      notifyListeners();
      return;
    }

    try {
      await _service.registerUser(name, email, password);
    } catch (e) {
      _errorMsg = "Erro ao registrar. Tente novamente mais tarde.";
    }

    notifyListeners();
  }
}
