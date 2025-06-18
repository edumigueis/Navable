import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:navable/src/pages/models/review.dart';
import 'package:navable/src/util/config/app_config.dart';

class ReviewService {
  final String baseUrl = AppConfig.baseUrl;

  Future<Review> submitReview(Review review) async {
    final response = await http.post(
      Uri.parse('$baseUrl/avaliacoes'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(review.toJson()),
    );

    if (response.statusCode == 201 || response.statusCode == 200) {
      return Review.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to submit review: ${response.body}');
    }
  }
}