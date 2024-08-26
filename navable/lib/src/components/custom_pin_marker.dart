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
    return Stack(
      alignment: Alignment.center,
      children: [
        IconButton(
            onPressed: onTap,
            icon: Icon(
              Icons.location_on_rounded,
              color: color,
              size: size, // Adjust the icon size relative to the pin
            )), // Icon Inside the Pin
      ],
    );
  }
}