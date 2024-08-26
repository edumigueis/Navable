import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';

import '../components/custom_pin_marker.dart';
import '../components/top_bar.dart';
import '../sample_feature/sample_item.dart';

class MapView extends StatefulWidget {
  const MapView({
    super.key,
  });

  static const routeName = '/';

  @override
  MapViewState createState() => MapViewState();
}

class MapViewState extends State<MapView> with TickerProviderStateMixin {
  static const _startedId = 'AnimatedMapController#MoveStarted';
  static const _inProgressId = 'AnimatedMapController#MoveInProgress';
  static const _finishedId = 'AnimatedMapController#MoveFinished';

  final mapController = MapController();

  void _animatedMapMove(LatLng destLocation, double destZoom) {
    final camera = mapController.camera;
    final latTween = Tween<double>(
        begin: camera.center.latitude, end: destLocation.latitude);
    final lngTween = Tween<double>(
        begin: camera.center.longitude, end: destLocation.longitude);
    final zoomTween = Tween<double>(begin: camera.zoom, end: destZoom);

    final controller = AnimationController(
        duration: const Duration(milliseconds: 500), vsync: this);
    final Animation<double> animation =
        CurvedAnimation(parent: controller, curve: Curves.fastOutSlowIn);

    final startIdWithTarget =
        '$_startedId#${destLocation.latitude},${destLocation.longitude},$destZoom';
    bool hasTriggeredMove = false;

    controller.addListener(() {
      final String id;
      if (animation.value == 1.0) {
        id = _finishedId;
      } else if (!hasTriggeredMove) {
        id = startIdWithTarget;
      } else {
        id = _inProgressId;
      }

      hasTriggeredMove |= mapController.move(
        LatLng(latTween.evaluate(animation), lngTween.evaluate(animation)),
        zoomTween.evaluate(animation),
        id: id,
      );
    });

    animation.addStatusListener((status) {
      if (status == AnimationStatus.completed) {
        controller.dispose();
      } else if (status == AnimationStatus.dismissed) {
        controller.dispose();
      }
    });

    controller.forward();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const TopBar(),
      extendBodyBehindAppBar: true,
      extendBody: true,
      body: Stack(
        children: [
          FlutterMap(
            mapController: mapController,
            options: const MapOptions(
              initialCenter: LatLng(51.509364, -0.128928),
              initialZoom: 17.2,
            ),
            children: [
              TileLayer(
                urlTemplate: 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
                userAgentPackageName: 'com.unicamp.navable',
                tileUpdateTransformer: _animatedMoveTileUpdateTransformer,
              ),
              MarkerLayer(
                markers: [
                  Marker(
                    point: const LatLng(30, 40),
                    width: 20,
                    height: 20,
                    child: CustomPinMarker(
                      onTap: () {},
                      icon: Icons.wheelchair_pickup,
                      color: Colors.red,
                      size: 20.0,
                    ),
                  ),
                ],
              ),
            ],
          ),
        ],
      ),
      floatingActionButton: Column(
        mainAxisAlignment: MainAxisAlignment.end,
        children: [
          FloatingActionButton(
            onPressed: () {
              _animatedMapMove(LatLng(53.509764, -0.128928), 16);
            },
            backgroundColor: Colors.grey.withOpacity(0.5), // Cinza transparente
            child: const Icon(Icons.edit), // Substitua pelo ícone desejado
          ),
          const SizedBox(height: 16), // Espaço entre os botões
          FloatingActionButton(
            onPressed: () {},
            backgroundColor: Colors.blue, // Cor azul
            child: const Icon(Icons.add), // Substitua pelo ícone desejado
          ),
        ],
      ),
    );
  }

  final _animatedMoveTileUpdateTransformer =
      TileUpdateTransformer.fromHandlers(handleData: (updateEvent, sink) {
    final mapEvent = updateEvent.mapEvent;

    final id = mapEvent is MapEventMove ? mapEvent.id : null;
    if (id?.startsWith(_startedId) ?? false) {
      final parts = id!.split('#')[2].split(',');
      final lat = double.parse(parts[0]);
      final lon = double.parse(parts[1]);
      final zoom = double.parse(parts[2]);

      // When animated movement starts load tiles at the target location and do
      // not prune. Disabling pruning means existing tiles will remain visible
      // whilst animating.
      sink.add(
        updateEvent.loadOnly(
          loadCenterOverride: LatLng(lat, lon),
          loadZoomOverride: zoom,
        ),
      );
    } else if (id == _inProgressId) {
      // Do not prune or load whilst animating so that any existing tiles remain
      // visible. A smarter implementation may start pruning once we are close to
      // the target zoom/location.
    } else if (id == _finishedId) {
      // We already prefetched the tiles when animation started so just prune.
      sink.add(updateEvent.pruneOnly());
    } else {
      sink.add(updateEvent);
    }
  });
}
