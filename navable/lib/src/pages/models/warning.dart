import 'package:latlong2/latlong.dart';

class Warning {
  final String type;
  final LatLng location;

  Warning(this.type, this.location);

  factory Warning.fromJson(Map<String, dynamic> json) {
    return Warning(
      json['type'] as String,
      LatLng(
        (json['latitude'] as num).toDouble(),
        (json['longitude'] as num).toDouble(),
      ),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'type': type,
      'latitude': location.latitude,
      'longitude': location.longitude,
    };
  }
}
