import 'package:flutter/material.dart';

class PlaceGradeDisplay extends StatefulWidget {
  final double value; // Value from 0 to 2
  final double stroke;

  const PlaceGradeDisplay(
      {super.key, required this.value, required this.stroke});

  @override
  PlaceGradeDisplayState createState() => PlaceGradeDisplayState();
}

class PlaceGradeDisplayState extends State<PlaceGradeDisplay> {
  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 180,
      height: widget.stroke,
      child: LayoutBuilder(
        builder: (context, constraints) {
          // Calculate the position of the arrow based on the value
          double arrowPosition = (widget.value / 2) * constraints.maxWidth;

          return Stack(
            alignment: Alignment.centerLeft,
            children: [
              // Background divided into three parts: gray when inactive, colored when active
              ClipRRect(
                  borderRadius: BorderRadius.circular(12),
                  // Apply border radius
                  child: Row(children: [
                    Expanded(
                      flex: 1,
                      child: Container(
                        color: widget.value <= 0.66
                            ? Colors.red
                            : Colors.grey, // Red or gray depending on the value
                      ),
                    ),
                    const SizedBox(width: 1),
                    Expanded(
                      flex: 1,
                      child: Container(
                        color: widget.value > 0.66 && widget.value <= 1.32
                            ? Colors.yellow
                            : Colors
                                .grey, // Yellow or gray depending on the value
                      ),
                    ),
                    const SizedBox(width: 1),
                    Expanded(
                      flex: 1,
                      child: Container(
                        color: widget.value > 1.32
                            ? Colors.green
                            : Colors
                                .grey, // Green or gray depending on the value
                      ),
                    ),
                  ])),
              // Arrow positioned proportionally
              Positioned(
                left: arrowPosition - widget.stroke,
                // Offset arrow to center it
                child: Icon(
                  Icons.circle,
                  size: widget.stroke,
                  color: Colors.black,
                ),
              ),
            ],
          );
        },
      ),
    );
  }
}
