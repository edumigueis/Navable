import 'package:flutter/material.dart';
import 'package:latlong2/latlong.dart';

import '../services/map_service.dart';

class MapViewController with ChangeNotifier {
  final MapService _mapService;

  MapViewController(this._mapService);

  late LatLng _currentLocation;
  bool _isModalOpen = false;

  LatLng get currentLocation => _currentLocation;

  bool get isModalOpen => _isModalOpen;

  Future<void> loadCurrentLocation() async {
    _currentLocation = await _mapService.getCurrentLocation();
    notifyListeners();
  }

  void togglePlaceModal() {
    _isModalOpen = !_isModalOpen;
    notifyListeners();
  }

  void closePlaceModal() {
    _isModalOpen = false;
    notifyListeners();
  }
}
