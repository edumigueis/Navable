import 'package:flutter/material.dart';
import 'package:latlong2/latlong.dart';
import 'package:navable/src/pages/models/place.dart';

import '../models/warning.dart';
import '../services/map_service.dart';

class MapViewController with ChangeNotifier {
  final MapService _mapService;

  MapViewController(this._mapService);

  late LatLng _currentLocation;
  bool _isModalOpen = false;

  LatLng get currentLocation => _currentLocation;

  bool get isModalOpen => _isModalOpen;

  List<Warning> _warnings = [];

  List<Warning> get warnings => _warnings;

  set warnings(List<Warning> newWarnings) {
    _warnings = newWarnings;
    notifyListeners();
  }

  List<Place> _places = [];

  List<Place> get places => _places;

  set places(List<Place> newPlaces) {
    _places = newPlaces;
    notifyListeners();
  }

  Future<void> loadCurrentLocation() async {
    await _mapService.getCurrentLocation().then((value) async {
      _currentLocation = value;
      _places = await _mapService.getNearbyVenues(value);
      _warnings = await _mapService.getNearbyWarnings(value);
    });
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
