import 'package:flutter/material.dart';

class AccessibilityChecks extends StatefulWidget {
  final String title;
  final List<String> buttons;

  const AccessibilityChecks({
    super.key,
    required this.title,
    required this.buttons,
  });

  @override
  AccessibilityChecksState createState() => AccessibilityChecksState();
}

class AccessibilityChecksState extends State<AccessibilityChecks> {
  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text("Category 1"),
        Wrap(
          children: List.generate(
              5,
              (index) => _buildSelectableButton(
                  context, 'Option ${index + 1}', index)),
        ),
        Text("Category 2"),
        Wrap(
          children: List.generate(
              5,
              (index) => _buildSelectableButton(
                  context, 'Option ${index + 1}', index)),
        ),
      ],
    );
  }

  Widget _buildSelectableButton(BuildContext context, String text, int index) {
    return StatefulBuilder(
      builder: (context, setState) {
        bool _isSelected = false;

        return GestureDetector(
          onTap: () {
            setState(() {
              _isSelected = !_isSelected;
            });
          },
          child: Container(
            padding: const EdgeInsets.symmetric(
              vertical: 10,
              horizontal: 16,
            ),
            decoration: BoxDecoration(
              color: _isSelected ? Colors.blue : Colors.grey[300],
              borderRadius: BorderRadius.circular(8),
            ),
            child: Text(
              text,
              style: TextStyle(
                color: _isSelected ? Colors.white : Colors.black,
              ),
            ),
          ),
        );
      },
    );
  }
}
