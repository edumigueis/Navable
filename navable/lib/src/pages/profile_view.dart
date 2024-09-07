import 'package:flutter/material.dart';
import 'package:navable/src/components/accessibility_checks.dart';
import 'package:navable/src/pages/models/acc_category.dart';
import 'package:navable/src/pages/models/badge.dart';
import 'package:navable/src/pages/controllers/profile_controller.dart';

class ProfileView extends StatelessWidget {
  ProfileView({super.key, required this.controller});

  static const routeName = '/profile';

  final ProfileController controller;
  List<AccessibilityBadge> badges = [
    AccessibilityBadge("aaa", ""),
    AccessibilityBadge("aaaa", "aaa")
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      body: ListView(
        children: [
          Card(
            elevation: 4,
            margin: const EdgeInsets.fromLTRB(15, 0, 15, 15),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(8),
            ),
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Row(
                children: [
                  const CircleAvatar(
                    radius: 30,
                    backgroundImage: AssetImage(
                        'assets/images/flutter_logo.png'), // Replace with your photo asset
                  ),
                  const SizedBox(width: 16),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'John Doe', // Replace with dynamic name if needed
                          style: Theme.of(context).textTheme.headlineMedium,
                        ),
                        Text(
                          '800 POINTS', // Replace with dynamic name if needed
                          style: Theme.of(context).textTheme.bodySmall,
                        ),
                        const SizedBox(height: 8),
                        LinearProgressIndicator(
                          value: 0.7,
                          // Static progress, replace with dynamic value if needed
                          backgroundColor: Colors.grey[300],
                          color: const Color(0xff998CEB),
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(width: 16),
                  IconButton(
                    icon: const Icon(Icons.settings),
                    onPressed: () {
                      Navigator.restorablePushNamed(context, "/settings");
                    },
                  ),
                ],
              ),
            ),
          ),
          ExpandableSection(
              title: 'Accessibility',
              child: AccessibilityChecks(title: "a", buttons: [
                AccessibilityCategory("a", "b"),
                AccessibilityCategory("b", "b"),
                AccessibilityCategory("c", "a")
              ])),
          ExpandableSection(
              title: 'Badges',
              child: Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 15),
                  child: Wrap(
                spacing: 15,
                children: badges.map((badge) {
                  return Column(
                    children: [
                      CircleAvatar(
                        radius: 30,
                        backgroundImage: AssetImage(
                            badge.image), // Replace with your photo asset
                      ),
                      Text(badge.title)
                    ],
                  );
                }).toList(),
              ))),
        ],
      ),
    );
  }
}

class ExpandableSection extends StatefulWidget {
  final String title;
  final Widget child;

  const ExpandableSection({
    super.key,
    required this.title,
    required this.child,
  });

  @override
  ExpandableSectionState createState() => ExpandableSectionState();
}

class ExpandableSectionState extends State<ExpandableSection> {
  bool _isExpanded = false;

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        ListTile(
          title: Text(widget.title),
          trailing: IconButton(
            icon: Icon(
              _isExpanded ? Icons.expand_less : Icons.expand_more,
            ),
            onPressed: () {
              setState(() {
                _isExpanded = !_isExpanded;
              });
            },
          ),
        ),
        if (_isExpanded) widget.child
      ],
    );
  }
}
