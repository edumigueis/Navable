class Place {
  final String name;
  final double grade;
  final String image;
  final String address;

  Place(this.name, this.grade, this.image, this.address);

  factory Place.fromJson(Map<String, dynamic> json) {
    return Place(
      json['name'] as String,
      (json['grade'] as num).toDouble(),
      json['image'] as String,
      json['address'] as String,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'name': name,
      'grade': grade,
      'image': image,
      'address': address,
    };
  }
}
