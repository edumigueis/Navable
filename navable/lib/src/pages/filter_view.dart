import 'package:flutter/material.dart';

import '../components/accessibility_checks.dart';
import '../components/expandable_section.dart';
import 'models/acc_category.dart';

class FilterView extends StatelessWidget {
  const FilterView({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 400,
      padding: const EdgeInsets.symmetric(horizontal: 15, vertical: 10),
      child: Column(
        children: [
          ExpandableSection(
              title: 'Accessibility',
              child: AccessibilityChecks(title: "a", buttons: [
                AccessibilityCategory("a", "b"),
                AccessibilityCategory("b", "b"),
                AccessibilityCategory("c", "a")
              ])),
          ElevatedButton(
            onPressed: () {
              // Example data to return
              String selectedData = "Some Filter Data";
              Navigator.pop(context, selectedData); // Pass data back
            },
            child: const Text('Apply Filter'),
          ),
        ],
      ),
    );
  }
}
