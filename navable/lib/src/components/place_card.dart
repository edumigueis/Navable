import 'dart:math';

import 'package:flutter/material.dart';
import 'package:navable/src/components/basics/navable_button.dart';
import 'package:navable/src/components/place_grade_display.dart';
import 'package:navable/src/pages/models/place.dart';
import 'package:navable/src/util/styles.dart';

class PlaceCard extends StatelessWidget {
  const PlaceCard(
      {super.key,
      required this.place,
      required this.icon,
      required this.iconColor,
      required this.onClose});

  final Place place;
  final IconData icon;
  final Color iconColor;
  final VoidCallback onClose;

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 200,
      margin: EdgeInsets.symmetric(
        horizontal: max(MediaQuery.of(context).size.width * 0.05, 15),
      ),
      padding: const EdgeInsets.symmetric(horizontal: 15.0, vertical: 10),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(16.0),
        boxShadow: const [
          BoxShadow(
            color: Colors.black26,
            blurRadius: 10.0,
            offset: Offset(0, -4),
          ),
        ],
      ),
      child: Stack(children: [
        Positioned(
            right: 0,
            top: 0,
            child: IconButton(
              icon: const Icon(Icons.close),
              onPressed: onClose, // Close the modal when "X" is clicked
            )),
        Positioned(
            right: 5,
            bottom: 10,
            child: NavableButton(
              "AVALIAR",
              height: 30,
              width: 100,
              textSize: 15,
              onPressed: () {
                Navigator.pushNamed(context, "/review", arguments: place);
              },
            )),
        Row(
          children: [
            Container(
              width: 160,
              height: 160,
              decoration: const BoxDecoration(color: Color(0xff77E4D4)),
              child: () {
                if (place.image == "") {
                  return Icon(
                    icon,
                    color: iconColor,
                    size: 24.0,
                  );
                } else {
                  return Image(
                    image: AssetImage(place.image),
                  );
                }
              }(),
            ),
            const SizedBox(width: 8), // Espaço entre o ícone e o texto
            Expanded(
                child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  place.name,
                  style: Theme.of(context).textTheme.headlineMedium?.copyWith(
                        fontWeight: FontWeight.bold,
                      ),
                ),
                PlaceGradeDisplay(value: place.grade, stroke: 9),
                Text(
                  place.grade > 1.32
                      ? "Accessible"
                      : place.grade < 0.66
                          ? "Not accessible"
                          : "Partially accessible",
                  style: Theme.of(context).textTheme.minititle,
                ),
                const SizedBox(height: 10),
                Text(
                  place.address,
                  style: Theme.of(context).textTheme.body,
                ),
              ],
            )),
          ],
        ),
      ]),
    );
  }
}
