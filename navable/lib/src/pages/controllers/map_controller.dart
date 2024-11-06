import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:latlong2/latlong.dart';
import 'package:navable/src/pages/models/place.dart';

import '../models/warning.dart';
import '../services/map_service.dart';

class MapViewController with ChangeNotifier {
  final MapService _mapService;

  MapViewController(this._mapService);

  final storage = const FlutterSecureStorage();

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

  List<WarningType> _warningTypes = [];

  List<WarningType> get warningTypes => _warningTypes;

  set warningTypes(List<WarningType> newTypes) {
    _warningTypes = newTypes;
    notifyListeners();
  }

  Place? selectedPlace;

  Future<void> loadCurrentLocation() async {
    await _mapService.getCurrentLocation().then((value) async {
      _currentLocation = value;
      _places = await _mapService.getNearbyVenues(value);
      _warnings = await _mapService.getNearbyWarnings(value);
      _warningTypes = await _mapService.getWarningTypes();
    });
    notifyListeners();
  }

  Future<void> createOcurrence(WarningType warning) async {
    final id = await storage.read(key: 'userId');
    if (id == null) {
      throw Exception('User ID not found in storage');
    } else {
      await _mapService.saveOcurrence(warning, _currentLocation, int.parse(id));
    }
    notifyListeners();
  }

  void togglePlaceModal(Place place) {
    _isModalOpen = !_isModalOpen;
    selectedPlace = place;
    notifyListeners();
  }

  void closePlaceModal() {
    _isModalOpen = false;
    notifyListeners();
  }
}
