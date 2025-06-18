import 'package:flutter/material.dart';
import 'package:navable/src/util/styles.dart';

class PlaceGradeDisplay extends StatelessWidget {
  final double value; // Value from 0 to 2 (will be converted to percentage)
  final double stroke; // Height of the bar

  const PlaceGradeDisplay({
    Key? key,
    required this.value,
    required this.stroke,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Convert value (0-2) to percentage (0-100)
    final double percentage = (value / 2) * 100;

    // Determine color based on percentage
    Color progressColor;
    if (percentage >= 77) {
      progressColor = Colors.green;
    } else if (percentage <= 33) {
      progressColor = Colors.red;
    } else {
      progressColor = Colors.yellow;
    }

    return LayoutBuilder(
      builder: (context, constraints) {
        final double maxWidth = constraints.maxWidth;
        final double progressWidth = (percentage / 100) * maxWidth;

        return Container(
          width: maxWidth, // Takes 100% of available width
          height: stroke,
          decoration: BoxDecoration(
            color: Colors.grey.shade300,
            borderRadius: BorderRadius.circular(stroke / 2),
          ),
          child: Stack(
            children: [
              // Background bar
              Container(
                width: maxWidth,
                decoration: BoxDecoration(
                  color: NavableColors.gray,
                  borderRadius: BorderRadius.circular(stroke / 2),
                ),
              ),
              // Progress bar
              Container(
                width: progressWidth, // This will be a percentage of parent width
                decoration: BoxDecoration(
                  color: progressColor,
                  borderRadius: BorderRadius.circular(stroke / 2),
                ),
              ),
            ],
          ),
        );
      },
    );
  }
}