import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:navable/src/pages/models/place.dart';
import 'package:navable/src/pages/models/warning.dart';

import '../components/custom_pin_marker.dart';
import '../util/animated_map_move.dart';

class MapWidget extends StatelessWidget {
  MapWidget(
      {super.key,
      required this.mapController,
      required this.onToggleModal,
      required this.onMapMove,
      required this.initialCenter,
      required this.warnings,
      required this.places});

  final MapController mapController;
  final VoidCallback onToggleModal;
  final void Function(LatLng latLng, double zoom) onMapMove;
  final LatLng initialCenter;
  final List<Warning> warnings;
  final List<Place> places;

  Widget tileBuilder(BuildContext context, Widget tileWidget, TileImage tile) {
    return ColorFiltered(
        colorFilter: const ColorFilter.matrix(<double>[
          0.2126,
          0.7152,
          0.0722,
          0,
          0,
          0.2126,
          0.7152,
          0.0722,
          0,
          0,
          0.2126,
          0.7152,
          0.0722,
          0,
          0,
          0,
          0,
          0,
          1,
          0,
        ]),
        child: tileWidget);
  }

  @override
  Widget build(BuildContext context) {
    return FlutterMap(
      mapController: mapController,
      options: MapOptions(
        initialCenter: initialCenter,
        initialZoom: 16.2,
      ),
      children: [
        TileLayer(
          urlTemplate: 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
          userAgentPackageName: 'com.unicamp.navable',
          tileUpdateTransformer: _animatedMoveTileUpdateTransformer,
          tileBuilder: tileBuilder,
        ),
        MarkerLayer(
          markers: [
            Marker(
              point: const LatLng(30, 40),
              width: 20,
              height: 20,
              child: CustomPinMarker(
                onTap: onToggleModal,
                icon: Icons.wheelchair_pickup,
                color: Colors.red,
                size: 20.0,
              ),
            ),
          ],
        ),
      ],
    );
  }

  final _animatedMoveTileUpdateTransformer = TileUpdateTransformer.fromHandlers(
    handleData: (updateEvent, sink) {
      final mapEvent = updateEvent.mapEvent;
      final id = mapEvent is MapEventMove ? mapEvent.id : null;

      if (id?.startsWith(AnimatedMapMove.startedId) ?? false) {
        final parts = id!.split('#')[2].split(',');
        final lat = double.parse(parts[0]);
        final lon = double.parse(parts[1]);
        final zoom = double.parse(parts[2]);

        sink.add(
          updateEvent.loadOnly(
            loadCenterOverride: LatLng(lat, lon),
            loadZoomOverride: zoom,
          ),
        );
      } else if (id == AnimatedMapMove.inProgressId) {
        // No action needed
      } else if (id == AnimatedMapMove.finishedId) {
        sink.add(updateEvent.pruneOnly());
      } else {
        sink.add(updateEvent);
      }
    },
  );
}
