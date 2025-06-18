// review_controller.dart
import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:navable/src/pages/models/place.dart';
import 'package:navable/src/pages/models/review.dart';
import 'package:navable/src/pages/services/review_service.dart';
import 'package:navable/src/util/config/app_config.dart';

class ReviewController with ChangeNotifier {
  final ReviewService _service;
  final FlutterSecureStorage _storage = const FlutterSecureStorage();

  bool _isSubmitting = false;
  String? _errorMessage;
  bool _isSuccess = false;

  ReviewController(this._service);

  bool get isSubmitting => _isSubmitting;
  String? get errorMessage => _errorMessage;
  bool get isSuccess => _isSuccess;

  void resetState() {
    _errorMessage = null;
    _isSuccess = false;
    notifyListeners();
  }

  Future<void> submitReview({
    required Place place,
    required String reviewText,
    required int rating,
  }) async {
    _isSubmitting = true;
    _errorMessage = null;
    _isSuccess = false;
    notifyListeners();

    try {
      final userId = await _storage.read(key: AppConfig.userIdKey);

      if (userId == null) {
        throw Exception('User not logged in');
      }

      final review = Review(
        idUsuario: int.parse(userId),
        idEstabelecimento: int.parse(place.name.hashCode.toString()), // Assuming place has an id; if not, we need to handle this differently
        avaliacao: reviewText,
        nota: rating,
      );

      await _service.submitReview(review);

      _isSuccess = true;
    } catch (e) {
      _errorMessage = e.toString();
    } finally {
      _isSubmitting = false;
      notifyListeners();
    }
  }
}