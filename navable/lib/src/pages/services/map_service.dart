import 'dart:convert';

import 'package:geolocator/geolocator.dart';
import 'package:latlong2/latlong.dart';

import '../../util/config/app_config.dart';
import '../models/place.dart';
import '../models/warning.dart';

import 'package:http/http.dart' as http;
class MapService {
  Future<LatLng> getCurrentLocation() async {
    try {
      bool serviceEnabled = await Geolocator.isLocationServiceEnabled();
      if (!serviceEnabled) {
        throw Exception("Location services are disabled.");
      }
    }
    catch(ex){
      return LatLng(-22.813366, -47.063731);
    }

    LocationPermission permission = await Geolocator.checkPermission();
    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();
      if (permission == LocationPermission.deniedForever) {
        return LatLng(-22.813366, -47.063731);
        //throw Exception("Location permissions are permanently denied.");
      }
    }

    Position position = await Geolocator.getCurrentPosition();
    return LatLng(position.latitude, position.longitude);
  }

  Future<List<Place>> getNearbyVenues(LatLng center) async {
    final url = Uri.parse('${AppConfig.baseUrl}/estabelecimentos/${center.latitude}/${center.longitude}');
    try {
      final response = await http.get(url);

      if (response.statusCode == 200) {
        final List<dynamic> data = json.decode(utf8.decode(response.bodyBytes));
        return data.map((json) => Place.fromJson(json)).toList();
      } else {
        throw Exception('Failed to load places');
      }
    } catch (e) {
      throw Exception('Error fetching places: $e');
    }
  }

  Future<List<Warning>> getNearbyWarnings(LatLng center) async {
    final url = Uri.parse('${AppConfig.baseUrl}/ocorrencias/${center.latitude}/${center.longitude}');
    try {
      final response = await http.get(url);

      if (response.statusCode == 200) {
        final List<dynamic> data = json.decode(utf8.decode(response.bodyBytes));
        return data.map((json) => Warning.fromJson(json)).toList();
      } else {
        throw Exception('Failed to load warnings');
      }
    } catch (e) {
      throw Exception('Error fetching warnings: $e');
    }
  }

  Future<List<WarningType>> getWarningTypes() async {
    final url = Uri.parse('${AppConfig.baseUrl}/ocorrencias');
    try {
      final response = await http.get(url);

      if (response.statusCode == 200) {
        final List<dynamic> data = json.decode(utf8.decode(response.bodyBytes));
        return data.map((json) => WarningType.fromJson(json)).toList();
      } else {
        throw Exception('Failed to load warnings');
      }
    } catch (e) {
      throw Exception('Error fetching warnings: $e');
    }
  }

  Future<void> saveOcurrence(WarningType w, LatLng loc, int userId) async {
    final url = Uri.parse('${AppConfig.baseUrl}/ocorrencias');
      Map<String, dynamic> ocorrenciaDTO = {
        'idUsuario': userId,
        'idTipoOcorrencia': w.idTipoOcorrencia,
        'tipoOcorrencia': w.toJson(),
        'latitude': loc.latitude,
        'longitude': loc.longitude,
      };

      final response = await http.post(
        url,
        headers: {
          'Content-Type': 'application/json',
        },
        body: jsonEncode(ocorrenciaDTO),
      );

      if (response.statusCode != 200) {
        throw Exception('Failed to save warning');
      }
  }
}
