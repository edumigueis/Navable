import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:navable/src/pages/models/place.dart';
import 'package:navable/src/pages/models/warning.dart';
import 'package:navable/src/util/styles.dart';

import '../components/custom_pin_marker.dart';
import '../util/animated_map_move.dart';

class MapWidget extends StatelessWidget {
  MapWidget(
      {super.key,
      required this.mapController,
      required this.onToggleModal,
      required this.onMapMove,
      required this.onToggleWarningModal,
      required this.initialCenter,
      required this.warnings,
      required this.places});

  final MapController mapController;
  final void Function(Place place) onToggleModal;
  final void Function(LatLng latLng, double zoom) onMapMove;
  final void Function(Warning warning) onToggleWarningModal;
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
            markers: places
                    .map(
                      (place) => Marker(
                        point: LatLng(
                            place.location.latitude, place.location.longitude),
                        width: 20,
                        height: 20,
                        child: CustomPinMarker(
                          onTap: () => onToggleModal(place),
                          icon: Icons.location_on_rounded,
                          color: place.grade > 1.32
                              ? Colors.green
                              : place.grade < 0.66
                                  ? Colors.red
                                  : Colors.yellow,
                          size: 25.0,
                        ),
                      ),
                    )
                    .toList() +
                warnings
                    .map(
                      (warn) => Marker(
                        point: LatLng(
                            warn.location.latitude, warn.location.longitude),
                        width: 30,
                        height: 30,
                        child: CustomPinMarker(
                          onTap: () => onToggleWarningModal(warn),
                          icon: Icons.warning_rounded,
                          color: NavableColors.grayAccent,
                          size: 30.0,
                        ),
                      ),
                    )
                    .toList() +
                [
                  Marker(
                    point:
                        LatLng(initialCenter.latitude, initialCenter.longitude),
                    width: 20,
                    height: 20,
                    child: CustomPinMarker(
                      onTap: () => {},
                      icon: Icons.person_pin_circle_rounded,
                      color: NavableColors.blueAccent,
                      size: 25.0,
                    ),
                  ),
                ])
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
