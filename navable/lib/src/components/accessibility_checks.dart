import 'package:flutter/material.dart';
import 'package:collection/collection.dart';

import '../pages/models/acc_category.dart';

class AccessibilityChecks extends StatefulWidget {
  final String title;
  final List<AccessibilityCategory> buttons;

  const AccessibilityChecks({
    super.key,
    required this.title,
    required this.buttons,
  });

  @override
  AccessibilityChecksState createState() => AccessibilityChecksState();
}

class AccessibilityChecksState extends State<AccessibilityChecks> {
  Map<String, List<AccessibilityCategory>> _groupedButtons = {};

  @override
  void initState() {
    super.initState();
    _groupedButtons = groupBy(
      widget.buttons,
      (AccessibilityCategory button) => button.group,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
        padding: EdgeInsets.symmetric(horizontal: 15),
        child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: _groupedButtons.entries.map((entry) {
        final group = entry.key;
        final buttons = entry.value;

        return Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(group, style: const TextStyle(fontWeight: FontWeight.bold)),
            Wrap(
              children: buttons.map((button) {
                return _buildSelectableButton(
                  context,
                  button.title,
                );
              }).toList(),
            ),
          ],
        );
      }).toList(),
    ));
  }

  Widget _buildSelectableButton(BuildContext context, String text) {
    bool isSelected = false;
    return StatefulBuilder(
      builder: (context, setState) {
        return GestureDetector(
          onTap: () {
            setState(() {
              isSelected = !isSelected;
            });
          },
          child: Container(
            margin: const EdgeInsets.all(4.0), // Adds spacing between buttons
            padding: const EdgeInsets.symmetric(
              vertical: 10,
              horizontal: 16,
            ),
            decoration: BoxDecoration(
              color: isSelected ? Colors.blue : Colors.grey[300],
              borderRadius: BorderRadius.circular(8),
            ),
            child: Text(
              text,
              style: TextStyle(
                color: isSelected ? Colors.white : Colors.black,
              ),
            ),
          ),
        );
      },
    );
  }
}
