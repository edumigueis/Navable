import 'package:flutter/material.dart';
import 'package:navable/src/pages/models/acc_category.dart';
import 'package:navable/src/pages/services/pick_accessibilities_service.dart';

class PickAccessibilitiesController with ChangeNotifier {
  final PickAccessibilitiesService _service;
  String? _errorMessage;  // To hold any error messages
  bool _loading = false;   // To indicate loading state

  PickAccessibilitiesController(this._service);

  List<AccessibilityCategory> _categories = [];

  List<AccessibilityCategory> get categories => _categories;

  set categories(List<AccessibilityCategory> newCategories) {
    _categories = newCategories;
    notifyListeners();
  }

  String? get errorMessage => _errorMessage; // Get the error message
  bool get loading => _loading;              // Get loading state

  Future<void> fetchCategories() async {
    _categories = await _service.getCategories();
    notifyListeners();
  }

  Future<void> registerCategories() async {
    _loading = true; // Start loading
    _errorMessage = null; // Reset previous error message
    notifyListeners();

    try {
      // Create a list of category IDs
      final categoryIds = _categories.map((e) => e.id).toList();
      await _service.registerCategories(categoryIds); // Pass list of IDs to the service
      // Optionally show success message
    } catch (e) {
      _errorMessage = e.toString(); // Store the error message
    } finally {
      _loading = false; // Stop loading
      notifyListeners();
    }
  }
}