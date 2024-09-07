import 'package:flutter/material.dart';

class CustomActionBar extends StatelessWidget {
  const CustomActionBar({
    super.key,
    required this.onTapLocate,
    required this.onTapAddWarning,
    required this.bottom,
  });

  final VoidCallback onTapLocate;
  final VoidCallback onTapAddWarning;
  final double bottom;

  @override
  Widget build(BuildContext context) {
    return AnimatedPositioned(
      duration: const Duration(milliseconds: 200),
      curve: Curves.easeInOut,
      bottom: bottom,
      right: 15,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.end,
        children: [
          FloatingActionButton(
            onPressed: onTapLocate,
            backgroundColor: Colors.grey.withOpacity(0.5),
            shape: const CircleBorder(),
            child: const Icon(Icons.center_focus_weak),
          ),
          const SizedBox(height: 16),
          FloatingActionButton(
            onPressed: onTapAddWarning,
            shape: const CircleBorder(),
            backgroundColor: const Color(0xff998CEB),
            child: const Icon(Icons.add),
          ),
        ],
      ),
    );
  }
}
