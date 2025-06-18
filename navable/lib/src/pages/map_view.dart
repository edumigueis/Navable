import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:navable/src/pages/models/acc_category.dart';
import 'package:navable/src/pages/models/warning.dart';

import '../components/warning_card.dart';
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
  double _ratingFilter = 2; // State for rating filter
  List<AccessibilityCategory> _accessibilityFilters = []; // Selected accessibility categories
  List<AccessibilityCategory> _establishmentFilters = []; // Selected establishment types

  @override
  void initState() {
    super.initState();
    widget.controller.loadCurrentLocation(); // Load the current location
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: TopBar(
        onTapSearch: () => _openFilterModal(context),
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
                onToggleWarningModal: widget.controller.toggleWarningModal,
                warnings: widget.controller.warnings,
                places: _applyFilters(widget.controller.places),
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
            animation: widget.controller,
            builder: (context, child) {
              return widget.controller.isModalOpen
                  ? Positioned(
                bottom: 0,
                left: 0,
                right: 0,
                child: _buildBottomSheet(),
              )
                  : const SizedBox.shrink();
            },
          ),
        ],
      ),
    );
  }

  // Method to build modal content based on selected item
  Widget _buildBottomSheet() {
    if (widget.controller.selectedPlace != null) {
      return PlaceCard(
        place: widget.controller.selectedPlace!,
        icon: Icons.insert_emoticon_sharp,
        iconColor: Colors.green,
        onClose: widget.controller.closeModal,
      );
    } else if (widget.controller.selectedWarning != null) {
      return WarningCard(
        warning: widget.controller.selectedWarning!,
        onClose: widget.controller.closeModal,
      );
    } else {
      return const Center(child: Text("Erro"));
    }
  }

  // Open the filter modal
  void _openFilterModal(BuildContext context) {
    showModalBottomSheet(
      context: context,
      builder: (BuildContext context) {
        return FilterView(
          onApplyFilters: (String filters) {
            // Update filter states based on user selection
            setState(() {

            });
            updateFilteredLists(); // Update the displayed lists
          },
        );
      },
      isScrollControlled: true,
    );
  }

  List<Place> _applyFilters(List<Place> places) {
    // Filter the list of places based on the current filters
    return places.where((place) {
      // Apply your filtering criteria
      return true; // Placeholder logic
    }).toList();
  }

  // Refresh data based on the filter criteria
  void updateFilteredLists() {
    // Assume you have methods to fetch the filtered data
    // This could involve making API calls or filtering a local list
    setState(() {
      // Perform the necessary changes
    });
  }

  void _openAddWarningModal(BuildContext context, List<WarningType> types) async {
    final result = await showModalBottomSheet<WarningType>(
      context: context,
      builder: (BuildContext context) {
        return AddWarningView(warnings: types);
      },
      isScrollControlled: true,
    );

    if (result != null) {
      widget.controller.createOcurrence(result);
    }
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