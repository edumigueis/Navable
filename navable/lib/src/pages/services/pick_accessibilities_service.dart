import '../models/acc_category.dart';

class PickAccessibilitiesService {
  Future<List<AccessibilityCategory>> getCategories() async {
    await Future.delayed(Duration(seconds: 2));
    return [
      AccessibilityCategory("a", "b"),
      AccessibilityCategory("b", "b"),
      AccessibilityCategory("c", "a")
    ]; // Replace with real data fetching logic
  }
}
