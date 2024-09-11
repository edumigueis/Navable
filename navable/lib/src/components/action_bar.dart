import 'package:flutter/material.dart';
import 'package:navable/src/util/styles.dart';

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
            backgroundColor: NavableColors.grayAccent.withOpacity(0.5),
            shape: const CircleBorder(),
            child: const Icon(
              Icons.center_focus_weak,
              color: NavableColors.white,
            ),
          ),
          const SizedBox(height: 16),
          FloatingActionButton(
            onPressed: onTapAddWarning,
            shape: const CircleBorder(),
            backgroundColor: NavableColors.yellowAccent,
            child: const Icon(
              Icons.add,
              color: NavableColors.black,
            ),
          ),
        ],
      ),
    );
  }
}
