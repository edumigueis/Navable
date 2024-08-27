import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:navable/src/components/action_bar.dart';
import 'package:navable/src/components/place_card.dart';

import '../components/map_widget.dart';
import '../components/top_bar.dart';
import '../util/animated_map_move.dart';

class MapView extends StatefulWidget {
  const MapView({super.key});

  static const routeName = '/';

  @override
  MapViewState createState() => MapViewState();
}

class MapViewState extends State<MapView> with TickerProviderStateMixin {
  final MapController mapController = MapController();
  bool _isModalOpen = false;

  void _toggleModal() {
    setState(() {
      _isModalOpen = !_isModalOpen;
    });

    if (_isModalOpen) {
      _showPlaceCardModal();
    }
  }

  void _showPlaceCardModal() {
    showModalBottomSheet(
      context: context,
      barrierColor: Colors.transparent,
      builder: (BuildContext context) {
        return const PlaceCard(
          title: "a",
          icon: Icons.insert_emoticon_sharp,
          iconColor: Colors.green,
          text: "text",
        );
      },
      backgroundColor: Colors.transparent,
      isScrollControlled: true,
    ).whenComplete(() {
      setState(() {
        _isModalOpen = false;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const TopBar(),
      extendBodyBehindAppBar: true,
      extendBody: true,
      body: Stack(
        children: [
          MapWidget(
            mapController: mapController,
            onToggleModal: _toggleModal,
            onMapMove: (LatLng latLng, double zoom) =>
                AnimatedMapMove.move(mapController, latLng, zoom, this),
          ),
          CustomActionBar(
            onTapLocate: () {
              AnimatedMapMove.move(
                mapController,
                LatLng(51.509364, -0.128928),
                18,
                this,
              );
            },
            onTapAddWarning: () {
              AnimatedMapMove.move(
                mapController,
                LatLng(52.509364, -0.128928),
                18,
                this,
              );
            },
            bottom: _isModalOpen ? 200 : 20,
          ),
        ],
      ),
    );
  }
}
