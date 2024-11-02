class AccessibilityBadge {
  final int id;
  final String title;
  final String image;

  AccessibilityBadge(this.id, this.title, this.image);

  factory AccessibilityBadge.fromJson(Map<String, dynamic> json) {
    return AccessibilityBadge(
      json['idSelo'] as int,
      json['nome'] as String,
      json['imagem'] as String,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'idSelo': id,
      'nome': title,
      'imagem': image,
    };
  }
}
