import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:navable/src/pages/models/warning.dart';

import '../components/action_bar.dart';
import '../components/map_widget.dart';
import '../components/place_card.dart';
import '../components/top_bar.dart';
import '../util/animated_map_move.dart';
import 'add_warning_view.dart';
import 'controllers/map_controller.dart';
import 'filter_view.dart';
import 'models/place.dart';

class MapView extends StatefulWidget {
  const MapView({super.key, required this.controller});

  static const routeName = '/home';

  final MapViewController controller;

  @override
  MapViewState createState() => MapViewState();
}

class MapViewState extends State<MapView> with TickerProviderStateMixin {
  final MapController _mapController = MapController();

  @override
  void initState() {
    super.initState();
    widget.controller.loadCurrentLocation();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: TopBar(
        onTapSearch: widget.controller.togglePlaceModal,
        onTapFilter: () => _openFilterModal(context),
      ),
      extendBodyBehindAppBar: true,
      extendBody: true,
      body: Stack(
        children: [
          FutureBuilder<void>(
            future: widget.controller.loadCurrentLocation(),
            builder: (context, snapshot) {
              if (snapshot.connectionState == ConnectionState.waiting) {
                return const Center(child: CircularProgressIndicator());
              }
              if (snapshot.hasError) {
                return const Center(
                    child: Text("Erro ao carregar localização"));
              }
              return MapWidget(
                mapController: _mapController,
                initialCenter: widget.controller.currentLocation,
                onToggleModal: widget.controller.togglePlaceModal,
                warnings: widget.controller.warnings,
                places: widget.controller.places,
                onMapMove: (LatLng latLng, double zoom) {
                  _animateMapMove(latLng, zoom);
                },
              );
            },
          ),
          CustomActionBar(
            onTapLocate: () =>
                _animateMapMove(widget.controller.currentLocation, 18),
            onTapAddWarning: () => _openAddWarningModal(context, widget.controller.warningTypes),
            bottom: widget.controller.isModalOpen ? 225 : 15,
          ),
          AnimatedBuilder(
            animation: widget.controller, // Ouve mudanças no controlador
            builder: (context, child) {
              return widget.controller.isModalOpen
                  ? Positioned(
                      bottom: 10,
                      left: 0,
                      right: 0,
                      child: GestureDetector(
                        onTap: () {
                          Navigator.pushNamed(context, "/settings");
                        },
                        child: PlaceCard(
                          place: Place(
                            "name",
                            1.5,
                            "assets/images/flutter_logo.png",
                            "Rua Rita Lee, 255",
                          ),
                          icon: Icons.insert_emoticon_sharp,
                          iconColor: Colors.green,
                          onClose: widget.controller.closePlaceModal,
                        ),
                      ),
                    )
                  : const SizedBox.shrink();
            },
          ),
        ],
      ),
    );
  }

  void _openFilterModal(BuildContext context) {
    showModalBottomSheet(
      context: context,
      builder: (BuildContext context) {
        return const FilterView();
      },
      isScrollControlled: true,
    );
  }

  void _openAddWarningModal(BuildContext context, List<WarningType> types) {
    showModalBottomSheet(
      context: context,
      builder: (BuildContext context) {
        return AddWarningView(warnings: types);
      },
      isScrollControlled: true,
    );
  }

  void _animateMapMove(LatLng latLng, double zoom) {
    AnimatedMapMove.move(
      _mapController,
      latLng,
      zoom,
      this,
    );
  }
}
