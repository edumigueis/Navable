import 'package:flutter/material.dart';

// Custom Marker Widget
class CustomPinMarker extends StatelessWidget {
  final IconData icon;
  final Color color;
  final double size;
  final VoidCallback onTap;

  const CustomPinMarker({
    super.key,
    required this.icon,
    required this.onTap,
    this.color = Colors.red,
    this.size = 50.0,
  });

  @override
  Widget build(BuildContext context) {
    return Center(
        child: MouseRegion(
            cursor: SystemMouseCursors.click,
            child: GestureDetector(
                onTap: onTap,
                child: Icon(
                  icon,
                  color: color,
                  size: size,
                )))); // Icon Inside the Pi
  }
}
