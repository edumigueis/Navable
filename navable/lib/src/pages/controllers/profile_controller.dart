import 'package:flutter/material.dart';
import 'package:navable/src/pages/models/user.dart';

import '../models/acc_category.dart';
import '../models/badge.dart';
import '../services/profile_service.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class ProfileController with ChangeNotifier {
  ProfileController(this._service);

  final ProfileService _service;
  late User user;
  late String userId;
  final storage = const FlutterSecureStorage();

  List<AccessibilityCategory> _categories= [];
  List<AccessibilityCategory> get categories => _categories;

  set categories(List<AccessibilityCategory> newCategories) {
    _categories = newCategories;
    notifyListeners();
  }

  List<AccessibilityBadge> _badges= [];
  List<AccessibilityBadge> get badges => _badges;

  set badges(List<AccessibilityBadge> newAccessibilityBadges) {
    _badges = newAccessibilityBadges;
    notifyListeners();
  }

  Future<void> fetchUserCategories() async {
    badges = await _service.getUserBadges(userId);
  }

  Future<void> fetchUserAccessibilityBadges() async {
    categories = await _service.getUserCategories(userId);
  }

  Future<void> fetchLoggedUser() async {
      final id = await storage.read(key: 'userId');
      if (id == null) {
        throw Exception('User ID not found in storage');
      } else {
        userId = id;
      }
      user = await _service.getUser(userId);
      notifyListeners();
  }
}
