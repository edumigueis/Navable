import 'package:collection/collection.dart';
import 'package:flutter/material.dart';
import 'package:navable/src/util/styles.dart';

import '../pages/models/acc_category.dart';

class AccessibilityChecks extends StatefulWidget {
  final String title;
  final List<AccessibilityCategory> buttons;
  final ValueChanged<List<AccessibilityCategory>> onSelectionChanged;

  const AccessibilityChecks({
    super.key,
    required this.title,
    required this.buttons,
    required this.onSelectionChanged,
  });

  @override
  AccessibilityChecksState createState() => AccessibilityChecksState();
}

class AccessibilityChecksState extends State<AccessibilityChecks> {
  Map<String, List<AccessibilityCategory>> _groupedButtons = {};
  List<AccessibilityCategory> _selectedCategories = [];

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
      padding: const EdgeInsets.symmetric(horizontal: 15),
      child: ListView(
        children: _groupedButtons.entries.map((entry) {
          final group = entry.key;
          final buttons = entry.value;

          return Padding(
            padding: const EdgeInsets.only(bottom: 15),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(group, style: Theme.of(context).textTheme.smalltitle),
                Wrap(
                  spacing: 8,
                  runSpacing: 10,
                  children: buttons.map((button) {
                    return _buildSelectableButton(
                      context,
                      button,
                    );
                  }).toList(),
                ),
              ],
            ),
          );
        }).toList(),
      ),
    );
  }

  Widget _buildSelectableButton(BuildContext context, AccessibilityCategory button) {
    bool isSelected = _selectedCategories.contains(button);

    return GestureDetector(
      onTap: () {
        setState(() {
          // Toggle selection
          if (isSelected) {
            _selectedCategories.remove(button);
          } else {
            _selectedCategories.add(button);
          }
          // Only notify if selected status has changed to avoid infinite calls
          widget.onSelectionChanged(_selectedCategories);
        });
      },
      child: Container(
        padding: const EdgeInsets.symmetric(vertical: 5, horizontal: 15),
        decoration: BoxDecoration(
          color: isSelected ? NavableColors.blueAccent : NavableColors.gray,
          borderRadius: BorderRadius.circular(8),
        ),
        child: Text(
          button.title,
          style: isSelected
              ? Theme.of(context).textTheme.minititle.copyWith(color: Colors.white)
              : Theme.of(context).textTheme.minititle,
        ),
      ),
    );
  }
}
