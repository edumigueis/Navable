import 'dart:math';

import 'package:latlong2/latlong.dart';

class Place {
  final String name;
  final double grade;
  final String image;
  final String address;
  final LatLng location;

  Place(this.name, this.grade, this.image, this.address, this.location,);

  factory Place.fromJson(Map<String, dynamic> json) {
    return Place(
      json['nome'] as String,
      Random().nextDouble() * 2,
      json['imagem'] as String,
      json['endereco'] as String,
      LatLng(
        (json['latitude'] as num).toDouble(),
        (json['longitude'] as num).toDouble(),
      ),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'nome': name,
      'nota': grade,
      'imagem': image,
      'endereco': address,
      'latitude': location.latitude,
      'longitude': location.longitude,
    };
  }
}
