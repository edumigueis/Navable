import 'package:flutter/material.dart';
import '../services/signin_service.dart';

class SigninController with ChangeNotifier {
  final SigninService _service;

  SigninController(this._service);

  String? _errorMsg;
  bool _showErrorMsg = false;

  String? get errorMsg => _errorMsg;

  set errorMsg(String? newMsg) {
    _errorMsg = newMsg;
    notifyListeners();
  }

  Future<bool> signin(String email, String password) async {
    bool success = false;
    try {
      // Attempt to sign in using the service
      success = await _service.signin(email, password);

      if (!success) {
        errorMsg = "Invalid email or password";
      } else {
        errorMsg = null;
      }
    } catch (e) {
      errorMsg = "An error occurred. Please try again.";
    }
    notifyListeners();
    return success;
  }

  Future<bool> isUserSignedIn() async {
    return await _service.isUserSignedIn();
  }

  void toggleErrorMsg() {
    _showErrorMsg = !_showErrorMsg;
    notifyListeners();
  }
}
