import 'package:latlong2/latlong.dart';

class Warning {
  final String title;
  final String type;
  final LatLng location;

  Warning(this.title, this.type, this.location);
}