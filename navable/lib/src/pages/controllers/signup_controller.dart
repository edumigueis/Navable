import 'package:flutter/material.dart';
import 'package:latlong2/latlong.dart';

import '../services/map_service.dart';

class SignupController with ChangeNotifier {
  final SignupService _service;

  SignupController(this._service);

  Sring _errorMsg;
  bool _showErrorMsg = false;

  Sring get errorMsg => _errorMsg;

  set errorMsg(String newMsg) {
    _errorMsg = newMsg;
    notifyListeners();
  }

  Future<void> loadCurrentLocation() async {
    _currentLocation = await _mapService.getCurrentLocation();
    notifyListeners();
  }

  void toggleErrorMsg() {
    _showErrorMsg = !_showErrorMsg;
    notifyListeners();
  }
}
