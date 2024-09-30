import 'package:flutter/material.dart';
import 'package:latlong2/latlong.dart';
import 'package:navable/src/pages/models/acc_category.dart';
import 'package:navable/src/pages/services/pick_accessibilities_service.dart';

import '../services/map_service.dart';

class PickAccessibilitiesController with ChangeNotifier {
  final PickAccessibilitiesService _service;

  PickAccessibilitiesController(this._service);

  List<AccessibilityCategory> _categories= [];

  List<AccessibilityCategory> get categories => _categories;

  Future<void> fetchCategories() async {
    _categories = await _service.getCategories();
    notifyListeners();
  }
}
