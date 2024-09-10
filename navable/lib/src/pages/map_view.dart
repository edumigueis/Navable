import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:navable/src/pages/add_warning_view.dart';
import 'package:navable/src/pages/models/place.dart';

import '../components/action_bar.dart';
import '../components/map_widget.dart';
import '../components/place_card.dart';
import '../components/top_bar.dart';
import '../util/animated_map_move.dart';
import 'filter_view.dart';

class MapView extends StatefulWidget {
  const MapView({super.key});

  static const routeName = '/home';

  @override
  MapViewState createState() => MapViewState();
}

class MapViewState extends State<MapView> with TickerProviderStateMixin {
  final MapController mapController = MapController();
  bool _isModalOpen = false;

  void _togglePlaceModal() {
    setState(() {
      _isModalOpen = !_isModalOpen;
    });
  }

  void _closePlaceModal() {
    setState(() {
      _isModalOpen = false;
    });
  }

  void _openFilterModal() {
    showModalBottomSheet(
      context: context,
      builder: (BuildContext context) {
        return const FilterView();
      },
      isScrollControlled: true,
    ).then((result) {
      if (result != null) {
        // Use the result data here
        print('Filter data: $result');
      }
    });
  }

  void _openAddWarningModal() {
    showModalBottomSheet(
      context: context,
      builder: (BuildContext context) {
        return const AddWarningView();
      },
      isScrollControlled: true,
    ).then((result) {
      if (result != null) {
        // Use the result data here
        print('Filter data: $result');
      }
    });
  }

  void _moveToUserLocation() {
    AnimatedMapMove.move(
      mapController,
      LatLng(51.509364, -0.128928),
      18,
      this,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar:
          TopBar(onTapSearch: _togglePlaceModal, onTapFilter: _openFilterModal),
      extendBodyBehindAppBar: true,
      extendBody: true,
      body: Stack(
        children: [
          MapWidget(
            mapController: mapController,
            onToggleModal: _togglePlaceModal,
            onMapMove: (LatLng latLng, double zoom) =>
                AnimatedMapMove.move(mapController, latLng, zoom, this),
          ),
          CustomActionBar(
            onTapLocate: _moveToUserLocation,
            onTapAddWarning: _openAddWarningModal,
            bottom: _isModalOpen ? 225 : 15,
          ),
          if (_isModalOpen)
            Positioned(
              bottom: 10,
              left: 0,
              right: 0,
              child: GestureDetector(
                onTap: () {
                  Navigator.pushNamed(context, "/settings");
                }, // Prevent closing when tapping inside the card
                child: PlaceCard(
                  place: Place("name", 1.5, "assets/images/flutter_logo.png", "Rua Rita Lee, 255"),
                  icon: Icons.insert_emoticon_sharp,
                  iconColor: Colors.green,
                  onClose:
                      _closePlaceModal, // Close the modal when the "X" button is clicked
                ),
              ),
            ),
        ],
      ),
    );
  }
}
