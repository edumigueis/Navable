import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';

class AnimatedMapMove {
  static const startedId = 'AnimatedMapController#MoveStarted';
  static const inProgressId = 'AnimatedMapController#MoveInProgress';
  static const finishedId = 'AnimatedMapController#MoveFinished';

  // Main method that orchestrates the animation
  static void move(
      MapController mapController,
      LatLng destLocation,
      double destZoom,
      TickerProvider vsync,
      ) {
    final tweens = _createTweens(mapController, destLocation, destZoom);
    final controller = _createAnimationController(vsync);
    final animation = _createAnimation(controller);

    final startIdWithTarget = _createTargetId(destLocation, destZoom);

    _addAnimationListeners(
        controller,
        animation,
        mapController,
        tweens,
        startIdWithTarget
    );

    controller.forward();
  }

  // Create tweens for latitude, longitude, and zoom
  static Map<String, Tween<double>> _createTweens(
      MapController mapController,
      LatLng destLocation,
      double destZoom
      ) {
    final camera = mapController.camera;

    return {
      'lat': Tween<double>(
        begin: camera.center.latitude,
        end: destLocation.latitude,
      ),
      'lng': Tween<double>(
        begin: camera.center.longitude,
        end: destLocation.longitude,
      ),
      'zoom': Tween<double>(
        begin: camera.zoom,
        end: destZoom,
      ),
    };
  }

  // Create animation controller
  static AnimationController _createAnimationController(TickerProvider vsync) {
    return AnimationController(
      duration: const Duration(milliseconds: 500),
      vsync: vsync,
    );
  }

  // Create curved animation
  static Animation<double> _createAnimation(AnimationController controller) {
    return CurvedAnimation(
      parent: controller,
      curve: Curves.fastOutSlowIn,
    );
  }

  // Create target ID for animation tracking
  static String _createTargetId(LatLng destLocation, double destZoom) {
    return '$startedId#${destLocation.latitude},${destLocation.longitude},$destZoom';
  }

  // Add animation listeners and handle map updates
  static void _addAnimationListeners(
      AnimationController controller,
      Animation<double> animation,
      MapController mapController,
      Map<String, Tween<double>> tweens,
      String startIdWithTarget,
      ) {
    bool hasTriggeredMove = false;

    // Add listener to update map during animation
    controller.addListener(() {
      final id = _determineAnimationId(
          animation.value,
          hasTriggeredMove,
          startIdWithTarget
      );

      hasTriggeredMove |= _moveMap(
          mapController,
          animation,
          tweens,
          id
      );
    });

    _addStatusListener(controller, animation);
  }

  static String _determineAnimationId(
      double animationValue,
      bool hasTriggeredMove,
      String startIdWithTarget
      ) {
    if (animationValue == 1.0) {
      return finishedId;
    } else if (!hasTriggeredMove) {
      return startIdWithTarget;
    } else {
      return inProgressId;
    }
  }

  static bool _moveMap(
      MapController mapController,
      Animation<double> animation,
      Map<String, Tween<double>> tweens,
      String id,
      ) {
    return mapController.move(
      LatLng(
          tweens['lat']!.evaluate(animation),
          tweens['lng']!.evaluate(animation)
      ),
      tweens['zoom']!.evaluate(animation),
      id: id,
    );
  }

  static void _addStatusListener(
      AnimationController controller,
      Animation<double> animation
      ) {
    animation.addStatusListener((status) {
      if (status == AnimationStatus.completed ||
          status == AnimationStatus.dismissed) {
        controller.dispose();
      }
    });
  }
}