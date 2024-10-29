import 'package:flutter/material.dart';
import 'package:latlong2/latlong.dart';

import '../services/map_service.dart';

class SigninController with ChangeNotifier {
  final SigninService _service;

  SigninController(this._service);

  Sring _errorMsg;
  bool _showErrorMsg = false;

  Sring get errorMsg => _errorMsg;

  set errorMsg(String newMsg) {
    _errorMsg = newMsg;
    notifyListeners();
  }

  Future<void> signin() async {
    await _service.signin();
    notifyListeners();
  }
  
  Future<bool> isUserSignedIn() async {
    return await _service.isUserSignedIn();
  }

  void toggleErrorMsg() {
    _showErrorMsg = !_showErrorMsg;
    notifyListeners();
  }
}
