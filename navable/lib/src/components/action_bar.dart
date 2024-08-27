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
      duration: Duration(milliseconds: 200),
      curve: Curves.easeInOut,
      bottom: bottom,
      right: 20,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.end,
        children: [
          FloatingActionButton(
            onPressed: onTapLocate,
            backgroundColor: Colors.grey.withOpacity(0.5),
            child: const Icon(Icons.edit),
          ),
          const SizedBox(height: 16),
          FloatingActionButton(
            onPressed: onTapAddWarning,
            backgroundColor: Colors.blue,
            child: const Icon(Icons.add),
          ),
        ],
      ),
    );
  }
}
