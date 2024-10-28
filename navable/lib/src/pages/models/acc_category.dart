class AccessibilityCategory {
  final int id;
  final String title;
  final String group;

  AccessibilityCategory(this.id, this.title, this.group);

  factory AccessibilityCategory.fromJson(Map<String, dynamic> json) {
    return AccessibilityCategory(
      json['categoriaAcId'],
      json['nome'],
      json['grupo'],
    );
  }
}